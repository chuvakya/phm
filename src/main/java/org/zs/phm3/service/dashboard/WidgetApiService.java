package org.zs.phm3.service.dashboard;

/**
 * Interface WidgetApiService
 * @author Pavel Chuvak
 */
public interface WidgetApiService {

    /**
     * Getting json string bar chart values by dashboard widget ID
     * @param dashboardWidgetId dashboard widget ID
     * @return json string bar chart values
     */
    String getBarChartValues(Long dashboardWidgetId);

    /**
     * Getting json string area chart values (X) by dashboard widget ID and period
     * @param dashboardWidgetId dashboard widget ID
     * @param period period
     * @return json string area chart values (X)
     */
    String getAreaChartValuesX(Long dashboardWidgetId, Long period);

    /**
     * Getting json string area chart values (Y) by dashboard widget ID and period
     * @param dashboardWidgetId dashboard widget ID
     * @param period period
     * @return json string area chart values (Y)
     */
    String getAreaChartValuesY(Long dashboardWidgetId, Long period);
}
