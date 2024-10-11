package org.zs.phm3.failure.failureid;

public interface FailureIdService {
    FailureIdEntity save(FailureIdEntity failureIdEntity);
    Iterable<FailureIdEntity> findAll();
    FailureIdEntity findById(Integer id);
    void delete(Integer id);
}
