package org.zs.phm3.service.maintenance.dashboard;

import org.zs.phm3.models.maintenance.dashboard.MaintenanceWidgetRequest;

import java.util.List;

public interface MaintenanceWidgetService {
    String getTotalCountAndOpenStatusAndPercentByProjectId(Integer projectId);
    void saveAllWidgetsByDashboardId(List<MaintenanceWidgetRequest> widgets, Integer dashboardId);
    Long getCountWithPriorityHighByProjectId(Integer projectId);
    Long getCountNotClosedByProjectIdAndCurrentTime(Integer projectId);
}
