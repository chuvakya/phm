package org.zs.phm3.controller.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.rule.*;
import org.zs.phm3.service.fault.FaultService;
import org.zs.phm3.service.rule.RuleService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rule/")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private SQLHelper sqlHelper;

    @Autowired
    private FaultService faultService;

    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create")
    public Rule create(@RequestParam String name,
                       @RequestParam RuleType ruleType, @RequestBody List<CaseParam> caseParams,
                       @RequestParam Long period) {
        Rule rule = new Rule(ruleType, name, null, userService.getByLogin("Admin"),
                System.currentTimeMillis(), period);
        for (CaseParam caseParam : caseParams) {
            Case aCase = new Case(ExpressionsType.valueOf(caseParam.getType()), rule);
            for (ConditionParam conditionParam : caseParam.getTriggers()) {
                Condition condition = new Condition(conditionParam.getConditionType(), conditionParam.getValue(),
                        tsKvAttributeService.getById(conditionParam.getAttributeId()), aCase);
                aCase.getConditions().add(condition);
            }
            aCase.setActions(caseParam.getActions());
            for (Action action : aCase.getActions()) {
                action.setCaseEntity(aCase);
            }
            rule.getCases().add(aCase);
        }
        rule.setProjectId(unitService.getUnitById(
                rule.getCases().get(0).getConditions().get(0).getTsKvAttribute().getUnitId()).getProject().getId());
        return ruleService.save(rule);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> ruleIds) {
        sqlHelper.deleteAll("rule", "id", ruleIds);
    }

    @GetMapping(value = "getJSONForExplorer/{projectId}", produces = "application/json")
    public String getJSONForExplorer(@PathVariable Integer projectId) {
        return ruleService.getJSONForExplorer(projectId);
    }

    @GetMapping(value = "getJSONForExplorerByUnitId/{unitId}", produces = "application/json")
    public String getJSONForExplorerByUnitId(@PathVariable String unitId) {
        return ruleService.getJSONForExplorerByUnitId(PhmUtil.toShortUUID(unitId));
    }

    @GetMapping(value = "getById/{ruleId}", produces = "application/json")
    public String getById(@PathVariable Long ruleId) {
        return ruleService.getJSONById(ruleId);
    }

    @Transactional
    @PatchMapping(value = "update")
    public void updateById(@RequestParam Long ruleId, @RequestParam String name,
                           @RequestParam RuleType ruleType,
                           @RequestBody List<CaseParam> caseParams) {
        Rule rule = ruleService.getById(ruleId);
        rule.setName(name);
        rule.setRuleType(ruleType);
        rule.setModifiedBy(userService.getByLogin("Admin"));
        rule.setModifiedTime(System.currentTimeMillis());
        rule.setLastScan(System.currentTimeMillis());
        List<Case> cases = rule.getCases();
        List<Long> caseIds = new ArrayList<>();
        for (Case aCase : cases) {
            caseIds.add(aCase.getId());
        }
        sqlHelper.deleteAll("rule_case", "rule_id", Arrays.asList(ruleId));
        for (CaseParam caseParam : caseParams) {
            Case aCase = new Case(ExpressionsType.valueOf(caseParam.getType()), rule);
            for (ConditionParam conditionParam : caseParam.getTriggers()) {
                Condition condition = new Condition(conditionParam.getConditionType(), conditionParam.getValue(),
                        tsKvAttributeService.getById(conditionParam.getAttributeId()), aCase);
                aCase.getConditions().add(condition);
            }
            aCase.setActions(caseParam.getActions());
            for (Action action : aCase.getActions()) {
                action.setCaseEntity(aCase);
            }
            rule.getCases().add(aCase);
        }
        ruleService.save(rule);
    }

    @GetMapping(value = "getJSONRulesByProjectId/{projectId}", produces = "application/json")
    public String getJSONRulesByProjectId(@PathVariable Integer projectId) {
        return ruleService.getAllByProjectId(projectId);
    }

    @PatchMapping(value = "updateStateByRuleIds/{isStop}")
    public void updateStateByRuleIds(@PathVariable Boolean isStop, @RequestBody List<Long> ruleIds) {
        ruleService.updateStopByRuleIds(isStop, ruleIds);
    }

/*    @GetMapping(value = "getJSONForExplorer/{projectId}", produces = "application/json")
    public String getJSONForExplorer(@PathVariable Integer projectId) {
        return ruleService.getJSONForExplorer(projectId);

    }*/
}
