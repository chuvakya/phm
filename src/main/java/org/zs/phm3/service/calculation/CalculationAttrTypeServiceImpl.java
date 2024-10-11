package org.zs.phm3.service.calculation;

import org.zs.phm3.models.calculation.CalculationAttrType;
import org.zs.phm3.repository.calculation.CalculationAttrTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class implementing CalculationAttrTypeService
 */
@Service
public class CalculationAttrTypeServiceImpl implements CalculationAttrTypeService {

    /** Calculation attribute type repository */
    @Autowired
    private CalculationAttrTypeRepository calculationAttrTypeRepository;

    /**
     * Saving if not exist
     * @param calculationAttrType calculation attribute type
     * @return calculation attribute type
     */
    @Override
    public CalculationAttrType saveIfNotExist(CalculationAttrType calculationAttrType) {
        CalculationAttrType attrType = calculationAttrTypeRepository.getByName(calculationAttrType.getName());
        if (attrType == null) {
            return calculationAttrTypeRepository.save(calculationAttrType);
        }
        return null;
    }

    /**
     * Saving calculation attribute type
     * @param calculationAttrType calculation attribute type
     * @return calculation attribute type
     */
    @Override
    public CalculationAttrType save(CalculationAttrType calculationAttrType) {
        return calculationAttrTypeRepository.save(calculationAttrType);
    }

    /**
     * Getting all attribute types
     * @return all attribute types
     */
    @Override
    public List<CalculationAttrType> getAll() {
        return (List<CalculationAttrType>) calculationAttrTypeRepository.findAll();
    }
}
