package org.zs.phm3.failure.failureid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FailureIdRepository extends CrudRepository<FailureIdEntity, Integer> {

}
