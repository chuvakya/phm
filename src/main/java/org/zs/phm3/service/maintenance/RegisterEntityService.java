package org.zs.phm3.service.maintenance;

import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.RegisterEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;
import java.util.List;

public interface RegisterEntityService {
//    RegisterEntity save(RegisterEntity registerEntityForSave) throws PhmException, NotFoundException, JsonProcessingException;

//    RegisterEntity createNew(String unitId, OperationType typePlaned,
//                             LocalDateTime plannedDateOperationStart) throws PhmException;

    void createPlannedTasks() throws PhmException;

    void createPlannedTasksManually(String unitId, LocalDateTime plannedDateOperationStart,
                                    String comment) throws PhmException;

    void TaskRecalculateFailure(String unitId, String comment) throws PhmException;

    void TaskRecalculateFailureManually(String unitId, String comment) throws PhmException;

    void CloseTask(Integer registerId, LocalDateTime actualDateOperationStart,
                   LocalDateTime actualDateOperationEnd, Integer operationActualTypeId,String comment) throws PhmException;

    RegisterEntity TaskRecalculateML(Integer registerId, LocalDateTime newTime) throws PhmException, NotFoundException, JsonProcessingException;

    RegisterEntity findById(Integer registerId) throws PhmException;

    List<RegisterEntity> getAll();

    void deleteById(Integer registerId);

}
