package org.zs.phm3.models.maintenance.dashboard;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance widget entity
 * @author Pavel Chuvak
 */
@Entity
public class MaintenanceWidget {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Maintenance widget name */
    private String name;

    /** Description */
    private String description;

    /** Maintenance dashboard widgets */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "maintenanceWidget")
    private List<MaintenanceDashboardWidget> maintenanceDashboardWidgets = new ArrayList<>();

    /**
     * Constructor
     */
    public MaintenanceWidget() {
    }

    /**
     * Constructor
     * @param name maintenance widget name
     * @param description description
     */
    public MaintenanceWidget(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets maintenance widget name
     * @return maintenance widget name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets maintenance widget name
     * @param name maintenance widget name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets maintenance dashboard widgets
     * @return maintenance dashboard widgets
     */
    public List<MaintenanceDashboardWidget> getMaintenanceDashboardWidgets() {
        return maintenanceDashboardWidgets;
    }

    /**
     * Sets maintenance dashboard widgets
     * @param maintenanceDashboardWidgets maintenance dashboard widgets
     */
    public void setMaintenanceDashboardWidgets(List<MaintenanceDashboardWidget> maintenanceDashboardWidgets) {
        this.maintenanceDashboardWidgets = maintenanceDashboardWidgets;
    }
}
