package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.MlModel;
import org.zs.phm3.models.ml.MlModelMetric;
import org.zs.phm3.models.ml.MlType;

import java.util.List;

/**
 * Interface MlModelService
 * @author Pavel Chuvak
 */
public interface MlModelService {

    /**
     * Saving ml model
     * @param mlModel ml model
     */
    void save(MlModel mlModel);

    /**
     * Getting all ml models
     * @return all ml models
     */
    List<MlModel> getAll();

    /**
     * Deleting ml model by ml model ID
     * @param id ml model ID
     */
    void delete(Long id);

    /**
     * Getting ml model by ml model ID
     * @param id ml model ID
     * @return ml model
     */
    MlModel getById(Long id);

    /**
     * Getting running ml models
     * @return running ml models
     */
    List<MlModel> getAllRunningModels();

    /**
     * Getting all bind ml models
     * @param projectId project ID
     * @return all bind ml models
     */
    List<MlModel> getAllBindModels(Integer projectId);

    /**
     * Getting json string ml models by project ID
     * @param projectId project ID
     * @return json string ml models
     */
    String getListModels(Integer projectId);

    /**
     * Getting json string bind ml models by project ID
     * @param projectId project ID
     * @return json string bind ml models
     */
    String getListBindModels(Integer projectId);

    /**
     * Getting list ml models by ml type and project ID
     * @param mlType ml type
     * @param projectId project ID
     * @return list ml models
     */
    List<MlModel> getMlModelsByMlType(MlType mlType, Integer projectId);

    /**
     * Getting list ml models for scheduler
     * @return list ml models for scheduler
     */
    List<MlModel> getAllRunningModelsForScheduler();

    /**
     * Getting all IDs
     * @return all IDs
     */
    List<Long> getAllIds();

    /**
     * Getting json string all ml models by offset, limit and project ID
     * @param projectId project ID
     * @param offset offset
     * @param limit limit
     * @return json string all ml models
     */
    String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    /**
     * Getting count ml models by project ID
     * @param projectId project ID
     * @return count ml models
     */
    Integer getCountByProjectId(Integer projectId);

    /**
     * Deleting ml models by ml model ID
     * @param mlModelId ml model ID
     */
    void deleteByIdSQL(Long mlModelId);

    /**
     * Getting ml model metrics by ml model ID
     * @param mlModelId ml model ID
     * @return ml model metrics
     */
    List<MlModelMetric> getMetricsByMlModelId(Long mlModelId);

    void setStopAndEndTimeAndMlServiceStateByModelIds(Boolean isStop, Long endTime, String mlServiceState,
                                                      List<Long> modelIds);
    void setStopAndEndTimeAndMlServiceStateAndStartTimeByModelIds(Boolean isStop, Long endTime, String mlServiceState,
                                                                  List<Long> modelIds, Long startTime);
}
