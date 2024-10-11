package org.zs.phm3.dto.ptl.mapping;

import org.springframework.stereotype.Component;
import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.models.ptl.ModelPTL;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapperPtlImpl implements ModelMapper{
    @Override
    public List<DtoIdIntName> map(List<ModelPTL> models) {
        if (models == null) {
            return null;
        }
        List<DtoIdIntName> modelslist = new ArrayList<>(models.size());
        for (ModelPTL model : models) {
            modelslist.add(modelToDtoIdIntName(model));
        }
        return modelslist;
    }

    private DtoIdIntName modelToDtoIdIntName(ModelPTL manufacturer) {
        if (manufacturer == null) {
            return null;
        }
        DtoIdIntName unitTypesIdName = new DtoIdIntName(Math.toIntExact(manufacturer.getId()), manufacturer.getName());
        return unitTypesIdName;
    }
}
