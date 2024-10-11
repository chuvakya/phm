package org.zs.phm3.repository.fault;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.fault.Fault;

import java.util.List;

@Transactional
@Repository
public interface FaultRepository extends CrudRepository<Fault, Long> {

    @Query(value = "SELECT DISTINCT * " +
            "FROM fault_unit fu " +
            "JOIN fault f on fu.fault_id = f.id " +
            "WHERE fu.unit_id IN ?1 ORDER BY f.start_time ASC", nativeQuery = true)
    List<Fault> getAllByUnitIds(List<String> unitIds);

    @Query(value = "SELECT DISTINCT f.id, f.start_time, f.end_time, f.name, f.priority, f.status, f.severity, f.message " +
            "FROM fault_unit fu " +
            "JOIN fault f on fu.fault_id = f.id " +
            "WHERE  fu.unit_id IN ?1 ORDER BY f.start_time ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getAllByUnitIdsAndOffsetAndLimit(List<String> unitIds, Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(DISTINCT f.id) FROM fault_unit fu " +
            "JOIN fault f on fu.fault_id = f.id " +
            "WHERE  fu.unit_id IN ?1", nativeQuery = true)
    Long getCountByUnitIds(List<String> unitIds);

    @Query(value = "FROM Fault WHERE caseEntity.id = ?1")
    Fault getByCaseId(Long caseId);

    @Query(value = "SELECT fu.unit_id, f.status FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "JOIN fault f ON fu.fault_id = f.id " +
            "WHERE u.project_id = ?1", nativeQuery = true)
    List<List<Object>> getUnitIdAndFaultStatusByProjectId(Integer projectId);

    @Query(value = "SELECT (SELECT fl.timestamp FROM fault_log fl " +
            "WHERE fl.fault_id IN " +
            "(SELECT DISTINCT fu.fault_id FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "WHERE u.project_id = ?1) " +
            "AND (fl.status = 2 OR fl.status = 1) ORDER BY fl.timestamp ASC LIMIT 1) AS min, " +
            "(SELECT fl.timestamp FROM fault_log fl " +
            "WHERE fl.fault_id IN " +
            "(SELECT DISTINCT fu.fault_id FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "WHERE u.project_id = ?1) " +
            "AND (fl.status = 2 OR fl.status = 1) ORDER BY fl.timestamp DESC LIMIT 1) AS max", nativeQuery = true)
    List<List<Object>> getMinAndMaxTimestampFaultLogByProjectId(Integer projectId);

    @Query(value = "SELECT (SELECT f.start_time FROM fault_unit fu " +
            "JOIN fault f ON f.id = fu.fault_id " +
            "WHERE f.id IN " +
            "(SELECT DISTINCT fu.fault_id FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "WHERE u.project_id = ?1) ORDER BY f.start_time ASC LIMIT 1) AS min, " +
            "(SELECT f.start_time FROM fault_unit fu " +
            "JOIN fault f ON f.id = fu.fault_id " +
            "WHERE f.id IN " +
            "(SELECT DISTINCT fu.fault_id FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "WHERE u.project_id = ?1) ORDER BY f.start_time DESC LIMIT 1) AS max", nativeQuery = true)
    List<List<Object>> getMinMaxStartTimeByProjectId(Integer projectId);

    @Query(value = "SELECT (SELECT f.end_time FROM fault_unit fu " +
            "JOIN fault f ON f.id = fu.fault_id " +
            "WHERE f.id IN " +
            "(SELECT DISTINCT fu.fault_id FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "WHERE u.project_id = ?1) ORDER BY f.end_time ASC LIMIT 1) AS min, " +
            "(SELECT f.end_time FROM fault_unit fu " +
            "JOIN fault f ON f.id = fu.fault_id " +
            "WHERE f.id IN " +
            "(SELECT DISTINCT fu.fault_id FROM fault_unit fu " +
            "JOIN units u ON fu.unit_id = u.id " +
            "WHERE u.project_id = ?1) ORDER BY f.end_time DESC LIMIT 1) AS max", nativeQuery = true)
    List<List<Object>> getMinMaxEndTimeByProjectId(Integer projectId);

    @Query(value = "SELECT (SELECT value FROM rule_action ra " +
            "WHERE f.case_entity_id = ra.case_entity_id AND ra.action_type = 'MESSAGE') " +
            "FROM fault f " +
            "JOIN rule_case rc ON f.case_entity_id = rc.id " +
            "WHERE f.id = ?1", nativeQuery = true)
    String getMessageByFaultId(Long faultId);
}
