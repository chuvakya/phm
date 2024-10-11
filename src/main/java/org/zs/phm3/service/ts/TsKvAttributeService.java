package org.zs.phm3.service.ts;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ts.TsKvAttributeType;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.ts.TsKvAttribute;

import java.util.List;

@Service
public interface TsKvAttributeService {

    TsKvAttribute save(TsKvAttribute tsAttributeForSave);

    List<TsKvAttribute> saveAll(List<TsKvAttribute> tsKvListForSave);

    TsKvAttribute findById(UnitAttributeKey tsAttribId) throws PhmException;

    List<TsKvAttribute> getAllTsKvAttributes();

//    List<TsKvAttribute> getAllTsKvAttributesByUnit(String unitId);

    void deleteByIdSQL(Long attributeId);

    void fillingDataFromTsKv();
    String getAllForUnit(String unitId);
    List<String> getAllDeviceIdByUnit(String unitId);
    String getAllForUnitForUI(String unitId);
    String getAllForDataSchema(String unitId);
    String getByIdForUI(Long attributeId);
    List<TsKvAttribute> getAllByDeviceIdAndUnitId(String deviceId, String unitId);
    String getDataAttributeChart(TsKvAttribute tsKvAttribute, Long n);
    String getDataAttributeTable(TsKvAttribute tsKvAttribute, Long n);
    TsKvAttribute saveIfNotExist(TsKvAttribute tsKvAttribute);
    String getAllNameAndKeyAndSymbolByUnitId(String unitId);
    TsKvAttribute getById(Long attributeId);
    Long getCountByNameAndUnitId(String name, String unitId);

}
