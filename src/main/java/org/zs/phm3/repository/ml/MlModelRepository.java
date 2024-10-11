package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.MlModel;
import org.zs.phm3.models.ml.MlModelMetric;
import org.zs.phm3.models.ml.MlType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MlModelRepository extends CrudRepository<MlModel, Long> {

    @Query(value = "FROM MlModel m WHERE m.mlServiceState <> '' AND m.isStop = false")
    List<MlModel> getAllRunningModels();

    @Query(value = "FROM MlModel m WHERE m.mlServiceState = 'LAUNCHED'")
    List<MlModel> getAllRunningModelsForScheduler();

    @Query(value = "FROM MlModel m WHERE m.mlType = ?1 AND m.projectId = ?2")
    List<MlModel> getMlModelsByMlType(MlType mlType, Integer projectId);

    @Query(value = "FROM MlModel m WHERE m.projectId = ?1")
    List<MlModel> getMlModelsByProjectId(Integer projectId);

    @Query(value = "SELECT m.id FROM MlModel m WHERE m.mlServiceState <> '' AND m.isStop = false")
    List<List<Object>> getAllIds();

    @Query(value = "SELECT m.id, m.name AS model_name, m.ml_algorithm, ml_service_state, m.start_time, m.end_time, ue.name AS userName, u.name AS unit_name " +
            "FROM ml_model m " +
            "JOIN units u ON m.unit_id = u.id " +
            "JOIN user_entity ue ON m.modified_by_id = ue.id " +
            "WHERE m.project_id = ?1 ORDER BY u.name ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM ml_model WHERE project_id = ?1", nativeQuery = true)
    Integer getCountByProjectId(Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM ml_model WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long mlModelId);

    @Query(value = "FROM MlModelMetric WHERE mlModel.id = ?1 ORDER BY name")
    List<MlModelMetric> getMetricsByMlModelId(Long mlModelId);

    @Query(value = "SELECT * FROM ml_model m " +
            "JOIN model_schema ms ON m.model_schema_id = ms.id " +
            "WHERE ms.train_period IS NOT null AND (?1 - ms.train_period) > m.time_to_dataset", nativeQuery = true)
    List<MlModel> getModelForLearningByCurrentTime(Long currentTime);

    @Modifying
    @Query(value = "UPDATE ml_model SET is_stop = ?1, end_time = ?2, ml_service_state = ?3 " +
            "WHERE id IN ?4", nativeQuery = true)
    void setStopAndEndTimeAndMlServiceStateByModelIds(Boolean isStop, Long endTime, String mlServiceState,
                                                      List<Long> modelIds);

    @Modifying
    @Query(value = "UPDATE MlModel SET isStop = ?1, endTime = ?2, mlServiceState = ?3, startTime = ?5 " +
            "WHERE id IN ?4")
    void setStopAndEndTimeAndMlServiceStateAndStartTimeByModelIds(Boolean isStop, Long endTime, String mlServiceState,
                                                      List<Long> modelIds, Long startTime);
}
