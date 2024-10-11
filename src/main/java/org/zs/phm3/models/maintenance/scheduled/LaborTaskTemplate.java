package org.zs.phm3.models.maintenance.scheduled;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.maintenance.workorder.LaborTask;
import org.zs.phm3.models.maintenance.workorder.WorkOrder;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * Scheduled maintenance labor task template entity
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "maintenance_labor_task_template")
public class LaborTaskTemplate {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Task description */
    private String taskDescription;

    /** Unit ID */
    private String unitId;

    /** Completion notes */
    private String completionNotes;

    /** assigned to user */
    @JsonIgnore
    @ManyToOne
    private UserEntity assignedToUser;

    /** Assigned to user ID */
    @Transient
    private Long assignedToUserId;

    /** Completed by user */
    @JsonIgnore
    @ManyToOne
    private UserEntity completedByUser;

    /** Completed by user ID */
    @Transient
    private Long completedByUserId;

    /** Time estimate */
    private Integer timeEstimate;

    /** Time spent */
    private Integer timeSpent;

    /** Completed time */
    private Long completedTime;

    /** Work order template */
    @JsonIgnore
    @ManyToOne
    private WorkOrderTemplate workOrderTemplate;

    /**
     * Constructor
     */
    public LaborTaskTemplate() {
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
     * @param workOrderTemplate work order template
     */
    public LaborTaskTemplate(String taskDescription, String unitId, String completionNotes, UserEntity assignedToUser,
                     UserEntity completedByUser, Integer timeEstimate, Integer timeSpent, Long completedTime,
                     WorkOrderTemplate workOrderTemplate) {
        this.taskDescription = taskDescription;
        this.unitId = unitId;
        this.completionNotes = completionNotes;
        this.assignedToUser = assignedToUser;
        this.completedByUser = completedByUser;
        this.timeEstimate = timeEstimate;
        this.timeSpent = timeSpent;
        this.completedTime = completedTime;
        this.workOrderTemplate = workOrderTemplate;
    }

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
