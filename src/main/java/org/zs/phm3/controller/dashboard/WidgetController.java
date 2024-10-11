package org.zs.phm3.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.dashboard.Widget;
import org.zs.phm3.models.dashboard.WidgetEditLog;
import org.zs.phm3.models.dashboard.request.WidgetFilter;
import org.zs.phm3.models.dashboard.request.WidgetRequest;
import org.zs.phm3.service.dashboard.WidgetEditLogService;
import org.zs.phm3.service.dashboard.WidgetService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/widget/")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @Autowired
    private SQLHelper sqlHelper;

    @Autowired
    private WidgetEditLogService widgetEditLogService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody WidgetRequest widgetRequest, @RequestParam Integer projectId) {
        Widget widget = new Widget(widgetRequest.getFrontWidgetId(), widgetRequest.getName(), widgetRequest.getJson(),
                projectId, widgetRequest.getType(), userService.getByLogin("Admin"));
        widgetService.save(widget);
        widgetService.addAttributesByWidgetId(widgetRequest.getAttributes(), widget.getId());
    }

    @PostMapping(value = "saveCopyByWidgetId/{widgetId}")
    public String saveAs(@PathVariable Long widgetId, @RequestParam String newName) {
        Widget widget = widgetService.getById(widgetId);
        if (widget.getDefaultWidget()) {
            return "This widget can not be saved!";
        }
        Widget copyWidget = new Widget(widget.getFrontWidgetId(), newName, widget.getJson(), widget.getProjectId(),
                widget.getType(), userService.getByLogin("Admin"));
        widgetService.save(copyWidget);
        widgetService.copyAttributes(widgetId, copyWidget.getId());
        return "Success!";
    }

    @DeleteMapping(value = "deleteByWidgetId/{widgetId}")
    public void deleteByWidgetId(@PathVariable Long widgetId) {
        sqlHelper.deleteAll("widget","id", Arrays.asList(widgetId));
    }

    @PatchMapping(value = "updateByWidgetId/{widgetId}")
    public String updateByWidgetId(@PathVariable Long widgetId, @RequestBody WidgetRequest widgetRequest) {
        Widget widget = widgetService.getById(widgetId);
        if (widget.getDefaultWidget()) {
            return "This widget is not editable!";
        }
        widget.setFrontWidgetId(widgetRequest.getFrontWidgetId());
        widget.setName(widgetRequest.getName());
        widget.setJson(widgetRequest.getJson());
        widget.setType(widgetRequest.getType());
        widgetService.save(widget);
        widgetEditLogService.save(new WidgetEditLog(userService.getByLogin("Admin"), widget, System.currentTimeMillis()));
        sqlHelper.deleteAll("widget_attribute", "widget_id", Arrays.asList(widgetId));
        widgetService.addAttributesByWidgetId(widgetRequest.getAttributes(), widget.getId());
        return "Success!";
    }

    @GetMapping(value = "getAllByProjectId/{projectId}", produces = "application/json")
    public String getAllByProjectId(@PathVariable Integer projectId) {
        return widgetService.getAllByProjectId(projectId);
    }

    @GetMapping(value = "getAllByProjectIdForAddWidget/{projectId}", produces = "application/json")
    public String getAllByProjectIdForAddWidget(@PathVariable Integer projectId) {
        return widgetService.getAllByProjectIdForAddWidget(projectId);
    }

    @GetMapping(value = "getByIdJSON/{widgetId}", produces = "application/json")
    public String getByIdJSON(@PathVariable Long widgetId) {
        return widgetService.getByIdJSON(widgetId);
    }

    @GetMapping(value = "getUserInfoByWidgetId/{widgetId}", produces = "application/json")
    public String getUserInfoByWidgetId(@PathVariable Long widgetId) {
        return widgetService.getUserInfoByWidgetId(widgetId);
    }

    @PostMapping(value = "getAllByFilter", produces = "application/json")
    public String getAllByFilter(@RequestBody WidgetFilter filter) {
        return widgetService.getAllByFilter(filter, "Admin");
    }

}
