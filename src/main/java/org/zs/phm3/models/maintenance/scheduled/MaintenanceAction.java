package org.zs.phm3.models.maintenance.scheduled;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Scheduled maintenance action entity
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "maintenance_action")
public class MaintenanceAction {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Action type */
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    /** Maintenance rule */
    @JsonIgnore
    @ManyToOne
    private MaintenanceRule maintenanceRule;

    /**
     * Constructor
     */
    public MaintenanceAction() {
    }

    /**
     * Constructor
     * @param actionType action type
     * @param maintenanceRule maintenance rule
     */
    public MaintenanceAction(ActionType actionType, MaintenanceRule maintenanceRule) {
        this.actionType = actionType;
        this.maintenanceRule = maintenanceRule;
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
     * Gets action type
     * @return action type
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Sets action type
     * @param actionType action type
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * Gets maintenance rule
     * @return maintenance rule
     */
    public MaintenanceRule getMaintenanceRule() {
        return maintenanceRule;
    }

    /**
     * Sets maintenance rule
     * @param maintenanceRule maintenance rule
     */
    public void setMaintenanceRule(MaintenanceRule maintenanceRule) {
        this.maintenanceRule = maintenanceRule;
    }
}
