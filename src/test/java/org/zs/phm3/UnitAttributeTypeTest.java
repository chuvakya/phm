package org.zs.phm3;

import org.zs.phm3.models.unit.UnitAttributeType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.repository.unit.UnitAttributeTypeRepository;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    @SpringBootTest

    public class UnitAttributeTypeTest {
        @Autowired
        UnitAttributeTypeRepository unitAttributeTypeRepository;

        @Test
        public void n1_save() {
//            List<String> list = new ArrayList<String>(Arrays.asList("Status","Environmental","Signal",
//                    "Calibration","BIT", "Business data","Log information"));
            List<UnitAttributeType> unitAttributeTypesList = new ArrayList<>();
            unitAttributeTypesList.add(new UnitAttributeType("Status"));
            unitAttributeTypesList.add(new UnitAttributeType("Environmental"));
            unitAttributeTypesList.add(new UnitAttributeType("Signal"));
            unitAttributeTypesList.add(new UnitAttributeType("Calibration"));
            unitAttributeTypesList.add(new UnitAttributeType("BIT"));
            unitAttributeTypesList.add(new UnitAttributeType("Business data"));
            unitAttributeTypesList.add(new UnitAttributeType("Log information"));
            unitAttributeTypeRepository.saveAll(unitAttributeTypesList);
        }
}
