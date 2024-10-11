package org.zs.phm3.models.maintenance.workorder;

import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * Work order labor task entity
 * @author Pavel Chuvak
 */
@Entity
public class LaborTask {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Task description */
    private String taskDescription;

    /** Unit ID */
    private String unitId;

    /** Completion notes */
    private String completionNotes;

    /** Assigned to user */
    @ManyToOne
    private UserEntity assignedToUser;

    /** Completed by user */
    @ManyToOne
    private UserEntity completedByUser;

    /** TIme estimate */
    private Integer timeEstimate;

    /** Time spent */
    private Integer timeSpent;

    /** Completed time */
    private Long completedTime;

    /** Work order */
    @ManyToOne
    private WorkOrder workOrder;

    /**
     * Constructor
     */
    public LaborTask() {
    }

    /**
     * Constructor
     * @param taskDescription task description
     * @param unitId unit ID
     * @param completionNotes completion notes
     * @param assignedToUser assigned to user
     * @param completedByUser comleted by user
     * @param timeEstimate time estimate
     * @param timeSpent time spent
     * @param completedTime completed time
     * @param workOrder work order
     */
    public LaborTask(String taskDescription, String unitId, String completionNotes, UserEntity assignedToUser,
                     UserEntity completedByUser, Integer timeEstimate, Integer timeSpent, Long completedTime,
                     WorkOrder workOrder) {
        this.taskDescription = taskDescription;
        this.unitId = unitId;
        this.completionNotes = completionNotes;
        this.assignedToUser = assignedToUser;
        this.completedByUser = completedByUser;
        this.timeEstimate = timeEstimate;
        this.timeSpent = timeSpent;
        this.completedTime = completedTime;
        this.workOrder = workOrder;
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
     * Gets assigned to user
     * @return assigned to user
     */
    public UserEntity getAssignedToUser() {
        return assignedToUser;
    }

    /**
     * Sets assigned to user
     * @param assignedToUser assigned to user
     */
    public void setAssignedToUser(UserEntity assignedToUser) {
        this.assignedToUser = assignedToUser;
    }

    /**
     * Gets completed by user
     * @return completed by user
     */
    public UserEntity getCompletedByUser() {
        return completedByUser;
    }

    /**
     * Sets completed by user
     * @param completedByUser completed by user
     */
    public void setCompletedByUser(UserEntity completedByUser) {
        this.completedByUser = completedByUser;
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

    /**
     * Gets work order
     * @return work order
     */
    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    /**
     * Sets work order
     * @param workOrder work order
     */
    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }
}
