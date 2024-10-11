package org.zs.phm3.models.maintenance;

import org.zs.phm3.models.maintenance.workorder.MaintenanceType;
import org.zs.phm3.models.maintenance.workorder.OrderStatus;

public class MaintenanceUtil {

    static public String getMaintenanceTypeName(MaintenanceType maintenanceType) {
        return switch (maintenanceType) {
            case DAMAGE -> "Damage";
            case CORRECTIVE -> "Corrective";
            case INSPECTION -> "Inspection";
            case METER_READ -> "Meter read";
            case PREVENTIVE -> "Preventive";
        };
    }

    static public String getOrderStatusName(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case ASSIGNED -> "Assigned";
            case CLOSED_COMPLETED -> "Closed, Completed";
            case CLOSED_INCOMPLETE -> "Closed, Incomplete";
            case DRAFT -> "Draft";
            case ON_HOLD -> "On Hold";
            case OPEN -> "Open";
            case REQUESTED -> "Requested";
            case WAITING_PART -> "Waiting Part";
            case WORK_IN_PROGRESS -> "Work In Progress";
        };
    }


}
