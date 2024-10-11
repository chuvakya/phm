package org.zs.phm3.repository.unit;

import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UnitAttributeRepository extends CrudRepository<UnitAttribute, UnitAttributeKey> {
    @Query(value = "SELECT COUNT(*)=0 FROM unit_attribute WHERE " +
            "attribute_key=?1 AND unit_id=?2", nativeQuery = true)
    Boolean isNew(String attributeKey, String unitId);

    @Query(value = "SELECT * FROM unit_attribute "+
            "WHERE unit_id=?1", nativeQuery = true)
    List<UnitAttribute> getAllByUnitId(String unitId);


    @Query(value = "FROM UnitAttribute u WHERE u.id.unitId = ?1 AND u.name = ?2")
    UnitAttribute getUnitAttrFromUnitIdAndName(String unitId, String name);

    @Modifying
    @Query(value = "DELETE FROM unit_attribute WHERE unit_id=?1", nativeQuery = true)
    void deleteByUnit(String unitId);

//    @Query(value = "SELECT * FROM unit_attribute "+
//            "WHERE unit_id=?1", nativeQuery = true)
//    List<UnitAttributeDtoOutput> getAllForTableByUnitId(String unitId);

/*    @Query(value = "SELECT attribute_key, unit_id, bool_v, dbl_v, long_v, str_v, symbol AS uom "+
            "FROM unit_attribute AS t1 LEFT JOIN uom AS t2 ON t1.uom_input_id = t2.id",
            nativeQuery = true)
    List<UnitAttributeDtoOutput> getAllForTableByUnitId(String unitId);*/

//    List<UnitAttributeDtoOutput> getAllForTableByUnitId(String unitId);
//    SELECT attribute_key as key, unit_id, bool_v, dbl_v, long_v, str_v, symbol FROM unit_attribute AS t1 LEFT JOIN uom AS t2 ON t1.uom_input_id = t2.id


    List<UnitAttribute> getAllById_AttributeKey(String attributeKey);
}
