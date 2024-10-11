package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.MlJob;
import org.zs.phm3.models.ml.ModelSchema;
import org.zs.phm3.models.user.UserEntity;

import java.util.List;

/**
 * Interface MlJobService
 * @author Pavel Chuvak
 */
public interface MlJobService {

    /**
     * Getting ml job by ml job ID
     * @param id ml job ID
     * @return ml job
     */
    MlJob getById(Long id);

    /**
     * Getting all ml jobs
     * @return all ml jobs
     */
    List<MlJob> getAll();

    /**
     * Getting json string all ml jobs by project ID
     * @param projectId project ID
     * @return json string all ml jobs
     */
    String getAllForUI(Integer projectId);

    /**
     * Deleting ml job by ml job ID
     * @param id ml job ID
     */
    void deleteById(Long id);

    /**
     * Saving ml job
     * @param mlJob ml job
     * @return ml job
     */
    MlJob save(MlJob mlJob);
    String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    /**
     * Getting count ml jobs by project ID
     * @param projectId project ID
     * @return count ml jobs
     */
    Integer getCountByProjectId(Integer projectId);

    /**
     * Deleting ml job by ml job ID
     * @param mlJobId ml job ID
     */
    void deleteByIdSQL(Long mlJobId);

    /**
     * Create ml job and add to queue
     * @param userName user name
     * @param modelSchema model schema
     */
    void createJobAndAddToQueue(UserEntity userName, ModelSchema modelSchema);
}
