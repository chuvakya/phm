package org.zs.phm3.repository.maintenance.scheduled;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;

import java.util.List;

@Repository
@Transactional
public interface WorkOrderTemplateRepository extends CrudRepository<WorkOrderTemplate, Long> {

    @Query(value = "SELECT id, unit_id, actual_labor, assigned_to_user_id, completed_by_user_id, " +
            "completion_date, estimated_labor, maintenance_type, order_name, order_status, priority, " +
            "summary_of_issue, work_instruction FROM maintenance_work_order_template WHERE id = ?1", nativeQuery = true)
    List<List<Object>> getAllById(Long workOrderTemplateId);
}
