package org.zs.phm3.controller.maintenance.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.maintenance.scheduled.*;
import org.zs.phm3.models.rule.ExpressionsType;
import org.zs.phm3.service.maintenance.scheduled.MaintenanceRuleService;
import org.zs.phm3.service.maintenance.scheduled.WorkOrderTemplateService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/maintenance_rule/")
public class MaintenanceRuleController {

    @Autowired
    private UserService userService;

    @Autowired
    private SQLHelper sqlHelper;

    @Autowired
    private MaintenanceRuleService maintenanceRuleService;

    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    @Autowired
    private WorkOrderTemplateService workOrderTemplateService;


    @PostMapping(value = "create")
    public void create(@RequestParam String ruleName, @RequestParam ExpressionsType expressionsType,
                       @RequestBody MaintenanceRuleRequest ruleRequest, @RequestParam Boolean isOn) {
        WorkOrderTemplate template = new WorkOrderTemplate();
        template.setOrderName(ruleRequest.getWorkOrderTemplate().getOrderName());
        template.setUnitId(PhmUtil.toShortUUID(ruleRequest.getWorkOrderTemplate().getUnitId()));
        template.setOrderStatus(ruleRequest.getWorkOrderTemplate().getOrderStatus());
        template.setMaintenanceType(ruleRequest.getWorkOrderTemplate().getMaintenanceType());
        template.setPriority(ruleRequest.getWorkOrderTemplate().getPriority());
        template.setSummaryOfIssue(ruleRequest.getWorkOrderTemplate().getSummaryOfIssue());
        template.setWorkInstruction(ruleRequest.getWorkOrderTemplate().getWorkInstruction());
        template.setEstimatedLabor(ruleRequest.getWorkOrderTemplate().getEstimatedLabor());
        template.setActualLabor(ruleRequest.getWorkOrderTemplate().getActualLabor());
        template.setDaysToComplete(ruleRequest.getWorkOrderTemplate().getDaysToComplete());
        template.setModifiedTime(System.currentTimeMillis());
        if (ruleRequest.getWorkOrderTemplate().getAssignedToUserId() == null) {
            template.setAssignedToUser(null);
        } else {
            template.setAssignedToUser(userService.getById(ruleRequest.getWorkOrderTemplate().getAssignedToUserId()));
        }
        if (ruleRequest.getWorkOrderTemplate().getCompletedByUserId() == null) {
            template.setCompletedByUser(null);
        } else {
            template.setCompletedByUser(userService.getById(ruleRequest.getWorkOrderTemplate().getCompletedByUserId()));
        }
        MaintenanceRule rule = new MaintenanceRule(ruleName, expressionsType, null, true, null);
        for (Trigger trigger : ruleRequest.getTriggers()) {
            if (trigger.getTsKvAttributeId() != null) {
                trigger.setTsKvAttribute(tsKvAttributeService.getById(trigger.getTsKvAttributeId()));
            }
            if (trigger.getStartDate() != null) {
                trigger.setLastRepeatDate(trigger.getStartDate());
            } else {
                trigger.setLastRepeatDate(System.currentTimeMillis());
            }
            trigger.setLastScanEveryNumber(trigger.getEveryStartNumber());
            trigger.setMaintenanceRule(rule);
            rule.getTriggers().add(trigger);
        }
        for (MaintenanceAction maintenanceAction : ruleRequest.getMaintenanceActions()) {
            maintenanceAction.setMaintenanceRule(rule);
            rule.getMaintenanceActions().add(maintenanceAction);
        }
        rule.setWhenString(maintenanceRuleService.generateWhenByTriggers(rule.getTriggers()));
        rule.setOn(isOn);
        maintenanceRuleService.save(rule);
        template.setMaintenanceRule(rule);
        workOrderTemplateService.save(template);
        workOrderTemplateService.addLaborTasks(ruleRequest.getWorkOrderTemplate().getLaborTasks(), template.getId());
        workOrderTemplateService.addWorkOrderParts(ruleRequest.getWorkOrderTemplate().getWorkOrderParts(),
                template.getId());
    }

    @DeleteMapping(value = "deleteAllByRuleIds")
    public void deleteAll(@RequestBody List<Long> ruleIds) {
        sqlHelper.deleteAll("maintenance_rule", "id", ruleIds);
    }

    @GetMapping(value = "getById/{ruleId}", produces = "application/json")
    public String getByIdJSON(@PathVariable Long ruleId) {
        return maintenanceRuleService.getJSONById(ruleId);
    }

