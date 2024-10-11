package org.zs.phm3.failure;

public interface FailureService {
    FailureEntity save(FailureEntity failureEntity);
    Iterable<FailureEntity> findAll();
    FailureEntity findById(Long id);
    void delete(Long id);
    Iterable<FailureEntity> findByUnitId(String unitId);
    Iterable<FailureEntity> findAllUnrelatedFailures();
    void deleteAllByUnitId(String unitId);
}
