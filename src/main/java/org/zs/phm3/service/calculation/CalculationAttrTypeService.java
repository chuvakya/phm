package org.zs.phm3.service.calculation;

import org.zs.phm3.models.calculation.CalculationAttrType;

import java.util.List;

/**
 * Interface CalculationAttrTypeService
 * @author Pavel Chuvak
 */
public interface CalculationAttrTypeService {

    /**
     * Saving if not exist
     * @param calculationAttrType calculation attribute type
     * @return calculation attribute type
     */
    CalculationAttrType saveIfNotExist(CalculationAttrType calculationAttrType);

    /**
     * Saving calculation attribute type
     * @param calculationAttrType calculation attribute type
     * @return calculation attribute type
     */
    CalculationAttrType save(CalculationAttrType calculationAttrType);

    /**
     * Getting all attribute types
     * @return all attribute types
     */
    List<CalculationAttrType> getAll();
}
