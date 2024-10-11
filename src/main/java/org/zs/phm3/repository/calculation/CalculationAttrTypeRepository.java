package org.zs.phm3.repository.calculation;

import org.zs.phm3.models.calculation.CalculationAttrType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CalculationAttrTypeRepository extends CrudRepository<CalculationAttrType, Integer> {
    CalculationAttrType getByName(String name);
}
