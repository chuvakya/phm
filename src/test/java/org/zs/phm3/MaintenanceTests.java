package org.zs.phm3;

import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.OperationTypeEntity;
import org.zs.phm3.models.maintenance.TaskHeader;
import org.zs.phm3.repository.maintenance.TaskHeaderRepository;
import org.zs.phm3.service.maintenance.OperationTypeService;
import org.zs.phm3.service.maintenance.RegisterEntityService;
import org.zs.phm3.service.maintenance.TaskService;
import org.zs.phm3.service.unit.UnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.util.mapping.PhmUtil;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MaintenanceTests {
    @Autowired
    OperationTypeService operationTypeService;

    @Autowired
    TaskService taskService;

    OperationTypeEntity selectedOperation;
    @Autowired
    UnitService unitService;
    @Autowired
    TaskHeaderRepository taskHeaderRepository;
    @Test
    public void n1OperationTypeSave() throws PhmException, NotFoundException, JsonProcessingException {
        OperationTypeEntity tuningOper = operationTypeService.save(new OperationTypeEntity("Tuning", "1"));
        OperationTypeEntity repairsOper = operationTypeService.save(new OperationTypeEntity("Repairs", "2"));
        OperationTypeEntity replacementOper = operationTypeService.save(new OperationTypeEntity("Replacement", "2Z"));
        selectedOperation=replacementOper;
//        OperationType newItem = operationTypeService.save(new OperationType("Replacement", ""));
    }

/*    @Test
    public void n3RegisterEntityCreateNew() throws PhmException, NotFoundException, JsonProcessingException {
        registerEntityService.createNew("53626921-185f-11eb-88ec-4b3ee8aa1759", operationTypeService.findOperationById(20), LocalDateTime.now().plusMonths(6));
    }*/
@Test
    public void n1createPlannedTasks() throws PhmException {
        taskService.createPlannedTasks();
}

    @Test
    public void n4TaskRecalculateFailure() throws PhmException, NotFoundException, JsonProcessingException {
        taskService.TaskRecalculateFailure("2d5a9c63-0d36-11eb-836e-7dba77163d85", "Failure");
    }
    @Test
    public void n4CloseTask() throws PhmException, NotFoundException, JsonProcessingException {
    }

    @Test
    public void n1FullFunctionalityTest() throws PhmException, NotFoundException, JsonProcessingException {
        List<TaskHeader> tasksList=taskService.getAll();
        System.out.println(tasksList.size());
        tasksList.forEach(task -> taskService.deleteById(task.getId()));
        List<TaskHeader> tasksList1=taskService.getAll();
        System.out.println(tasksList1.size());
        taskService.createPlannedTasks();
        List<TaskHeader> tasksList2=taskService.getAll();
        System.out.println(tasksList2.size());
//        d0d96beb-24c1-11eb-8249-e3d672c808bd | 1eb24c1d0d96beb8249e3d672c808bd
        TaskHeader selTask=taskService.findByUnitId("d0d96beb-24c1-11eb-8249-e3d672c808bd");
//        System.out.println(selTask);
        selTask.setDateCreated(selTask.getDateCreated().minusYears(1));
        TaskHeader closedTask=taskService.CloseTask(selTask.getId(), LocalDateTime.now(), LocalDateTime.now(), null,
                "Closed");
        taskService.createPlannedTasks();

        TaskHeader editedTask=taskHeaderRepository.findByUnitIdAndDateCreatedIsMax(PhmUtil.toShortUUID("d0d96beb-24c1-11eb-8249-e3d672c808bd"));
        editedTask.setDateCreated(editedTask.getDateCreated().minusMonths(6));

//        TaskHeader recalclTask=
                taskService.recalculateTaskFromML(editedTask.getUnit().getId().toString(), LocalDateTime.now().plusSeconds(8640000));
        taskService.recalculateTaskFromML(editedTask.getUnit().getId().toString(), LocalDateTime.now().plusSeconds(8640000/2));

        taskService.TaskRecalculateFailure(editedTask.getUnit().getId().toString(), "Alarm!!!");
        TaskHeader closedTask2=taskService.CloseTask(editedTask.getId(), LocalDateTime.now(), LocalDateTime.now(), null,
                "Closed");
    }
}
