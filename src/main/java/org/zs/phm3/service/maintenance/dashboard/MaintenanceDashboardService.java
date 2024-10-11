package org.zs.phm3.service.maintenance.dashboard;

import org.zs.phm3.models.maintenance.dashboard.MaintenanceDashboard;

public interface MaintenanceDashboardService {
    MaintenanceDashboard save(MaintenanceDashboard maintenanceDashboard);
    MaintenanceDashboard getById(Integer dashboardId);
    String getAllForMenuByLoginAndProjectId(String login, Integer projectId);
}
