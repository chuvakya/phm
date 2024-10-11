package org.zs.phm3.failure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FailureRepository extends CrudRepository<FailureEntity, Long> {
    Iterable<FailureEntity> findAllByUnitId(String unitId);
    Iterable<FailureEntity> findAllByUnitIdIsNull();
    void deleteAllByUnitId(String unitId);
}
