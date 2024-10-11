package org.zs.phm3.repository.maintenance.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.dashboard.MaintenanceWidget;

import java.util.List;

@Transactional
@Repository
public interface MaintenanceWidgetRepository extends CrudRepository<MaintenanceWidget, Long> {

    @Query(value = "SELECT (SELECT COUNT(*) FROM work_order wo " +
            "JOIN units u ON wo.unit_id = u.id WHERE u.project_id = ?1) AS totalCount, " +
            "(SELECT COUNT(*) FROM work_order wo " +
            "JOIN units u ON wo.unit_id = u.id WHERE wo.order_status = 'OPEN' AND u.project_id = ?1) AS openStatusCount, " +
            "(SELECT COUNT(*) FROM work_order wo " +
            "JOIN units u ON wo.unit_id = u.id WHERE wo.order_status = 'OPEN' AND u.project_id = ?1)  * 100 / " +
            "NULLIF((SELECT COUNT(*) FROM work_order wo " +
            "JOIN units u ON wo.unit_id = u.id WHERE u.project_id = ?1), 0) as percentCount", nativeQuery = true)
    List<List<Object>> getTotalCountAndOpenStatusAndPercentByProjectId(Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM work_order wo " +
            "JOIN units u ON wo.unit_id = u.id " +
            "WHERE u.project_id = ?1 AND wo.priority = 'High' " +
            "AND wo.order_status <> 'CLOSED_COMPLETED' " +
            "AND wo.order_status <> 'CLOSED_INCOMPLETE'", nativeQuery = true)
    Long getCountWithPriorityHighByProjectId(Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM work_order wo " +
            "JOIN units u ON wo.unit_id = u.id " +
            "WHERE u.project_id = ?1 AND wo.completion_date < ?2 " +
            "AND wo.order_status <> 'CLOSED_COMPLETED' " +
            "AND wo.order_status <> 'CLOSED_INCOMPLETE'", nativeQuery = true)
    Long getCountNotClosedByProjectIdAndCurrentTime(Integer projectId, Long currentTime);

}
