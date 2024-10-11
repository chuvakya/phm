package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.OccurrenceScoreScale;

@Repository

public interface OccurrenceScoreScaleRepository extends CrudRepository<OccurrenceScoreScale, String> {
    int countById(String id);
}
