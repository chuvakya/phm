package org.zs.phm3.repository.calculation;

import org.zs.phm3.models.calculation.CalculationAttribute;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CalculationAttrRepository extends CrudRepository<CalculationAttribute, Integer> {

//        List<CalculationAttribute> getAllByUnitIdAndAttrType_Id(String unitId, Integer attrTypeId);
        CalculationAttribute getByAttrType_NameAndNameAndUnitId(String attrTypeName, String name, String unitId);

        List<CalculationAttribute> getAllByUnitIdAndAttrType_Id(String unitId, Integer attrTypeId);

        @Modifying
        @Query(value = "DELETE FROM calculation_attribute WHERE unit_id=?1", nativeQuery = true)
        void deleteByUnit(String unitId);

}
