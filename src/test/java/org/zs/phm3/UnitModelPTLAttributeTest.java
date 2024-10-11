package org.zs.phm3;

import org.zs.phm3.models.unit.UnitModel;
import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitModelAttributeKey;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.unit.UnitModelAttributeService;
import org.zs.phm3.service.unit.UnitModelService;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitModelPTLAttributeTest extends AbstractTest {
    @Autowired
    UnitModelAttributeService unitModelAttributeService;
    @Autowired
    UnitModelService unitModelService;

    @Test
    public void n1Save() throws PhmException {
        UnitModel modelSelected = unitModelService.findById(1);
        UnitModelAttribute unitModelAttributeForSaving =
                new UnitModelAttribute(new UnitModelAttributeKey(modelSelected.getId(), "Type"), "Mechanical Press");
        UnitModelAttribute unitModelAttributeSaved = unitModelAttributeService.save(unitModelAttributeForSaving);
        assertNotNull("error saving unit", unitModelAttributeSaved);
    }

    @Test
    public void n2ReadByKey() throws PhmException {
        UnitModel modelSelected = unitModelService.findById(1);
        UnitModelAttribute unitModelAttributeReaded =
                unitModelAttributeService.findById((new UnitModelAttributeKey(modelSelected.getId(), "Type")));
        System.out.println("Find By id: "+unitModelAttributeReaded);
        assertNotNull("error saving unit", unitModelAttributeReaded);
    }
    @Test
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
    }
}
