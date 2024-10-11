package org.zs.phm3;

import org.zs.phm3.models.ptl.UnitTypePTL;
import org.zs.phm3.models.unit.UnitType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.ptl.UnitTypePTLService;
import org.zs.phm3.service.unit.UnitTypeService;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTypeTest{//} extends AbstractTest{
    @Autowired
    private UnitTypeService unitTypeService;
    @Autowired
    private UnitTypePTLService unitTypePTLService;

    @Test
    public void n1Save() throws PhmException {
        UnitType unitType=new UnitType("Basic Machine Tools2");
//        unitType.setPic_id(1);
        unitTypeService.save(unitType);
//        unitTypeService.save(new UnitType("Basic Machine Tools"));
//        unitTypeService.save(new UnitType("type_2"));
//        unitTypeService.save(new UnitType("type from "+LocalDateTime.now()));
    }
    @Test
    public void n2FindById() throws PhmException {
        UnitType unitTypeFinded = unitTypeService.findById(5);

        assertTrue("error reading project", unitTypeFinded!=null);
    }
    @Test
    public void n3DeleteById() {
        unitTypeService.deleteById(5);
    }

    @Test
    public void n3SavePtl() throws PhmException {
        UnitTypePTL unitType = new UnitTypePTL("Basic Machine Tools2", 2, null, null, null);
//        unitType.setPic_id(1);
        UnitTypePTL unitTypeSaved = unitTypePTLService.save(unitType);
        System.out.println(unitTypeSaved.getId()+", "+unitTypeSaved.getName());
    }


}
