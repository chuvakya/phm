package org.zs.phm3.repository.rpm;

import org.zs.phm3.models.rpm.CalcId;
import org.zs.phm3.models.rpm.ProductCalc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public interface ProductCalcRepository extends CrudRepository<ProductCalc, CalcId> {

    List<ProductCalc> findAllByParentIdIs(String parentId);

    @Query(value = "SELECT * FROM pt_product_calc WHERE unit_id = ?1", nativeQuery = true)
    List<ProductCalc> findAllByUnitId(String uniId);

//    1eb0d35cf18aa4684f3c39386b12a13
    @Query(value = "SELECT u.id, u.name, u.serial_number, m.name AS model, t.name AS type, f.name AS manufacturer "+
            "FROM units AS u "+
            "LEFT JOIN model_ptl AS m ON m.id=u.model "+
            "LEFT JOIN unit_type_ptl AS t ON m.unit_type_ptl_id=t.id "+
            "LEFT JOIN manufacturer_ptl AS f ON m.manufacturer_ptl=f.id "+
            "WHERE u.parent_id = ?1", nativeQuery = true)
    List<String []> getAllUnitChildsPtlData(String parentId);
}

