package org.zs.phm3.service.dashboard;

import org.zs.phm3.models.dashboard.WidgetEditLog;

/**
 * Interface WidgetEditLogService
 * @author Pavle Chuvak
 */
public interface WidgetEditLogService {

    /**
     * Saving widget edit log
     * @param widgetEditLog widget edit log
     * @return widget edit log
     */
    WidgetEditLog save(WidgetEditLog widgetEditLog);
}
