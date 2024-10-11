package org.zs.phm3.service.rule;

import org.zs.phm3.models.rule.Condition;
import org.zs.phm3.models.rule.Rule;
import org.zs.phm3.models.rule.RuleType;
import org.zs.phm3.models.ts.TsKvAttribute;

import java.util.List;

public interface RuleService {
    Rule save(Rule rule);
    Rule getById(Long  ruleId);
    List<Rule> getAll();
    String getAllByProjectId(Integer projectId);
    List<Rule> getAllByPeriodAndRuleTypeAndStop(Long period, RuleType ruleType, Boolean isStop);
    String getJSONById(Long ruleId);
    List<String> getAllUnitIdsByRuleId(Long ruleId);
    String getJSONForExplorer(Integer projectId);
    String getJSONForExplorerByUnitId(String unitId);
    List<TsKvAttribute> getAttributesByRuleId(Long ruleId);
    List<Condition> getAllConditionsByRuleIdSort(Long ruleId);
    List<String> getAllUnitIdsByCaseId(Long caseId);
    void updateStopByRuleIds(Boolean isStop, List<Long> ruleIds);
}
