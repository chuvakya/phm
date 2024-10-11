package org.zs.phm3.service.maintenance.dashboard;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.dashboard.MaintenanceWidgetRequest;
import org.zs.phm3.repository.maintenance.dashboard.MaintenanceWidgetRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MaintenanceWidgetServiceImpl implements MaintenanceWidgetService {

    @Autowired
    private MaintenanceWidgetRepository maintenanceWidgetRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getTotalCountAndOpenStatusAndPercentByProjectId(Integer projectId) {
        List<List<Object>> results = maintenanceWidgetRepository.getTotalCountAndOpenStatusAndPercentByProjectId(projectId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", results.get(0).get(0));
        jsonObject.put("openStatusCount", results.get(0).get(1));
        jsonObject.put("percentOpenStatusOfTotalCount", results.get(0).get(2));
        return jsonObject.toJSONString();
    }

    @Transactional
    @Override
    public void saveAllWidgetsByDashboardId(List<MaintenanceWidgetRequest> widgets, Integer dashboardId) {
        if (!widgets.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO maintenance_dashboard_widget " +
                    "(maintenance_dashboard_id, maintenance_widget_id, json_settings) VALUES (" + dashboardId + ", " +
                    widgets.get(0).getWidgetId() + ", '" + widgets.get(0).getJsonSettings().replaceAll("'", "''") + "');");
            for (int i = 1; i < widgets.size(); i++) {
                stringBuilder.append("INSERT INTO maintenance_dashboard_widget " +
                        "(maintenance_dashboard_id, maintenance_widget_id, json_settings) VALUES (" + dashboardId + ", " +
                        widgets.get(i).getWidgetId() + ", '" + widgets.get(i).getJsonSettings().replaceAll("'", "''") + "');");
            }
            entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
        }
    }

    @Override
    public Long getCountWithPriorityHighByProjectId(Integer projectId) {
        return maintenanceWidgetRepository.getCountWithPriorityHighByProjectId(projectId);
    }

    @Override
    public Long getCountNotClosedByProjectIdAndCurrentTime(Integer projectId) {
        return maintenanceWidgetRepository.getCountNotClosedByProjectIdAndCurrentTime(projectId, System.currentTimeMillis());
    }
}
