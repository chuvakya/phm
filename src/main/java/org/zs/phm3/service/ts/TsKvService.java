package org.zs.phm3.service.ts;

import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.zs.phm3.models.unit.UnitEntity;

import java.util.List;
import java.util.Optional;

public interface TsKvService {
    TsKvEntity save(TsKvEntity tsKvForSave);

    List<TsKvEntity> saveAll(List<TsKvEntity> tsKvListForSave);

    Optional<UnitEntity> findById(TsKvId tsKvId);

    List<TsKvEntity> getAllTsKv();

    Boolean exportToCsv(String unitId);

    Boolean exportToParquet(String unitId);

    List<TsKvEntity> findForDataSet(String unitId, List<String> AttributeKeyList, Long from, Long to);

    List<TsKvEntity> getAllFromTimeKeyAndDeviceId(long timeFrom, long timeTo, String deviceId, String key);

    List<String> getAllUnitIdWithTsData();
    TsKvEntity getTsKvFromUnitByTsAndKey(Long ts, String key, String unitId);

    List<TsKvEntity> getAllFromTimeKeyAndDeviceIdLimit(long timeFrom, long timeTo, String deviceId, String key, Integer limit);
}
