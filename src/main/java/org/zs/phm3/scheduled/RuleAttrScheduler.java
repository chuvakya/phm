package org.zs.phm3.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.fault.*;
import org.zs.phm3.models.rule.*;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.fault.FaultLogService;
import org.zs.phm3.service.fault.FaultService;
import org.zs.phm3.service.rule.CaseService;
import org.zs.phm3.service.rule.RuleService;
import org.zs.phm3.service.ts.TsKvService;
import org.zs.phm3.service.unit.UnitService;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleAttrScheduler {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private TsKvService tsKvService;

    @Autowired
    private UomService uomService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private FaultService faultService;

    @Autowired
    private FaultLogService faultLogService;

    @Autowired
    private CaseService caseService;

    @Transactional
    @Scheduled(fixedRateString = "1000")
    public void saveFaultAndFaultLogScheduler() {
        while(!FaultQueue.getFaultAndFaultLogs().isEmpty()) {
            FaultAndFaultLog faultAndFaultLog = FaultQueue.getFaultAndFaultLogs().pollFirst();
            faultService.save(faultAndFaultLog.getFault());
            faultLogService.save(faultAndFaultLog.getFaultLog());
        }
    }

    @Transactional
    @Scheduled(fixedRateString = "1000")
    public void scheduler1() {
        defaultScheduler(ruleService.getAllByPeriodAndRuleTypeAndStop(1000L, RuleType.ATTRIBUTE, false));
    }

    @Transactional
    @Scheduled(fixedRateString = "5000")
    public void scheduler2() {
        defaultScheduler(ruleService.getAllByPeriodAndRuleTypeAndStop(5000L, RuleType.ATTRIBUTE, false));
    }

    @Transactional
    @Scheduled(fixedRateString = "10000")
    public void scheduler3() {
        defaultScheduler(ruleService.getAllByPeriodAndRuleTypeAndStop(10000L, RuleType.ATTRIBUTE, false));
    }

    @Transactional
    @Scheduled(fixedRateString = "30000")
    public void scheduler4() {
        defaultScheduler(ruleService.getAllByPeriodAndRuleTypeAndStop(30000L, RuleType.ATTRIBUTE, false));
    }

    @Transactional
    @Scheduled(fixedRateString = "60000")
    public void scheduler5() {
        defaultScheduler(ruleService.getAllByPeriodAndRuleTypeAndStop(60000L, RuleType.ATTRIBUTE, false));
    }

    private void defaultScheduler(List<Rule> rules) {
        for (Rule rule : rules) {
            Long endTime = System.currentTimeMillis();
            for (Case aCase : rule.getCases()) {
                if (aCase.getExpressionType().toString().equals("OR")) {
                    runRule(aCase, rule, true, endTime);
                } else {
                    runRule(aCase, rule,false, endTime);
                }
            }
        }
    }

    private void runRule(Case aCase, Rule rule, boolean flag, Long endTime) {
        List<List<TsKvEntity>> listListTsKvEntities = new ArrayList<>();
        for (Condition condition : aCase.getConditions()) {
            List<TsKvEntity> tsKvEntities = tsKvService.getAllFromTimeKeyAndDeviceIdLimit(rule.getLastScan() + 1,
                    endTime, condition.getTsKvAttribute().getDeviceId(),
                    condition.getTsKvAttribute().getAttributeKey(), 1000000);
            listListTsKvEntities.add(tsKvEntities);
        }
        if (listListTsKvEntities.get(0).isEmpty()) {
            return;
        }
        Fault fault = faultService.getByCaseId(aCase.getId());
        if (fault == null) {
            fault = new Fault();
            fault.setError(false);
        }
        for (int i = 0; i < listListTsKvEntities.get(0).size(); i++) {
            List<Boolean> successAllConditions = new ArrayList<>();
            for (int y = 0; y < aCase.getConditions().size(); y++) {
                if (getConditionResult(aCase.getConditions().get(y), listListTsKvEntities.get(y).get(i))) {
                    successAllConditions.add(true);
                } else {
                    successAllConditions.add(false);
                }
            }
            if (flag) {
                if (successAllConditions.contains(true)) {
                    saveResults(rule, aCase, listListTsKvEntities.get(0).get(i), fault);
                } else {
                    setErrorFalse(fault, listListTsKvEntities.get(0).get(i), aCase.getConditions().get(0).getTsKvAttribute());
                }
            } else {
                if (!successAllConditions.contains(flag)) {
                    saveResults(rule, aCase, listListTsKvEntities.get(0).get(i), fault);
                } else {
                    setErrorFalse(fault, listListTsKvEntities.get(0).get(i), aCase.getConditions().get(0).getTsKvAttribute());
                }
            }
        }
        if (!listListTsKvEntities.get(0).isEmpty()) {
            rule.setLastScan(listListTsKvEntities.get(0).get(listListTsKvEntities.get(0).size() - 1).getId().getTs());
            ruleService.save(rule);
        }
    }

    private void setErrorFalse(Fault fault, TsKvEntity tsKvEntity, TsKvAttribute tsKvAttribute) {
        if (fault != null && fault.getError()) {
            fault.setError(false);
            fault.setEndTime(tsKvEntity.getId().getTs());
            fault.setStatus(FaultStatus.NORMAL);
            String symbol = tsKvAttribute.getOutputSymbol() == null ? "" : " " + tsKvAttribute.getOutputSymbol();
            FaultQueue.getFaultAndFaultLogs().add(new FaultAndFaultLog(fault, new FaultLog(tsKvEntity.getId().getTs(),
                    tsKvAttribute.getName(), getValueFromTsKvEntity(tsKvEntity, tsKvAttribute) + symbol,
                    fault, FaultStatus.NORMAL)));
        }
    }

    private Object getValueFromTsKvEntity(TsKvEntity tsKvEntity, TsKvAttribute tsKvAttribute) {
        return switch (tsKvAttribute.getDataType()) {
            case LONG -> tsKvEntity.getLongValue();
            case DOUBLE -> Math.round(tsKvEntity.getDoubleValue() * 100) / 100.0;
            case STRING -> tsKvEntity.getStrValue();
            case BOOLEAN -> tsKvEntity.getBooleanValue();
        };
    }

    private void saveResults(Rule rule, Case aCase, TsKvEntity tsKvEntity, Fault fault) {
        String symbol = aCase.getConditions().get(0).getTsKvAttribute().getOutputSymbol() == null ? "" : " " +
                aCase.getConditions().get(0).getTsKvAttribute().getOutputSymbol();
        if (fault.getId() == null && fault.getName() == null) {
            runAction(fault, aCase);
            fault.setName(generateFaultNameByCase(aCase));
            fault.setError(true);
            fault.setCaseEntity(aCase);
            fault.setStartTime(tsKvEntity.getId().getTs());
            fault.setRuleType(RuleType.ATTRIBUTE);
            List<String> unitIds = ruleService.getAllUnitIdsByCaseId(aCase.getId());
            for (String unitId : unitIds) {
                fault.getFaultUnits().add(new FaultUnit(unitId, fault));
            }
            FaultAndFaultLog faultAndFaultLog = new FaultAndFaultLog(fault, new FaultLog(tsKvEntity.getId().getTs(),
                    aCase.getConditions().get(0).getTsKvAttribute().getName(),
                    getValueFromTsKvEntity(tsKvEntity, aCase.getConditions().get(0).getTsKvAttribute()) + symbol,
                    fault, fault.getStatus()));
            FaultQueue.getFaultAndFaultLogs().add(faultAndFaultLog);
        } else {
            if (!fault.getError()) {
                runAction(fault, aCase);
                fault.setError(true);
                fault.setEndTime(null);
                fault.setStartTime(tsKvEntity.getId().getTs());
                FaultAndFaultLog faultAndFaultLog = new FaultAndFaultLog(fault, new FaultLog(tsKvEntity.getId().getTs(),
                        aCase.getConditions().get(0).getTsKvAttribute().getName(),
                        getValueFromTsKvEntity(tsKvEntity, aCase.getConditions().get(0).getTsKvAttribute()) + symbol,
                        fault, fault.getStatus()));
                FaultQueue.getFaultAndFaultLogs().add(faultAndFaultLog);
            }
        }
    }

    private void runAction(Fault fault, Case aCase) {
        for (Action action : aCase.getActions()) {
            switch (action.getActionType()) {
                case STATUS -> fault.setStatus(FaultStatus.valueOf(action.getValue()));
                case PRIORITY -> fault.setPriority(Priority.valueOf(action.getValue()));
                case SEVERITY -> fault.setSeverity(Severity.valueOf(action.getValue()));
            }
        }
    }

    private String generateFaultNameByCase(Case aCase) {
        UnitEntity unitEntity = unitService.getUnitById(aCase.getConditions().get(0).getTsKvAttribute().getUnitId());
        String name = unitEntity.getName() + " - " + aCase.getConditions().get(0).getTsKvAttribute().getName();
        String symbol = aCase.getConditions().get(0).getTsKvAttribute().getOutputSymbol() == null ? "" : " " +
                aCase.getConditions().get(0).getTsKvAttribute().getOutputSymbol();
        for (int i = 0; i < aCase.getConditions().size(); i++) {
            switch (aCase.getConditions().get(i).getConditionType()) {
                case LESS -> name += " < " + aCase.getConditions().get(i).getValue() + symbol;
                case MORE -> name += " > " + aCase.getConditions().get(i).getValue() + symbol;
                case LESS_OR_EQUAL -> name += " <= " + aCase.getConditions().get(i).getValue() + symbol;
                case MORE_OR_EQUAL -> name += " >= " + aCase.getConditions().get(i).getValue() + symbol;
                case EQUAL -> name += " = " + aCase.getConditions().get(i).getValue() + symbol;
            }
            if (i != aCase.getConditions().size() - 1) {
                switch(aCase.getExpressionType()) {
                    case OR -> name += " OR";
                    case AND -> name += " AND";
                }
            }
        }
        return name;
    }

    private boolean getConditionResult(Condition condition, TsKvEntity tsKvEntity) {
        switch(condition.getConditionType()) {
            case LESS -> {
                switch (condition.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (((double) getValue(condition)) > ((double) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (((long) getValue(condition)) > ((long) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case MORE -> {
                switch (condition.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (((double) getValue(condition)) < ((double) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (((long) getValue(condition)) < ((long) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case EQUAL -> {
                switch (condition.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (((double) getValue(condition)) == ((double) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (((long) getValue(condition)) == ((long) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                    case BOOLEAN -> {
                        if (((boolean) getValue(condition)) == ((boolean) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                    case STRING -> {
                        if (((String) getValue(condition)).equals((String) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case LESS_OR_EQUAL -> {
                switch (condition.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (((double) getValue(condition)) >= (((double) getValue(condition, tsKvEntity)))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (((long) getValue(condition)) >= ((long) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
            case MORE_OR_EQUAL -> {
                switch (condition.getTsKvAttribute().getDataType()) {
                    case DOUBLE -> {
                        if (((double) getValue(condition)) <= ((double) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                    case LONG -> {
                        if (((long) getValue(condition)) <= ((long) getValue(condition,
                                tsKvEntity))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Object getValue(Condition condition) {
        Object value = null;
        switch (condition.getTsKvAttribute().getDataType()) {
            case BOOLEAN -> value = Boolean.valueOf(condition.getValue());
            case LONG -> value =  Long.valueOf(condition.getValue());
            case DOUBLE -> value = Double.valueOf(condition.getValue());
            case STRING -> value = condition.getValue();
        }
        return value;
    }

    private Object getValue(Condition condition, TsKvEntity tsKvEntity) {
        Object value = null;
        switch (condition.getTsKvAttribute().getDataType()) {
            case BOOLEAN -> value = tsKvEntity.getBooleanValue();
            case LONG -> value = uomService.convert(condition.getTsKvAttribute().getUomInput(),
                    condition.getTsKvAttribute().getUomOutput(), tsKvEntity.getLongValue().doubleValue());
            case DOUBLE -> value = uomService.convert(condition.getTsKvAttribute().getUomInput(),
                    condition.getTsKvAttribute().getUomOutput(), tsKvEntity.getDoubleValue());
            case STRING -> value = tsKvEntity.getStrValue();
        }
        return value;
    }
}