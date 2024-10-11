package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DataSchema;

import java.util.List;

/**
 * Interface DataSchemaService
 * @author Pavel Chuvak
 */
public interface DataSchemaService {

    /**
     * Getting data schema by data schema ID
     * @param id data schema ID
     * @return data schema
     */
    DataSchema getById(Long id);

    /**
     * Saving data schema
     * @param dataSchema data schema
     * @return data schema
     */
    DataSchema save(DataSchema dataSchema);

    /**
     * Getting list all data schemas by project ID
     * @param projectId project ID
     * @return list data schemas
     */
    List<DataSchema> getAllDataSchemasByProject(Integer projectId);

    /**
     * Deleting data schema by data schema ID
     * @param dataSchemaId data schema ID
     */
    void deleteById(Long dataSchemaId);

    /**
     * Getting json string all id and name by unit ID
     * @param unitId unit ID
     * @return json string all id and name
     */
    String getAllIdAndNameByUnitId(String unitId);

    /**
     * Getting json string data schema preview by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string data schema preview
     */
    public String getDataSchemaPreview(Long dataSchemaId);

    /**
     * Getting full period for data schema by ts kv attribute ID
     * @param attributeId ts kv attribute ID
     * @return full period for data schema
     */
    String getFullPeriodForDataSchema(Long attributeId);

    /**
     * Getting max period for data schema
     * @param dataSchema data schema
     * @return json string
     */
    String getMaxPeriodForDataSchema(DataSchema dataSchema);

    /**
     * Getting dataset preview by data schema ID and count values
     * @param dataSchemaId data schema ID
     * @param n count values
     * @return json string
     */
    String getDatasetPreview(Long dataSchemaId, Long n);

    /**
     * Getting count data schemas by project ID
     * @param projectId project ID
     * @return count
     */
    Integer getCountByProjectId(Integer projectId);

    /**
     * Getting all by offset and limit and project ID
     * @param offset offset
     * @param limit limit
     * @param projectId project ID
     * @return json string
     */
    String getByOffsetAndLimitAndProjectId(Integer offset, Integer limit, Integer projectId);

    /**
     * Deleting data schema by data schame ID
     * @param dataSchemaId data schema ID
     */
    void deleteByIdSQL(Long dataSchemaId);

    /**
     * Getting save data schema by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string
     */
    String getSaveDataSchemaById(Long dataSchemaId);

    /**
     * Deleting dataset param by data schema ID
     * @param dataSchemaId data schema ID
     */
    void deleteDatasetParamByDataSchemaId(Long dataSchemaId);

    /**
     * Getting name for new data schema by project ID
     * @param projectId project ID
     * @return name
     */
    String getNameForNewDataSchema(Integer projectId);

    /**
     * Getting all data schemas by data schema name and project ID
     * @param name data schema name
     * @param projectId project ID
     * @return all data schemas
     */
    List<DataSchema> getAllByNameAndProjectId(String name, Integer projectId);
}
