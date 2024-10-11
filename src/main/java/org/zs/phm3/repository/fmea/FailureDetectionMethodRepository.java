package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureDetectionMethod;
import org.zs.phm3.models.maintenance.OperationTypeEntity;

@Repository

public interface FailureDetectionMethodRepository extends CrudRepository<FailureDetectionMethod, Integer> {
}
