package org.zs.phm3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.maintenance.RegisterEntityService;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/maintenance/")
public class MaintenanceController extends BaseController{

    @Autowired
    RegisterEntityService registerEntityService;

    @GetMapping(value = "failureManual/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void TaskRecalculateFailure(String unitId, String comment) throws PhmException {
        registerEntityService.TaskRecalculateFailureManually(unitId, comment);
    }

    @GetMapping(value = "cratePlannedTask/{id}/{plannedDateOperationStart}/{comment}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlannedTasksManually(String unitId, LocalDateTime plannedDateOperationStart,
                                           String comment) throws PhmException {
        registerEntityService.createPlannedTasksManually(unitId, plannedDateOperationStart, comment);
    }


/*
    @GetMapping(value = "/unit_import/{filePath}/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void importUnit(@PathVariable("filePath") String filePath,
                           @PathVariable("projectId") Integer projectId) throws PhmException, IOException {
    */
}
