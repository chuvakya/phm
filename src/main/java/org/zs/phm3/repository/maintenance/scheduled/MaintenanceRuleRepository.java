package org.zs.phm3.repository.maintenance.scheduled;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.MaintenanceRule;

import java.util.List;

@Repository
@Transactional
public interface MaintenanceRuleRepository extends CrudRepository<MaintenanceRule, Long> {

    @Query(value = "SELECT id, action_type FROM maintenance_action WHERE maintenance_rule_id = ?1", nativeQuery = true)
    List<List<Object>> getAllActionsByRuleId(Long ruleId);

    @Query(value = "SELECT mt.id, mt.condition_type, mt.count_repeat, mt.date_type, mt.end_date, mt.every_end_number, mt.every_number, " +
            "mt.every_start_number, mt.repeat_interval, mt.start_date, mt.trigger_type, tsk.unit_id, value AS val, ts_kv_attribute_id " +
            "FROM maintenance_trigger mt " +
            "LEFT JOIN ts_kv_attribute tsk ON mt.ts_kv_attribute_id = tsk.id " +
            "WHERE maintenance_rule_id = ?1", nativeQuery = true)
    List<List<Object>> getAllTriggersByRuleId(Long ruleId);

    @Modifying
    @Query(value = "DELETE FROM maintenance_trigger WHERE maintenance_rule_id = ?1", nativeQuery = true)
    void deleteTriggersByRuleId(Long ruleId);

    @Modifying
    @Query(value = "DELETE FROM maintenance_action WHERE maintenance_rule_id = ?1", nativeQuery = true)
    void deleteActionsByRuleId(Long ruleId);

    @Query(value = "SELECT mr.id, mr.name AS ruleName, mr.is_on, mr.when_string, u.name AS unitName, " +
            "ue.name AS userName, wot.priority, wot.maintenance_type FROM maintenance_work_order_template wot " +
            "JOIN maintenance_rule mr ON wot.maintenance_rule_id = mr.id " +
            "JOIN units u ON wot.unit_id = u.id " +
            "LEFT JOIN user_entity ue ON wot.assigned_to_user_id = ue.id " +
            "WHERE u.project_id = ?1 ORDER BY mr.name", nativeQuery = true)
    List<List<Object>> getAllRulesByProjectId(Integer projectId);

    @Modifying
    @Query(value = "UPDATE maintenance_rule SET is_on = ?1 WHERE id IN ?2", nativeQuery = true)
    void updateIsOnByRuleIds(Boolean isOn, List<Long> ruleIds);

    @Query(value = "FROM MaintenanceRule mr WHERE mr.isOn = true")
    List<MaintenanceRule> getAllIsOn();

    @Query(value = "SELECT mr.id AS ruleId, mr.expressions_type, mr.is_on, mr.name, " +
            "(SELECT t.id FROM maintenance_work_order_template t WHERE t.maintenance_rule_id = mr.id) AS workOrderId " +
            "FROM maintenance_rule mr WHERE mr.id = ?1", nativeQuery = true)
    List<List<Object>> getAllFieldRuleById(Long ruleId);

}
