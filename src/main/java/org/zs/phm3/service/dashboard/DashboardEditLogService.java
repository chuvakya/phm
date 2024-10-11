package org.zs.phm3.service.dashboard;

import org.zs.phm3.models.dashboard.DashboardEditLog;

/**
 * Interface DashboardEditLogService
 * @author Pavel Chuvak
 */
public interface DashboardEditLogService {

    /**
     * Saving dashboard edit log
     * @param dashboardEditLog dashboard edit log
     * @return dashboard edit log
     */
    DashboardEditLog save(DashboardEditLog dashboardEditLog);
}
