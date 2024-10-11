package org.zs.phm3.repository.fmea;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zs.phm3.models.fmea.DetectionScoreScale;

public interface DetectionScoreScaleRepository extends CrudRepository<DetectionScoreScale, String> {
    int countById(String id);
}
