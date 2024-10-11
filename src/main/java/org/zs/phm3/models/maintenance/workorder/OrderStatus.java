package org.zs.phm3.models.maintenance.workorder;

/**
 * Enum order statuses
 * @author Pavel Chuvak
 */
public enum OrderStatus {
    REQUESTED,
    ON_HOLD,
    DRAFT,
    ASSIGNED,
    OPEN,
    WORK_IN_PROGRESS,
    CLOSED_COMPLETED,
    CLOSED_INCOMPLETE,
    WAITING_PART
}
