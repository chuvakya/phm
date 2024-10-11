package org.zs.phm3.repository.fault;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.fault.FaultLog;

import java.util.List;

@Transactional
@Repository
public interface FaultLogRepository extends CrudRepository<FaultLog, Long> {

    @Query(value = "FROM FaultLog f WHERE f.fault.id = ?1 ORDER BY f.timestamp DESC")
    List<FaultLog> getAllFaultLogByFaultId(Long faultId);

    @Query(value = "FROM FaultLog f WHERE f.timestamp >= ?1 AND f.fault.id = ?2 ORDER BY f.timestamp DESC")
    List<FaultLog> getByMoreTimestampAndFaultId(Long timestamp, Long faultId);

    @Query(value = "FROM FaultLog f WHERE f.timestamp >= ?1 AND f.timestamp <= ?2 AND f.fault.id = ?3")
    List<FaultLog> getAllByIntervalAndFaultId(Long startTime, Long endTime, Long faultId);

    @Query(value = "SELECT (SELECT fl.timestamp FROM fault_log fl " +
            "WHERE fl.fault_id = ?1 ORDER BY fl.timestamp ASC LIMIT 1) AS min, " +
            "(SELECT fl.timestamp FROM fault_log fl " +
            "WHERE fl.fault_id = ?1 ORDER BY fl.timestamp DESC LIMIT 1) AS max", nativeQuery = true)
    List<List<Object>> getMinMaxTimestampByFaultId(Long faultId);
}
