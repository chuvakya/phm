package org.zs.phm3.service.dashboard;

import org.zs.phm3.models.dashboard.DashboardWidget;
import org.zs.phm3.models.dashboard.request.WidgetRequest;

import java.util.List;

/**
 * Interface DashboardWidgetService class
 * @author Pavel Chuvak
 */
public interface DashboardWidgetService {

    /**
     * Saving dashboard widget
     * @param dashboardWidget dashboard widget
     * @return dashboard widget
     */
    DashboardWidget save(DashboardWidget dashboardWidget);

    /**
     * Adding attributes from widgets and dashboard widget IDs
     * @param widgets widgets
     * @param dashboardWidgetIds dashboard widget IDs
     */
    void addAttributesByDashboardWidgetIds(List<WidgetRequest> widgets, List<Long> dashboardWidgetIds);

    /**
     * Getting json string all dashboard widgets by unit ID
     * @param unitId unit ID
     * @return json string all dashboard widgets
     */
    String getAllDashboardWidgetsByUnitId(String unitId);
}
