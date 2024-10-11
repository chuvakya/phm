package org.zs.phm3;

import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.UnitAttributeDtoInput;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.models.unit.UnitEntity;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.repository.UomEntityRepository;
import org.zs.phm3.repository.unit.UnitAttributeRepository;
import org.zs.phm3.repository.unit.UnitAttributeSQLRepository;
import org.zs.phm3.service.unit.UnitAttributeService;
import org.zs.phm3.service.unit.UnitService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitAttributeTest extends AbstractTest {
    @Autowired
    UnitAttributeService unitAttributeService;
    @Autowired
    UnitService unitService;
    @Autowired
    UomEntityRepository uomRepository;
    @Autowired
    UnitAttributeRepository unitAttributeRepository;
    @Autowired
    UnitAttributeSQLRepository unitAttributeSQLRepo;

    @Test
    public void n1Save() throws PhmException {
        ArrayList<UnitEntity> unitList = (ArrayList<UnitEntity>) unitService.getAll();
        UnitEntity unitSelected = unitList.get(12);
        System.out.println(unitSelected.getId().toString());
        System.out.println(UUIDConverter.fromTimeUUID(unitSelected.getId()));

//        UnitAttribute unitAttributeSaved=unitAttributeService.save(new UnitAttribute(new UnitAttributeKey(unitSelected.getId().toString(),"YYY1"),"YYY"));
//        UnitAttribute unitAttributeSaved=unitAttributeService.save(new UnitAttribute(new UnitAttributeKey(unitSelected.getId().toString(),"YYX3"),20.1,null));

        UnitAttributeDtoInput unitAttributeDtoInput = new UnitAttributeDtoInput();
//        unitAttributeDtoInput.setAttributeKey("test_key");
//        unitAttributeDtoInput.setUnitId(unitSelected.getId().toString());
//        unitAttributeDtoInput.setDoubleValue(1200800.5796);
//        unitAttributeDtoInput.setUomInput(24);
//      *** Boolean ***
        unitAttributeDtoInput.setAttributeKey("test_bool");
        unitAttributeDtoInput.setUnitId(unitSelected.getId().toString());
        unitAttributeDtoInput.setBooleanValue(true);
        UnitAttribute unitAttributeSaved = unitAttributeService.save(unitAttributeDtoInput);
//      *** Long ***
        UnitAttributeDtoInput unitAttributeDtoInputL = new UnitAttributeDtoInput();
        unitAttributeDtoInputL.setAttributeKey("test_long");
        unitAttributeDtoInputL.setUnitId(unitSelected.getId().toString());
        unitAttributeDtoInputL.setLongValue(999999999999999997L);
        unitAttributeDtoInputL.setType(3);
        unitAttributeDtoInputL.setUomInput(46);
        UnitAttribute unitAttributeSavedL = unitAttributeService.save(unitAttributeDtoInputL);
//      *** String ***
        UnitAttributeDtoInput unitAttributeDtoInputS = new UnitAttributeDtoInput();
        unitAttributeDtoInputS.setAttributeKey("test_string");
        unitAttributeDtoInputS.setUnitId(unitSelected.getId().toString());
        unitAttributeDtoInputS.setStrValue("999999999999999997L");

        UnitAttribute unitAttributeSavedS = unitAttributeService.save(unitAttributeDtoInputS);
//        UnitAttribute unitAttributeSaved=unitAttributeService.save(new UnitAttribute(new UnitAttributeKey(unitSelected.getId().toString(),
//                "YYY3"),20.1,
//                unitAttributeService.uomGetById(24)));
        assertNotNull("error saving unit attribute", unitAttributeSaved);
    }

    @Test
    public void n1SaveById() throws PhmException {

        String unitId = "823a825d-06e2-11eb-82ea-7b496179312e";
        UnitAttributeDtoInput unitAttributeDtoInput = new UnitAttributeDtoInput();
//      *** Boolean ***
        unitAttributeDtoInput.setAttributeKey("test_bool");
        unitAttributeDtoInput.setUnitId(unitId);
        unitAttributeDtoInput.setBooleanValue(true);
        UnitAttribute unitAttributeSaved = unitAttributeService.save(unitAttributeDtoInput);
//      *** Long ***
        UnitAttributeDtoInput unitAttributeDtoInputL = new UnitAttributeDtoInput();
        unitAttributeDtoInputL.setAttributeKey("test_long");
        unitAttributeDtoInputL.setUnitId(unitId);
        unitAttributeDtoInputL.setLongValue(999999999999999997L);
        unitAttributeDtoInputL.setType(3);
        unitAttributeDtoInputL.setUomInput(46);
        UnitAttribute unitAttributeSavedL = unitAttributeService.save(unitAttributeDtoInputL);
//      *** String ***
        UnitAttributeDtoInput unitAttributeDtoInputS = new UnitAttributeDtoInput();
        unitAttributeDtoInputS.setAttributeKey("test_string");
        unitAttributeDtoInputS.setUnitId(unitId);
        unitAttributeDtoInputS.setStrValue("999999999999999997L");

        UnitAttribute unitAttributeSavedS = unitAttributeService.save(unitAttributeDtoInputS);
//        UnitAttribute unitAttributeSaved=unitAttributeService.save(new UnitAttribute(new UnitAttributeKey(unitSelected.getId().toString(),
//                "YYY3"),20.1,
//                unitAttributeService.uomGetById(24)));
        assertNotNull("error saving unit attribute", unitAttributeSaved);
    }

    @Test
    public void n2ReadByKey() throws PhmException {
        ArrayList<UnitEntity> unitList = (ArrayList<UnitEntity>) unitService.getAll();
        UnitEntity unitSelected = unitList.get(4);
//        UnitAttribute unitAttributeReaded=unitAttributeService.getAllByUnitId(new UnitAttributeKey());
        UnitAttribute unitAttributeReaded = unitAttributeService.findById(new UnitAttributeKey(unitSelected.getId().toString(), "YYY"));
        System.out.println(unitSelected.getId().toString() + ", " + unitAttributeReaded);
        assertNotNull("error reading unit attribute", unitAttributeReaded);
    }

    @Test
    public void n3ReadAllByUnitId() {
        ArrayList<UnitEntity> unitList = (ArrayList<UnitEntity>) unitService.getAll();
        UnitEntity unitSelected = unitList.get(2);
        ArrayList<UnitAttribute> unitAttributeReaded = (ArrayList<UnitAttribute>) unitAttributeService.getAllByUnitId(unitSelected.getId().toString());
        unitAttributeReaded.forEach(item -> System.out.println("+——————+ " + item.toString()));
    }

    @Test
    public void n4DeleteById() throws PhmException {
        unitAttributeService.deleteById(new UnitAttributeKey("06aa8437-4e36-11ea-a143-4939fc42cecd", "k06"));
    }

    @Test
    public void getAllForTableByUnitId() {
        List<UnitAttribute> c1 = unitAttributeService.getAllByUnitId("e59a8dec-74e7-11ea-8ea6-4be4e5f710e2");
        final String shortUnitId = UUIDConverter.fromTimeUUID(UUID.fromString("e59a8dec-74e7-11ea-8ea6-4be4e5f710e2"));
//        List<UnitAttributeDtoOutput> c2=unitAttributeRepository.getAllForTableByUnitId(shortUnitId);
        List<Object[]> unitAttributeTables = unitAttributeSQLRepo.getAllForTableByUnitId(shortUnitId);
        byte a = 1;
    }

/*    @Test
    public void n1Save() {
        Optional<UnitModel> modelSelected = unitModelService.findById(14);
        UnitModelAttribute unitModelAttributeForSaving =
                new UnitModelAttribute(new UnitModelAttributeKey(modelSelected.get().getId(), "Type"), "Mechanical Press");
        UnitModelAttribute unitModelAttributeSaved = unitModelAttributeService.save(unitModelAttributeForSaving);
        assertNotNull("error saving unit", unitModelAttributeSaved);
    }

    @Test
    public void n2ReadByKey() {
        Optional<UnitModel> modelSelected = unitModelService.findById(1);
        Optional<UnitModelAttribute> unitModelAttributeReaded =
                unitModelAttributeService.findById((new UnitModelAttributeKey(modelSelected.get().getId(), "Type")));
        System.out.println("Find By id: "+unitModelAttributeReaded);
        assertNotNull("error saving unit", unitModelAttributeReaded.get());
    }*/

/*    @Test
    public void n3ReadByKey() {
        List<UnitModelAttribute> unitModelAttributeList =
                unitModelAttributeService.getAll();
        System.out.println("UnitModelAttribute List: "+unitModelAttributeList);
        assertNotNull("error saving unit", unitModelAttributeList.size());
    }
    @Test
    public void n4IsNewCheck() {
        System.out.println(unitModelAttributeService.isNew("Type",14));
    }

    @Test
    public void n5getAllByUnitModelId() {
        System.out.println(unitModelAttributeService.getAllByUnitModelId(14));
    }*/
}
