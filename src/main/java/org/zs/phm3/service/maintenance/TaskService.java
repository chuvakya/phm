package org.zs.phm3.service.maintenance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.RegisterEntity;
import org.zs.phm3.models.maintenance.TaskHeader;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    void createPlannedTasks() throws PhmException;

    void createPlannedTasksManually(String unitId, LocalDateTime plannedDateOperationStart,
                                    String comment) throws PhmException;

    void TaskRecalculateFailure(String unitId, String comment) throws PhmException;

//    void TaskRecalculateFailureManually(String unitId, String comment) throws PhmException;

    TaskHeader CloseTask(Integer registerId, LocalDateTime actualDateOperationStart,
                   LocalDateTime actualDateOperationEnd, Integer operationActualTypeId,String comment) throws PhmException;

    void recalculateTaskFromML(String unitId, LocalDateTime recalculatedDateStart) throws PhmException, NotFoundException, JsonProcessingException;

    TaskHeader findById(Integer taskId) throws PhmException;

    List<TaskHeader> getAll();

    void deleteById(Integer taskId);

    TaskHeader findByUnitId(String unitId);
}
