package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.MlServiceResult;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MlServiceResultRepository extends CrudRepository<MlServiceResult, Long> {

    @Query(value = "SELECT * FROM ml_service_result WHERE ml_model_id=?1 AND unit_id=?2 ORDER BY time DESC LIMIT 1", nativeQuery = true)
    MlServiceResult getLastServiceResultForUnitAndMlModel(Long mlModelId, String unitId);

    @Query(value = "SELECT * FROM ml_service_result WHERE unit_id=?1 AND ml_model_id=?2 ORDER BY time ASC", nativeQuery = true)
    List<MlServiceResult> getAllForUnitAndMlModel(String unitId, Long mlModel);

    @Modifying
    @Query(value = "DELETE FROM ml_service_result WHERE unit_id=?1", nativeQuery = true)
    void deleteByUnit(String unitId);
}
