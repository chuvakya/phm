package org.zs.phm3.models.maintenance.scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class maintenance rule request
 * @author Pavel Chuvak
 */
public class MaintenanceRuleRequest {

    /** Work order template */
    private WorkOrderTemplate workOrderTemplate;

    /** Triggers */
    private List<Trigger> triggers = new ArrayList<>();

    /** Maintenance actions */
    private List<MaintenanceAction> maintenanceActions = new ArrayList<>();

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
}
