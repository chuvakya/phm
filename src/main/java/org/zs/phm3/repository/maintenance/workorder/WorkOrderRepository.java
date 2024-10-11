package org.zs.phm3.repository.maintenance.workorder;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.workorder.WorkOrder;

import java.util.List;

@Repository
@Transactional
public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {

    @Modifying
    @Query(value = "DELETE FROM work_order WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long workOrderId);

    @Query(value = "SELECT * FROM work_order w " +
                "JOIN units u ON w.unit_id = u.id " +
                "WHERE u.project_id = ?1", nativeQuery = true)
    List<WorkOrder> getAllByProjectId(Integer projectId);

    @Query(value = "SELECT id, unit_id, actual_labor, assigned_to_user_id, completed_by_user_id, " +
            "completion_date, estimated_labor, maintenance_type, order_name, order_status, priority, " +
            "summary_of_issue, work_instruction FROM work_order WHERE id = ?1", nativeQuery = true)
    List<List<Object>> getAllById(Long workOrderId);

    @Query(value = "SELECT w.id, w.order_status, w.order_name, w.maintenance_type, u.name AS unitName, w.priority, " +
            "us1.name AS userName1, us2.name AS userName2, w.completion_date, w.modified_time, w.is_scheduled " +
            "FROM work_order w " +
            "JOIN units u ON w.unit_id = u.id " +
            "LEFT JOIN user_entity us1 on w.assigned_to_user_id = us1.id " +
            "LEFT JOIN user_entity us2 on w.completed_by_user_id = us2.id " +
            "WHERE u.project_id = ?1 ORDER BY modified_time", nativeQuery = true)
    List<List<Object>> getAllForTableByProjectId(Integer projectId);

    @Query(value = "SELECT w.id, w.order_status, w.order_name, w.maintenance_type, u.name AS unitName, w.priority, " +
            "us1.name AS userName1, us2.name AS userName2, w.completion_date, w.modified_time, w.is_scheduled " +
            "FROM work_order w " +
            "JOIN units u ON w.unit_id = u.id " +
            "LEFT JOIN user_entity us1 on w.assigned_to_user_id = us1.id " +
            "LEFT JOIN user_entity us2 on w.completed_by_user_id = us2.id " +
            "WHERE u.project_id = ?1 AND w.order_status = ?2 ORDER BY modified_time", nativeQuery = true)
    List<List<Object>> getAllForTableByProjectIdAndStatus(Integer projectId, String orderStatus);

    @Query(value = "SELECT w.id, w.order_status, w.order_name, w.maintenance_type, u.name AS unitName, w.priority, " +
            "us1.name AS userName1, us2.name AS userName2, w.completion_date, w.modified_time, w.is_scheduled " +
            "FROM work_order w " +
            "JOIN units u ON w.unit_id = u.id " +
            "LEFT JOIN user_entity us1 on w.assigned_to_user_id = us1.id " +
            "LEFT JOIN user_entity us2 on w.completed_by_user_id = us2.id " +
            "WHERE u.project_id = ?1 AND (w.order_status = 'CLOSED_COMPLETED' OR w.order_status = 'CLOSED_INCOMPLETE') " +
            "ORDER BY modified_time", nativeQuery = true)
    List<List<Object>> getAllForTableByProjectIdAndStatusClosed(Integer projectId);

    @Query(value = "SELECT w.id, w.order_status, w.order_name, w.maintenance_type, u.name AS unitName, w.priority, " +
            "us1.name AS userName1, us2.name AS userName2, w.completion_date, w.modified_time, w.is_scheduled  " +
            "FROM work_order w " +
            "JOIN units u ON w.unit_id = u.id " +
            "LEFT JOIN user_entity us1 on w.assigned_to_user_id = us1.id " +
            "LEFT JOIN user_entity us2 on w.completed_by_user_id = us2.id " +
            "WHERE u.project_id = ?1 ORDER BY modified_time ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getAllForTableByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);



    @Query(value = "SELECT COUNT(*) FROM work_order " +
            "JOIN units u ON work_order.unit_id = u.id " +
            "WHERE u.project_id = ?1", nativeQuery = true)
    Long getCountByProjectId(Integer projectId);

}
