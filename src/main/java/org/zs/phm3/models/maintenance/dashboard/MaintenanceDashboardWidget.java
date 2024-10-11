package org.zs.phm3.models.maintenance.dashboard;

import javax.persistence.*;

/**
 * Maintenance dashboard widget entity
 * @atuhor Pavel Chuvak
 */
@Entity
public class MaintenanceDashboardWidget {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** JSON settings */
    @Column(length = 5000)
    private String jsonSettings;

    /** Maintenance dashboard */
    @ManyToOne
    private MaintenanceDashboard maintenanceDashboard;

    /** Maintenance widget */
    @ManyToOne
    private MaintenanceWidget maintenanceWidget;

    /**
     * Constructor
     */
    public MaintenanceDashboardWidget() {
    }

    /**
     * Constructor
     * @param maintenanceDashboard maintenance dashboard
     * @param maintenanceWidget maintenance widget
     * @param jsonSettings json settings
     */
    public MaintenanceDashboardWidget(MaintenanceDashboard maintenanceDashboard, MaintenanceWidget maintenanceWidget,
                                      String jsonSettings) {
        this.maintenanceDashboard = maintenanceDashboard;
        this.maintenanceWidget = maintenanceWidget;
        this.jsonSettings = jsonSettings;
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

    /**
     * Gets ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets maintenance dashboard
     * @return maintenance dashboard
     */
    public MaintenanceDashboard getMaintenanceDashboard() {
        return maintenanceDashboard;
    }

    /**
     * Sets maintenance dashboard
     * @param maintenanceDashboard maintenance dashboard
     */
    public void setMaintenanceDashboard(MaintenanceDashboard maintenanceDashboard) {
        this.maintenanceDashboard = maintenanceDashboard;
    }

    /**
     * Gets maintenance widget
     * @return maintenance widget
     */
    public MaintenanceWidget getMaintenanceWidget() {
        return maintenanceWidget;
    }

    /**
     * Sets maintenance widget
     * @param maintenanceWidget maintenance widget
     */
    public void setMaintenanceWidget(MaintenanceWidget maintenanceWidget) {
        this.maintenanceWidget = maintenanceWidget;
    }
}
