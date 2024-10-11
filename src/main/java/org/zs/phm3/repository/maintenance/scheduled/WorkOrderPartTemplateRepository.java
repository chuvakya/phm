package org.zs.phm3.repository.maintenance.scheduled;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderPartTemplate;

import java.util.List;

@Transactional
@Repository
public interface WorkOrderPartTemplateRepository extends CrudRepository<WorkOrderPartTemplate, Long> {

    @Query(value = "SELECT wop.actual_quantity_used, wop.part_id, wop.part_stock_id " +
            "FROM maintenance_work_order_part_template wop " +
            "WHERE work_order_template_id = ?1", nativeQuery = true)
    List<List<Object>> getAllByWorkOrderTemplateId(Long workOrderTemplateId);

    @Query(value = "SELECT DISTINCT p.id, p.name " +
            "FROM maintenance_work_order_part_template wop " +
            "JOIN part p on wop.part_id = p.id " +
            "WHERE work_order_template_id = ?1", nativeQuery = true)
    List<List<Object>> getAllIdAndNamePartByWorkOrderTemplateId(Long workOrderTemplateId);
}
