package org.zs.phm3.repository.ts;

import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.ts.TsKvAttribute;

import java.util.List;

@Repository
@Transactional
public interface TsKvAttributeRepository extends CrudRepository<TsKvAttribute, Long> {

    @Query(value = "SELECT ts.id, ts.name FROM TsKvAttribute ts WHERE ts.unitId = ?1")
    List<List<Object>> getAllForUnit(String unitId);

    @Query(value = "FROM TsKvAttribute ts WHERE ts.unitId = ?1")
    List<TsKvAttribute> getAllByUnitId(String unitId);

    @Query(value = "SELECT ts.id, ts.name, ts.data_type, ts.output_symbol " +
            "FROM ts_kv_attribute ts " +
            "WHERE ts.unit_id = ?1", nativeQuery = true)
    List<List<Object>> getAllIdAndNameAndDataTypeAndUomOutputSymbolByUnitId(String unitId);

    @Query(value = "SELECT DISTINCT device_id FROM ts_kv_attribute WHERE unit_id = ?1", nativeQuery = true)
    List<List<Object>> getAllDeviceIdByUnit(String unitId);

    List<TsKvAttribute> getAllByDeviceIdAndUnitId(String deviceId, String unitId);

    @Query(value = "SELECT DISTINCT category_name FROM ts_kv_attribute WHERE project_id = ?1", nativeQuery = true)
    List<List<Object>> getCategoriesByProjectId(Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM ts_kv_attribute WHERE unit_id=?1", nativeQuery = true)
    void deleteByUnit(String unitId);

    @Modifying
    @Query(value = "DELETE FROM ts_kv_attribute WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long attributeId);

    @Query(value = "SELECT t.name, t.attribute_key, u.symbol FROM ts_kv_attribute t CROSS JOIN uom u " +
            "WHERE t.uom_output_id = u.id AND unit_id = ?1", nativeQuery = true)
    List<List<Object>> getAllNameAndKeyAndSymbolByUnitId(String unitId);

    List<TsKvAttribute> getAllByUnitIdAndTsKvAttributeType_Id(String unitId, Integer attributeTypeId);

    @Query(value = "SELECT a.id, a.name AS attribute_name, a.attribute_key, a.data_type, at.name AS type_name, " +
            "a.device_id, a.is_table, a.uom_input_id, a.uom_output_id " +
            "FROM ts_kv_attribute a CROSS JOIN ts_kv_attribute_type at " +
            "WHERE a.ts_kv_attribute_type_id = at.id AND a.id = ?1", nativeQuery = true)
    List<List<Object>> getByIdSQL(Long attributeId);

    @Query(value = "SELECT COUNT(*) FROM TsKvAttribute WHERE name = ?1 AND unitId = ?2")
    Long getCountByNameAndUnitId(String name, String unitId);

    @Query(value = "FROM TsKvAttribute WHERE name = ?1 AND unitId = ?2")
    TsKvAttribute getByNameAndUnitId(String name, String unitId);
}
