package org.zs.phm3.repository.ptl;

import org.zs.phm3.models.ptl.ModelAttrTypePTL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ModelAttrTypePTLRepository extends CrudRepository<ModelAttrTypePTL, Integer> {

    @Modifying
    @Query(value = "DELETE FROM model_attr_type_ptl WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Integer modelAttrTypeId);

}
