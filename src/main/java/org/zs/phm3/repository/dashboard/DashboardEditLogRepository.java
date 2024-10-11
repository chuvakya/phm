package org.zs.phm3.repository.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.DashboardEditLog;

import java.util.List;

@Transactional
@Repository
public interface DashboardEditLogRepository extends CrudRepository<DashboardEditLog, Long> {

    @Query(value = "SELECT del.timestamp, ue.name FROM dashboard_edit_log del " +
            "JOIN user_entity ue ON del.user_entity_id = ue.id " +
            "WHERE del.dashboard_id = ?1 ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
    List<List<Object>> getLastLogByDashboardId(Integer dashboardId);
}
