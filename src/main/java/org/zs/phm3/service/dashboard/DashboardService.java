package org.zs.phm3.service.dashboard;

import org.zs.phm3.models.dashboard.Dashboard;
import org.zs.phm3.models.dashboard.request.DashboardFilter;

/**
 * Interface DashboardService
 * @author Pavel Chuvak
 */
public interface DashboardService {

    /**
     * Saving dashboard
     * @param dashboard dashboard
     * @return dashboard
     */
    Dashboard save(Dashboard dashboard);

    /**
     * Getting dashboard by dashboard ID
     * @param dashboardId dashboard ID
     * @return deshboard
     */
    Dashboard getById(Integer dashboardId);

    /**
     * Getting json string dashboard by dashboard ID
     * @param dashboardId dashboard ID
     * @return json string dashboard
     */
    String getByIdJSON(Integer dashboardId);

    /**
     * Getting json string system dashboards by project ID
     * @param projectId project ID
     * @return json string system dashboards
     */
    String getAllSystemByProjectId(Integer projectId);

    /**
     * Getting json string unit dashboards by project ID
     * @param projectId project ID
     * @return json string unit dashboards
     */
    String getAllUnitByProjectId(Integer projectId);

    /**
     * Getting json string user info by dashboard ID
     * @param dashboardId dashboard ID
     * @return json string user info
     */
    String getUserInfoByDashboardId(Integer dashboardId);

    /**
     * Getting json string all dashboards by dashboard filter and login
     * @param dashboardFilter dashboard filter
     * @param userLogin user login
     * @return json string all dashboards
     */
    String getAllByFilter(DashboardFilter dashboardFilter, String userLogin);

    Integer getDashboardCountByUnitId(String unitId);
    Integer getDashboardCountByModelPTLId(Long modelPTLId);
}
