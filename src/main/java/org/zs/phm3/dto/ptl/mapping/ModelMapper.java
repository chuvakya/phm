package org.zs.phm3.dto.ptl.mapping;

import org.mapstruct.Mapper;
import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.models.ptl.ModelPTL;

import java.util.List;

@Mapper
public interface ModelMapper {
    List<DtoIdIntName> map(List<ModelPTL> models);
}
