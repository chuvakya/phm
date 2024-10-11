package org.zs.phm3.models.maintenance.dashboard;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance dashboard entity
 * @author Pavel Chuvak
 */
@Entity
public class MaintenanceDashboard {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Maintenance dashboard name */
    private String name;

    /** Project ID */
    private Integer projectId;

    /** User role */
    @ManyToOne
    private UserRole userRole;

    /** Maintenance dashboard widget */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "maintenanceDashboard")
    private List<MaintenanceDashboardWidget> maintenanceDashboardWidgets = new ArrayList<>();

    /**
     * Constructor
     */
    public MaintenanceDashboard() {
    }

    /**
     * Constructor
     * @param name maintenance dashboard name
     * @param projectId project ID
     * @param userRole user role
     */
    public MaintenanceDashboard(String name, Integer projectId, UserRole userRole) {
        this.name = name;
        this.projectId = projectId;
        this.userRole = userRole;
    }

    /**
     * Gets user role
     * @return user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets user role
     * @param userRole user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
     * Gets maintenance dashboard name
     * @return maintenance dashboard name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets maintenance dashboard name
     * @param name maintenance dashboard name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets project ID
     * @return prohject ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets project ID
     * @param projectId project ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
