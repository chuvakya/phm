package org.zs.phm3.service.maintenance;

import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.OperationTypeEntity;
import org.zs.phm3.repository.maintenance.OperationTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationTypeServiceImpl implements OperationTypeService {
    @Autowired
    OperationTypeRepository operationTypeRepository;

    @Override
    public OperationTypeEntity save(OperationTypeEntity operationForSave) throws PhmException, NotFoundException, JsonProcessingException {
        return operationTypeRepository.save(operationForSave);
    }

    @Override
    public OperationTypeEntity findOperationById(Integer operationId) throws PhmException {
        return operationTypeRepository.findById(operationId)
                .orElseThrow(() -> (new PhmException("Operation with id " + operationId +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public List<OperationTypeEntity> getAllOperations() {
        return (List<OperationTypeEntity>) operationTypeRepository.findAll();
    }

    @Override
    public void deleteById(Integer operationId) {
        operationTypeRepository.deleteById(operationId);
    }
}
