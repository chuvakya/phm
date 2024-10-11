package org.zs.phm3.service.ts;


import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.models.ts.TsKvCustomEntity;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.zs.phm3.repository.ts.TsKvCustomRepository;
import org.zs.phm3.util.Randoms;
import org.zs.phm3.util.parquet.csv.PhmCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class TsKvIntegrationServiceImpl implements TsKvIntegrationService {
    @Autowired
    TsKvCustomRepository tsKvCustomRepository;
    @Autowired
    TsKvService tsKvService;
    @PersistenceContext
    private EntityManager entityManager;

    @Value("${global.sourceCustomTsFilePath}")
    private String sourceCustomTsFilePath;

    static final String COMMA_DELIMITER = ",";
    static final int numberRecordsForUnit = 99999;
    static final int numberRecordsForInfo = 10000;
    static final int numberRecordsForSaving = 49999;

    @Override
    @Transactional
    public String importToCustomTsFromCsvFile() throws IOException, InterruptedException {
        int csvRowCount = 0;
        int countRecordsForUnit = 0;
        int countRecordsForInfo = 0;
        int countRecordsForSaving = 0;
        StringBuilder stringBuilder = new StringBuilder("insert into ts_kv_custom (key,ts,unit_id,dbl_v) values ");
        List<TsKvCustomEntity> tsRowsForOneCsvRowList = new ArrayList<TsKvCustomEntity>();
        long start = System.currentTimeMillis() / 1000;
        System.out.println("Import is started, " + LocalDateTime.now());

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\CETC\\ML\\dataset_sigma\\sigma_1000.csv"))) {

            String line;
            String[] headerNames = new String[0];
            String customUnitId = Randoms.generate_ID();
            long ts = System.currentTimeMillis();
            while ((line = br.readLine()) != null) {
                csvRowCount++;
                countRecordsForUnit++;
                countRecordsForInfo++;
                countRecordsForSaving++;

                if (countRecordsForUnit == 1)
                    headerNames = line.split(COMMA_DELIMITER);
                else {
                    if (countRecordsForSaving > numberRecordsForSaving) {
//                        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
                        entityManager.createNativeQuery(stringBuilder.substring(0, stringBuilder.length() - 1)).executeUpdate();
                        System.out.println("        * Saving Data");
                        tsRowsForOneCsvRowList.clear();
                        stringBuilder.setLength(0);
                        stringBuilder.append("insert into ts_kv_custom (key,ts,unit_id,dbl_v) values ");
                        countRecordsForSaving = 0;
                    }
                    if (countRecordsForInfo > numberRecordsForInfo) {

                        System.out.println("        Processing CSV file line: " + csvRowCount + ", Unit Line:" +
                                countRecordsForUnit);
                        countRecordsForInfo = 0;
                    }
                    if (countRecordsForUnit > numberRecordsForUnit) {
//                        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
                        countRecordsForUnit = 1;
                        customUnitId = Randoms.generate_ID();
                        System.out.println("New customUnitId: " + customUnitId);
                    }
                    String[] values = line.split(COMMA_DELIMITER);
                    int count = 0;
//                    long ts = System.currentTimeMillis();
//                    Thread.sleep(1);
                    for (String value : values) {
//                        tsRowsForOneCsvRowList.add(new TsKvCustomEntity(new TsKvId(customUnitId, headerNames[count], ts), Double.valueOf(value)));
//                        tsRowsForOneCsvRowList.add(new TsKvCustomEntity(new TsKvId(customUnitId, headerNames[count], ts), Double.valueOf(value)));

                        stringBuilder.append("('" + headerNames[count] + "'," + ts + ",'" + customUnitId +"', "+
                                Double.valueOf(value)+"),");

//                        entityManager.persist(new TsKvCustomEntity(new TsKvId(customUnitId, headerNames[count], ts), Double.valueOf(value)));
                        count++;
                    }
                }
                ts++;
            }
        }
//        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
        entityManager.createNativeQuery(stringBuilder.substring(0, stringBuilder.length() - 1)).executeUpdate();
        long finish = System.currentTimeMillis() / 1000;

        System.out.println("Import is finished. Rows = " + csvRowCount);
        System.out.println("Time processing (sec): " + (finish - start));
        return "Import is finished. Rows = " + csvRowCount+System.getProperty("line.separator")+
                "Time processing (sec): " + (finish - start);
    }

    //    @Transactional
