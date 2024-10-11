package org.zs.phm3.repository.rule;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.rule.Condition;
import org.zs.phm3.models.rule.Rule;
import org.zs.phm3.models.rule.RuleType;
import org.zs.phm3.models.ts.TsKvAttribute;

import java.util.List;

@Repository
@Transactional
public interface RuleRepository extends CrudRepository<Rule, Long> {

    @Modifying
    @Query(value = "UPDATE rule SET is_stop = ?1 WHERE id IN ?2", nativeQuery = true)
    void updateStopByRuleIds(Boolean isStop, List<Long> ruleIds);

    @Query(value = "SELECT id, name FROM Rule WHERE projectId = ?1 ORDER BY name")
    List<List<Object>> getAllByProjectId(Integer projectId);

    List<Rule> getAllByPeriodAndRuleTypeAndIsStop(Long period, RuleType ruleType, Boolean isStop);

    @Query(value = "SELECT DISTINCT attr.unit_id, u.name FROM condition con CROSS JOIN ts_kv_attribute attr, units u " +
            "WHERE con.ts_kv_attribute_id = attr.id AND attr.unit_id = u.id AND u.project_id = ?1 ORDER BY u.name ASC",
            nativeQuery = true)
    List<List<Object>> getAllUniqueUnitIdAndUnitNameByProjectId(Integer project);

    @Query(value = "SELECT DISTINCT c.caseEntity.rule.id, c.caseEntity.rule.name FROM Condition c WHERE c.tsKvAttribute.unitId = ?1 ORDER BY c.caseEntity.rule.name ASC")
    List<List<Object>> getAllRuleIdAndRuleNameByUnitId(String unitId);

    @Query(value = "SELECT c.tsKvAttribute.unitId, c.tsKvAttribute.id, c.conditionType, c.value, c.id FROM " +
            "Condition c WHERE c.caseEntity.id = ?1")
    List<List<Object>> getAllConditionsByCaseId(Long caseId);

    @Query(value = "SELECT DISTINCT c.caseEntity.rule FROM Condition c WHERE c.tsKvAttribute.unitId = ?1")
    List<Rule> getAllRulesByUnitId(String unitId);

    @Query(value = "SELECT DISTINCT c.caseEntity.rule.id, c.caseEntity.rule.name, c.caseEntity.rule.ruleType, " +
            "c.caseEntity.rule.modifiedBy.name, c.caseEntity.rule.isStop, c.caseEntity.rule.modifiedTime " +
            "FROM Condition c WHERE c.tsKvAttribute.unitId IN ?1")
    List<List<Object>> getAllRulesByUnitIds(List<String> unitIds);

    @Query(value = "SELECT DISTINCT c.caseEntity.rule.id FROM Condition c WHERE c.tsKvAttribute.unitId IN ?1")
    List<Long> getAllRuleIdsByUnitIds(List<String> unitIds);

    @Query(value = "SELECT DISTINCT c.tsKvAttribute.unitId FROM Condition c WHERE c.caseEntity.rule.id = ?1")
    List<String> getAllUnitIdsByRuleId(Long ruleId);

    @Query(value = "SELECT DISTINCT c.tsKvAttribute.unitId FROM Condition c WHERE c.caseEntity.id = ?1")
    List<String> getAllUnitIdsByCaseId(Long caseId);

    @Query(value = "SELECT DISTINCT c.caseEntity.rule.id, c.caseEntity.rule.name FROM Condition c WHERE c.tsKvAttribute.unitId IN ?1")
    List<String> getAllRuleIdAndNameByUnitIds(List<String> unitIds);

    @Query(value = "SELECT DISTINCT r.id, r.name, a.unit_id AS parent_id FROM condition c "+
            "LEFT JOIN rule r ON c.rule_id=r.id "+
            "LEFT JOIN ts_kv_attribute a ON c.ts_kv_attribute_id=a.id "+
            "WHERE a.unit_id IN "+
            "(SELECT id FROM units WHERE project_id = ?1)", nativeQuery = true)
    List<Object[]> getAllRulesByProjectId(Integer projectId);

    @Query(value = "SELECT c.tsKvAttribute FROM Condition c WHERE c.caseEntity.rule.id = ?1 ORDER BY c.tsKvAttribute.id")
    List<TsKvAttribute> getAttributesByRuleId(Long ruleId);

    @Query(value = "FROM Condition c WHERE c.caseEntity.rule.id = ?1 ORDER BY c.tsKvAttribute.id")
    List<Condition> getAllConditionsByRuleIdSort(Long ruleId);
}
