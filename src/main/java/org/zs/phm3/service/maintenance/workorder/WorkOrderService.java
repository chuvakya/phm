package org.zs.phm3.service.maintenance.workorder;

import org.json.simple.JSONObject;
import org.zs.phm3.models.maintenance.workorder.WorkOrder;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPartRequest;
import org.zs.phm3.models.maintenance.workorder.WorkOrderRequest;

import java.util.List;

public interface WorkOrderService {
    WorkOrder save(WorkOrder workOrder);
    WorkOrder getById(Long workOrderId);
    void deleteByIdSQL(Long workOrderId);
    List<WorkOrder> getListByProjectId(Integer projectId);
    JSONObject getJSONById(Long workOrderId);
    String getAllForTableByProjectId(Integer projectId);
    String getAllForTableByProjectIdAndStatus(Integer projectId, String orderStatus);
    String getAllForTableByProjectIdAndStatusClosed(Integer projectId);
    String getAllForTableByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);
    void addLaborTaskFromRequest(WorkOrderRequest workOrderRequest, WorkOrder workOrder);
    Long getCountByProjectId(Integer projectId);
    String writeOffFromTheStocks(List<WorkOrderPartRequest> workOrderPartRequests);
}
