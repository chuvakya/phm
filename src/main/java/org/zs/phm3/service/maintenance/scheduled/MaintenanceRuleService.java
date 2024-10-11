package org.zs.phm3.service.maintenance.scheduled;

import org.zs.phm3.models.maintenance.scheduled.MaintenanceRule;
import org.zs.phm3.models.maintenance.scheduled.Trigger;

import java.util.List;

public interface MaintenanceRuleService {
    MaintenanceRule save(MaintenanceRule maintenanceRule);
    MaintenanceRule getById(Long ruleId);
    String getListByProjectId(Integer projectId);
    String getJSONById(Long ruleId);
    void deleteTriggersByRuleId(Long ruleId);
    void deleteActionsByRuleId(Long ruleId);
    String getAllRulesByProjectId(Integer projectId);
    void updateIsOnByRuleIds(Boolean isOn, List<Long> ruleIds);
    String generateWhenByTriggers(List<Trigger> triggers);
}
