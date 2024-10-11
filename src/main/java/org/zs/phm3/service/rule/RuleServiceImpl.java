package org.zs.phm3.service.rule;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.rule.*;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.repository.rule.RuleRepository;
import org.zs.phm3.repository.ts.TsKvAttributeRepository;
import org.zs.phm3.repository.unit.UnitRepository;
import org.zs.phm3.service.fault.FaultService;
import org.zs.phm3.service.fault.FaultServiceImpl;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private FaultServiceImpl faultService;

    @Autowired
    private TsKvAttributeRepository tsKvAttributeRepository;

    @Override
    public Rule save(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public Rule getById(Long ruleId) {
        return ruleRepository.findById(ruleId).get();
    }

    @Override
    public List<Rule> getAll() {
        return (List<Rule>) ruleRepository.findAll();
    }

    @Override
    public String getAllByProjectId(Integer projectId) {
        List<List<Object>> rules = ruleRepository.getAllByProjectId(projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> rule : rules) {
            JSONObject jsonObjectRule = new JSONObject();
            jsonObjectRule.put("id", rule.get(0));
            jsonObjectRule.put("name", rule.get(1));
            jsonArray.add(jsonObjectRule);
        }
        return jsonArray.toJSONString();
    }


    @Override
    public List<Rule> getAllByPeriodAndRuleTypeAndStop(Long period, RuleType ruleType, Boolean isStop) {
        return ruleRepository.getAllByPeriodAndRuleTypeAndIsStop(period, ruleType, isStop);
    }

    @Override
    public String getJSONById(Long ruleId) {
        Rule rule = ruleRepository.findById(ruleId).get();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayCases = new JSONArray();

        jsonObject.put("id", rule.getId());
        jsonObject.put("name", rule.getName());
        jsonObject.put("ruleType", rule.getRuleType().toString());
        jsonObject.put("period", rule.getPeriod());
        jsonObject.put("isStop", rule.getStop());

        for (int i = 1; i <= rule.getCases().size(); i++) {
            JSONObject object = new JSONObject();
            object.put("caseId", i);
            object.put("type", rule.getCases().get(i - 1).getExpressionType().toString());
            JSONArray jsonArrayCondition = new JSONArray();
            for (List<Object> listCondition : ruleRepository.getAllConditionsByCaseId(rule.getCases().get(i - 1).getId())) {
                JSONObject json = new JSONObject();
                json.put("unitId", PhmUtil.toLongUUID((String) listCondition.get(0)));
                json.put("attributeId", listCondition.get(1));
                json.put("conditionType", listCondition.get(2).toString());
                json.put("value", listCondition.get(3));
                json.put("triggerId", listCondition.get(4));
                JSONArray arrayTsKvAttr = new JSONArray();
                for (TsKvAttribute tsKvAttribute : tsKvAttributeRepository.getAllByUnitId((String) listCondition.get(0))) {
                    JSONObject jsonTsKvAttr = new JSONObject();
                    jsonTsKvAttr.put("id", tsKvAttribute.getId());
                    jsonTsKvAttr.put("name", tsKvAttribute.getName());
                    jsonTsKvAttr.put("symbol", tsKvAttribute.getOutputSymbol());
                    jsonTsKvAttr.put("dataType", tsKvAttribute.getDataType().toString());
                    arrayTsKvAttr.add(jsonTsKvAttr);
                }
                json.put("attributes", arrayTsKvAttr);
                jsonArrayCondition.add(json);
            }
            object.put("triggers", jsonArrayCondition);
            jsonArrayCases.add(object);

            JSONArray jsonActions = new JSONArray();
            for (Action action : rule.getCases().get(i - 1).getActions()) {
                JSONObject jsonAction = new JSONObject();
                jsonAction.put("actionId", action.getId());
                jsonAction.put("value", action.getValue());
                jsonAction.put("actionType", action.getActionType().toString());
                jsonActions.add(jsonAction);
            }
            object.put("actions", jsonActions);
        }
        jsonObject.put("cases", jsonArrayCases);
        return jsonObject.toJSONString();
    }

    @Override
    public List<String> getAllUnitIdsByRuleId(Long ruleId) {
        return ruleRepository.getAllUnitIdsByRuleId(ruleId);
    }

    @Override
    public String getJSONForExplorer(Integer projectId) {
        List<List<Object>> unitsList = unitRepository.getAllUnitIdsAndNameAndParentIdSQL(projectId);

        Object mainUnitId = null;
        for (List<Object> objects : unitsList) {
            if (objects.get(2) == null) {
                mainUnitId = objects.get(0);
                break;
            }
        }

        JSONArray jsonArray = new JSONArray();
        for (List<Object> unitFields: unitsList) {
            if (mainUnitId.equals(unitFields.get(0))) continue;
            JSONObject jsonObjectUnit = new JSONObject();
            if (unitFields.get(2) != null && !unitFields.get(2).equals(mainUnitId)) {
                jsonObjectUnit.put("parentId", PhmUtil.toLongUUID((String) unitFields.get(2)));
            }
            if (unitRepository.getCountChildByParentId((String) unitFields.get(0)) > 0) {
                jsonObjectUnit.put("hasChild", true);
            }
            jsonObjectUnit.put("id", PhmUtil.toLongUUID((String) unitFields.get(0)));
            jsonObjectUnit.put("name", unitFields.get(1));
            jsonObjectUnit.put("type", "unit");
            if (unitFields.get(3) != null)
                jsonObjectUnit.put("unitTypePictureId", unitFields.get(3));

            List<List<Object>> rulesList = ruleRepository.getAllRuleIdAndRuleNameByUnitId((String) unitFields.get(0));
            if (!rulesList.isEmpty()) {
                jsonObjectUnit.put("hasChild", true);
            }
            for (List<Object> ruleFields: rulesList) {
                JSONObject jsonRule = new JSONObject();
                jsonRule.put("id", ruleFields.get(0));
                jsonRule.put("name", ruleFields.get(1));
                jsonRule.put("parentId", PhmUtil.toLongUUID((String) unitFields.get(0)));
                jsonRule.put("type", "rule");
                jsonArray.add(jsonRule);
            }
            jsonArray.add(jsonObjectUnit);
        }

        return jsonArray.toJSONString();
    }

    @Override
    public String getJSONForExplorerByUnitId(String unitId) {
        List<List<Object>> rules = ruleRepository.getAllRulesByUnitIds(faultService.getAllUnitIdsByUnitId(unitId));
        JSONArray jsonArray = new JSONArray();
        for (List<Object> rule : rules) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", rule.get(0));
            jsonObject.put("name", rule.get(1));
            if (rule.get(2).toString().equals("BIT")) {
                jsonObject.put("type", "BIT Rule");
            } else if (rule.get(2).toString().equals("ATTRIBUTE")) {
                jsonObject.put("type", "Attribute Rule");
            }
            jsonObject.put("alert", "on");
            jsonObject.put("isStop", rule.get(4));
            jsonObject.put("modifiedBy", rule.get(3));
            jsonObject.put("modifiedTime", rule.get(5));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public List<TsKvAttribute> getAttributesByRuleId(Long ruleId) {
        return ruleRepository.getAttributesByRuleId(ruleId);
    }

    @Override
    public List<Condition> getAllConditionsByRuleIdSort(Long ruleId) {
        return ruleRepository.getAllConditionsByRuleIdSort(ruleId);
    }

    @Override
    public List<String> getAllUnitIdsByCaseId(Long caseId) {
        return ruleRepository.getAllUnitIdsByCaseId(caseId);
    }

    @Override
    public void updateStopByRuleIds(Boolean isStop, List<Long> ruleIds) {
        ruleRepository.updateStopByRuleIds(isStop, ruleIds);
    }
}
