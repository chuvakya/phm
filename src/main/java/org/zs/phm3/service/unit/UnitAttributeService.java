package org.zs.phm3.service.unit;

import org.zs.phm3.dto.UnitAttributeDtoInput;
import org.zs.phm3.dto.UnitAttributeDtoOutput;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.IdTextReturn;
import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.models.unit.UnitAttributeType;

import java.util.List;

public interface UnitAttributeService {
    UnitAttribute save(UnitAttributeDtoInput UnitAttributeForSave) throws PhmException;

    List<UnitAttribute> saveAll(List<UnitAttribute> attributes);

    UnitAttribute findById(UnitAttributeKey UnitAttributeId) throws PhmException;

    void deleteById(UnitAttributeKey UnitAttributeId) throws PhmException;

    List<UnitAttribute> getAllByUnitId(String unitId);

    public List<IdTextReturn> getUomDataForDisplay();

    public UomEntity uomGetById(Integer uomId) throws PhmException;

    List<UnitAttributeDtoOutput> getAllForTableByUnitId(String UnitId);

    List<IdTextReturn> getAllAttrKeysForUnitId(String unitId);

    List<IdTextReturn> getAllCustomUnitId();

    List<UnitAttributeType> getTypes();

    UnitAttribute getUnitAttrFromUnitIdAndName(String unitId, String name);
}

