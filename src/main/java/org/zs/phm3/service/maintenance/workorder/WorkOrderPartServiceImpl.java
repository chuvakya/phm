package org.zs.phm3.service.maintenance.workorder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPartRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WorkOrderPartServiceImpl implements WorkOrderPartService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addWorkOrderParts(Long workOrderId, List<WorkOrderPartRequest> parts) {
        if (!parts.isEmpty()) {
            StringBuilder builderSQL = new StringBuilder("INSERT INTO work_order_part " +
                    "(part_id, part_stock_id, work_order_id, actual_quantity_used) " +
                    "VALUES (" + parts.get(0).getPartId() + ", " + parts.get(0).getPartStockId() + ", " +
                    workOrderId + ", " + parts.get(0).getActualQuantityUsed() + ")");
            for (int i = 1; i < parts.size(); i++) {
                builderSQL.append(", (" + parts.get(i).getPartId() + ", " + parts.get(i).getPartStockId() + ", " +
                        workOrderId + ", " + parts.get(i).getActualQuantityUsed() + ")");
            }
            entityManager.createNativeQuery(builderSQL.toString()).executeUpdate();
        }
    }
}
