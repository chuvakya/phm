package org.zs.phm3.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.MaintenanceQueue;
import org.zs.phm3.models.maintenance.scheduled.MaintenanceRule;
import org.zs.phm3.models.maintenance.scheduled.Trigger;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;
import org.zs.phm3.models.maintenance.workorder.LaborTaskRequest;
import org.zs.phm3.models.maintenance.workorder.WorkOrder;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPartRequest;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.repository.maintenance.scheduled.MaintenanceRuleRepository;
import org.zs.phm3.repository.maintenance.scheduled.TriggerRepository;
import org.zs.phm3.repository.maintenance.workorder.LaborTaskRepository;
import org.zs.phm3.repository.maintenance.workorder.WorkOrderPartRepository;
import org.zs.phm3.repository.maintenance.workorder.WorkOrderRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.maintenance.workorder.LaborTaskService;
import org.zs.phm3.service.maintenance.workorder.WorkOrderPartService;
import org.zs.phm3.util.mapping.PhmUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class MaintenanceScheduler {

    @Autowired
    private MaintenanceRuleRepository ruleRepository;

    @Autowired
    private TriggerRepository triggerRepository;

    @Autowired
    private TsKvRepository tsKvRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private LaborTaskService laborTaskService;

    @Autowired
    private LaborTaskRepository laborTaskRepository;

    @Autowired
    private WorkOrderPartService workOrderPartService;

    @Autowired
    private WorkOrderPartRepository workOrderPartRepository;

    @Autowired
    private UomService uomService;

    @Transactional
    @Scheduled(fixedRateString = "1000")
    public void saveResultsTriggers() {
        while (!MaintenanceQueue.getTriggers().isEmpty()) {
            Trigger trigger = MaintenanceQueue.getTriggers().getFirst();
            triggerRepository.save(trigger);
            MaintenanceQueue.getTriggers().pollFirst();
        }
    }

    @Transactional
    @Scheduled(fixedRateString = "1000")
    public void saveResultsRules() {
        while (!MaintenanceQueue.getRules().isEmpty()) {
            MaintenanceRule rule = MaintenanceQueue.getRules().getFirst();
            ruleRepository.save(rule);
            MaintenanceQueue.getRules().pollFirst();
        }
    }

    @Transactional
    @Scheduled(fixedRateString = "1000")
    public void run() {
        List<MaintenanceRule> rules = ruleRepository.getAllIsOn();
        for (MaintenanceRule rule : rules) {
            if (getResult(rule)) {
                generateWorkOrder(rule.getWorkOrderTemplate());
            }
        }
    }

    private boolean getResult(MaintenanceRule rule) {
        List<Boolean> triggerResults = new ArrayList<>();
        for (Trigger trigger : rule.getTriggers()) {
            switch (trigger.getTriggerType()) {
                case TIME_SCHEDULE -> triggerResults.add(timeSchedule(trigger));
                case METER_READING -> triggerResults.add(meterReading(trigger, rule));
            }
        }
        if (rule.getExpressionsType().toString().equals("AND") && triggerResults.contains(false)) {
            return false;
        } else if (rule.getExpressionsType().toString().equals("AND") && !triggerResults.contains(false)) {
            return true;
        } else if (rule.getExpressionsType().toString().equals("OR") && triggerResults.contains(true)) {
            return true;
        }
        return false;
    }

    private boolean meterReading(Trigger trigger, MaintenanceRule rule) {
        Long nowDate = System.currentTimeMillis();
        TsKvAttribute attribute = trigger.getTsKvAttribute();
        List<TsKvEntity> tsKvEntities = tsKvRepository.getAllFromTimeKeyAndDeviceIdLimit(trigger.getLastScan(),
                nowDate, attribute.getDeviceId(), attribute.getAttributeKey(), 1000000);

        if (trigger.getEveryNumber() != null) {
            for (TsKvEntity tsKvEntity : tsKvEntities) {
                double value = (double) getValue(attribute, tsKvEntity);
                if (value >= trigger.getEveryStartNumber()) {
                    if (trigger.getEveryEndNumber() != null && value > trigger.getEveryEndNumber()) {
                        trigger.setLastScan(nowDate);
                        MaintenanceQueue.getTriggers().add(trigger);
                        return false;
                    } else {
                        if (value - trigger.getLastScanEveryNumber() > trigger.getEveryNumber()) {
                            trigger.setLastScanEveryNumber((double) getValue(attribute, tsKvEntities.get(0)));
                            trigger.setLastScan(nowDate);
                            MaintenanceQueue.getTriggers().add(trigger);
                            return true;
                        }
                    }
                }
            }
        } else {
            for (TsKvEntity tsKvEntity : tsKvEntities) {
                if (getConditionResult(trigger, tsKvEntity)) {
                    trigger.setLastScan(nowDate);
                    rule.setOn(false);
                    MaintenanceQueue.getRules().add(rule);
                    MaintenanceQueue.getTriggers().add(trigger);
                    return true;
                }
            }
        }
        trigger.setLastScan(nowDate);
        MaintenanceQueue.getTriggers().add(trigger);
        return false;
    }

    private Object getValue(TsKvAttribute attribute, TsKvEntity tsKvEntity) {
        return switch (attribute.getDataType()) {
            case LONG -> uomService.convert(attribute.getUomInput(), attribute.getUomOutput(),
                    Double.valueOf(tsKvEntity.getLongValue()));
            case DOUBLE -> uomService.convert(attribute.getUomInput(), attribute.getUomOutput(),
                    tsKvEntity.getDoubleValue());
            case STRING -> tsKvEntity.getStrValue();
            case BOOLEAN -> tsKvEntity.getBooleanValue();
        };
    }

    private boolean timeSchedule(Trigger trigger) {
        Long nowDate = System.currentTimeMillis();
        if ((trigger.getStartDate() == null || trigger.getStartDate() <= nowDate) &&
                (trigger.getEndDate() == null || trigger.getEndDate() > nowDate) && (trigger.getCountRepeat() == null)) {
            if (getRepeatInterval(trigger) + trigger.getLastRepeatDate() <= nowDate) {
                trigger.setLastRepeatDate(nowDate);
                MaintenanceQueue.getTriggers().add(trigger);
                return true;
            }
        } else if ((trigger.getStartDate() == null || trigger.getStartDate() <= nowDate) &&
                trigger.getCountRepeat() != null && (trigger.getCurrentCountRepeat() < trigger.getCountRepeat()) &&
                (getRepeatInterval(trigger) + trigger.getLastRepeatDate() <= nowDate)) {
            trigger.setCurrentCountRepeat(trigger.getCurrentCountRepeat() + 1);
            trigger.setLastRepeatDate(nowDate);
            MaintenanceQueue.getTriggers().add(trigger);
            return true;
        }
        return false;
    }

    private Long getRepeatInterval(Trigger trigger) {
        return switch (trigger.getDateType()) {
            case DAYS -> trigger.getRepeatInterval() * 3600 * 24 * 1000L;
            case HOURS -> trigger.getRepeatInterval() * 3600 * 1000L;
            case WEEKS -> trigger.getRepeatInterval() * 3600 * 24 * 7 * 1000L;
            case MONTHS -> trigger.getRepeatInterval() * 3600 * 24 * 30 * 1000L;
            case YEARS -> trigger.getRepeatInterval() * 3600 * 24 * 365 * 1000L;
        };
    }

    private boolean getConditionResult(Trigger trigger, TsKvEntity tsKvEntity) {
        switch(trigger.getConditionType()) {
            case LESS -> {
                switch (trigger.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (Double.parseDouble(trigger.getValue()) > ((double) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (Long.parseLong(trigger.getValue()) > ((long) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case MORE -> {
                switch (trigger.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (Double.parseDouble(trigger.getValue()) < ((double) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (Long.parseLong(trigger.getValue()) < ((long) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case EQUAL -> {
                switch (trigger.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (Double.parseDouble(trigger.getValue()) == ((double) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (Long.parseLong(trigger.getValue()) == ((long) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                    case BOOLEAN -> {
                        if (Boolean.parseBoolean(trigger.getValue()) == ((boolean) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                    case STRING -> {
                        if (trigger.getValue().equals(getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case LESS_OR_EQUAL -> {
                switch (trigger.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (Double.parseDouble(trigger.getValue()) >= (((double) getValue(trigger.getTsKvAttribute(), tsKvEntity)))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (Long.parseLong(trigger.getValue()) >= ((long) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case MORE_OR_EQUAL -> {
                switch (trigger.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (Double.parseDouble(trigger.getValue()) <= ((double) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (Long.parseLong(trigger.getValue()) <= ((long) getValue(trigger.getTsKvAttribute(), tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    void generateWorkOrder(WorkOrderTemplate template) {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setOrderName(template.getOrderName());
        workOrder.setUnitId(template.getUnitId());
        workOrder.setOrderStatus(template.getOrderStatus());
        workOrder.setMaintenanceType(template.getMaintenanceType());
        workOrder.setPriority(template.getPriority());
        workOrder.setSummaryOfIssue(template.getSummaryOfIssue());
        workOrder.setWorkInstruction(template.getWorkInstruction());
        workOrder.setEstimatedLabor(template.getEstimatedLabor());
        workOrder.setActualLabor(template.getActualLabor());
        workOrder.setCompletionDate(template.getDaysToComplete() * 3600 * 1000 * 24 + System.currentTimeMillis());
        workOrder.setModifiedTime(System.currentTimeMillis());
        workOrder.setAssignedToUser(template.getAssignedToUser());
        workOrder.setCompletedByUser(template.getCompletedByUser());
        workOrder.setScheduled(true);
        workOrderRepository.save(workOrder);

        List<List<Object>> laborTasks = laborTaskRepository.getAllByWorkOrderId(template.getId());
        List<LaborTaskRequest> laborTaskRequests = new ArrayList<>();
        for (List<Object> laborTask : laborTasks) {
            LaborTaskRequest request = new LaborTaskRequest();
            request.setCompletedTime(Long.parseLong(((BigInteger) laborTask.get(0)).toString()));
            request.setCompletionNotes((String) laborTask.get(1));
            request.setTaskDescription((String) laborTask.get(2));
            request.setTimeEstimate((Integer) laborTask.get(3));
            request.setTimeSpent((Integer) laborTask.get(4));
            request.setUnitId(PhmUtil.toLongUUID((String) laborTask.get(5)));
            request.setAssignedToUserId(Long.parseLong(((BigInteger) laborTask.get(6)).toString()));
            request.setCompletedByUserId(Long.parseLong(((BigInteger) laborTask.get(7)).toString()));
            laborTaskRequests.add(request);
        }
        laborTaskService.addLaborTasks(laborTaskRequests, workOrder.getId());
        List<List<Object>> parts = workOrderPartRepository.getAllByWorkOrderId(template.getId());
        List<WorkOrderPartRequest> workOrderPartRequests = new ArrayList<>();
        for (List<Object> part : parts) {
            WorkOrderPartRequest request = new WorkOrderPartRequest();
            request.setActualQuantityUsed((Integer) part.get(0));
            request.setPartId(Long.parseLong(((BigInteger) part.get(1)).toString()));
            request.setPartStockId(Long.parseLong(((BigInteger) part.get(2)).toString()));
            workOrderPartRequests.add(request);
        }
        workOrderPartService.addWorkOrderParts(workOrder.getId(), workOrderPartRequests);
    }
}
