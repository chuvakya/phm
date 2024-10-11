package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.DataSchema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DataSchemaRepository extends CrudRepository<DataSchema, Long> {

    List<DataSchema> getAllByProjectId(Integer projectId);

    @Query(value = "SELECT d.id, d.name FROM DataSchema d WHERE d.unitId = ?1")
    List<List<Object>> getAllIdAndNameByUnitId(String unitId);

    @Query(value = "SELECT COUNT(*) FROM data_schema WHERE project_id = ?1", nativeQuery = true)
    Integer getCountByProjectId(Integer projectId);

    @Query(value = "SELECT d.id, d.name AS data_schema_name, d.created_time, ue.name AS userName, u.name AS unit_name " +
            "FROM data_schema d " +
            "JOIN units u ON d.unit_id = u.id " +
            "JOIN user_entity ue ON d.modified_by_id = ue.id " +
            "WHERE d.project_id = ?3 ORDER BY u.name ASC OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<List<Object>> getByOffsetAndLimitAndProjectId(Integer offset, Integer limit, Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM data_schema WHERE unit_id=?1", nativeQuery = true)
    void deleteByUnit(String unitId);

    @Modifying
    @Query(value = "DELETE FROM data_schema WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long dataSchemaId);

    @Modifying
    @Query(value = "DELETE FROM dataset_param WHERE data_schema_id = ?1", nativeQuery = true)
    void deleteDatasetParamByDataSchemaId(Long dataSchemaId);

    List<DataSchema> getAllByNameAndProjectId(String name, Integer projectId);

    @Query(value = "SELECT id, unitId, name, bitAttribute.id, bitErrorCode.id FROM DataSchema WHERE id = ?1")
    List<List<Object>> getById(Long dataSchemaId);

}
