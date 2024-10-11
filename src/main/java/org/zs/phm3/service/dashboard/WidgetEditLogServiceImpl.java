package org.zs.phm3.service.dashboard;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.dashboard.WidgetEditLog;
import org.zs.phm3.repository.dashboard.WidgetEditLogRepository;
import org.zs.phm3.repository.dashboard.WidgetRepository;

import java.util.List;

/**
 * Implementing WidgetEditLogService class
 * @author Pavel Chuvak
 */
@Service
public class WidgetEditLogServiceImpl implements WidgetEditLogService {

    /** Widget edit log repository */
    @Autowired
    private WidgetEditLogRepository widgetEditLogRepository;

    /** Widget repository */
    @Autowired
    private WidgetRepository widgetRepository;

    /**
     * Saving widget edit log
     * @param widgetEditLog widget edit log
     * @return widget edit log
     */
    @Override
    public WidgetEditLog save(WidgetEditLog widgetEditLog) {
        return widgetEditLogRepository.save(widgetEditLog);
    }
}
