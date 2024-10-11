package org.zs.phm3.service.dashboard;

import org.zs.phm3.models.dashboard.Widget;
import org.zs.phm3.models.dashboard.request.WidgetAttributeRequest;
import org.zs.phm3.models.dashboard.request.WidgetFilter;

import java.util.List;

/**
 * Interface WidgetService
 * @author Pavel Chuvak
 */
public interface WidgetService {

    /**
     * Getting widget by widget ID
     * @param widgetId widget ID
     * @return widget
     */
    Widget getById(Long widgetId);

    /**
     * Saving widget
     * @param widget widget
     * @return widget
     */
    Widget save(Widget widget);

    /**
     * Adding attributes from attributes and widget ID
     * @param attributes attributes
     * @param widgetId widget ID
     */
    void addAttributesByWidgetId(List<WidgetAttributeRequest> attributes, Long widgetId);

    /**
     * Getting json string all widgets by project ID
     * @param projectId project ID
     * @return json string all widgets
     */
    String getAllByProjectId(Integer projectId);

    /**
     * Getting json string widget by widget ID
     * @param widgetId widget ID
     * @return json string widget
     */
    String getByIdJSON(Long widgetId);

    /**
     * Getting json string user info by widget ID
     * @param widgetId widget ID
     * @return json string user info
     */
    String getUserInfoByWidgetId(Long widgetId);

    /**
     * Getting json string all widgets by widget filter and user login
     * @param widgetFilter widget filter
     * @param userLogin user login
     * @return json string all widgets
     */
    String getAllByFilter(WidgetFilter widgetFilter, String userLogin);

    /**
     * Coping attributes by widget ID and copy widget ID
     * @param widgetId widget ID
     * @param copyWidgetId copy widget ID
     */
    void copyAttributes(Long widgetId, Long copyWidgetId);

    /**
     * Getting json string all widgets by project ID
     * @param projectId project ID
     * @return json string all widgets
     */
    String getAllByProjectIdForAddWidget(Integer projectId);
}
