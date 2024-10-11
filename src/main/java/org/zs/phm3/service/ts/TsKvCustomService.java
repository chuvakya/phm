package org.zs.phm3.service.ts;

import java.util.List;

public interface TsKvCustomService {
/*    TsKvEntity save(TsKvEntity tsKvForSave);

    List<TsKvEntity> saveAll(List<TsKvEntity> tsKvListForSave);

    Optional<UnitEntity> findById(TsKvId tsKvId);

    List<TsKvEntity> getAllTsKv();

    Boolean exportToCsv(String unitId);

    Boolean exportToParquet(String unitId);

    List<TsKvEntity> findForDataSet(String unitId, List<String> AttributeKeyList, Long from, Long to);

    List<TsKvEntity> getAllFromTimeAndUnitId(long timeFrom, long timeTo, String unitId);*/

    List<String> getAllUnitIdWithTsData();
}
