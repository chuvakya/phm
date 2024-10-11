package org.zs.phm3.dto.ptl.mapping;

import org.mapstruct.Mapper;
import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.models.ptl.ManufacturerPTL;

import java.util.List;

@Mapper
public interface ManufacturerMapper {
    List<DtoIdIntName> map(List<ManufacturerPTL> manufacturers);
}
