package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.*;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.ml.MlModelRepository;
import org.zs.phm3.service.unit.UnitService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing MlModelService class
 * @author Pavel Chuvak
 */
@Service
public class MlModelServiceImpl implements MlModelService {

    /** Ml model repository */
    @Autowired
    private MlModelRepository mlModelRepository;

    /** Unit service */
    @Autowired
    private UnitService unitService;

    /**
     * Saving ml model
     * @param mlModel ml model
     */
    @Override
    public void save(MlModel mlModel) {
        mlModelRepository.save(mlModel);
    }

    /**
     * Getting all ml models
     * @return all ml models
     */
    @Override
    public List<MlModel> getAll() {
        return (List<MlModel>) mlModelRepository.findAll();
    }

    /**
     * Deleting ml model by ml model ID
     * @param id ml model ID
     */
    @Override
    public void delete(Long id) {
        mlModelRepository.deleteById(id);
    }

    /**
     * Getting ml model by ml model ID
     * @param id ml model ID
     * @return ml model
     */
    @Override
    public MlModel getById(Long id) {
        return mlModelRepository.findById(id).get();
    }

    /**
     * Getting running ml models
     * @return running ml models
     */
    @Override
    public List<MlModel> getAllRunningModels() {
        return mlModelRepository.getAllRunningModels();
    }

    /**
     * Getting all bind ml models
     * @param projectId project ID
     * @return all bind ml models
     */
    @Override
    public List<MlModel> getAllBindModels(Integer projectId) {
        return mlModelRepository.getMlModelsByProjectId(projectId);
    }

