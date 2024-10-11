package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureReason;

@Repository

public interface FailureReasonRepository extends CrudRepository<FailureReason, Integer> {
}
