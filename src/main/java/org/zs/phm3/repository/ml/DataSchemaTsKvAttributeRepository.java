package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.DataSchemaTsKvAttribute;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DataSchemaTsKvAttributeRepository extends CrudRepository<DataSchemaTsKvAttribute, Long> {

    @Query(value = "SELECT dt.tsKvAttribute FROM DataSchemaTsKvAttribute dt WHERE dt.dataSchema.id = ?1 " +
            "ORDER BY dt.tsKvAttribute.id ASC")
    List<TsKvAttribute> getAllAttributesByDataSchemaId(Long dataSchemaId);

    @Modifying
    @Query(value = "DELETE FROM data_schema_ts_kv_attribute WHERE data_schema_id = ?1", nativeQuery = true)
    void deleteByDataSchemaIdSQL(Long dataSchemaId);

    @Query(value = "SELECT dt.ts_kv_attribute_id, ts.name AS attr_name, u.name AS unit_name " +
            "FROM data_schema_ts_kv_attribute dt CROSS JOIN units u, ts_kv_attribute ts " +
            "WHERE dt.data_schema_id = ?1 AND dt.ts_kv_attribute_id = ts.id AND ts.unit_id = u.id", nativeQuery = true)
    List<List<Object>> getAllAttributeIdAndNameAndUnitNameByDataSchemaId(Long dataSchemaId);
}
