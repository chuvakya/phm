package org.zs.phm3.repository.ptl;

import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.zs.phm3.models.ptl.ModelPTL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
@Repository
public interface ModelPTLRepository extends CrudRepository<ModelPTL, Long> {

    @Modifying
    @Query(value = "DELETE FROM model_ptl WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long modelId);

    @Query(value = "SELECT DISTINCT m.manufacturerPTL FROM ModelPTL m WHERE m.unitTypePTL.id = ?1")
    List<ManufacturerPTL> getAllManufacturerByUnitTypeId(Integer unitTypeId);

    List<ModelPTL> getAllByManufacturerPTL_IdAndUnitTypePTL_Id(Long manufacturerId, Integer unitTypeId);

    @Query(value = "SELECT model.id, model.name AS model_name, mp.name AS manuf_name, utp.name AS unit_type_name, " +
            "ue.name AS userName, model.create_time, model.revision " +
            "FROM model_ptl model " +
            "JOIN manufacturer_ptl mp ON model.manufacturer_ptl = mp.id " +
            "JOIN unit_type_ptl utp ON model.unit_type_ptl_id = utp.id " +
            "JOIN user_entity ue ON model.modified_by_id = ue.id " +
            "ORDER BY model.name OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<List<Object>> getListByOffsetAndLimit(Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM model_ptl", nativeQuery = true)
    Long getCount();

    @Query(value = "SELECT m.id, m.name FROM ModelPTL m WHERE m.manufacturerPTL.id = ?1 AND m.unitTypePTL.id = ?2")
    List<List<Object>> getAllIdAndNameByManufacturerIdAndUnitTypeId(Long manufacturerId, Integer unitTypeId);

    @Query(value = "SELECT DISTINCT m.manufacturerPTL FROM ModelPTL m WHERE m.unitTypePTL.id = ?1")
    List<ManufacturerPTL> getAllManufacturersByUnitTypeId(Integer unitTypeId);

    //    ModelPTL getByManufacturerPTLIsEmptyAndNameIsLike(String empty);
    @Query(value = "SELECT m.* FROM model_ptl AS m WHERE m.unit_type_ptl_id = ?1 " +
            "AND m.name LIKE 'empty%'", nativeQuery = true)
    ModelPTL getEmptyModelForUnitType(Integer unitTypeId);

    @Query(value = "SELECT id, revision FROM ModelPTL WHERE name = ?1")
    List<List<Object>> getAllIdAndRevisionByModelName(String modelName);

}