    /**
     * Getting json string ml models by project ID
     * @param projectId project ID
     * @return json string ml models
     */
    @Override
    public String getListModels(Integer projectId) {
        List<MlModel> mlModels = (List<MlModel>) mlModelRepository.getMlModelsByProjectId(projectId);
        JSONArray jsonArray = new JSONArray();
        for (MlModel mlModel : mlModels) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("modelName", mlModel.getName());
            jsonObject.put("taskType", MlAlgorithmType.getType(mlModel.getMlAlgorithm()));
            jsonObject.put("algorithm", MlAlgorithmType.getAlgorithm(mlModel.getMlAlgorithm()));
            jsonObject.put("algorithmCode", mlModel.getMlAlgorithm().toString());
            jsonObject.put("algorithmFullName", MlAlgorithmType.getFullNameAlgorithm(mlModel.getMlAlgorithm()));
            jsonObject.put("modifiedBy", "Pavel Chuvak");
            jsonObject.put("modelId", mlModel.getId());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string bind ml models by project ID
     * @param projectId project ID
     * @return json string bind ml models
     */
    @Override
    public String getListBindModels(Integer projectId) {
        List<MlModel> mlModels = getAllBindModels(projectId);
        JSONArray jsonArray = new JSONArray();
        for (MlModel mlModel : mlModels) {
            jsonArray.add(getJSONObjectForMlModel(mlModel, unitService.getUnitById(mlModel.getDataSchema().getUnitId())));
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting list ml models by ml type and project ID
     * @param mlType ml type
     * @param projectId project ID
     * @return list ml models
     */
    @Override
    public List<MlModel> getMlModelsByMlType(MlType mlType, Integer projectId) {
        return mlModelRepository.getMlModelsByMlType(mlType, projectId);
    }

    /**
     * Getting list ml models for scheduler
     * @return list ml models for scheduler
     */
    @Override
    public List<MlModel> getAllRunningModelsForScheduler() {
        return mlModelRepository.getAllRunningModelsForScheduler();
    }

    /**
     * Getting all IDs
     * @return all IDs
     */
    @Override
    public List<Long> getAllIds() {
        List<List<Object>> ids = mlModelRepository.getAllIds();
        List<Long> idsLong = new ArrayList<>();
        for (List<Object> id : ids) {
            idsLong.add((Long) id.get(0));
        }
        return idsLong;
    }

    /**
     * Getting json string all ml models by offset, limit and project ID
     * @param projectId project ID
     * @param offset offset
     * @param limit limit
     * @return json string all ml models
     */
    @Override
    public String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        List<List<Object>> lists = mlModelRepository.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mlModelId", list.get(0));
            jsonObject.put("mlModelName", list.get(1));
            jsonObject.put("taskType", MlAlgorithmType.getType(MlAlgorithm.valueOf((String) list.get(2))));
            jsonObject.put("algorithm", MlAlgorithmType.getAlgorithm(MlAlgorithm.valueOf((String) list.get(2))));
            jsonObject.put("state", list.get(3));
            jsonObject.put("startTime", list.get(4));
            jsonObject.put("endTime", list.get(5));
            jsonObject.put("modifiedBy", list.get(6));
            jsonObject.put("unitName", list.get(7));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting count ml models by project ID
     * @param projectId project ID
     * @return count ml models
     */
    @Override
    public Integer getCountByProjectId(Integer projectId) {
        return mlModelRepository.getCountByProjectId(projectId);
    }

    /**
     * Deleting ml models by ml model ID
     * @param mlModelId ml model ID
     */
    @Override
    public void deleteByIdSQL(Long mlModelId) {
        mlModelRepository.deleteByIdSQL(mlModelId);
    }

    /**
     * Getting ml model metrics by ml model ID
     * @param mlModelId ml model ID
     * @return ml model metrics
     */
    @Override
    public List<MlModelMetric> getMetricsByMlModelId(Long mlModelId) {
        return mlModelRepository.getMetricsByMlModelId(mlModelId);
    }

    @Override
    public void setStopAndEndTimeAndMlServiceStateByModelIds(Boolean isStop, Long endTime, String mlServiceState,
                                                             List<Long> modelIds) {
        mlModelRepository.setStopAndEndTimeAndMlServiceStateByModelIds(isStop, endTime, mlServiceState, modelIds);
    }

    @Override
    public void setStopAndEndTimeAndMlServiceStateAndStartTimeByModelIds(Boolean isStop, Long endTime,
                                                                         String mlServiceState, List<Long> modelIds,
                                                                         Long startTime) {
        mlModelRepository.setStopAndEndTimeAndMlServiceStateAndStartTimeByModelIds(isStop, endTime, mlServiceState,
                modelIds, startTime);
    }

    /**
     * Getting JSONObject for ml model by ml model and unit
     * @param mlModel ml model
     * @param unit unit
     * @return JSONObject
     */
    private JSONObject getJSONObjectForMlModel(MlModel mlModel, UnitEntity unit) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mlModelId", mlModel.getId());
        jsonObject.put("mlModelName", mlModel.getName());
        jsonObject.put("unitName", unit.getName());
        jsonObject.put("unitId", unit.getId().toString());
        if (mlModel.getMlServiceState().equals("LAUNCHED")) {
            jsonObject.put("state", "LAUNCHED");
        } else if (mlModel.getMlServiceState().equals("")) {
            jsonObject.put("state", "");
        } else {
            jsonObject.put("state", "STOPPED");
        }
        jsonObject.put("taskType", MlAlgorithmType.getType(mlModel.getMlAlgorithm()));
        jsonObject.put("algorithm", MlAlgorithmType.getAlgorithm(mlModel.getMlAlgorithm()));
        jsonObject.put("algorithmCode", mlModel.getMlAlgorithm().toString());
        jsonObject.put("algorithmFullName", MlAlgorithmType.getFullNameAlgorithm(mlModel.getMlAlgorithm()));
        jsonObject.put("modifiedBy", "Pavel Chuvak");

        if (mlModel.getStartTime() == null) {
            jsonObject.put("startTime", "");
        } else {
            jsonObject.put("startTime", mlModel.getStartTime());
        }
        if (mlModel.getEndTime() == null || mlModel.getEndTime() == 0) {
            jsonObject.put("endTime", "");
        } else {
            jsonObject.put("endTime", mlModel.getEndTime());
        }
        return jsonObject;
    }
}
