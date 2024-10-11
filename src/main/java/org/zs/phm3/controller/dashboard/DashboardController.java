package org.zs.phm3.controller.dashboard;

import jdk.jshell.EvalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.dashboard.*;
import org.zs.phm3.models.dashboard.request.DashboardFilter;
import org.zs.phm3.models.dashboard.request.WidgetRequest;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.service.dashboard.DashboardEditLogService;
import org.zs.phm3.service.dashboard.DashboardService;
import org.zs.phm3.service.dashboard.DashboardWidgetService;
import org.zs.phm3.service.ptl.ModelPTLService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dashboard/")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private DashboardWidgetService dashboardWidgetService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelPTLService modelPTLService;

    @Autowired
    private DashboardEditLogService dashboardEditLogService;

    @Autowired
    private SQLHelper sqlHelper;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam String name, @RequestParam DashboardCategory dashboardCategory,
                       @RequestBody List<WidgetRequest> widgets, @RequestParam Integer projectId,
                       @RequestParam(required = false) DashboardType dashboardType,
                       @RequestParam(required = false) Long modelPTLId,
                       @RequestParam(required = false) String unitId) {
        ModelPTL modelPTL = null;
        if (modelPTLId != null) {
            if (dashboardService.getDashboardCountByModelPTLId(modelPTLId) > 0) {
                return "This model PTL dashboard already exist!";
            }
            modelPTL = modelPTLService.getById(modelPTLId);
        }
        String unitIdShort = null;
        if (unitId != null) {
            unitIdShort = PhmUtil.toShortUUID(unitId);
            if (dashboardService.getDashboardCountByUnitId(unitIdShort) > 0) {
                return "This unit dashboard already exist!";
            }
        }
        Dashboard dashboard = new Dashboard(name, projectId, dashboardCategory,
                dashboardType, modelPTL, unitIdShort, userService.getByLogin("Admin"));
        dashboardService.save(dashboard);
        List<Long> dwIds = new ArrayList<>();
        for (WidgetRequest widget : widgets) {
            DashboardWidget dashboardWidget = new DashboardWidget(widget.getFrontWidgetId(), widget.getJson(),
                        widget.getAdditionalJSON(), dashboard, widget.getName());
            dashboardWidgetService.save(dashboardWidget);
            dwIds.add(dashboardWidget.getId());
        }
        dashboardWidgetService.addAttributesByDashboardWidgetIds(widgets, dwIds);
        return "Success!";
    }

    @PatchMapping(value = "update")
    public String updateById(@RequestParam String name, @RequestParam DashboardCategory dashboardCategory,
                           @RequestBody List<WidgetRequest> widgets, @RequestParam Integer dashboardId,
                           @RequestParam(required = false) DashboardType dashboardType,
                           @RequestParam(required = false) Long modelPTLId,
                           @RequestParam(required = false) String unitId) {
        Dashboard dashboard = dashboardService.getById(dashboardId);
        ModelPTL modelPTL = null;
        if (modelPTLId != null) {
            if (dashboardService.getDashboardCountByModelPTLId(modelPTLId) > 0 && dashboard.getModelPTL().getId() != modelPTLId) {
                return "This model PTL dashboard already exist!";
            }
            modelPTL = modelPTLService.getById(modelPTLId);
        }
        String unitIdShort = null;
        if (unitId != null) {
            unitIdShort = PhmUtil.toShortUUID(unitId);
            if (dashboardService.getDashboardCountByUnitId(unitIdShort) > 0 && !dashboard.getUnitId().equals(unitIdShort)) {
                return "This unit dashboard already exist!";
            }
        }
        dashboard.setDashboardCategory(dashboardCategory);
        dashboard.setName(name);
        dashboard.setDashboardType(dashboardType);
        dashboard.setModelPTL(modelPTL);
        dashboard.setUnitId(unitIdShort);
        dashboardService.save(dashboard);
        dashboardEditLogService.save(new DashboardEditLog(userService.getByLogin("Admin"), System.currentTimeMillis(),
                dashboard));
        sqlHelper.deleteAll("dashboard_widget", "dashboard_id", Arrays.asList(dashboardId));
        List<Long> dwIds = new ArrayList<>();
        for (WidgetRequest widget : widgets) {
            DashboardWidget dashboardWidget = new DashboardWidget(widget.getFrontWidgetId(), widget.getJson(),
                        widget.getAdditionalJSON(), dashboard, widget.getName());
            dashboardWidgetService.save(dashboardWidget);
            dwIds.add(dashboardWidget.getId());
        }
        dashboardWidgetService.addAttributesByDashboardWidgetIds(widgets, dwIds);
        return "Success!";
    }

    @DeleteMapping(value = "deleteById/{dashboardId}")
    public void deleteById(@PathVariable Integer dashboardId) {
        sqlHelper.deleteAll("dashboard", "id", Arrays.asList(dashboardId));
    }

    @GetMapping(value = "getById/{dashboardId}", produces = "application/json")
    public String getById(@PathVariable Integer dashboardId) {
        return dashboardService.getByIdJSON(dashboardId);
    }

    @GetMapping(value = "getAllSystemByProjectId/{projectId}", produces = "application/json")
    public String getAllSystemByProjectId(@RequestParam Integer projectId) {
        return dashboardService.getAllSystemByProjectId(projectId);
    }

    @GetMapping(value = "getAllUnitByProjectId/{projectId}", produces = "application/json")
    public String getAllUnitByProjectId(@RequestParam Integer projectId) {
        return dashboardService.getAllUnitByProjectId(projectId);
    }

    @GetMapping(value = "getUserInfoByDashboardId/{dashboardId}", produces = "application/json")
    public String getUserInfoByDashboardId(@PathVariable Integer dashboardId) {
        return dashboardService.getUserInfoByDashboardId(dashboardId);
    }

    @PostMapping(value = "getAllByFilter", produces = "application/json")
    public String getAllByFilter(@RequestBody DashboardFilter filter) {
        return dashboardService.getAllByFilter(filter, "Admin");
    }

    @GetMapping(value = "getAllDashboardWidgetsByUnitId/{unitId}", produces = "application/json")
    public String getAllDashboardWidgetByUnitId(@PathVariable String unitId) {
        return dashboardWidgetService.getAllDashboardWidgetsByUnitId(PhmUtil.toShortUUID(unitId));
    }
}