    @PatchMapping(value = "updateById/{ruleId}")
    public void updateById(@PathVariable Long ruleId, @RequestParam String ruleName,
                           @RequestParam ExpressionsType expressionsType,
                           @RequestBody MaintenanceRuleRequest ruleRequest, @RequestParam Boolean isOn) {
        MaintenanceRule rule = maintenanceRuleService.getById(ruleId);
        rule.setName(ruleName);
        rule.setExpressionsType(expressionsType);
        rule.setOn(isOn);
        maintenanceRuleService.deleteTriggersByRuleId(ruleId);
        maintenanceRuleService.deleteActionsByRuleId(ruleId);
        for (Trigger trigger : ruleRequest.getTriggers()) {
            if (trigger.getTsKvAttributeId() != null) {
                trigger.setTsKvAttribute(tsKvAttributeService.getById(trigger.getTsKvAttributeId()));
            }
            if (trigger.getStartDate() != null) {
                trigger.setLastRepeatDate(trigger.getStartDate());
            } else {
                trigger.setLastRepeatDate(System.currentTimeMillis());
            }
            trigger.setLastScanEveryNumber(trigger.getEveryStartNumber());
            trigger.setLastScan(System.currentTimeMillis());
            trigger.setCurrentCountRepeat(0L);
            trigger.setMaintenanceRule(rule);
            rule.getTriggers().add(trigger);
        }
        for (MaintenanceAction maintenanceAction : ruleRequest.getMaintenanceActions()) {
            maintenanceAction.setMaintenanceRule(rule);
            rule.getMaintenanceActions().add(maintenanceAction);
        }
        rule.setWhenString(maintenanceRuleService.generateWhenByTriggers(rule.getTriggers()));
        maintenanceRuleService.save(rule);
        WorkOrderTemplate template = rule.getWorkOrderTemplate();
        template.setOrderName(ruleRequest.getWorkOrderTemplate().getOrderName());
        template.setUnitId(PhmUtil.toShortUUID(ruleRequest.getWorkOrderTemplate().getUnitId()));
        template.setOrderStatus(ruleRequest.getWorkOrderTemplate().getOrderStatus());
        template.setMaintenanceType(ruleRequest.getWorkOrderTemplate().getMaintenanceType());
        template.setPriority(ruleRequest.getWorkOrderTemplate().getPriority());
        template.setSummaryOfIssue(ruleRequest.getWorkOrderTemplate().getSummaryOfIssue());
        template.setWorkInstruction(ruleRequest.getWorkOrderTemplate().getWorkInstruction());
        template.setEstimatedLabor(ruleRequest.getWorkOrderTemplate().getEstimatedLabor());
        template.setActualLabor(ruleRequest.getWorkOrderTemplate().getActualLabor());
        template.setDaysToComplete(ruleRequest.getWorkOrderTemplate().getDaysToComplete());
        template.setModifiedTime(System.currentTimeMillis());
        if (ruleRequest.getWorkOrderTemplate().getAssignedToUserId() == null) {
            template.setAssignedToUser(null);
        } else {
            template.setAssignedToUser(userService.getById(ruleRequest.getWorkOrderTemplate().getAssignedToUserId()));
        }
        if (ruleRequest.getWorkOrderTemplate().getCompletedByUserId() == null) {
            template.setCompletedByUser(null);
        } else {
            template.setCompletedByUser(userService.getById(ruleRequest.getWorkOrderTemplate().getCompletedByUserId()));
        }
        sqlHelper.deleteAll("maintenance_labor_task_template", "work_order_template_id", Arrays.asList(template.getId()));
        workOrderTemplateService.addLaborTasks(ruleRequest.getWorkOrderTemplate().getLaborTasks(), template.getId());
        sqlHelper.deleteAll("maintenance_work_order_part_template", "work_order_template_id", Arrays.asList(template.getId()));
        workOrderTemplateService.addWorkOrderParts(ruleRequest.getWorkOrderTemplate().getWorkOrderParts(),
                template.getId());
        workOrderTemplateService.save(template);
    }

    @GetMapping(value = "getAllForTableByProjectId/{projectId}", produces = "application/json")
    public String getAllForTableByProjectId(@PathVariable Integer projectId) {
        return maintenanceRuleService.getAllRulesByProjectId(projectId);
    }

    @PatchMapping(value = "onOffRuleByRuleIds/{isOn}")
    public void onOffRuleByRuleIds(@PathVariable Boolean isOn, @RequestBody List<Long> ruleIds) {
        maintenanceRuleService.updateIsOnByRuleIds(isOn, ruleIds);
    }

}
