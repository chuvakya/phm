package org.zs.phm3.models.maintenance.dashboard;

/**
 * Helper class
 * @author Pavel Chuvak
 */
public class MaintenanceWidgetRequest {

    /** Widget ID */
    private Integer widgetId;

    /** JSON settings */
    private String jsonSettings;

    /**
     * Constructor
     */
    public MaintenanceWidgetRequest() {
    }

    /**
     * Constructor
     * @param widgetId widget ID
     * @param jsonSettings json settings
     */
    public MaintenanceWidgetRequest(Integer widgetId, String jsonSettings) {
        this.widgetId = widgetId;
        this.jsonSettings = jsonSettings;
    }

    /**
     * Gets widget ID
     * @return widget ID
     */
    public Integer getWidgetId() {
        return widgetId;
    }

    /**
     * Sets widegt ID
     * @param widgetId widget ID
     */
    public void setWidgetId(Integer widgetId) {
        this.widgetId = widgetId;
    }

    /**
     * Gets json settings
     * @return json settings
     */
    public String getJsonSettings() {
        return jsonSettings;
    }

    /**
     * Sets json settings
     * @param jsonSettings json settings
     */
    public void setJsonSettings(String jsonSettings) {
        this.jsonSettings = jsonSettings;
    }
}
