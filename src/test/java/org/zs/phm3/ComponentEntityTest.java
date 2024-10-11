package org.zs.phm3;

import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.models.ComponentEntity;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.repository.unit.ComponentRepository;
import org.zs.phm3.service.unit.ComponentService;
import org.zs.phm3.service.unit.UnitService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComponentEntityTest {

    @Autowired
    ComponentRepository componentEntityRepository;
    @Autowired
    ComponentService componentService;
    @Autowired
    UnitService unitService;
    @Test
    public void n1_save() throws PhmException {
        componentEntityRepository.save(new ComponentEntity(UUIDConverter.fromTimeUUID(unitService
                .findByIdReturnEntity("d5a09524-9ffb-11ea-969d-e9c3fe8e7afc",true)
                .getId()),"test",""));
    }

    @Test
    public void n2_multiplyComponent() throws PhmException {//(String unitId, String parentUnitId, Integer number){
        String unitId="50cce2bd-a994-11ea-90de-e5dd1e5288b5";
        componentService.multiplyComponent(unitId,"",2);
    }
}
