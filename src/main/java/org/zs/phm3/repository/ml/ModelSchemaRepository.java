package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.ModelSchema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ModelSchemaRepository extends CrudRepository<ModelSchema, Long> {

    @Query(value = "SELECT m.id, m.create_time, m.ml_algorithm, m.name AS modelSchemaName, ue.name AS userName " +
            "FROM model_schema m " +
            "JOIN user_entity ue ON m.modified_by_id = ue.id " +
            "WHERE m.project_id = ?1 ORDER BY m.name ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM model_schema WHERE project_id = ?1", nativeQuery = true)
    Integer getCountByProjectId(Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM model_schema WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long modelSchemaId);

    List<ModelSchema> getAllByNameAndProjectId(String name, Integer projectId);

    @Query(value = "SELECT ms.id AS modelSchemaId, ms.ml_algorithm, ms.name AS modelSchemaName, ms.window_length, ds.unit_id, " +
            "ds.id AS dataSchemaId, d.id AS datasetId, tka.name AS bitAttributeName, bp.error_code AS bitErrorCode, bp.name AS bitName " +
            "FROM model_schema ms " +
            "JOIN dataset d on ms.dataset_id = d.id " +
            "JOIN data_schema ds on d.data_schema_id = ds.id " +
            "LEFT JOIN ts_kv_attribute tka on ds.bit_attribute_id = tka.id " +
            "LEFT JOIN bit_ptl bp on ds.bit_error_code_id = bp.id " +
            "WHERE ms.id = ?1", nativeQuery = true)
    List<List<Object>> getById(Long modelSchemaId);

}
