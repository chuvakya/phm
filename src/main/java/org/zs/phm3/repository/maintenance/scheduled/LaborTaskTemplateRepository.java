package org.zs.phm3.repository.maintenance.scheduled;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.LaborTaskTemplate;

import java.util.List;

@Transactional
@Repository
public interface LaborTaskTemplateRepository extends CrudRepository<LaborTaskTemplate, Long> {

    @Query(value = "SELECT t.id, t.task_description, u.name AS unitName, userAssigned.name AS userName1, " +
            "userCompleted.name AS userName2, t.completed_time, " +
            "t.completion_notes, t.time_estimate, t.time_spent, t.completed_by_user_id, t.assigned_to_user_id, t.unit_id " +
            "FROM maintenance_labor_task_template t " +
            "CROSS JOIN units u " +
            "LEFT JOIN  user_entity userAssigned ON t.assigned_to_user_id = userAssigned.id " +
            "LEFT JOIN user_entity userCompleted ON t.completed_by_user_id = userCompleted.id " +
            "WHERE t.unit_id = u.id AND work_order_template_id = ?1 ORDER BY t.task_description",
            nativeQuery = true)
    List<List<Object>> getAllByWorkOrderTemplateId(Long workOrderTemplateId);
}
