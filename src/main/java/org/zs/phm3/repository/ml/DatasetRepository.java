package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.Dataset;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DatasetRepository extends CrudRepository<Dataset, Long> {

    @Query(value = "FROM Dataset d WHERE d.projectId = ?1")
    List<Dataset> getAllByProjectId(Integer projectId);

    @Query(value = "FROM Dataset d WHERE d.name = ?1 AND d.projectId = ?2")
    List<Dataset> getAllByNameAndProjectId(String name, Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM dataset WHERE project_id = ?1", nativeQuery = true)
    Integer getCountByProjectId(Integer projectId);

    @Query(value = "SELECT d.id, d.name, d.state, u.name AS unit_name, ds.name AS data_schema_name, ue.name AS userName, d.create_time, d.elapsed_time " +
            "FROM dataset d " +
            "JOIN user_entity ue ON d.modified_by_id = ue.id " +
            "JOIN data_schema ds ON d.data_schema_id = ds.id " +
            "JOIN units u ON ds.unit_id = u.id " +
            "WHERE d.project_id = ?3 ORDER BY u.name ASC OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<List<Object>> getByOffsetAndLimitAndProjectId(Integer offset, Integer limit, Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM dataset WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long datasetId);

    @Query(value = "SELECT d.id, d.name FROM Dataset d WHERE d.dataSchema.id = ?1")
    List<List<Object>> getAllIdAndNameByDataSchemaId(Long dataSchemaId);

    @Query(value = "SELECT d.id, d.name FROM Dataset d WHERE d.dataSchema.id = ?1 AND d.isAnomaly = true")
    List<List<Object>> getAllIdAndNameByDataSchemaIdAnomaly(Long dataSchemaId);

    @Query(value = "SELECT d.id, d.name FROM Dataset d WHERE d.dataSchema.id = ?1 AND d.isFault = true")
    List<List<Object>> getAllIdAndNameByDataSchemaIdFault(Long dataSchemaId);

    @Query(value = "SELECT d.id, d.name FROM Dataset d WHERE d.dataSchema.id = ?1 AND d.isRul = true")
    List<List<Object>> getAllIdAndNameByDataSchemaIdRUL(Long dataSchemaId);
}
