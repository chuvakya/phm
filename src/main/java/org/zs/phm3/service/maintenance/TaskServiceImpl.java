package org.zs.phm3.service.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.*;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.maintenance.RegisterRepository;
import org.zs.phm3.repository.maintenance.TaskHeaderRepository;
import org.zs.phm3.repository.maintenance.TaskLineRepository;
import org.zs.phm3.repository.unit.UnitAttributeSQLRepository;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.service.user.UserServiceImpl;
import org.zs.phm3.util.mapping.PhmUtil;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.zs.phm3.exception.PhmErrorCode.ITEM_NOT_FOUND;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskHeaderRepository taskHeaderRepository;

    @Autowired
    TaskLineRepository taskLineRepository;

    @Autowired
    UnitService unitService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UnitAttributeSQLRepository unitAttributeRepository;
    @Autowired
    OperationTypeService operationTypeService;

/*    private RegisterEntity createNew(String unitId, OperationTypeEntity typePlaned,
                                     LocalDateTime plannedDateOperationStart) throws PhmException {
        UnitEntity unit = unitService.findByIdReturnEntity(unitId, false);
        return registerRepository.save(RegisterEntity.createNewTask(unit, userService.getActualUser(), typePlaned,
                plannedDateOperationStart, ""));
    }*/

    @Override
    public void recalculateTaskFromML(String unitId, LocalDateTime recalculatedDateStart) throws PhmException {
        String specifiedUnitId = unitId;
        if (unitId.length() == 36)
            specifiedUnitId = PhmUtil.toShortUUID(unitId);
        TaskHeader actualTask = taskHeaderRepository.findByUnitIdAndDateCreatedIsMax(specifiedUnitId);

        List<TaskLine> taskLinesList = new ArrayList<>();
        taskLinesList.add(new TaskLine(actualTask, DataSourceType.DIAGNOSTICS, null,
                recalculatedDateStart, "Diagnostic Data"));

//        actualTask.setTaskLinesList(taskLinesList);
//        return taskHeaderRepository.save(actualTask);

        taskLineRepository.save(new TaskLine(actualTask, DataSourceType.DIAGNOSTICS, null,
                recalculatedDateStart, "Diagnostic Data"));
//        taskHeaderRepository.save(actualTask);
/*
        TaskHeader closedTask=taskHeaderRepository.findById(taskId).orElseThrow(() ->
                new PhmException("No task found with id=" + taskId,
                        PhmErrorCode.ITEM_NOT_FOUND));
        List<TaskLine> taskLinesList = new ArrayList<>();

        closedTask.setStatus(MsTaskStatus.COMPLETED);
        closedTask.setDateClosing(actualDateOperationEnd);

        taskLineRepository.save(new TaskLine(closedTask, DataSourceType.MANUAL, null,
                actualDateOperationEnd,"Closing Task"));

        return taskHeaderRepository.save(closedTask);*/
    }

    @Override
    public TaskHeader findById(Integer taskId) throws PhmException {
        return taskHeaderRepository.findById(taskId).orElseThrow(() ->
                new PhmException("No Maintenance Task found with key=" + taskId,
                        ITEM_NOT_FOUND));
    }

    @Override
    public List<TaskHeader> getAll() {
        return (List<TaskHeader>) taskHeaderRepository.findAll();
    }

    @Override
    public void deleteById(Integer taskId) {
        taskHeaderRepository.deleteById(taskId);
    }

    @Override
    public TaskHeader findByUnitId(String unitId) {
//        if unitId.length()
        return taskHeaderRepository.findByUnitId(PhmUtil.toShortUUID(unitId));
    }

    @Override
    public void createPlannedTasks() throws PhmException {
        List<TaskHeader> taskHeadersList = new ArrayList<>();
        createForUnitsWithoutTasks(taskHeadersList);
        createForUnitsWithClosedTasks(taskHeadersList);

        if (taskHeadersList.size() > 0)
            taskHeaderRepository.saveAll(taskHeadersList);
    }

    private List<TaskHeader> createForUnitsWithClosedTasks(List<TaskHeader> taskHeadersList) throws PhmException {
        List<Object[]> unitMtbfList = unitAttributeRepository.getUnitsWithClosedMsTasks();
        for (Object[] unitMtbf : unitMtbfList) {
            long mtbf = ((BigInteger) unitMtbf[2]).longValue() / 1000;
            UnitEntity unit = getUnitEntity(PhmUtil.toLongUUID((String) unitMtbf[0]));

            long startDate = ((Timestamp) unitMtbf[1]).getTime();

            TaskHeader task = TaskHeader.createNew(unit, userService.getActualUser(),
                    "Automatic Planned Task");
            List<TaskLine> taskLinesList = new ArrayList<>();

            taskLinesList.add(new TaskLine(task, DataSourceType.DOCUMENTATION, null,
                    LocalDateTime.now().plusSeconds(mtbf), "Automatic Planned Task Line"));
            task.setTaskLinesList(taskLinesList);
            taskHeadersList.add(task);
//                    }
        }
        //  } // this condition in SQL already
        System.out.println("UnitsWithClosedTasks = " + taskHeadersList.size());
        return taskHeadersList;
    }

    private List<TaskHeader> createForUnitsWithoutTasks(List<TaskHeader> taskHeadersList) throws PhmException {
        long nowInMs = System.currentTimeMillis();

        List<Object[]> unitMtbfList = unitAttributeRepository.getUnitsWithoutMsTasks();//id, mtbf, status
        for (Object[] unitMtbf : unitMtbfList) {
            // if (unitMtbf[1] != null) { // this condition in SQL already
            long mtbf = ((BigInteger) unitMtbf[1]).longValue();
            UnitEntity unit = getUnitEntity(PhmUtil.toLongUUID((String) unitMtbf[0]));

            // No Planned task at all because status = null
            if (unitMtbf[2] == null) { // status
                TaskHeader task = TaskHeader.createNew(unit, userService.getActualUser(),
                        "Automatic Planned Task");
                List<TaskLine> taskLinesList = new ArrayList<>();
                taskLinesList.add(new TaskLine(task, DataSourceType.DOCUMENTATION, null,
                        LocalDateTime.now().plusSeconds(mtbf), "Automatic Planned Task Line"));
                task.setTaskLinesList(taskLinesList);
                taskHeadersList.add(task);

            }
        }
        System.out.println("UnitsWithoutTasks = " + taskHeadersList.size());
        return taskHeadersList;
    }

    @Override
    public void createPlannedTasksManually(String unitId, LocalDateTime plannedDateOperationStart,
                                           String comment) throws PhmException {

        UnitEntity unit = getUnitEntity(PhmUtil.toLongUUID((unitId)));

        TaskHeader task = TaskHeader.createNew(unit, userService.getActualUser(),
                "Manual Planned Task");
        List<TaskLine> taskLinesList = new ArrayList<>();

        taskLinesList.add(new TaskLine(task, DataSourceType.DOCUMENTATION, null,
                plannedDateOperationStart, "Manual Planned Task Line"));

        task.setTaskLinesList(taskLinesList);
        taskHeaderRepository.save(task);

    }

    private UnitEntity getUnitEntity(String unitId) throws PhmException {
        return unitService.findByIdReturnEntity(unitId, false);
    }

    @Override
    public void TaskRecalculateFailure(String unitId, String comment) throws PhmException {
        String specifiedUnitId = unitId;
        if (unitId.length() == 36)
            specifiedUnitId = PhmUtil.toShortUUID(unitId);
        TaskHeader actualTask = taskHeaderRepository.findByUnitIdAndDateCreatedIsMax(specifiedUnitId);

        List<TaskLine> taskLinesList = new ArrayList<>();
        actualTask.setStatus(MsTaskStatus.EMERGENCY);

//        taskLinesList.add(new TaskLine(actualTask, DataSourceType.FAILURE, null,
//                LocalDateTime.now(), "Diagnostic Data"));
        taskLineRepository.save(new TaskLine(actualTask, DataSourceType.FAILURE, null,
                LocalDateTime.now(), "Failure"));

//        actualTask.setTaskLinesList(taskLinesList);
        taskHeaderRepository.save(actualTask);

/*        List<TaskLine> taskLinesList = new ArrayList<>();
        taskLinesList.add(new TaskLine(actualTask, DataSourceType.DIAGNOSTICS, null,
                recalculatedDateStart, "Diagnostic Data"));
                        taskLineRepository.save(new TaskLine(actualTask, DataSourceType.DIAGNOSTICS, null,
                recalculatedDateStart, "Diagnostic Data"));

                */


    }

    @Override
    public TaskHeader CloseTask(Integer taskId, LocalDateTime actualDateOperationStart,
                                LocalDateTime actualDateOperationEnd, Integer operationActualTypeId, String comment) throws PhmException {

        TaskHeader closedTask = taskHeaderRepository.findById(taskId).orElseThrow(() ->
                new PhmException("No task found with id=" + taskId,
                        PhmErrorCode.ITEM_NOT_FOUND));

        List<TaskLine> taskLinesList = new ArrayList<>();

        closedTask.setStatus(MsTaskStatus.COMPLETED);
        closedTask.setDateClosing(actualDateOperationEnd);

        taskLineRepository.save(new TaskLine(closedTask, DataSourceType.MANUAL, null,
                actualDateOperationEnd, "Closing Task"));

        return taskHeaderRepository.save(closedTask);

    }
}
