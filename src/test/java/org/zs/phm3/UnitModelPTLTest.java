package org.zs.phm3;

import org.zs.phm3.dto.ptl.PtlData;
import org.zs.phm3.models.unit.UnitModel;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.repository.ptl.ModelPTLRepository;
import org.zs.phm3.service.ptl.ModelPTLService;
import org.zs.phm3.service.unit.UnitPtlService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.service.unit.UnitTypeService;
import org.zs.phm3.service.unit.UnitModelService;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitModelPTLTest {
    @Autowired
    private UnitModelService unitModelService;

    @Autowired
    private UnitTypeService unitTypeService;

    @Autowired
    private ModelPTLService modelPTLService;

    @Autowired
    UnitPtlService unitPtlService;

    @Autowired
    UnitService unitService;

    @Test
    public void n1Save() throws PhmException {
        UnitModel unitModelSaved = unitModelService.save(new UnitModel("model_1", unitTypeService.findById(1)));
//        UnitModel unitModelSaved = unitModelService.save(new UnitModel("Manual Mechanical Press 300T",unitTypeService.findUnitTypeById(1).get()));
//        unitModelSaved = unitModelService.save(new UnitModel("Manual C Type Mechanical Power Press 20T",unitTypeService.findUnitTypeById(1).get()));
        assertNotNull("error saving unit", unitModelSaved);
    }

    @Test
    public void n2ReadByKey() throws PhmException {
        UnitModel unitModelFinded = unitModelService.findById(1);
        assertNotNull("error saving unit", unitModelFinded);
    }

    @Test
    public void n3getEmptyModelForUnitType() throws PhmException {
        modelPTLService.getEmptyModelForUnitType(7);
    }

    @Test
    public void n4getPtlData() throws PhmException {
//        String unitId = "226ff6eb-0d36-11eb-82da-4d0ee3527018";
        String unitId = "a6e37589-13a8-11eb-8506-ed00daacb253";
//        String unitId = "cccbc8b9-13a9-11eb-bfc1-9f1d3ba27805";
        PtlData result=unitPtlService.getPtlData(unitId);
        assertNotNull("result is null", result);
    }
}
