//package org.zs.phm3;
//
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.zs.phm3.models.ProjectEntity;
//import org.zs.phm3.models.unit.UnitEntity;
//import org.zs.phm3.service.project.ProjectUnitService;
//import org.zs.phm3.service.unit.UnitService;
//import org.zs.phm3.service.unit.UnitServiceImpl;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static junit.framework.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ProjectUnitTests {
//    @Autowired
//    private ProjectUnitService projectUnitService;
//    @Autowired
//    private UnitService unitService;
//
///*    @Test
//    public void n1SaveProjectNewUnitRelation() {
//        Optional<ProjectEntity> projectFinded=projectUnitService.findProjectById(29);
//
//        UnitEntity unitSaved = unitService.save(new UnitEntity(projectFinded,"test unit " +
//                LocalDateTime.now()));
//
////        Optional<UnitEntity> unit=unitService.findById("1ea41a8feb77bc3b3acd75e24651f04");
////        if (proj.isPresent()||unit.isPresent())
//
//        projectUnitService.saveProjectUnit(projectFinded.get(), unitSaved);
//
//    }*/
//
//    @Test
//    public void n1SaveProjectExistUnitRelation() {
//        Optional<ProjectEntity> projectFinded=projectUnitService.findProjectById(25);
//
////        UnitEntity unitSaved = unitService.save(new UnitEntity("test unit " +
////                LocalDateTime.now()));
//
//        UnitEntity unitFinded=unitService.findById("c95baae6-58a4-11ea-a51d-19cf039fa451");
////       if (proj.isPresent()||unit.isPresent())
//
//        projectUnitService.saveProjectUnit(projectFinded.get(), unitFinded);
//
//    }
///*    @TestConfiguration
//    static class ProjectUnitTestContextConfiguration {
//        @Bean
//        public UnitService unitService() {
//            return new UnitServiceImpl();
//        }
//
////        @Bean
////        public ProjectService projectService() {
////            return new ProjectServiceImpl();
////        }
//    }
//
//    @Autowired
//    private ProjectUnitService projectUnitService;
//    @Autowired
//    private UnitService unitService;
//
//    //    private DeviceEntity deviceSelected;
////    private static String uuidSaved;
////    private static UUID uuidSavedUUID;
//    private static UnitEntity deviceSaved;
//    private static ProjectEntity projectSelected;
//    private static UnitEntity unitChild;
//*//*        @Test
//        public void n1SaveProjec() {
//            ProjectEntity projectSaved = projectUnitService.save(new ProjectEntity("very new Project " +
//                    LocalDateTime.now()));
//            assertNotNull("error saving project", projectSaved);
//        }
//
//    @Test
//    public void n2FindProjectById() {
//        Optional<ProjectEntity> projectFinded = projectUnitService.findProjectById(1);
//        System.out.println(projectFinded.get());
//        assertTrue("error reading project", projectFinded.isPresent());
//    }*//*
//
//    @Test
//    public void n3SaveProjecUnitRelaion() {
//        Optional<ProjectEntity> proj = projectUnitService.findProjectById(1);
//        UnitEntity unitSaved = unitService.save(new UnitEntity("test unit " +
//                LocalDateTime.now()));
//
////        Optional<UnitEntity> unit=unitService.findById("1ea41a8feb77bc3b3acd75e24651f04");
////        if (proj.isPresent()||unit.isPresent())
//        if (proj.isPresent())
//            projectSelected=proj.get();
//            projectUnitService.saveProjectUnit(proj.get(), unitSaved);
//    }
//
//    @Test
//    public void n4SaveProjecUnitRelaionParent() {
////        Optional<ProjectEntity> proj = projectUnitService.findProjectById(1);
//        unitChild = unitService.save(new UnitEntity("test child unit " +
//                LocalDateTime.now()));
//        UnitEntity unitParent = unitService.save(new UnitEntity("test parent unit " +
//                LocalDateTime.now()));
//*//*        Optional<UnitEntity> unit = unitService.findById("1ea48bb78bae61a963ae30a9d3d08bb");
////        Optional<UnitEntity> parent=unitService.findById("1ea48ae367e0879949eb12323926531");
//        Optional<UnitEntity> parent = unitService.findById("1ea48bb491183aca850bd35401e439a");
//
//        if (proj.isPresent() || unit.isPresent() || parent.isPresent())*//*
////            projectUnitService.saveProjectUnitParent(projectSelected, unit.get(), parent.get());
//        projectUnitService.saveProjectUnitParent(projectSelected, unitChild, unitParent);
//    }
//
//    @Test
//    public void n5SetParentById() {
//        UnitEntity unitParent = unitService.save(new UnitEntity("test parent2 unit " +
//                LocalDateTime.now()));
//        projectUnitService.setParentById(projectSelected.getId(),unitChild.getId().toString(),unitParent.getId().toString());
//*//*        Optional<ProjectEntity> proj = projectUnitService.findProjectById(2);
//        Optional<UnitEntity> unit = unitService.findById("1ea48bb78bae61a963ae30a9d3d08bb");
////        Optional<UnitEntity> parent=unitService.findById("1ea48ae367e0879949eb12323926531");
//        Optional<UnitEntity> parent = unitService.findById("1ea48bb80cfafb8a94489fcab74a9cd");
//
//
//        UUIDConverter.fromTimeUUID(UUIDConverter.fromString("1ea48bb80cfafb8a94489fcab74a9cd"));
//
//        if (proj.isPresent() || unit.isPresent() || parent.isPresent())
////            projectUnitService.saveProjectUnitParent(proj.get(),unit.get(),parent.get());
////        projectUnitService.setParentById(proj.get().getId(),unit.get().getId().toString(),parent.get().getId().toString());
//            projectUnitService.setParentById(proj.get().getId(), unit.get().getId().toString(), UUIDConverter.fromTimeUUID(parent.get().getId()));*//*
//    }*/
//
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
//
//}
