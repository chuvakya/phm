package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureConsequenceScore;

@Repository

public interface FailureConsequenceScoreRepository extends CrudRepository<FailureConsequenceScore, String> {
    int countById(String id);
}
