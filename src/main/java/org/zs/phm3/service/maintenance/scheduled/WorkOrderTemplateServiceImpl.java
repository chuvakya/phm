package org.zs.phm3.service.maintenance.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.LaborTaskTemplate;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderPartTemplate;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;
import org.zs.phm3.repository.maintenance.scheduled.WorkOrderTemplateRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WorkOrderTemplateServiceImpl implements WorkOrderTemplateService {

    @Autowired
    private WorkOrderTemplateRepository workOrderTemplateRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WorkOrderTemplate save(WorkOrderTemplate workOrderTemplate) {
        return workOrderTemplateRepository.save(workOrderTemplate);
    }

    @Transactional
    @Override
    public void addLaborTasks(List<LaborTaskTemplate> laborTasks, Long workOrderTemplateId) {
        if (!laborTasks.isEmpty()) {
            StringBuilder builderSQL = new StringBuilder("INSERT INTO maintenance_labor_task_template (completed_time, completion_notes, " +
                    "task_description, time_estimate, time_spent, unit_id, assigned_to_user_id, completed_by_user_id, " +
                    "work_order_template_id) VALUES (" + laborTasks.get(0).getCompletedTime() + ", " + get(laborTasks.get(0).getCompletionNotes()) +
                    ", " + get(laborTasks.get(0).getTaskDescription()) + ", " + laborTasks.get(0).getTimeEstimate() + ", " +
                    laborTasks.get(0).getTimeSpent() + ", " + get(PhmUtil.toShortUUID(laborTasks.get(0).getUnitId())) + ", " + laborTasks.get(0).getAssignedToUserId() +
                    ", " + laborTasks.get(0).getCompletedByUserId() + ", " + workOrderTemplateId + ")");
            for (int i = 1; i < laborTasks.size(); i++) {
                builderSQL.append(", (" + laborTasks.get(i).getCompletedTime() + ", " + get(laborTasks.get(i).getCompletionNotes()) +
                        ", " + get(laborTasks.get(i).getTaskDescription()) + ", " + laborTasks.get(i).getTimeEstimate() + ", " +
                        laborTasks.get(i).getTimeSpent() + ", " + get(laborTasks.get(i).getUnitId()) + ", " + laborTasks.get(i).getAssignedToUserId() +
                        ", " + laborTasks.get(i).getCompletedByUserId() + ", " + workOrderTemplateId + ")");
            }
            entityManager.createNativeQuery(builderSQL.toString()).executeUpdate();
        }
    }

    @Transactional
    @Override
    public void addWorkOrderParts(List<WorkOrderPartTemplate> parts, Long workOrderTemplateId) {
        if (!parts.isEmpty()) {
            StringBuilder builderSQL = new StringBuilder("INSERT INTO maintenance_work_order_part_template " +
                    "(part_id, part_stock_id, work_order_template_id, actual_quantity_used) " +
                    "VALUES (" + parts.get(0).getPartId() + ", " + parts.get(0).getPartStockId() + ", " +
                    workOrderTemplateId + ", " + parts.get(0).getActualQuantityUsed() + ")");
            for (int i = 1; i < parts.size(); i++) {
                builderSQL.append(", (" + parts.get(i).getPartId() + ", " + parts.get(i).getPartStockId() + ", " +
                        workOrderTemplateId + ", " + parts.get(i).getActualQuantityUsed() + ")");
            }
            entityManager.createNativeQuery(builderSQL.toString()).executeUpdate();
        }
    }

    public String get(Object object) {
        if (object == null) {
            return "null";
        } else {
            return "'" + object.toString().replaceAll("'", "''") + "'";
        }
    }
}
