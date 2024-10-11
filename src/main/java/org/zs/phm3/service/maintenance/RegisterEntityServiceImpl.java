package org.zs.phm3.service.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.maintenance.DataSourceType;
import org.zs.phm3.models.maintenance.MsTaskStatus;
import org.zs.phm3.models.maintenance.OperationTypeEntity;
import org.zs.phm3.models.maintenance.RegisterEntity;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.maintenance.RegisterRepository;
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
public class RegisterEntityServiceImpl implements RegisterEntityService {
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    UnitService unitService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UnitAttributeSQLRepository unitAttributeRepository;
    @Autowired
    OperationTypeService operationTypeService;

//    @Override
    private RegisterEntity createNew(String unitId, OperationTypeEntity typePlaned,
                                    LocalDateTime plannedDateOperationStart) throws PhmException {
        UnitEntity unit = unitService.findByIdReturnEntity(unitId, false);
        return registerRepository.save(RegisterEntity.createNewTask(unit, userService.getActualUser(), typePlaned,
                plannedDateOperationStart,""));
    }

    @Override
    public RegisterEntity TaskRecalculateML(Integer registerId, LocalDateTime newPlannedDateStart) throws PhmException {

        RegisterEntity recalculatedRegistry = findById(registerId);
        recalculatedRegistry.setPlannedDateOperationStart(newPlannedDateStart);
        recalculatedRegistry.setStatus(MsTaskStatus.RECALCULATED);
        recalculatedRegistry.setDataSource(DataSourceType.DIAGNOSTICS);
        recalculatedRegistry.setComment(recalculatedRegistry.getComment()+"\n"+LocalDateTime.now()+
                " Recalculate ML with newPlannedDateStart = "+newPlannedDateStart);
        return registerRepository.save(recalculatedRegistry);
    }

    @Override
    public RegisterEntity findById(Integer registerId) throws PhmException {
        return registerRepository.findById(registerId).orElseThrow(() ->
                new PhmException("No Maintenance Task found with key=" + registerId,
                        ITEM_NOT_FOUND));
    }

    @Override
    public List<RegisterEntity> getAll() {
        return (List<RegisterEntity>) registerRepository.findAll();
    }

    @Override
    public void deleteById(Integer registerId) {
        registerRepository.deleteById(registerId);
    }

    @Override
    public void createPlannedTasks() throws PhmException {
        List<Object[]> unitMtbfList = unitAttributeRepository.getAllMTBFandLastOpenMsTask();//getAllMTBF();
        if (unitMtbfList.size() > 0) {
            long nowInMs = System.currentTimeMillis();
            List<RegisterEntity> registersList = new ArrayList<>();
            for (Object[] unitMtbf : unitMtbfList) {
                if (unitMtbf[1] != null) {
                    long mtbf = ((BigInteger) unitMtbf[1]).longValue();
                    UnitEntity unit = getUnitEntity(PhmUtil.toLongUUID((String) unitMtbf[0]));

                    // No Planned task at all because no planned_data_start
                    if (unitMtbf[3] == null) {
                        registersList.add(RegisterEntity.createNewTask(unit, userService.getActualUser(),
                                operationTypeService.findOperationById(20),
                                LocalDateTime.now().plusSeconds(mtbf / 1000), "Planned"));
                    } else {
                        long startDate = ((Timestamp) unitMtbf[2]).getTime();
                        /*
                        If the task has already been created, its date is in the future and it isn't closed
                        we pass such case
                        and vice versa
                        if task date is in the past and it is closed we create new task
                        */
                        if (nowInMs > startDate & (Integer) unitMtbf[3] == 4) {
                            registersList.add(RegisterEntity.createNewTask(unit, userService.getActualUser(),
                                    operationTypeService.findOperationById(20),
                                    LocalDateTime.now().plusSeconds(mtbf / 1000), "Planned"));
                        }
                    }

                    if (registersList.size() > 0)
                        registerRepository.saveAll(registersList);
                }
            }
            byte a = 0;
        }
    }

    @Override
    public void createPlannedTasksManually(String unitId, LocalDateTime plannedDateOperationStart,
                                           String comment) throws PhmException {

        UnitEntity unit = getUnitEntity(PhmUtil.toLongUUID((unitId)));

/*            RegisterEntity.createNewTask(unit, userService.getActualUser(),
                    operationTypeService.findOperationById(20),
                    plannedDateOperationStart, comment);*/
        registerRepository.save(RegisterEntity.createNewTask(unit, userService.getActualUser(),
                operationTypeService.findOperationById(20),
                plannedDateOperationStart, LocalDateTime.now()+" "+comment));
    }

    private UnitEntity getUnitEntity(String unitId) throws PhmException {
        return unitService.findByIdReturnEntity(unitId, false);
    }

    @Override
    public void TaskRecalculateFailure(String unitId, String comment) throws PhmException {
        RegisterEntity recalculatedRegistry = registerRepository.findTopByUnitOrderByPlannedDateOperationStartDesc(getUnitEntity(unitId));

        recalculatedRegistry.setPlannedDateOperationStart(LocalDateTime.now());
        recalculatedRegistry.setStatus(MsTaskStatus.EMERGENCY);
        recalculatedRegistry.setDataSource(DataSourceType.FAILURE);
        recalculatedRegistry.setComment(recalculatedRegistry.getComment()+"\n"+LocalDateTime.now()+" "+comment);
        registerRepository.save(recalculatedRegistry);
    }

    @Override
    public void TaskRecalculateFailureManually(String unitId, String comment) throws PhmException {
        RegisterEntity recalculatedRegistry = registerRepository.findTopByUnitOrderByPlannedDateOperationStartDesc(getUnitEntity(unitId));

        recalculatedRegistry.setPlannedDateOperationStart(LocalDateTime.now());
        recalculatedRegistry.setStatus(MsTaskStatus.EMERGENCY);
        recalculatedRegistry.setDataSource(DataSourceType.MANUAL);
        recalculatedRegistry.setComment(recalculatedRegistry.getComment()+"\n"+LocalDateTime.now()+" "+comment);
        registerRepository.save(recalculatedRegistry);
    }

    @Override
    public void CloseTask(Integer registerId, LocalDateTime actualDateOperationStart,
                          LocalDateTime actualDateOperationEnd, Integer operationActualTypeId,String comment) throws PhmException {
        RegisterEntity closedTask= findById(registerId);
        closedTask.setDataSource(DataSourceType.MANUAL);
        closedTask.setStatus(MsTaskStatus.COMPLETED);
        closedTask.setActualDateOperationStart(actualDateOperationStart);
        closedTask.setActualDateOperationEnd(actualDateOperationEnd);
        closedTask.setTypeActual(operationTypeService.findOperationById(operationActualTypeId));
        closedTask.setComment(closedTask.getComment()+"\n"+ LocalDateTime.now()+" "+comment);
    }
}
