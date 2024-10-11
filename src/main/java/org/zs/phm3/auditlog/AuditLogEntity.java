package org.zs.phm3.auditlog;

//import jakarta.validation.constraints.Max;

import org.zs.phm3.models.basic.IdLongEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "audit_log")
public class AuditLogEntity extends IdLongEntity {

    @Column(length = 31)
    private String entityId;

    @Column(length = 31)
    private String userId;

    private String userName;

    private LocalDateTime actionTime;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    @Column(length = 1000000)
    private String actionData;

    @Column(length = 1000000)
    private String actionErrorDetails;

    public AuditLogEntity() {
    }

    public AuditLogEntity(String entityId, String userId, String userName, ActionType actionType,
                          ActionStatus actionStatus, String actionData,
                          String actionErrorDetails) {
        this.entityId = entityId;           // test
        this.userId = null;                 // test
        this.userName = null;               // test
        this.actionTime = LocalDateTime.now();
        this.actionType = actionType;
        this.actionStatus = actionStatus;
        this.actionData = actionData;
        this.actionErrorDetails = actionErrorDetails;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getActionData() {
        return actionData;
    }

    public void setActionData(String actionData) {
        this.actionData = actionData;
    }

    public String getActionErrorDetails() {
        return actionErrorDetails;
    }

    public void setActionErrorDetails(String actionErrorDetails) {
        this.actionErrorDetails = actionErrorDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLogEntity that = (AuditLogEntity) o;
        return Objects.equals(entityId, that.entityId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(actionTime, that.actionTime) &&
                actionType == that.actionType &&
                actionStatus == that.actionStatus &&
                Objects.equals(actionData, that.actionData) &&
                Objects.equals(actionErrorDetails, that.actionErrorDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityId, userId, userName, actionTime, actionType, actionStatus, actionData, actionErrorDetails);
    }
}
