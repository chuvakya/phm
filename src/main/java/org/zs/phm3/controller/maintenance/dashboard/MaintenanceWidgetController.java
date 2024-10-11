package org.zs.phm3.controller.maintenance.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.maintenance.dashboard.MaintenanceWidgetService;

@RestController
@RequestMapping(value = "/api/maintenance_widget/")
public class MaintenanceWidgetController {

    @Autowired
    private MaintenanceWidgetService maintenanceWidgetService;

    @GetMapping(value = "getTotalCountAndOpenStatusAndPercentByProjectId/{projectId}", produces = "application/json")
    public String getTotalCountAndOpenStatusAndPercentByProjectId(@PathVariable Integer projectId) {
        return maintenanceWidgetService.getTotalCountAndOpenStatusAndPercentByProjectId(projectId);
    }

    @GetMapping(value = "getCountWithPriorityHighByProjectId/{projectId}", produces = "application/json")
    public Long getCountWithPriorityHighByProjectId(@PathVariable Integer projectId) {
        return maintenanceWidgetService.getCountWithPriorityHighByProjectId(projectId);
    }

    @GetMapping(value = "getCountNotClosedByProjectIdAndCurrentTime/{projectId}", produces = "application/json")
    public Long getCountNotClosedByProjectIdAndCurrentTime(@PathVariable Integer projectId) {
        return maintenanceWidgetService.getCountNotClosedByProjectIdAndCurrentTime(projectId);
    }

}
