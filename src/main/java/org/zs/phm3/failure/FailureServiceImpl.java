package org.zs.phm3.failure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FailureServiceImpl implements FailureService{

    @Autowired
    FailureRepository failureRepository;

    @Override
    public FailureEntity save(FailureEntity failureEntity) {
        return failureRepository.save(failureEntity);
    }

    @Override
    public Iterable<FailureEntity> findAll() {
        return failureRepository.findAll();
    }

    @Override
    public FailureEntity findById(Long id) {
        return failureRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        failureRepository.deleteById(id);
    }

    @Override
    public Iterable<FailureEntity> findByUnitId(String unitId){
        return failureRepository.findAllByUnitId(unitId);
    }

    @Override
    public Iterable<FailureEntity> findAllUnrelatedFailures() {
        return failureRepository.findAllByUnitIdIsNull();
    }

    @Override
    public void deleteAllByUnitId(String unitId) {
        failureRepository.deleteAllByUnitId(unitId);
    }
}
