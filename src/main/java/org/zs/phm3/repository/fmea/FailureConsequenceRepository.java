package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureConsequence;

@Repository

public interface FailureConsequenceRepository extends CrudRepository<FailureConsequence, Integer> {
}
