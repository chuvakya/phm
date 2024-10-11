package org.zs.phm3.models.maintenance.scheduled;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.rule.ExpressionsType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scheduled maintenance rule entity
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "maintenance_rule")
public class MaintenanceRule {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Rule name */
    private String name;

    /** Expression type */
    @Enumerated(EnumType.STRING)
    private ExpressionsType expressionsType;

    /** When string */
    private String whenString;

    /** Is on */
    private Boolean isOn;

    /** Triggers */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "maintenanceRule", cascade = CascadeType.ALL)
    private List<Trigger> triggers = new ArrayList<>();

    /** Maintenance actions */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "maintenanceRule", cascade = CascadeType.ALL)
    private List<MaintenanceAction> maintenanceActions = new ArrayList<>();

    /** Work order template */
    @OneToOne(mappedBy = "maintenanceRule", cascade = CascadeType.ALL)
    private WorkOrderTemplate workOrderTemplate;

    /**
     * Constructor
     */
    public MaintenanceRule() {
    }

    /**
     * Constructor
     * @param name maintenance rule name
     * @param expressionsType expression types
     * @param workOrderTemplate work order template
     * @param isOn is on
     * @param whenString when string
     */
    public MaintenanceRule(String name, ExpressionsType expressionsType, WorkOrderTemplate workOrderTemplate, Boolean isOn,
                           String whenString) {
        this.name = name;
        this.isOn = isOn;
        this.workOrderTemplate = workOrderTemplate;
        this.expressionsType = expressionsType;
        this.whenString = whenString;
    }

    /**
     * Gets is on
     * @return is on
     */
    public Boolean getOn() {
        return isOn;
    }

    /**
     * Sets is on
     * @param on is on
     */
    public void setOn(Boolean on) {
        isOn = on;
    }

    /**
     * Gets maintenance actions
     * @return maintenance actions
     */
    public List<MaintenanceAction> getMaintenanceActions() {
        return maintenanceActions;
    }

    /**
     * Sets maintenance actions
     * @param maintenanceActions maintenance actions
     */
    public void setMaintenanceActions(List<MaintenanceAction> maintenanceActions) {
        this.maintenanceActions = maintenanceActions;
    }

    /**
     * Gets work order template
     * @return work order template
     */
    public WorkOrderTemplate getWorkOrderTemplate() {
        return workOrderTemplate;
    }

    /**
     * Sets work order template
     * @param workOrderTemplate work order template
     */
    public void setWorkOrderTemplate(WorkOrderTemplate workOrderTemplate) {
        this.workOrderTemplate = workOrderTemplate;
    }

    /**
     * Gets when string
     * @return when string
     */
    public String getWhenString() {
        return whenString;
    }

    /**
     * Sets when string
     * @param when when string
     */
    public void setWhenString(String when) {
        this.whenString = when;
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
     * Gets maintenance rule name
     * @return maintenance rule name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets maintenance rule name
     * @param name maintenance rule name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets expression types
     * @return expression types
     */
    public ExpressionsType getExpressionsType() {
        return expressionsType;
    }

    /**
     * Sets expression types
     * @param expressionsType expression types
     */
    public void setExpressionsType(ExpressionsType expressionsType) {
        this.expressionsType = expressionsType;
    }

    /**
     * Gets triggers
     * @return triggers
     */
    public List<Trigger> getTriggers() {
        return triggers;
    }

    /**
     * Sets triggers
     * @param triggers triggers
     */
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    /**
     * Gets maintenance actions
     * @return maintenance actions
     */
    public List<MaintenanceAction> getActions() {
        return maintenanceActions;
    }

    /**
     * Sets maintenance actions
     * @param maintenanceActions maintenance actions
     */
    public void setActions(List<MaintenanceAction> maintenanceActions) {
        this.maintenanceActions = maintenanceActions;
    }
}
