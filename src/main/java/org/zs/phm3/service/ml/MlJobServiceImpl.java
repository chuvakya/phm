package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.*;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.repository.ml.MlJobRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementing MlJobService class
 * @author Pavel Chuvak
 */
@Service
public class MlJobServiceImpl implements MlJobService {

    /** Ml job repository */
    @Autowired
    private MlJobRepository mlJobRepository;

    /**
     * Getting ml job by ml job ID
     * @param id ml job ID
     * @return ml job
     */
    @Override
    public MlJob getById(Long id) {
        return mlJobRepository.findById(id).get();
    }

    /**
     * Getting all ml jobs
     * @return all ml jobs
     */
    @Override
    public List<MlJob> getAll() {
        return (List<MlJob>) mlJobRepository.findAll();
    }

    /**
     * Getting json string all ml jobs by project ID
     * @param projectId project ID
     * @return json string all ml jobs
     */
    @Override
    public String getAllForUI(Integer projectId) {
        JSONArray jsonArray = new JSONArray();
        for (MlJob mlJob : mlJobRepository.getAllByProjectId(projectId)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", mlJob.getId());
            jsonObject.put("name", mlJob.getName());
            jsonObject.put("createdTime", mlJob.getCreateTime());
            jsonObject.put("algorithmCode", mlJob.getModelSchema().getMlAlgorithm().toString());
            jsonObject.put("modifiedBy", mlJob.getModifiedBy());
            jsonObject.put("taskType", MlAlgorithmType.getType(mlJob.getModelSchema().getMlAlgorithm()));
            jsonObject.put("algorithm", MlAlgorithmType.getAlgorithm(mlJob.getModelSchema().getMlAlgorithm()));
            if (mlJob.getElapsedTime() == null) {
                jsonObject.put("elapsedTime", "");
            } else {
                jsonObject.put("elapsedTime", mlJob.getElapsedTime());
            }
            jsonObject.put("state", mlJob.getState());
            if (mlJob.getResult() == null) {
                jsonObject.put("result", "");
            } else {
                jsonObject.put("result", mlJob.getResult());
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Deleting ml job by ml job ID
     * @param id ml job ID
     */
    @Override
    public void deleteById(Long id) {
        mlJobRepository.deleteById(id);
    }

    /**
     * Saving ml job
     * @param mlJob ml job
     * @return ml job
     */
    @Override
    public MlJob save(MlJob mlJob) {
        return mlJobRepository.save(mlJob);
    }

    @Override
    public String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        List<List<Object>> lists = mlJobRepository.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mlJobId", list.get(0));
            jsonObject.put("mlJobName", list.get(1));
            jsonObject.put("state", list.get(2));
            jsonObject.put("modifiedBy", list.get(3));
            jsonObject.put("createTime", list.get(4));
            jsonObject.put("elapsedTime", list.get(5));
            jsonObject.put("taskType", MlAlgorithmType.getType(MlAlgorithm.valueOf((String) list.get(6))));
            jsonObject.put("algorithm", MlAlgorithmType.getAlgorithm(MlAlgorithm.valueOf((String) list.get(6))));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting count ml jobs by project ID
     * @param projectId project ID
     * @return count ml jobs
     */
    @Override
    public Integer getCountByProjectId(Integer projectId) {
        return mlJobRepository.getCountByProjectId(projectId);
    }

    /**
     * Deleting ml job by ml job ID
     * @param mlJobId ml job ID
     */
    @Override
    public void deleteByIdSQL(Long mlJobId) {
        mlJobRepository.deleteByIdSQL(mlJobId);
    }

    /**
     * Create ml job and add to queue
     * @param userName user name
     * @param modelSchema model schema
     */
    @Override
    public void createJobAndAddToQueue(UserEntity userName, ModelSchema modelSchema) {
        MlJob mlJob = new MlJob(modelSchema.getName(), userName, modelSchema);
        mlJob.setCreateTime(System.currentTimeMillis());
        if (MlJobQueued.getMlJobQueue().isEmpty()) {
            mlJob.setState("RUNNING");
        } else {
            mlJob.setState("QUEUED");
        }
        mlJobRepository.save(mlJob);
        MlJobQueued.getMlJobQueue().addLast(mlJob);
    }

}
