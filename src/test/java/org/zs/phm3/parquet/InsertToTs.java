package org.zs.phm3.parquet;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.zs.phm3.models.ts.TsKvEntity;
//import org.zs.phm3.repository.TsKvRepository;
//import org.zs.phm3.service.TsKvService;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.service.ts.TsKvService;
import org.zs.phm3.util.parquet.csv.PhmCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsertToTs extends TestCase {
    @Autowired
    TsKvService tsKvService;
    @Autowired
    TsKvRepository tsKvRepository;
    @Test
    public void test_ReadFileMap() {
        PhmCSV test = new PhmCSV();
        test.setPath("D:\\Java_CETC\\phm4\\src\\test\\resources\\td2.csv");
        final String unitId = "1ea6ce8997ade2b8e68396ac0f21a39";
        long start = System.currentTimeMillis() / 1000;
        // read data from file
        this.assertTrue(test.read());

        // get keys
        List<String> keys = test.getKeys();

        test.toMap();
        // get keys values
        List<Map<String, String>> keysValues = test.getKeysValues();
        List<TsKvEntity> tsKvListForSave = new ArrayList<>();
        keysValues.forEach(e -> {
            long ts = System.currentTimeMillis();
            keys.forEach(keyMap -> {
//                if (!keyMap.equals("timestamp") && (!keyMap.equals("BIT_code"))) {
                if (!keyMap.equals("timestamp")) {
                    /*
                    TsKvEntity tsKvForSave = new TsKvEntity(new TsKvId(unitId, keyMap, ts),
                            Double.parseDouble(e.get(keyMap)));
                    tsKvListForSave.add(tsKvForSave);*/
//                tsKvService.save((TsKvEntity) tsKvForSave);
                    byte a = 1;
                }
            });
            tsKvRepository.saveAll(tsKvListForSave);

            tsKvListForSave.clear();
        });
//        tsKvService.saveAll(tsKvListForSave);
//        tsKvRepository.saveAll(tsKvListForSave);
        long finish = System.currentTimeMillis() / 1000;
        System.out.println("Time read (sec): " + (finish - start));
    }
}
