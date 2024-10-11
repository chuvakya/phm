package org.zs.phm3;

import org.zs.phm3.dto.ptl.PtlInput;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.ptl.ModelPTLService;
import org.zs.phm3.service.ptl.UnitTypePTLService;
import org.zs.phm3.service.unit.UnitService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitModelTest {//} extends AbstractTest{

    @Autowired
    private UnitTypePTLService unitTypePTLService;
    @Autowired
    private ModelPTLService modelPTLService;
    @Autowired
    UnitService unitService;

    @Test
    public void n1Save() throws PhmException {
        UnitTypePTL unitType=new UnitTypePTL();
        unitType.setName("Steel Arms");
        unitType.setPictureId(2);

        UnitTypePTL unitTypeSaved= unitTypePTLService.save(unitType);

        ModelPTL model=new ModelPTL();
        model.setName("Rapier");
        model.setUnitTypePTL(unitTypeSaved);
        modelPTLService.save(model);

        UnitDtoInput unitForSave=new UnitDtoInput();
        unitForSave.setId("a6e37589-13a8-11eb-8506-ed00daacb253");
        unitForSave.setPtlInput(new PtlInput(model.getId(),null));
        unitService.save(unitForSave);
    }

    @Test
    public void n5ReAssignModel() throws PhmException {
        UnitDto unitEdited=unitService.findById("cccbc8b9-13a9-11eb-bfc1-9f1d3ba27805");
        System.out.println(unitEdited);

        UnitDtoInput newUnit1=new UnitDtoInput();
        newUnit1.setId("cccbc8b9-13a9-11eb-bfc1-9f1d3ba27805");
        newUnit1.setPtlInput(new PtlInput(10L,null));
        UnitDto unitSaved=unitService.save(newUnit1);
        System.out.println(unitSaved);

/*        newUnit1.setPtlInput(new PtlInput(3L,null));
        UnitDto unitSaved2=unitService.save(newUnit1);
        System.out.println(unitSaved2);*/

/*        newUnit1.setPtlInput(new PtlInput(null,8));
        UnitDto unitSaved3=unitService.save(newUnit1);
        System.out.println(unitSaved3);*/


/*        UnitDto unitEdited=unitService.findById("cccbc8b9-13a9-11eb-bfc1-9f1d3ba27805");
        unitEdited.setModelPtlId((long) 10);
        UnitDto unitSaved=unitService.save(unitEdited);
        System.out.println(unitSaved.getModelPtlId());
        unitEdited.setModelPtlId((long) 3);
        UnitDto unitSaved1=unitService.save(unitEdited);
        System.out.println(unitSaved1.getModelPtlId());*/
    }

/*
    @Test
    public void n2FindById() throws PhmException {
        UnitType unitTypeFinded = unitTypeService.findById(5);

        assertTrue("error reading project", unitTypeFinded!=null);
    }
    @Test
    public void n3DeleteById() {
        unitTypeService.deleteById(5);
    }*/

    /*
    @Test
    public void n3SavePtl() throws PhmException {
        UnitTypePTL unitType = new UnitTypePTL("Basic Machine Tools2", 2, null, "Pavel Chuvak",
                System.currentTimeMillis());
//        unitType.setPic_id(1);
        UnitTypePTL unitTypeSaved = unitTypePTLService.save(unitType);
        System.out.println(unitTypeSaved.getId()+", "+unitTypeSaved.getName());
    }*/
}
