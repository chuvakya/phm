package org.zs.phm3.service.ts;

import org.zs.phm3.models.ts.TsKvAttributeType;

import java.util.List;

public interface TsKvAttributeTypeService {

    TsKvAttributeType getById(Integer categoryId);
    List<TsKvAttributeType> getAllByProjectId(Integer projectId);
    List<TsKvAttributeType> getAllUsersTypesByProjectId(Integer projectId);
    TsKvAttributeType saveIfNotExist(TsKvAttributeType tsKvAttributeType);
    TsKvAttributeType save(TsKvAttributeType tsKvAttributeType);
    void deleteById(Integer categoryId);
    List<TsKvAttributeType> getAllAttributeTypeByUnitId(String unitId);
}
