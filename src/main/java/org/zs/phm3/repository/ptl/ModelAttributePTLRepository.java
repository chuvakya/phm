package org.zs.phm3.repository.ptl;

import org.zs.phm3.models.ptl.ModelAttrTypePTL;
import org.zs.phm3.models.ptl.ModelAttributePTL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ModelAttributePTLRepository extends CrudRepository<ModelAttributePTL, Long> {

    @Modifying
    @Query(value = "DELETE FROM model_attribute_ptl WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long modelAttributeId);

    List<ModelAttributePTL> getAllByModelPTL_Id(Long modelId);

    @Query(value = "SELECT DISTINCT m.modelAttrTypePTL FROM ModelAttributePTL m WHERE m.modelPTL.id = ?1")
    List<ModelAttrTypePTL> getAllAttrTypeNamesByModelId(Long modelId);

    @Query(value = "SELECT m.id, m.modified_time, m.name AS mName, m.value, ue.name AS userName, m.uom_symbol " +
            "FROM model_attribute_ptl m " +
            "JOIN user_entity ue ON m.modified_by_id = ue.id", nativeQuery = true)
    List<List<Object>> getAllByModelPTL_IdAndModelAttrTypePTL_Id(Long modelId, Integer modelAttrTypeId);

}
