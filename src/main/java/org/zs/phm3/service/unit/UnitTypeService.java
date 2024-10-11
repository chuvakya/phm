package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitType;

import java.util.List;

public interface UnitTypeService {
    UnitType save(UnitType unitTypeForSave) throws PhmException;
    UnitType findById(Integer unitTypeId) throws PhmException;
    List<UnitType> getAll();
    void deleteById(Integer unitTypeIdForDeleting);
    void deleteBy(UnitType unitTypeForDeleting);
    void saveAll(List<UnitType> unitTypesList);
}
