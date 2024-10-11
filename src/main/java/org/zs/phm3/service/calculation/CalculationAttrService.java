package org.zs.phm3.service.calculation;

import org.zs.phm3.models.calculation.CalculationAttribute;

import java.util.List;

/**
 * Interface CalculationAttrService
 * @author Pavel Chuvak
 */
public interface CalculationAttrService {

    /**
     * Getting all calculation attribute
     * @return all calculation attribute
     */
    List<CalculationAttribute> getAll();

    /**
     * Saving calculation attribute
     * @param calculationAttribute calculation attribute
     * @return calculation attribute
     */
    CalculationAttribute save(CalculationAttribute calculationAttribute);

    /**
     * Deliting calculation attribute by ID
     * @param id ID
     */
    void deleteById(Integer id);

    /**
     * Getting by calculation attribute by ID
     * @param id ID
     * @return calculation attribute
     */
    CalculationAttribute getById(Integer id);

    /**
     * Getting json string by unit ID
     * @param unitId unit ID
     * @return json string
     */
    String getAllByUnit(String unitId);
}
