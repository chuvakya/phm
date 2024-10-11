package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.SignificanceScoreScale;

@Repository

public interface SignificanceScoreScaleRepository extends CrudRepository<SignificanceScoreScale, String> {
    int countById(String id);
}
