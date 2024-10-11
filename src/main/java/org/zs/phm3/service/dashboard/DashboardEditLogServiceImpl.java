package org.zs.phm3.service.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.dashboard.DashboardEditLog;
import org.zs.phm3.repository.dashboard.DashboardEditLogRepository;

/**
 * Implementing DashboardEditLogService class
 * @author Pavel Chuvak
 */
@Service
public class DashboardEditLogServiceImpl implements DashboardEditLogService {

    /** Dashboard edit log repository */
    @Autowired
    private DashboardEditLogRepository dashboardEditLogRepository;

    /**
     * Saving dashboard edit log
     * @param dashboardEditLog dashboard edit log
     * @return dashboard edit log
     */
    @Override
    public DashboardEditLog save(DashboardEditLog dashboardEditLog) {
        return dashboardEditLogRepository.save(dashboardEditLog);
    }
}