//    @Override
    public String importToCustomTsFromCsvFileOld() throws IOException, InterruptedException {
        int csvRowCount = 0;
        int countRecordsForUnit = 0;
        int countRecordsForInfo = 0;
        int countRecordsForSaving = 0;

        long start = System.currentTimeMillis() / 1000;

        System.out.println("Import is started, " + LocalDateTime.now());
        List<TsKvCustomEntity> tsRowsForOneCsvRowList = new ArrayList<TsKvCustomEntity>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\CETC\\ML\\dataset_sigma\\sigma_1000.csv"))) {

            String line;
            String[] headerNames = new String[0];
            String customUnitId = Randoms.generate_ID();
            while ((line = br.readLine()) != null) {
                csvRowCount++;
                countRecordsForUnit++;
                countRecordsForInfo++;
                countRecordsForSaving++;

                if (countRecordsForUnit == 1)
                    headerNames = line.split(COMMA_DELIMITER);
                else {
/*                    switch (countRecordsForUnit) {
                        case 2000, 4000, 6000, 8000 ->
                                System.out.println("     countRecordsForUnit="+countRecordsForUnit);
                    }*/
                    if (countRecordsForSaving > numberRecordsForSaving) {
                        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
                        System.out.println("        * Saving Data");
                        tsRowsForOneCsvRowList.clear();
                        countRecordsForSaving = 0;
                    }
                    if (countRecordsForInfo > numberRecordsForInfo) {
//                        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
//                        tsRowsForOneCsvRowList.clear();
                        System.out.println("        Processing CSV file line: " + csvRowCount + ", Unit Line:" +
                                countRecordsForUnit);
                        countRecordsForInfo = 0;
                    }
                    if (countRecordsForUnit > numberRecordsForUnit) {
//                        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
                        countRecordsForUnit = 1;
                        customUnitId = Randoms.generate_ID();
                        System.out.println("New customUnitId: " + customUnitId);
                    }
                    String[] values = line.split(COMMA_DELIMITER);
                    int count = 0;
                    long ts = System.currentTimeMillis();
                    Thread.sleep(1);
                    for (String value : values) {
//                        tsRowsForOneCsvRowList.add(new TsKvCustomEntity(new TsKvId(customUnitId, headerNames[count], ts), Double.valueOf(value)));
                        tsRowsForOneCsvRowList.add(new TsKvCustomEntity(new TsKvId(customUnitId, headerNames[count], ts), Double.valueOf(value)));
//                        entityManager.persist(new TsKvCustomEntity(new TsKvId(customUnitId, headerNames[count], ts), Double.valueOf(value)));
                        count++;
                    }
                }
            }
        }
        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
        long finish = System.currentTimeMillis() / 1000;

        System.out.println("Import is finished. Rows = " + csvRowCount);
        System.out.println("Time processing (sec): " + (finish - start));
        return "Import is finished. Rows = " + csvRowCount+System.getProperty("line.separator")+
                "Time processing (sec): " + (finish - start);
    }


    @Override
    public String copyingCustomTsToTs(String customUnitId, String unitId) {

        List<TsKvCustomEntity> tsCustomList = (ArrayList<TsKvCustomEntity>) tsKvCustomRepository.findTsForUnitId(customUnitId);
        List<TsKvEntity> tsList =  new ArrayList<>();
        String unitIdShort = UUIDConverter.fromTimeUUID(UUID.fromString(unitId));

/*        List<TsKvCustomEntity> newItems = tsCustomList.stream()
                .map(o -> o.getPropValue() == newObject.getPropValue() ? newObject : o)
                .collect(toList());
        putIntoCache(newObject.getKey(), newItems);*/
        long start = System.currentTimeMillis();
        tsCustomList.forEach(item->{
            TsKvId custimId=item.getTsKvId();

            custimId.setUnitId(unitIdShort);
            //tsList.add(new TsKvEntity(custimId,item.getDoubleValue()));
            item.setTsKvId(item.getTsKvId());
        });
        long finish = System.currentTimeMillis();
        System.out.println("Time lists processing (msec): " + (finish - start));
        start = System.currentTimeMillis() / 1000;
        tsKvService.saveAll(tsList);
        finish = System.currentTimeMillis() / 1000;
        System.out.println("Time saving (sec): " + (finish - start));
        return null;
    }


    public void createCustomDataSet() {
        PhmCSV test = new PhmCSV();
//        test.setPath(sourceCustomTsFilePath);
        test.setPath("D:\\CETC\\ML\\dataset_sigma\\sigma_1000.csv");
        System.out.println("Import is started, " + LocalDateTime.now());
        long start = System.currentTimeMillis() / 1000;
        // read data from file
        test.read();

        // get keys
        List<String> keys = test.getKeys();

        test.toMap();
        // get keys values
        List<Map<String, String>> keysValues = test.getKeysValues();
        List<TsKvCustomEntity> tsKvListForSave = new ArrayList<>();
        final String[] customUnitId = {Randoms.generate_ID()};
        final int[] countRecordsForUnit = {0};

        final int[] csvRowCount = {0};
        final int[] countRecordsForInfo = {0};

        keysValues.forEach(e -> {
            csvRowCount[0]++;
            countRecordsForUnit[0]++;
            countRecordsForInfo[0]++;

            long ts = System.currentTimeMillis();

            if (countRecordsForUnit[0] > numberRecordsForUnit) {
                countRecordsForUnit[0] = 1;
                customUnitId[0] = Randoms.generate_ID();
                System.out.println("New customUnitId: " + customUnitId[0]);
            }

            if (countRecordsForInfo[0] > numberRecordsForInfo) {
//                        tsKvCustomRepository.saveAll(tsRowsForOneCsvRowList);
                System.out.println("        Processing CSV file, line: " + csvRowCount[0] + ", Unit Line:" + countRecordsForUnit[0]);
                countRecordsForInfo[0] = 0;
            }

            keys.forEach(keyMap -> {
//                if (!keyMap.equals("timestamp")) {
                TsKvCustomEntity tsKvForSave = new TsKvCustomEntity(new TsKvId(customUnitId[0], keyMap, ts),
                        Double.parseDouble(e.get(keyMap)));
                tsKvListForSave.add(tsKvForSave);
                byte a = 1;
//                }
            });
            tsKvCustomRepository.saveAll(tsKvListForSave);

            tsKvListForSave.clear();
        });

        long finish = System.currentTimeMillis() / 1000;
//        return "Time read (sec): " + (finish - start);

        System.out.println("Import is finished. Rows = " + csvRowCount[0]);
        System.out.println("Time read (sec): " + (finish - start));
    }
}
