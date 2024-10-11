package org.zs.phm3.service.maintenance.workorder;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.workorder.LaborTask;
import org.zs.phm3.models.maintenance.workorder.LaborTaskRequest;
import org.zs.phm3.repository.maintenance.workorder.LaborTaskRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Service
public class LaborTaskServiceImpl implements LaborTaskService {

    @Autowired
    private LaborTaskRepository laborTaskRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LaborTask save(LaborTask laborTask) {
        return laborTaskRepository.save(laborTask);
    }

    @Override
    public LaborTask getById(Long laborTaskId) {
        return laborTaskRepository.findById(laborTaskId).get();
    }

    @Override
    public void deleteByIdSQL(Long laborTaskId) {
        laborTaskRepository.deleteByIdSQL(laborTaskId);
    }

    @Override
    public void deleteAllByWorkOrderSQL(Long workOrderId) {
        laborTaskRepository.deleteAllByWorkOrderSQL(workOrderId);
    }

    @Transactional
    @Override
    public void addLaborTasks(List<LaborTaskRequest> laborTasks, Long workOrderId) {
        if (!laborTasks.isEmpty()) {
            StringBuilder builderSQL = new StringBuilder("INSERT INTO labor_task (completed_time, completion_notes, " +
                    "task_description, time_estimate, time_spent, unit_id, assigned_to_user_id, completed_by_user_id, " +
                    "work_order_id) VALUES (" + laborTasks.get(0).getCompletedTime() + ", " + get(laborTasks.get(0).getCompletionNotes()) +
                    ", " + get(laborTasks.get(0).getTaskDescription()) + ", " + laborTasks.get(0).getTimeEstimate() + ", " +
                    laborTasks.get(0).getTimeSpent() + ", " + get(PhmUtil.toShortUUID(laborTasks.get(0).getUnitId())) + ", " + laborTasks.get(0).getAssignedToUserId() +
                    ", " + laborTasks.get(0).getCompletedByUserId() + ", " + workOrderId + ")");
            for (int i = 1; i < laborTasks.size(); i++) {
                builderSQL.append(", (" + laborTasks.get(i).getCompletedTime() + ", " + get(laborTasks.get(i).getCompletionNotes()) +
                        ", " + get(laborTasks.get(i).getTaskDescription()) + ", " + laborTasks.get(i).getTimeEstimate() + ", " +
                                laborTasks.get(i).getTimeSpent() + ", " + get(laborTasks.get(i).getUnitId()) + ", " + laborTasks.get(i).getAssignedToUserId() +
                                ", " + laborTasks.get(i).getCompletedByUserId() + ", " + workOrderId + ")");
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
