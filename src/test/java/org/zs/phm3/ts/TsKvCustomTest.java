package org.zs.phm3.ts;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.models.ts.TsKvCustomEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.zs.phm3.repository.ts.TsKvCustomRepository;
import org.zs.phm3.service.ts.TsKvIntegrationService;
import org.zs.phm3.service.ts.TsKvCustomService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest


public class TsKvCustomTest {
    @Autowired
    TsKvIntegrationService tsKvIntegrationService;
    @Autowired
    TsKvCustomRepository tsKvCustomRepository;
    @Autowired
    TsKvCustomService tsKvCustomService;

/*    @Test
    public void n1_read1() throws IOException {

        importCustomTsKV.readFromExcel();
        var a = 1;
    }*/

    @Test
    public void n2_importToCustomTSFromCsvFile() throws IOException, InterruptedException {
        tsKvIntegrationService.importToCustomTsFromCsvFile();
    }

/*    @Test
    public void n3_createCustomDataSet() throws IOException {
        tsKvIntegrationService.createCustomDataSet();
    }*/
    @Test
    public void n3_getAllUnitIdWithTsData() throws IOException {
        tsKvCustomService.getAllUnitIdWithTsData();
//        importCustomTsKV.createCustomDataSet();
    }
    @Test
    public void n4_Save() throws IOException {

        List<TsKvCustomEntity> tsCustomList = new ArrayList();

        for (int i = 0; i < 50000; i++) {

            tsCustomList.add(new TsKvCustomEntity(new TsKvId("id_"+i,"aaa",System.currentTimeMillis()),1000.00));

        }
        tsKvCustomRepository.saveAll(tsCustomList);


//        TsKvCustomEntity tsKvCustomSaved=tsKvCustomRepository.save(new TsKvCustomEntity(new TsKvId("111","aaa",333),1000.00));
//        System.out.println(tsKvCustomSaved);
    }
    @Test
    public void n4_copyingCustomTsToTs() {
//        n48glhORAjPV4A00  KoftxRpFcVZqrUfA
//        U9GTwaYZYBQQZ8Hx
        tsKvIntegrationService.copyingCustomTsToTs("U9GTwaYZYBQQZ8Hx", "d5a09524-9ffb-11ea-969d-e9c3fe8e7afc");

    }
}
