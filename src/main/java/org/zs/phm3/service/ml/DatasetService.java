package org.zs.phm3.service.ml;

import org.springframework.data.jpa.repository.Query;
import org.zs.phm3.models.ml.Dataset;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Interface DatasetService
 * @author Pavel Chuvak
 */
public interface DatasetService {

    /**
     * Saving dataset
     * @param dataset dataset
     * @return dataset
     */
    Dataset save(Dataset dataset);

    /**
     * Getting all datasets
     * @return all datasets
     */
    List<Dataset> getAll();

    /**
     * Getting dataset by dataset ID
     * @param id dataset ID
     * @return dataset
     */
    Dataset getById(Long id);

    /**
     * Deleting dataset by dataset ID
     * @param id dataset ID
     */
    void deleteById(Long id);

    /**
     * Getting all datasets by project ID
     * @param projectId project ID
     * @return all datasets
     */
    List<Dataset> getAllByProjectId(Integer projectId);
    String viewDataset(Long datasetId, Long n);

    /**
     * Create and validation dataset
     * @param dataset dataset
     * @return dataset
     * @throws IOException
     * @throws ParseException
     */
    Dataset createDataset(Dataset dataset) throws IOException, ParseException;

    /**
     * Getting all datasets by name and project ID
     * @param name dataset name
     * @param projectId project ID
     * @return all datasets
     */
    List<Dataset> getAllByNameAndProjectId(String name, Integer projectId);

    /**
     * Getting count datasets by project ID
     * @param projectId project ID
     * @return count datasets
     */
    Integer getCountByProjectId(Integer projectId);

    /**
     * Getting json string datasets by limit and
     * @param offset
     * @param limit
     * @param projectId
     * @return json string datasets
     */
    String getByOffsetAndLimitAndProjectId(Integer offset, Integer limit, Integer projectId);

    /**
     * Deleting dataset by dataset ID
     * @param datasetId dataset ID
     */
    void deleteByIdSQL(Long datasetId);

    /**
     * Getting name for new dataset by project ID
     * @param projectId project ID
     * @return name faot new dataset
     */
    public String getNameForNewDataset(Integer projectId);

    /**
     * Getting json string ID and name datasets by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string
     */
    String getAllIdAndNameByDataSchemaId(Long dataSchemaId);

    /**
     * Getting json string all ID and name datasets anomaly type by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string all ID and name datasets anomaly type
     */
    String getAllIdAndNameByDataSchemaIdAnomaly(Long dataSchemaId);

    /**
     * Getting json string all ID and name datasets fault type by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string all ID and name datasets fault type
     */
    String getAllIdAndNameByDataSchemaIdFault(Long dataSchemaId);

    /**
     * Getting json string all ID and name datasets rul type by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string all ID and name datasets rul type
     */
    String getAllIdAndNameByDataSchemaIdRUL(Long dataSchemaId);
}
