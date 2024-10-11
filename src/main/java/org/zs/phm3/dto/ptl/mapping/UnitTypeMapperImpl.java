package org.zs.phm3.dto.ptl.mapping;

import org.springframework.stereotype.Component;
import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.models.ptl.UnitTypePTL;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnitTypeMapperImpl implements UnitTypeMapper{
    @Override
    public List<DtoIdIntName> map(List<UnitTypePTL> unitTypes) {

        if (unitTypes == null) {
            return null;
        }
        List<DtoIdIntName> unitTypesIdNamelist = new ArrayList<>(unitTypes.size());
        for (UnitTypePTL unitType : unitTypes) {
            unitTypesIdNamelist.add(unitTypeToDtoIdIntName(unitType));
        }
        return unitTypesIdNamelist;
    }
    private DtoIdIntName unitTypeToDtoIdIntName(UnitTypePTL unitType) {
        if (unitType == null) {
            return null;
        }
        DtoIdIntName unitTypesIdName = new DtoIdIntName(unitType.getId(), unitType.getName());
        return unitTypesIdName;
    }
}
