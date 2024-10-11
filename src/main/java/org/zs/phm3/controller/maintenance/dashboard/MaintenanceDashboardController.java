package org.zs.phm3.controller.maintenance.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.maintenance.dashboard.MaintenanceDashboard;
import org.zs.phm3.models.maintenance.dashboard.MaintenanceWidgetRequest;
import org.zs.phm3.service.maintenance.dashboard.MaintenanceDashboardService;
import org.zs.phm3.service.maintenance.dashboard.MaintenanceWidgetService;
import org.zs.phm3.service.user.UserRoleService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/maintenance_dashboard/")
public class MaintenanceDashboardController {

    @Autowired
    private MaintenanceDashboardService maintenanceDashboardService;

    @Autowired
    private MaintenanceWidgetService maintenanceWidgetService;

    @Autowired
    private SQLHelper sqlHelper;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    private void create(@RequestParam String name, @RequestParam Integer userRoleId,
                        @RequestBody List<MaintenanceWidgetRequest> widgets, @RequestParam Integer projectId) {
        MaintenanceDashboard dashboard = new MaintenanceDashboard(name, projectId, userRoleService.getById(userRoleId));
        maintenanceDashboardService.save(dashboard);
        maintenanceWidgetService.saveAllWidgetsByDashboardId(widgets, dashboard.getId());
    }

    @PatchMapping(value = "updateById")
    private void updateById(@RequestParam Integer dashboardId, @RequestParam String name, @RequestParam Integer userRoleId,
                            @RequestBody List<MaintenanceWidgetRequest> widgets) {
        MaintenanceDashboard dashboard = maintenanceDashboardService.getById(dashboardId);
        dashboard.setName(name);
        dashboard.setUserRole(userRoleService.getById(userRoleId));
        maintenanceDashboardService.save(dashboard);
        sqlHelper.deleteAll("maintenance_dashboard_widget", "maintenance_dashboard_id", Arrays.asList(dashboardId));
        maintenanceWidgetService.saveAllWidgetsByDashboardId(widgets, dashboard.getId());
    }

    @DeleteMapping(value = "deleteById/{dashboardId}")
    public void deleteById(@PathVariable Long dashboardId) {
        sqlHelper.deleteAll("maintenance_dashboard", "id", Arrays.asList(dashboardId));
    }

    @GetMapping(value = "getListForMenuByProjectId/{projectId}", produces = "application/json")
    public String getListForMenuByProjectId(@PathVariable Integer projectId) {
        return maintenanceDashboardService.getAllForMenuByLoginAndProjectId("Admin", projectId);
    }
}
