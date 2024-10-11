package org.zs.phm3;

import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.models.ProjectEntity;
import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeEntityId;
import org.zs.phm3.models.SchemeType;
import org.zs.phm3.models.unit.UnitEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.SchemeService;
import org.zs.phm3.service.project.ProjectService;
import org.zs.phm3.service.unit.UnitService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SchemeTest {//extends AbstractTest{
    @Autowired
    private SchemeService schemeService;

    @Autowired
    private UnitService unitService;
    @Autowired
    private ProjectService projectService;
    @Test
    public void n1Save() {

        ObjectMapper mapper = new ObjectMapper();
        try {
//            JsonNode jsonNode = mapper.readTree("{\"key\":\"value\"}");
            JsonNode jsonNode = mapper.readTree(
                    "{\"firstName\": \"John\"," +
                            "\"lastName\": \"Smith\"," +
                            "\"isAlive\": true," +
                            "\"age\": 27," +
                            "\"address\": {" +
                            "\"streetAddress\": \"21 2nd Street\"," +
                            "\"city\": \"New York\"," +
                            "\"state\": \"NY\"," +
                            "\"postalCode\": \"10021-3100\"" +
                            "}," +
                            "\"phoneNumbers\": [" +
                            "{" +
                            "\"type\": \"home\"," +
                            "\"number\": \"212 555-1234\"" +
                            "}," +
                            "{" +
                            "\"type\": \"office\"," +
                            "\"number\": \"646 555-4567\"" +
                            "}," +
                            "{" +
                            "\"type\": \"mobile\"," +
                            "\"number\": \"123 456-7890\"" +
                            "}" +
                            "]," +
                            "\"children\": []," +
                            "\"spouse\": null" +
                            "}");
//            Optional<ProjectEntity> proj = projectUnitService.findProjectById(49);
//            Optional<UnitEntity> unit = unitService.findById("1ea496f42259c60b6a54f0573658fcb");
//            Optional<UnitEntity> unit = unitService.findById("f1e050bf-4981-11ea-9487-3f73eb66f5f3");
//            Optional<UnitEntity> unit = unitService.findById("e50a39d7-4982-11ea-bbb0-81b174777dc0");
            List<UnitEntity> unitList = unitService.getAll();
            UnitEntity unitSelected = unitList.get(0);

            SchemeEntity schemeSaved = schemeService.save(new SchemeEntity(
                    unitSelected.getId().toString(), SchemeType.FOURTH,
                    "scheme from date " + LocalDateTime.now(), jsonNode, "ZZZ"));
//            event.setBody(jsonNode);
            System.out.println("schemeSaved: " + schemeSaved.toString());
            assertNotNull("error saving scheme", schemeSaved);
        } catch (IOException e) {
//            log.error(e.getMessage(), e);
        }

//        SchemeEntity schemeSaved=schemeService.save(new SchemeEntity("111", SchemeType.FIRST));
        byte a = 0;

    }

    @Test
    public void n1SaveFull() throws JsonProcessingException {
/*        SchemeEntity schemeSaved = schemeService.saveFull(1,
                "690a1edb-62a8-11ea-9a7e-55eff7135424",
                SchemeType.FIRST, "{\"key3\":\"value2\"}", "LabelZZZ");*/
        ObjectMapper mapper = new ObjectMapper();
        SchemeEntity schemeSaved = schemeService.saveFull(2,
                "2376be0a-734a-11ea-bdaf-7d3c24e0754e",
                SchemeType.FIRST, mapper.readTree("{\"key3\":\"value2\"}"), "ZZZ", "");

        assertNotNull("error saving scheme", schemeSaved);
    }

    @Test
    public void n2ReadByKey() throws PhmException {
        Optional<SchemeEntity> schemeReaded = null;
        ProjectEntity proj = projectService.findProjectById(1);
        UnitDto unit = unitService.findById("f2462d89-4e35-11ea-a4a8-ab274ddb3eb3");

        schemeReaded = schemeService.findById(new SchemeEntityId(unit.getId().toString(), SchemeType.THIRD));
        System.out.println(schemeReaded.get().getBody());
//        }
        assertNotNull("error reading scheme", schemeReaded.get());
    }

/*    @Test
    public void n3DeleteByKey() {
//        Optional<SchemeEntity> schemeReaded=null;
        Optional<ProjectEntity> projOpt = projectUnitService.findProjectById(2);
        UnitEntity unitOpt = unitService.findById("e50a39d7-4982-11ea-bbb0-81b174777dc0");
//        if (projOpt.isPresent() || unitOpt.isPresent()) {

        ProjectEntity proj = projOpt.get();
        UnitEntity unit = unitOpt;
        schemeService.deleteById(new SchemeEntityId(unit.getId().toString(), SchemeType.FOURTH));
//        assertNotNull("error reading scheme", schemeReaded.get());
    }*/

    @Test
    public void n4GetAllByUnitId() {
        List<SchemeEntity> schemeList = schemeService.getAllByUnitId("f21e5a37-4e35-11ea-a4a8-8fb505f17004");
        schemeList.forEach(item -> System.out.println("+——————+ " + item.toString()));
        char a = 1;
    }

    @Test
    public void n4GetSchemeForProject() {
        List<String> scheme=schemeService.getSchemeForProject(30);
        System.out.println(scheme);
    }
}


