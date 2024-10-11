package org.zs.phm3.dto.ptl.mapping;

import org.springframework.stereotype.Component;
import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.models.ptl.ManufacturerPTL;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManufacturerMapperImpl implements ManufacturerMapper{
    @Override
    public List<DtoIdIntName> map(List<ManufacturerPTL> manufacturers) {

        if (manufacturers == null) {
            return null;
        }

        List<DtoIdIntName> manufacturerslist = new ArrayList<>(manufacturers.size());
        for (ManufacturerPTL manufacturer : manufacturers) {
            manufacturerslist.add(manufacturerToDtoIdIntName(manufacturer));
        }

        return manufacturerslist;
    }

    private DtoIdIntName manufacturerToDtoIdIntName(ManufacturerPTL manufacturer) {
        if (manufacturer == null) {
            return null;
        }

        DtoIdIntName unitTypesIdName = new DtoIdIntName(Math.toIntExact(manufacturer.getId()), manufacturer.getName());
        return unitTypesIdName;
    }
}
