package org.zs.phm3.dto.ptl.mapping;

import org.mapstruct.Mapper;
import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.models.ptl.UnitTypePTL;

import java.util.List;
@Mapper
public interface UnitTypeMapper {
    List<DtoIdIntName> map(List<UnitTypePTL> unitTypes);
}
