package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitModelAttributeKey;

import java.util.List;


public interface UnitModelAttributeService {
    UnitModelAttribute save(UnitModelAttribute unitModelAttributeForSave);

    UnitModelAttribute findById(UnitModelAttributeKey UnitModelAttributeId) throws PhmException;

    List<UnitModelAttribute> getAll();

    void deleteById(UnitModelAttributeKey UnitModelAttributeId);

    Boolean isNew(String attributeKey, Integer unitId);

    List<UnitModelAttribute> getAllByUnitModelId(Integer unitModelId);

    public void saveAll(List<UnitModelAttribute> unitModelAttributesList);
}
