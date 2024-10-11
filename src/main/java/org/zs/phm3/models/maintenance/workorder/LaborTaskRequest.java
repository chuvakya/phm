package org.zs.phm3.models.maintenance.workorder;

/**
 * Helper class labor task request
 * @author Pavel Chuvak
 */
public class LaborTaskRequest {

    /** Task description */
    private String taskDescription;

    /** Unit ID */
    private String unitId;

    /** Completion notes */
    private String completionNotes;

    /** Assigned to user ID */
    private Long assignedToUserId;

    /** Completed by user ID */
    private Long completedByUserId;

    /** Time estimate */
    private Integer timeEstimate;

    /** Time spent */
    private Integer timeSpent;

    /** Completed time */
    private Long completedTime;

    /**
     * Gets assigned to user ID
     * @return assigned to user ID
     */
    public Long getAssignedToUserId() {
        return assignedToUserId;
    }

    /**
     * Sets assigned to user ID
     * @param assignedToUserId assigned to user ID
     */
    public void setAssignedToUserId(Long assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    /**
     * Gets completed by user ID
     * @return completed by user ID
     */
    public Long getCompletedByUserId() {
        return completedByUserId;
    }

    /**
     * Sets completed by user ID
     * @param completedByUserId completed by user ID
     */
    public void setCompletedByUserId(Long completedByUserId) {
        this.completedByUserId = completedByUserId;
    }

    /**
     * Gets task description
     * @return task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets task description
     * @param taskDescription task description
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Gets completion notes
     * @return completion notes
     */
    public String getCompletionNotes() {
        return completionNotes;
    }

    /**
     * Sets completion notes
     * @param completionNotes completion notes
     */
    public void setCompletionNotes(String completionNotes) {
        this.completionNotes = completionNotes;
    }

    /**
     * Gets time estimate
     * @return time estimate
     */
    public Integer getTimeEstimate() {
        return timeEstimate;
    }

    /**
     * Sets time estimate
     * @param timeEstimate time estimate
     */
    public void setTimeEstimate(Integer timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    /**
     * Gets time spent
     * @return time spent
     */
    public Integer getTimeSpent() {
        return timeSpent;
    }

    /**
     * Sets time spent
     * @param timeSpent time spent
     */
    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     * Gets completed time
     * @return completed time
     */
    public Long getCompletedTime() {
        return completedTime;
    }

    /**
     * Sets completed time
     * @param completedTime completed time
     */
    public void setCompletedTime(Long completedTime) {
        this.completedTime = completedTime;
    }

}
