package org.zs.phm3.service.maintenance.workorder;

import org.zs.phm3.models.maintenance.workorder.LaborTask;
import org.zs.phm3.models.maintenance.workorder.LaborTaskRequest;

import java.sql.SQLException;
import java.util.List;

public interface LaborTaskService {
    LaborTask save(LaborTask laborTask);
    LaborTask getById(Long laborTaskId);
    void deleteByIdSQL(Long laborTaskId);
    void deleteAllByWorkOrderSQL(Long workOrderId);
    void addLaborTasks(List<LaborTaskRequest> laborTaskRequests, Long workOrderId);

}
