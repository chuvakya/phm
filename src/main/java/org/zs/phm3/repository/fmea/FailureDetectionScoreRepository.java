package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureDetectionScore;

@Repository

public interface FailureDetectionScoreRepository extends CrudRepository<FailureDetectionScore, String> {
    int countById(String id);
}
