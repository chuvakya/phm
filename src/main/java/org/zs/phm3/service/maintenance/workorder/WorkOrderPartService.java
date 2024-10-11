package org.zs.phm3.service.maintenance.workorder;

import org.zs.phm3.models.maintenance.workorder.WorkOrderPartRequest;

import java.util.List;

public interface WorkOrderPartService {

    void addWorkOrderParts(Long workOrderId, List<WorkOrderPartRequest> parts);

}
