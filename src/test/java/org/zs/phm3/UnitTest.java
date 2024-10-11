package org.zs.phm3;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.config.GlobalProperties;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.service.FileService;
import org.zs.phm3.service.project.ProjectService;
import org.zs.phm3.service.unit.UnitService;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("postgrestest")

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTest {
    @Autowired
    private UnitService unitService;
    @Autowired
    private GlobalProperties globalProperties;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private FileService fileService;

    private UnitEntity unitSelected;
    private static String uuidSaved;
    //    private static UUID uuidSavedUUID;
    private static UnitDto unitSavedDto;
    private static String uuidSavedUUIDStr;

    @Test
    public void n1Save() throws PhmException {
        System.out.println("unitService = " + unitService);
//            deviceRepository.save(new DeviceEntity("simple", "a1"));
//        ProjectEntity project=projectService.findProjectById(53);

//        UnitDtoInput newUnitDto = new UnitDtoInput(1, "test unit V .13" + LocalDateTime.now(), "",
//                new PtlInput(1L, null));

        UnitDtoInput newUnitDto = new UnitDtoInput(1, "test unit V .13" + LocalDateTime.now(), "");
        newUnitDto.setPic_id(1);
        newUnitDto.setPicbckgr_id(2);
        newUnitDto.setParentId(UUIDConverter.fromString(projectService.findProjectById(1).getFirstUnitId()).toString());
        unitSavedDto = unitService.save(newUnitDto);

//        System.out.println("Saved Unit: " + unitSaved.toString());
        System.out.println("Saved Unit: " + unitSavedDto.getId().toString() + ", " + unitSavedDto.getName());

        uuidSaved = unitSavedDto.getId().toString();
//        uuidSavedUUID = unitSaved.getId();
//        uuidSavedUUIDStr = uuidSavedUUID.toString();
        assertNotNull("error saving unit", unitSavedDto.getId());
    }

    @Test
    public void n1SaveHierarchy() throws PhmException, NotFoundException {
//        System.out.println("unitService = " + unitService);

        UnitDto unit1Saved = unitService.save(new UnitDtoInput(1, "Unit #1_1", ""));
        System.out.println(unit1Saved.getId());
        UnitDtoInput unitDto2 = new UnitDtoInput(1, "Unit #1_2", "");
        String parentId = unit1Saved.getId().toString();
        unitDto2.setParentId(parentId);
        UnitDto unit2Saved = unitService.save(unitDto2);
        System.out.println(unit2Saved.getId());

        UnitDtoInput unitDto3 = new UnitDtoInput(1, "Unit #1_3", "");
        String parentId2 = unit2Saved.getId().toString();
        unitDto3.setParentId(parentId2);
        UnitDto unit3Saved = unitService.save(unitDto3);

/*        ProjectEntity project=projectService.findProjectById(30);
        Integer projectId=projectService.findProjectById(30).getId();

        UnitEntity unit1Saved = unitService.save(new UnitDto(30, "Unit #1"));
        UnitEntity unit2 = new UnitEntity(project, "Unit #2");
        unit2.setParent(unit1Saved);
        UnitEntity unitS2aved = unitService.save(projectId,unit2);
        UnitEntity unit3 = new UnitEntity(project, "Unit #3");
        unit3.setParent(unit1Saved);
        unit3.setIcon(fileService.findById(3));
        unitService.save(projectId,unit3);
        UnitEntity unit4 = new UnitEntity(project, "Unit #4");
        unit4.setParent(unitS2aved);
        unitService.save(projectId,unit4);*/
    }

    @Test
    public void n2Update() throws PhmException {
        UnitDto unitReaded = unitService.findById("690a1edb-62a8-11ea-9a7e-55eff7135424");//"658e401c-57e5-11ea-87d4-2b385a4dea3c");
        unitReaded.setName(unitReaded.getName() + " xxxUPDATED");
        char a = 0;

    }


    @Test
    public void n2GetById() {
        String uuidReaded = null;

        UnitDto unitOptional = unitService.findById("a8bde4cf-6854-11ea-b55b-7dd64be6dc3d");
//        Optional<UnitEntity> unitOptional = unitService.findById(UUIDConverter.fromTimeUUID(UUID.fromString("f21e5a37-4e35-11ea-a4a8-8fb505f17004")));
//        Optional<UnitEntity> unitOptional = unitService.findById(uuidSavedUUIDStr);

    }

    @Test
    public void n4AssignParent() {
        unitService.assaignParent("1eab527fb1ff9569a047f3a3b024f37", "1eab5463cae10faa7ef093ddd96fca4");
    }


    @Test
    public void n6ShowUnitdTest() {
        unitService.showUnitId();
    }
}

