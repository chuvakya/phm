package org.zs.phm3.service.maintenance;

import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.OperationTypeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OperationTypeService {
    OperationTypeEntity save(OperationTypeEntity operationForSave) throws PhmException, NotFoundException, JsonProcessingException;

    OperationTypeEntity findOperationById(Integer operationId) throws PhmException;

    List<OperationTypeEntity> getAllOperations();

    void deleteById(Integer operationId);
}
