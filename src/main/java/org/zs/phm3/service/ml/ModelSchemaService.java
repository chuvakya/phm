package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.ModelSchema;

import java.util.List;

/**
 * Interface ModelSchemaService
 * @author Pavel Chuvak
 */
public interface ModelSchemaService {

    /**
     * Saving model schema
     * @param modelSchema model schema
     * @return model schema
     */
    ModelSchema save(ModelSchema modelSchema);

    /**
     * Getting model schema by model schema ID
     * @param id model schema ID
     * @return model schema
     */
    ModelSchema getById(Long id);

    /**
     * Getting json string all model schemas by project ID, offset and limit
     * @param projectId project ID
     * @param offset offset
     * @param limit limit
     * @return json string all model schemas
     */
    String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    /**
     * Getting count model schemas by project ID
     * @param projectId project ID
     * @return count model schemas
     */
    Integer getCountByProjectId(Integer projectId);

    /**
     * Deleting model schema by model schema ID
     * @param modelSchemaId model schema ID
     */
    void deleteByIdSQL(Long modelSchemaId);

    /**
     * Getting model schema name for new model schema by project ID
     * @param projectId project ID
     * @return model schema name for new model schema
     */
    String getNameForNewModelSchema(Integer projectId);

    /**
     * Getting all model schemas by model schema name and project ID
     * @param name model schema name
     * @param projectId project ID
     * @return all model schemas
     */
    List<ModelSchema> getAllByNameAndProjectId(String name, Integer projectId);

    /**
     * Getting json string model schema by model schema ID
     * @param modelSchemaId model schema ID
     * @return json string model schema
     */
    String getByIdJSON(Long modelSchemaId);
}
