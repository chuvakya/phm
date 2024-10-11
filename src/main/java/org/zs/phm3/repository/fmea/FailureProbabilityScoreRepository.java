package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureProbabilityScore;

@Repository

public interface FailureProbabilityScoreRepository extends CrudRepository<FailureProbabilityScore, String> {
    int countById(String id);
}
