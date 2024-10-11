package org.zs.phm3.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.service.dashboard.WidgetApiService;

@RestController
@RequestMapping(value = "/api/widget_api/")
public class WidgetApiController {

    @Autowired
    private WidgetApiService widgetApiService;


    @GetMapping(value = "getBarChartValues/{dashboardWidgetId}", produces = "application/json")
    public String getBarChartValues(@PathVariable Long dashboardWidgetId) {
        return widgetApiService.getBarChartValues(dashboardWidgetId);
    }

    @GetMapping(value = "getAreaChartValues/{dashboardWidgetId}/{isX}/{period}", produces = "application/json")
    public String getAreaChartValues(@PathVariable Long dashboardWidgetId, @PathVariable Boolean isX,
                                     @PathVariable Long period) {
        if (isX) {
            return widgetApiService.getAreaChartValuesX(dashboardWidgetId, period);
        } else {
            return widgetApiService.getAreaChartValuesY(dashboardWidgetId, period);
        }
    }


}
