package org.zs.phm3.repository.maintenance.workorder;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.workorder.LaborTask;

import java.util.List;

@Repository
@Transactional
public interface LaborTaskRepository extends CrudRepository<LaborTask, Long> {

    @Modifying
    @Query(value = "DELETE FROM labor_task WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long laborTaskId);

    @Modifying
    @Query(value = "DELETE FROM labor_task WHERE work_order_id = ?1", nativeQuery = true)
    void deleteAllByWorkOrderSQL(Long workOrderId);

    @Query(value = "SELECT t.id, t.task_description, u.name AS unitName, userAssigned.name AS userName1, " +
            "userCompleted.name AS userName2, t.completed_time, " +
            "t.completion_notes, t.time_estimate, t.time_spent, t.completed_by_user_id, t.assigned_to_user_id, t.unit_id " +
            "FROM labor_task t " +
            "CROSS JOIN units u " +
            "LEFT JOIN  user_entity userAssigned ON t.assigned_to_user_id = userAssigned.id " +
            "LEFT JOIN user_entity userCompleted ON t.completed_by_user_id = userCompleted.id " +
            "WHERE t.unit_id = u.id AND work_order_id = ?1 ORDER BY t.task_description",
            nativeQuery = true)
    List<List<Object>> getAllByWorkOrderIdForTable(Long workOrderId);


    @Query(value = "SELECT completed_time, completion_notes, task_description, time_estimate, time_spent, " +
            "unit_id, assigned_to_user_id, completed_by_user_id " +
            "FROM labor_task WHERE work_order_id = ?1", nativeQuery = true)
    List<List<Object>> getAllByWorkOrderId(Long workOrderId);
}
