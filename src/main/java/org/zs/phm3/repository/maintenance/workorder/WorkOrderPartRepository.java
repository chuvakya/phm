package org.zs.phm3.repository.maintenance.workorder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPart;

import java.util.List;

@Transactional
@Repository
public interface WorkOrderPartRepository extends CrudRepository<WorkOrderPart, Long> {

    @Query(value = "SELECT ps.id, p.name, ps.quantity FROM work_order_part wop " +
            "JOIN part_stock ps on wop.part_stock_id = ps.id " +
            "JOIN part p on ps.part_id = p.id " +
            "WHERE work_order_id = ?1", nativeQuery = true)
    List<List<Object>> getIdNameQuantityByWorkOrderId(Long workOrderId);

    @Query(value = "SELECT DISTINCT p.id, p.name " +
            "FROM work_order_part wop " +
            "JOIN part p on wop.part_id = p.id " +
            "WHERE work_order_id = ?1", nativeQuery = true)
    List<List<Object>> getAllIdAndNamePartByWorkOrderId(Long workOrderId);

    @Query(value = "SELECT wop.actual_quantity_used, wop.part_id, wop.part_stock_id " +
            "FROM work_order_part wop " +
            "WHERE work_order_id = ?1", nativeQuery = true)
    List<List<Object>> getAllByWorkOrderId(Long workOrderId);

}
