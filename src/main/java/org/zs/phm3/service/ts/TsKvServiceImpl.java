package org.zs.phm3.service.ts;

import org.springframework.cache.annotation.Cacheable;
import org.zs.phm3.config.GlobalProperties;
import org.zs.phm3.config.MlServiceProperties;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.util.mapping.PhmUtil;
import org.zs.phm3.util.parquet.csv.PhmCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
//import org.zs.phm3.repository.TsKvRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Primary
@Service
public class TsKvServiceImpl implements TsKvService {
    @Autowired
    private TsKvRepository tsKvRepository;

    @Value("${global.date_time_format}")
    private String dateTimeFormatPattern;

    @Autowired
    private GlobalProperties globalProperties;

    @Autowired
    private MlServiceProperties mlServiceProperties;

    @Override
    public TsKvEntity save(TsKvEntity TsKvForSave) {
        TsKvEntity TsKvSaved = tsKvRepository.save(TsKvForSave);
        return TsKvSaved;
    }

    @Override
    public List<TsKvEntity> saveAll(List<TsKvEntity> tsKvListForSave) {
        return (List<TsKvEntity>) tsKvRepository.saveAll(tsKvListForSave);
//        tsKvRepository.saveAll(tsKvListForSave);
//        return null;
    }

    @Override
    public Optional<UnitEntity> findById(TsKvId tsKvId) {
        return Optional.empty();
    }

    @Override
    public List<TsKvEntity> getAllTsKv() {
        List<TsKvEntity> TsKvList = (List<TsKvEntity>) tsKvRepository.findAll();
        return TsKvList;
    }

    @Override
    public Boolean exportToCsv(String unitId) {
        List<TsKvEntity> tsReadedList = new ArrayList<>();
        Map<String, String> tsMap = new LinkedHashMap<>();
        List<Map<String, String>> tsListMapForExport = new ArrayList<>();
        tsReadedList = tsKvRepository.getAllByUnitId(PhmUtil.toShortUUID(unitId));
        long packet_ts = 0;

        for (TsKvEntity ts : tsReadedList) {
            if (packet_ts == 0) {
                packet_ts = ts.getId().getTs();
                tsMap.put("timestamp", String.valueOf(packet_ts));
            }
            if (packet_ts == ts.getId().getTs()) {
                tsMap.put(ts.getId().getKey(), String.valueOf(ts.getDoubleValue()));

            } else {
                // close map data packet and add to list
                tsListMapForExport.add(tsMap);
                tsMap = new LinkedHashMap<>();// tsMap.clear();
                // new map data packet
                packet_ts = ts.getId().getTs();
                tsMap.put("timestamp", String.valueOf(packet_ts));
                tsMap.put(ts.getId().getKey(), String.valueOf(ts.getDoubleValue()));
            }

//            char a = 1;
        }

        PhmCSV test = new PhmCSV();
//        test.setPath("D:\\Java_CETC1\\phm4\\src\\test\\resources\\td2_export.csv");
        test.setPath(mlServiceProperties.getDatasetFolder());
        Boolean result=test.writeFrom(tsListMapForExport);

        return result;
    }

    @Override
    public Boolean exportToParquet(String unitId) {
        return null;
    }

    @Override
    public List<TsKvEntity> findForDataSet(String unitId, List<String> AttributeKeyList, Long from, Long to) {
        return null; //tsKvRepository.findForDataSet(unitId, AttributeKeyList, from, to);
    }

    @Override
    public List<TsKvEntity> getAllFromTimeKeyAndDeviceId(long timeFrom, long timeTo, String deviceId, String key) {
        return tsKvRepository.getAllFromTimeKeyAndDeviceId(timeFrom, timeTo, deviceId, key);
    }

    @Override
    public List<String> getAllUnitIdWithTsData() {
        List<String> unitIds = new ArrayList<>();
        List<Object[]> idsDateList=tsKvRepository.getAllUnitIdWithTsData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatPattern);
//        Integer counter=0;
        idsDateList.forEach(item -> {
//                    String dateTs=LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(item[0].toString())),
//                            TimeZone.getDefault().toZoneId()).toString();
                    String dateTs= LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(item[1].toString())),
                            TimeZone.getDefault().toZoneId()).format(formatter).toString();

//            unitIds.add((counter.addAndGet(1)),UUIDConverter.fromString(item[0].toString())+
//                    ", ts: "+item[1].toString());
                    unitIds.add(UUIDConverter.fromString(item[0].toString())+
                            ", DateTime: "+dateTs);
                }
        );
        return unitIds;
    }

    @Override
    public TsKvEntity getTsKvFromUnitByTsAndKey(Long ts, String key, String unitId) {
        return tsKvRepository.getTsKvFromUnitByTsAndKey(ts, key, unitId);
    }

    @Override
    public List<TsKvEntity> getAllFromTimeKeyAndDeviceIdLimit(long timeFrom, long timeTo, String deviceId, String key, Integer limit) {
        return tsKvRepository.getAllFromTimeKeyAndDeviceIdLimit(timeFrom, timeTo, deviceId, key, limit);
    }

    public void findForDataSet1(UnitAttributeKey attrKey, Long from, Long to) {
/*            public List<DealInfo> getDealInfos(List<String> dealIds) {
            String queryStr = "SELECT NEW com.admin.entity.DealInfo(deal.url, deal.url, deal.url, deal.url, deal.price, deal.value) " + "FROM Deal AS deal where deal.id in :inclList";
            TypedQuery<DealInfo> query = em.createQuery(queryStr, DealInfo.class);
            query.setParameter("inclList", dealIds);
            return query.getResultList();*/
    }
}
