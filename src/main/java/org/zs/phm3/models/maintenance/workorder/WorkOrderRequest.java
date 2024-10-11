package org.zs.phm3.models.maintenance.workorder;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class work order request
 * @author Pavel Chuvak
 */
public class WorkOrderRequest {

    /** Labor tasks */
    private List<LaborTaskRequest> laborTasks = new ArrayList<>();

    /** Parts */
    private List<WorkOrderPartRequest> parts = new ArrayList<>();

    /**
     * Gets labor tasks
     * @return labor tasks
     */
    public List<LaborTaskRequest> getLaborTasks() {
        return laborTasks;
    }

    /**
     * Sets labor tasks
     * @param laborTasks labor tasks
     */
    public void setLaborTasks(List<LaborTaskRequest> laborTasks) {
        this.laborTasks = laborTasks;
    }

    /**
     * Gets parts
     * @return parts
     */
    public List<WorkOrderPartRequest> getParts() {
        return parts;
    }

    /**
     * Set parts
     * @param parts parts
     */
    public void setParts(List<WorkOrderPartRequest> parts) {
        this.parts = parts;
    }
}
