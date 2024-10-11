package org.zs.phm3;

//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
import org.zs.phm3.models.ProjectEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
//import org.zs.phm3.repository.project.ProjectUnitSQLRepository;
import org.zs.phm3.service.project.ProjectService;

import java.time.LocalDateTime;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTests {
////    @TestConfiguration
////    static class ProjectUnitTestContextConfiguration {
////        @Bean
////        public UnitService unitService() {
////            return new UnitServiceImpl();
////        }
//
////        @Bean
////        public ProjectService projectService() {
////            return new ProjectServiceImpl();
////        }
////    }
//
    @Autowired
    private ProjectService projectsService;
//    @Autowired
//    private ProjectUnitSQLRepository projectUnitSQLRepository;
//    @Autowired
//    private UnitRepository unitRepository;
////    @Autowired
////    private UnitService unitService;
//
//    //    private DeviceEntity deviceSelected;
////    private static String uuidSaved;
////    private static UUID uuidSavedUUID;
////    private static UnitEntity deviceSaved;

    @Test
    public void n1Save() throws PhmException, NotFoundException, JsonProcessingException {
//            ProjectEntity projectSaved = projectsService.save(new ProjectEntity("very new Project " +
//                    LocalDateTime.now(),""));
        ProjectEntity projectSavedDescr = projectsService.save(new ProjectEntity("new Project w/Descr" +
                LocalDateTime.now(), "description "));
        assertNotNull("error saving project", projectSavedDescr);
    }

//    @Test
//    public void n1Update() throws PhmException, NotFoundException {
///*        Optional<ProjectEntity> projectFinded = projectsService.findProjectById(7);
//        if (projectFinded.isPresent()) {
//            ProjectEntity projectEdited= projectFinded.get();
//            projectEdited.setName("fjfuythgjhgghghg5555");
//            ProjectEntity projectSaved = projectsService.save(projectEdited);
//            assertNotNull("error saving project", projectSaved);
//        }*/
//        ProjectEntity projectFinded = projectsService.findProjectById(71);
//
////        projectFinded.setName("proj#49");
////        projectFinded.setDescription("descr#49");
//        ProjectEntity projectSaved = projectsService.save(projectFinded);
//        assertNotNull("error saving project", projectSaved);
//
//    }
//
//
//    @Test
//    public void n2FindById() throws PhmException {
//        ProjectEntity projectFinded = projectsService.findProjectById(10);
//        System.out.println(projectFinded.getScheme());
//        assertNotNull("error reading project", projectFinded);
//    }
//    @Test
//    public void n2getAll() {
//        List<ProjectEntity> projectsList = projectsService.getAllProjects();
//        System.out.println(projectsList.size());
//        assertTrue("error reading project", projectsList.size()>0);
//    }
//    @Test
//    public void n3DeleteById() {
//        projectsService.deleteById(9);
//    }
//
//
///*    @Test
//    public void n9GetRootUnitForProject() {
////        projectsService.deleteById(9);
//        String rootEntity = unitRepository.getRootUnitIdForProject(159);
//
//        byte a = 0;
//    }*/
//
///*    @Test
//    public void n3SaveProjecUnitRelaion() {
//        Optional<ProjectEntity> proj=projectsService.findProjectById(1);
//        Optional<UnitEntity> unit=unitService.findById("1ea41a8feb77bc3b3acd75e24651f04");
//        if (proj.isPresent()||unit.isPresent())
//        projectsService.saveProjectUnit(proj.get(),unit.get());
//    }
//    @Test
//    public void n4SaveProjecUnitRelaionParent() {
//        Optional<ProjectEntity> proj=projectsService.findProjectById(2);
//        Optional<UnitEntity> unit=unitService.findById("1ea41a937dd7533939951a72ad337e6");
//        Optional<UnitEntity> parent=unitService.findById("1ea41a8feb77bc3b3acd75e24651f04");
//        if (proj.isPresent()||unit.isPresent()||parent.isPresent())
//            projectsService.saveProjectUnitParent(proj.get(),unit.get(),parent.get());
//    }*/
//
///*    @Test
//    public void n3assignUnitToProject() {
//        Optional<ProjectEntity> proj = projectsService.findById(3);
//        Optional<UnitEntity> dev = deviceService.findById("1ea3e8a6421ff88a735eb37f468ee17");
//
//        assertTrue("error reading project", dev.isPresent());
//        projectsService.assignDeviceToProject(proj.get(), dev.get());
//    }*/
//
///*    @Test
//    public void n5assignParentToDevice() {
////        Optional<ProjectEntity> proj = projectsService.findById(3);
//        Optional<ProjectEntity> optProj=projectsService.findProjectById(2);
//        Optional<UnitEntity> optUnit=unitService.findById("1ea41a937dd7533939951a72ad337e6");
//        Optional<UnitEntity> optParent=unitService.findById("1ea41a8feb77bc3b3acd75e24651f04");
//
//
//
//        if (optProj.isPresent()||optUnit.isPresent()||optParent.isPresent()) {
//            UnitEntity parent=optParent.get();
//            UnitEntity unit=optUnit.get();
//            ProjectEntity proj=optProj.get();
//            Optional<ProjectUnitRelation> optProjUnitRelation=projectsService.findRelationById(proj,unit);
//            System.out.println("parent: "+optProjUnitRelation.get().getParent());
//
//            ProjectUnitRelation optProjSaved=projectsService.assignParentToUnit(proj, unit,parent);
//            System.out.println("parent: "+optProjSaved.getParent());
//
////            ProjectUnitRelation pur=optProjUnitRelation.get();
////            pur.setParent(parent);
//
////            ProjectUnitRelation pur=pur.setParent(parent);
////            ProjectUnitRelation pur1=pur.setParent(parent);
//
//        }
//
//
//*//*        projectsService.saveProjectDevice();
//
//        projectsService.assignParentToDevice(proj.get(), deviceService.findById("1ea3e8a6421ff88a735eb37f468ee17").get(),
//                deviceService.findById("1ea3e89efa5710bb5f219ac07ea9a3c").get());*//*
//    }*/
//
///*    @Test
//    public void n6SaveProjecOnlyRelaion() {
//        ProjectEntity projectSaved = projectsService.save(new ProjectEntity("new only Project " +
//                LocalDateTime.now()));
//        assertNotNull("error saving project", projectSaved);
//        ArrayList<UnitEntity> serviceUnitList= (ArrayList<UnitEntity>) unitService.findByServiceTrue();
//        UnitEntity serviceUnit=serviceUnitList.get(0);
//        ProjectUnitRelation relationSaved=projectsService.saveProjectUnit(projectSaved,serviceUnit);
//        assertNotNull("error saving relation", relationSaved);
//
//
////        Optional<UnitEntity> unit=unitService.findById("1ea41a937dd7533939951a72ad337e6");
////        Optional<UnitEntity> parent=unitService.findById("1ea41a8feb77bc3b3acd75e24651f04");
////        if (proj.isPresent()||unit.isPresent()||parent.isPresent())
////            projectsService.saveProjectOnlyRelation(projectSaved);
//    }*/

}
