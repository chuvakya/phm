package org.zs.phm3.repository.ptl;

import ch.qos.logback.core.boolex.EvaluationException;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UnitTypePTLRepository extends CrudRepository<UnitTypePTL, Integer> {

    @Modifying
    @Query(value = "DELETE FROM unit_type_ptl WHERE id = ?1", nativeQuery = true)
    void deleteSQL(Integer unitTypeId);

    List<UnitTypePTL> getAllByUnitTypePTL_IdIsNull();

    @Query(value = "SELECT id, name FROM unit_type_ptl", nativeQuery = true)
    List<List<Object>> getAllIdAndName();

    @Query(value = "SELECT COUNT(*) FROM unit_type_ptl WHERE unit_type_ptl_id = ?1", nativeQuery = true)
    Integer getCountByParentId(Integer parentId);

    @Query(value = "SELECT id, name, pictureId, isDefaultType, modifiedBy.name, modifiedTime, unitTypePTL.id FROM UnitTypePTL " +
            "ORDER BY name")
    List<List<Object>> getAllField();

    @Query(value = "SELECT name FROM UnitTypePTL WHERE id = 1")
    String getName();

    @Query(value = "SELECT id, isDefaultType FROM UnitTypePTL WHERE id IN ?1")
    List<List<Object>> getAllIdAndDefaultType(List<Integer> unitTypePTLIds);
}
