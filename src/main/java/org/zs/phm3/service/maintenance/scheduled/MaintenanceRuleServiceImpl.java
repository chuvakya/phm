package org.zs.phm3.service.maintenance.scheduled;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.maintenance.scheduled.MaintenanceRule;
import org.zs.phm3.models.maintenance.scheduled.Trigger;
import org.zs.phm3.repository.maintenance.scheduled.LaborTaskTemplateRepository;
import org.zs.phm3.repository.maintenance.scheduled.MaintenanceRuleRepository;
import org.zs.phm3.repository.maintenance.scheduled.WorkOrderPartTemplateRepository;
import org.zs.phm3.repository.maintenance.scheduled.WorkOrderTemplateRepository;
import org.zs.phm3.service.maintenance.workorder.WorkOrderService;
import org.zs.phm3.util.mapping.PhmUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceRuleServiceImpl implements MaintenanceRuleService {

    @Autowired
    private MaintenanceRuleRepository maintenanceRuleRepository;

    @Autowired
    private WorkOrderTemplateRepository workOrderTemplateRepository;

    @Autowired
    private LaborTaskTemplateRepository laborTaskTemplateRepository;

    @Autowired
    private WorkOrderPartTemplateRepository workOrderPartTemplateRepository;

    @Override
    public MaintenanceRule save(MaintenanceRule maintenanceRule) {
        return maintenanceRuleRepository.save(maintenanceRule);
    }

    @Override
    public MaintenanceRule getById(Long ruleId) {
        return maintenanceRuleRepository.findById(ruleId).get();
    }

    @Override
    public String getListByProjectId(Integer projectId) {
        return null;
    }

    @Override
    public String getJSONById(Long ruleId) {
        List<List<Object>> rule = maintenanceRuleRepository.getAllFieldRuleById(ruleId);
        JSONObject jsonObject = new JSONObject();
        JSONArray arrayActions = new JSONArray();
        JSONArray arrayTriggers = new JSONArray();
        jsonObject.put("ruleId", rule.get(0).get(0));
        jsonObject.put("isOn", rule.get(0).get(2));
        jsonObject.put("name", rule.get(0).get(3));
        jsonObject.put("expressionType", rule.get(0).get(1));
        for (List<Object> objects : maintenanceRuleRepository.getAllActionsByRuleId(ruleId)) {
            JSONObject action = new JSONObject();
            action.put("actionId", objects.get(0));
            action.put("actionType", objects.get(1));
            arrayActions.add(action);
        }
        for (List<Object> objects : maintenanceRuleRepository.getAllTriggersByRuleId(ruleId)) {
            JSONObject trigger = new JSONObject();
            trigger.put("triggerId", objects.get(0));
            trigger.put("conditionType", objects.get(1));
            trigger.put("countRepeat", objects.get(2));
            trigger.put("dateType", objects.get(3));
            trigger.put("endDate", objects.get(4));
            trigger.put("everyEndNumber", objects.get(5));
            trigger.put("everyNumber", objects.get(6));
            trigger.put("everyStartNumber", objects.get(7));
            trigger.put("repeatInterval", objects.get(8));
            trigger.put("startDate", objects.get(9));
            trigger.put("triggerType", objects.get(10));
            if (objects.get(11) != null) {
                trigger.put("unitId", PhmUtil.toLongUUID((String) objects.get(11)));
            } else {
                trigger.put("unitId", null);
            }
            trigger.put("value", objects.get(12));
            trigger.put("tsKvAttributeId", objects.get(13));
            arrayTriggers.add(trigger);
        }
        jsonObject.put("actions", arrayActions);
        jsonObject.put("triggers", arrayTriggers);

        List<List<Object>> workOrders = workOrderTemplateRepository.getAllById(Long.parseLong(rule.get(0).get(4).toString()));
        JSONObject jsonObjectWorkOrder = new JSONObject();
        jsonObjectWorkOrder.put("workOrderId", workOrders.get(0).get(0));
        jsonObjectWorkOrder.put("unitId", PhmUtil.toLongUUID((String) workOrders.get(0).get(1)));
        jsonObjectWorkOrder.put("actualLabor", workOrders.get(0).get(2));
        jsonObjectWorkOrder.put("assignedToUserId", workOrders.get(0).get(3));
        jsonObjectWorkOrder.put("completedByUserId", workOrders.get(0).get(4));
        jsonObjectWorkOrder.put("completionDate", workOrders.get(0).get(5));
        jsonObjectWorkOrder.put("estimatedLabor", workOrders.get(0).get(6));
        jsonObjectWorkOrder.put("maintenanceType", workOrders.get(0).get(7));
        jsonObjectWorkOrder.put("orderName", workOrders.get(0).get(8));
        jsonObjectWorkOrder.put("orderStatus", workOrders.get(0).get(9));
        jsonObjectWorkOrder.put("priority", workOrders.get(0).get(10));
        jsonObjectWorkOrder.put("summaryOfIssue", workOrders.get(0).get(11));
        jsonObjectWorkOrder.put("workInstruction", workOrders.get(0).get(12));
        JSONArray jsonArrayLaborTasks = new JSONArray();
        List<List<Object>> laborTasks = laborTaskTemplateRepository.getAllByWorkOrderTemplateId(
                Long.parseLong(rule.get(0).get(4).toString()));
        for (List<Object> laborTask : laborTasks) {
            JSONObject objectLaborTask = new JSONObject();
            objectLaborTask.put("laborTaskId", laborTask.get(0));
            objectLaborTask.put("taskDescription", laborTask.get(1));
            objectLaborTask.put("unitName", laborTask.get(2));
            objectLaborTask.put("userAssignedName", laborTask.get(3));
            objectLaborTask.put("completedByUserName", laborTask.get(4));
            objectLaborTask.put("completedTime", laborTask.get(5));
            objectLaborTask.put("completionNotes", laborTask.get(6));
            objectLaborTask.put("timeEstimate", laborTask.get(7));
            objectLaborTask.put("timeSpent", laborTask.get(8));
            objectLaborTask.put("completedByUserId", laborTask.get(9));
            objectLaborTask.put("assignedToUserId", laborTask.get(10));
            objectLaborTask.put("unitId", PhmUtil.toLongUUID((String) laborTask.get(11)));
            jsonArrayLaborTasks.add(objectLaborTask);
        }
        JSONArray jsonArrayParts = new JSONArray();
        List<List<Object>> parts = workOrderPartTemplateRepository.getAllIdAndNamePartByWorkOrderTemplateId(Long.parseLong(rule.get(0).get(4).toString()));
        List<List<Object>> partsWithStock = workOrderPartTemplateRepository.getAllByWorkOrderTemplateId(Long.parseLong(rule.get(0).get(4).toString()));
        JSONArray jsonArrayStockIds = new JSONArray();

        for (List<Object> part : parts) {
            JSONObject objectPart = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            objectPart.put("partId", part.get(0));
            objectPart.put("partName", part.get(1));
            Long actualQuantityUsed = 0L;
            for (List<Object> objects : partsWithStock) {
                if (objects.get(1) == part.get(0)) {
                    actualQuantityUsed += (Integer) objects.get(0);
                }
            }
            objectPart.put("actualQuantityUsed", actualQuantityUsed);
            jsonArrayParts.add(objectPart);
        }
        for (List<Object> objects : partsWithStock) {
            JSONObject jsonObjectPartWithStock = new JSONObject();
            jsonObjectPartWithStock.put("actualQuantityUsed", objects.get(0));
            jsonObjectPartWithStock.put("partId", objects.get(1));
            jsonObjectPartWithStock.put("partStockId", objects.get(2));
            jsonArrayStockIds.add(jsonObjectPartWithStock);
        }

        jsonObjectWorkOrder.put("parts", jsonArrayParts);
        jsonObjectWorkOrder.put("partStocks", jsonArrayStockIds);
        jsonObjectWorkOrder.put("laborTasks", jsonArrayLaborTasks);

        jsonObject.put("workOrderTemplate", jsonObjectWorkOrder);
        return jsonObject.toJSONString();
    }

    @Override
    public void deleteTriggersByRuleId(Long ruleId) {
        maintenanceRuleRepository.deleteTriggersByRuleId(ruleId);
    }

    @Override
    public void deleteActionsByRuleId(Long ruleId) {
        maintenanceRuleRepository.deleteActionsByRuleId(ruleId);
    }

    @Override
    public String getAllRulesByProjectId(Integer projectId) {
        List<List<Object>> lists = maintenanceRuleRepository.getAllRulesByProjectId(projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ruleId", list.get(0));
            jsonObject.put("ruleName", list.get(1));
            jsonObject.put("isOn", list.get(2));
            jsonObject.put("when", list.get(3));
            jsonObject.put("unitName", list.get(4));
            jsonObject.put("assignedToUserName", list.get(5));
            jsonObject.put("priority", list.get(6));
            jsonObject.put("type", list.get(7));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public void updateIsOnByRuleIds(Boolean isOn, List<Long> ruleIds) {
        maintenanceRuleRepository.updateIsOnByRuleIds(isOn, ruleIds);
    }

    @Override
    public String generateWhenByTriggers(List<Trigger> triggers) {
        StringBuilder s = new StringBuilder("");
        switch (triggers.get(0).getTriggerType()) {
            case TIME_SCHEDULE -> {
                s.append("Repeat every " + triggers.get(0).getRepeatInterval() + " " +
                        triggers.get(0).getDateType().toString().toLowerCase() + ".");
                Date startDate = new Date(triggers.get(0).getLastRepeatDate());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                s.append(" Start " + simpleDateFormat.format(startDate) + ".");
                if (triggers.get(0).getEndDate() != null) {
                    s.append(" End " + simpleDateFormat.format(new Date(triggers.get(0).getEndDate())) + ".");
                } else if (triggers.get(0).getCountRepeat() != null) {
                    s.append(" Repeat count: " + triggers.get(0).getCountRepeat() + ".");
                } else {
                    s.append(" No end.");
                }
            }
            case METER_READING -> {
                String symbol = triggers.get(0).getTsKvAttribute().getOutputSymbol() != null ?
                        triggers.get(0).getTsKvAttribute().getOutputSymbol() : "";
                if (triggers.get(0).getEveryNumber() != null) {
                    s.append("Every " + triggers.get(0).getEveryNumber() + " " + symbol + ". Start " +
                            triggers.get(0).getEveryStartNumber() + " " + symbol + ".");
                    if (triggers.get(0).getEveryEndNumber() != null) {
                        s.append(" End " + triggers.get(0).getEveryEndNumber() + " " + symbol);
                    } else {
                        s.append(" No end.");
                    }
                } else {
                    s.append(triggers.get(0).getTsKvAttribute().getName());
                    String type = switch (triggers.get(0).getConditionType()) {
                        case LESS -> " < ";
                        case MORE -> " > ";
                        case EQUAL -> " = ";
                        case LESS_OR_EQUAL -> " <= ";
                        case MORE_OR_EQUAL -> " >= ";
                    };
                    s.append(type + triggers.get(0).getValue() + " " + symbol);
                }
            }
        }
        return s.toString();
    }
}
