package org.zs.phm3.ts;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.service.ts.TsKvService;
import org.zs.phm3.service.ts.TsKvServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TsKvTest {
    @TestConfiguration
    static class TsKvTestContextConfiguration {
        @Bean
        public TsKvService TsKvService() {
            return new TsKvServiceImpl();
        }
    }

    @Autowired
    private TsKvService tsKvService;

    private static UnitEntity deviceSaved;
//    @Before
//    public void setUp() {
//        System.out.println("1111");
//    }

    @Test
    public void n1Save() {
        /*
        TsKvEntity ts1 = new TsKvEntity(new TsKvId("5555", "jjjj" + LocalDateTime.now()
                , System.currentTimeMillis()), true);
        tsKvService.save(ts1);
        TsKvEntity ts2 = new TsKvEntity(new TsKvId("5551", "ffff" + LocalDateTime.now(),
                System.currentTimeMillis()), new Double(2.53));
        tsKvService.save(ts2);
        TsKvEntity ts3 = new TsKvEntity(new TsKvId("5552", "ffff" + LocalDateTime.now(),
                System.currentTimeMillis()), new Long(700000000));
        tsKvService.save(ts3);
        TsKvEntity ts4 = new TsKvEntity(new TsKvId("5559", "ffff" + LocalDateTime.now(),
                System.currentTimeMillis()), "RRRRRRRRRRRRRRR");
        tsKvService.save(ts4);*/
    }

    @Test
    public void n2ReadAll() {
        ArrayList<TsKvEntity> tsKvList = (ArrayList<TsKvEntity>) tsKvService.getAllTsKv();
        tsKvList.forEach(item -> System.out.println("+——————+ " + item.toString()));
        assertNotNull(tsKvList);
        assertTrue(tsKvList.size() > 0);
    }

    @Test
    public void n3exportToCsv() {
//        +———+ test unit 2020-03-23T12:28:05.000074200 / 997ade2b-6ce8-11ea-8e68-396ac0f21a39 / 1ea6ce8997ade2b8e68396ac0f21a39
//        +———+ Unit #1_1 / 99827f4d-6ce8-11ea-8e68-d5a5ad9c3684 / 1ea6ce899827f4d8e68d5a5ad9c3684
        tsKvService.exportToCsv("997ade2b-6ce8-11ea-8e68-396ac0f21a39");
    }
}

