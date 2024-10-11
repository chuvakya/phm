package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.MlAlgorithm;
import org.zs.phm3.models.ml.MlAlgorithmType;
import org.zs.phm3.models.ml.ModelSchema;
import org.zs.phm3.models.ml.ModelSchemaRegistry;
import org.zs.phm3.repository.ml.ModelSchemaRegistryRepository;
import org.zs.phm3.repository.ml.ModelSchemaRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.List;

/**
 * Implementing ModelSchemaService class
 * @author Pavel Chuvak
 */
@Service
public class ModelSchemaServiceImpl implements ModelSchemaService {

    /** Model schema repository */
    @Autowired
    private ModelSchemaRepository modelSchemaRepository;

    /** Model schema registry repository */
    @Autowired
    private ModelSchemaRegistryRepository modelSchemaRegistryRepository;

    /**
     * Saving model schema
     * @param modelSchema model schema
     * @return model schema
     */
    @Override
    public ModelSchema save(ModelSchema modelSchema) {
        return modelSchemaRepository.save(modelSchema);
    }

    /**
     * Getting model schema by model schema ID
     * @param id model schema ID
     * @return model schema
     */
    @Override
    public ModelSchema getById(Long id) {
        return modelSchemaRepository.findById(id).get();
    }

    /**
     * Getting json string all model schemas by project ID, offset and limit
     * @param projectId project ID
     * @param offset offset
     * @param limit limit
     * @return json string all model schemas
     */
    @Override
    public String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        List<List<Object>> modelSchemas = modelSchemaRepository.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> modelSchema : modelSchemas) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("modelSchemaId", modelSchema.get(0));
            jsonObject.put("modelSchemaName", modelSchema.get(3));
            jsonObject.put("createTime", modelSchema.get(1));
            jsonObject.put("modifiedBy", modelSchema.get(4));
            jsonObject.put("taskType", MlAlgorithmType.getType(MlAlgorithm.valueOf((String) modelSchema.get(2))));
            jsonObject.put("algorithm", MlAlgorithmType.getAlgorithm(MlAlgorithm.valueOf((String) modelSchema.get(2))));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting count model schemas by project ID
     * @param projectId project ID
     * @return count model schemas
     */
    @Override
    public Integer getCountByProjectId(Integer projectId) {
        return modelSchemaRepository.getCountByProjectId(projectId);
    }

    /**
     * Deleting model schema by model schema ID
     * @param modelSchemaId model schema ID
     */
    @Override
    public void deleteByIdSQL(Long modelSchemaId) {
        modelSchemaRepository.deleteByIdSQL(modelSchemaId);
    }

    /**
     * Getting model schema name for new model schema by project ID
     * @param projectId project ID
     * @return model schema name for new model schema
     */
    @Override
    public String getNameForNewModelSchema(Integer projectId) {
        Long count = modelSchemaRegistryRepository.getSizeByProjectId(projectId) + 1;
        ModelSchemaRegistry modelSchemaRegistry = new ModelSchemaRegistry("ML Schema " + count.toString(), projectId);
        modelSchemaRegistryRepository.save(modelSchemaRegistry);
        return modelSchemaRegistry.getName();
    }

    /**
     * Getting all model schemas by model schema name and project ID
     * @param name model schema name
     * @param projectId project ID
     * @return all model schemas
     */
    @Override
    public List<ModelSchema> getAllByNameAndProjectId(String name, Integer projectId) {
        return modelSchemaRepository.getAllByNameAndProjectId(name, projectId);
    }

    /**
     * Getting json string model schema by model schema ID
     * @param modelSchemaId model schema ID
     * @return json string model schema
     */
    @Override
    public String getByIdJSON(Long modelSchemaId) {
        List<List<Object>> lists = modelSchemaRepository.getById(modelSchemaId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", lists.get(0).get(0));
        jsonObject.put("mlAlgorithm", lists.get(0).get(1).toString());
        jsonObject.put("name", lists.get(0).get(2));
        jsonObject.put("windowLength", lists.get(0).get(3));
        jsonObject.put("unitId", PhmUtil.toLongUUID(lists.get(0).get(4).toString()));
        jsonObject.put("dataSchemaId", lists.get(0).get(5));
        jsonObject.put("datasetId", lists.get(0).get(6));
        if (lists.get(0).get(7) != null) {
            jsonObject.put("bitAttributeName", lists.get(0).get(7));
            jsonObject.put("errorCode", lists.get(0).get(8).toString() + " - " + lists.get(0).get(9));
        }
        jsonObject.put("mlTaskType", MlAlgorithmType.getTypeFromAlgorithm(
                MlAlgorithm.valueOf(lists.get(0).get(1).toString())).toString());
        return jsonObject.toJSONString();
    }
}
