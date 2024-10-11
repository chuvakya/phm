package org.zs.phm3.models.maintenance.workorder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.maintenance.scheduled.LaborTaskTemplate;
import org.zs.phm3.models.maintenance.scheduled.MaintenanceRule;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderPartTemplate;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Work order entity
 * @author Pavel Chuvak
 */
@Entity
public class WorkOrder {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unit ID */
    private String unitId;

    /** Order name */
    private String orderName;

    /** Order status */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /** Maintenance type */
    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType;

    /** Priority */
    private String priority;

    /** Summary of issue */
    private String summaryOfIssue;

    /** Work instruction */
    private String workInstruction;

    /** Estimated labor */
    private Integer estimatedLabor;

    /** Actual labor */
    private Integer actualLabor;

    /** Completetion date */
    private Long completionDate;

    /** Modified time */
    private Long modifiedTime;

    /** Is scheduled */
    private Boolean isScheduled = false;

    /** Assigned to user */
    @JsonIgnore
    @ManyToOne
    private UserEntity assignedToUser;

    /** Completed by user */
    @JsonIgnore
    @ManyToOne
    private UserEntity completedByUser;

    /** Labor tasks */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "workOrder", cascade = CascadeType.ALL)
    private List<LaborTask> laborTasks = new ArrayList<>();

    /** Work order parts */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "workOrder", cascade = CascadeType.ALL)
    private List<WorkOrderPart> workOrderParts = new ArrayList<>();

    /**
     * Constructor
     */
    public WorkOrder() {
    }

    /**
     * Constructor
     * @param orderName order name
     * @param unitId unit ID
     * @param orderStatus order status
     * @param maintenanceType maintenance type
     * @param priority priority
     * @param summaryOfIssue summary of issue
     * @param workInstruction work instruction
     * @param estimatedLabor estimated labor
     * @param actualLabor actual labor
     * @param assignedToUser assigned to user
     * @param completedByUser completed by user
     * @param modifiedTime modified time
     */
    public WorkOrder(String orderName, String unitId, OrderStatus orderStatus, MaintenanceType maintenanceType, String priority,
                     String summaryOfIssue, String workInstruction, Integer estimatedLabor, Integer actualLabor,
                     Long completionDate, UserEntity assignedToUser, UserEntity completedByUser, Long modifiedTime) {
        this.orderName = orderName;
        this.modifiedTime = modifiedTime;
        this.unitId = unitId;
        this.orderStatus = orderStatus;
        this.maintenanceType = maintenanceType;
        this.priority = priority;
        this.summaryOfIssue = summaryOfIssue;
        this.workInstruction = workInstruction;
        this.estimatedLabor = estimatedLabor;
        this.actualLabor = actualLabor;
        this.completionDate = completionDate;
        this.assignedToUser = assignedToUser;
        this.completedByUser = completedByUser;
    }

    /**
     * Gets modified time
     * @return modified time
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * Sets modified time
     * @param modifiedTime modified time
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * Gets order name
     * @return order name
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * Sets order name
     * @param orderName order name
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
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
     * Gets order status
     * @return order status
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets order status
     * @param orderStatus order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Gets maintenance type
     * @return maintenance type
     */
    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    /**
     * Sets maintenance type
     * @param maintenanceType maintenance type
     */
    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    /**
     * Gets priority
     * @return priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets priority
     * @param priority priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Gets summary of issue
     * @return summary of issue
     */
    public String getSummaryOfIssue() {
        return summaryOfIssue;
    }

    /**
     * Sets summary of issue
     * @param summaryOfIssue summary of issue
     */
    public void setSummaryOfIssue(String summaryOfIssue) {
        this.summaryOfIssue = summaryOfIssue;
    }

    /**
     * Gets work instruction
     * @return work instruction
     */
    public String getWorkInstruction() {
        return workInstruction;
    }

    /**
     * Sets work instruction
     * @param workInstruction work instruction
     */
    public void setWorkInstruction(String workInstruction) {
        this.workInstruction = workInstruction;
    }

    /**
     * Gets estimated labor
     * @return estimated labor
     */
    public Integer getEstimatedLabor() {
        return estimatedLabor;
    }

    /**
     * Sets estimated labor
     * @param estimatedLabor estimated labor
     */
    public void setEstimatedLabor(Integer estimatedLabor) {
        this.estimatedLabor = estimatedLabor;
    }

    /**
     * Gets actual labor
     * @return actual labor
     */
    public Integer getActualLabor() {
        return actualLabor;
    }

    /**
     * Sets actual labor
     * @param actualLabor actual labor
     */
    public void setActualLabor(Integer actualLabor) {
        this.actualLabor = actualLabor;
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
     * Gets completion date
     * @return completion date
     */
    public Long getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets completion date
     * @param completionDate completion date
     */
    public void setCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Gets is scheduled
     * @return is scheduled
     */
    public Boolean getScheduled() {
        return isScheduled;
    }

    /**
     * Gets is scheduled
     * @param scheduled is scheduled
     */
    public void setScheduled(Boolean scheduled) {
        isScheduled = scheduled;
    }

    /**
     * Gets work order parts
     * @return work order parts
     */
    public List<WorkOrderPart> getWorkOrderParts() {
        return workOrderParts;
    }

    /**
     * Sets work order parts
     * @param workOrderParts work order parts
     */
    public void setWorkOrderParts(List<WorkOrderPart> workOrderParts) {
        this.workOrderParts = workOrderParts;
    }

    /**
     * Gets labor tasks
     * @return labor tasks
     */
    public List<LaborTask> getLaborTasks() {
        return laborTasks;
    }

    /**
     * Sets labor tasks
     * @param laborTasks labor tasks
     */
    public void setLaborTasks(List<LaborTask> laborTasks) {
        this.laborTasks = laborTasks;
    }
}
