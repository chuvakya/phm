package org.zs.phm3.service.dashboard;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.DashboardWidget;
import org.zs.phm3.models.dashboard.request.WidgetRequest;
import org.zs.phm3.repository.dashboard.DashboardRepository;
import org.zs.phm3.repository.dashboard.DashboardWidgetRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementing DashboardWidgetService class
 * @author Pavel Chuvak
 */
@Service
public class DashboardWidgetServiceImpl implements DashboardWidgetService {

    /** Dashboard widget repository */
    @Autowired
    private DashboardWidgetRepository dashboardWidgetRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    /** Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saving dashboard widget
     * @param dashboardWidget dashboard widget
     * @return dashboard widget
     */
    @Override
    public DashboardWidget save(DashboardWidget dashboardWidget) {
        return dashboardWidgetRepository.save(dashboardWidget);
    }

    /**
     * Adding attributes from widgets and dashboard widget IDs
     * @param widgets widgets
     * @param dashboardWidgetIds dashboard widget IDs
     */
    @Transactional
    @Override
    public void addAttributesByDashboardWidgetIds(List<WidgetRequest> widgets, List<Long> dashboardWidgetIds) {
        if (!widgets.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < widgets.size(); i++) {
                if (!widgets.get(i).getAttributes().isEmpty()) {
                    for (int y = 0; y < widgets.get(i).getAttributes().size(); y++) {
                        builder.append("INSERT INTO dashboard_widget_attribute " +
                                "(dashboard_widget_id, ts_kv_attribute_id, name) VALUES (" + dashboardWidgetIds.get(i) + ", " +
                                widgets.get(i).getAttributes().get(y).getAttributeId() + ", '"
                                + widgets.get(i).getAttributes().get(y).getName().replaceAll("'", "''") + "');");
                    }
                }
            }
            if (builder.length() != 0) {
                entityManager.createNativeQuery(builder.toString()).executeUpdate();
            }
        }
    }

    /**
     * Getting json string all dashboard widget by unit ID
     * @param unitId unit ID
     * @return json string all dashboard widget
     */
    @Override
    public String getAllDashboardWidgetsByUnitId(String unitId) {
        List<Integer> dashboardIds = dashboardRepository.getAllDashboardIdsByUnitId(unitId);
        if (dashboardIds.isEmpty()) {
            return "[]";
        }
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> widgets = dashboardWidgetRepository.getAllWidgetsByDashboardIdForExplorer(dashboardIds.get(0));
        for (List<Object> widget : widgets) {
            JSONObject object = new JSONObject();
            object.put("json", widget.get(0));
            object.put("frontWidgetId", widget.get(1));
            object.put("name", widget.get(2));
            object.put("dashboardWidgetId", widget.get(3));
            jsonArray.add(object);
        }
        return jsonArray.toJSONString();
    }
}
