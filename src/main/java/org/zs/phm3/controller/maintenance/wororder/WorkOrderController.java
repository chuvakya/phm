package org.zs.phm3.controller.maintenance.wororder;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.maintenance.workorder.MaintenanceType;
import org.zs.phm3.models.maintenance.workorder.OrderStatus;
import org.zs.phm3.models.maintenance.workorder.WorkOrder;
import org.zs.phm3.models.maintenance.workorder.WorkOrderRequest;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.service.maintenance.workorder.LaborTaskService;
import org.zs.phm3.service.maintenance.workorder.WorkOrderPartService;
import org.zs.phm3.service.maintenance.workorder.WorkOrderService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.service.user.UserServiceImpl;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/maintenance/work_order/")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LaborTaskService laborTaskService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private UnitService unitService;

    @Autowired
    private WorkOrderPartService workOrderPartService;

    @Transactional
    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody WorkOrderRequest workOrderRequest, @RequestParam String orderName,
                       @RequestParam String unitId, @RequestParam(required = false) OrderStatus orderStatus,
                       @RequestParam(required = false) MaintenanceType maintenanceType, @RequestParam(required = false) String priority,
                       @RequestParam(required = false) String summaryOfIssue, @RequestParam(required = false) String workInstruction,
                       @RequestParam(required = false) Integer estimatedLabor, @RequestParam(required = false) Integer actualLabor,
                       @RequestParam(required = false) Long completionDate, @RequestParam(required = false) Long assignedToUserId,
                       @RequestParam(required = false) Long completedByUserId) {
        String unitIdShort = PhmUtil.toShortUUID(unitId);
        UnitEntity unitEntity = unitService.getUnitById(unitIdShort);

        UserEntity assignedUser = null;
        UserEntity completedUser = null;
        if (assignedToUserId != null) {
            assignedUser = userService.getById(assignedToUserId);
        }
        if (completedByUserId != null) {
            completedUser = userService.getById(completedByUserId);
        }

        WorkOrder workOrder = new WorkOrder(orderName, unitIdShort, orderStatus, maintenanceType, priority, summaryOfIssue,
                workInstruction, estimatedLabor, actualLabor, completionDate, assignedUser, completedUser,
                System.currentTimeMillis());
        workOrderService.save(workOrder);
        laborTaskService.addLaborTasks(workOrderRequest.getLaborTasks(), workOrder.getId());
        workOrderPartService.addWorkOrderParts(workOrder.getId(), workOrderRequest.getParts());

        if (orderStatus != null && orderStatus.toString().equals("CLOSED_COMPLETED") && !workOrderRequest.getParts().isEmpty()) {
            String result = workOrderService.writeOffFromTheStocks(workOrderRequest.getParts());
            if (!result.equals("Success!")) {
                workOrderService.deleteByIdSQL(workOrder.getId());
                return result;
            }
        }
        return "Success!";
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteById(@RequestBody List<Long> workOrderIds) {
        SQLHelper.deleteAll("work_order", "id", workOrderIds);
    }

    @PatchMapping(value = "updateById/{workOrderId}")
    public String updateById(@PathVariable Long workOrderId, @RequestBody WorkOrderRequest workOrderRequest,
                           @RequestParam String orderName,
                           @RequestParam String unitId,
                           @RequestParam(required = false) OrderStatus orderStatus,
                           @RequestParam(required = false) MaintenanceType maintenanceType,
                           @RequestParam(required = false) String priority,
                           @RequestParam(required = false) String summaryOfIssue,
                           @RequestParam(required = false) String workInstruction,
                           @RequestParam(required = false) Integer estimatedLabor,
                           @RequestParam(required = false) Integer actualLabor,
                           @RequestParam(required = false) Long completionDate,
                           @RequestParam(required = false) Long assignedToUserId,
                           @RequestParam(required = false) Long completedByUserId) {
        if (orderStatus != null && orderStatus.toString().equals("CLOSED_COMPLETED") && !workOrderRequest.getParts().isEmpty()) {
            String result = workOrderService.writeOffFromTheStocks(workOrderRequest.getParts());
            if (!result.equals("Success!")) {
                return result;
            }
        }
        WorkOrder workOrder = workOrderService.getById(workOrderId);
        workOrder.setOrderName(orderName);
        workOrder.setUnitId(PhmUtil.toShortUUID(unitId));
        workOrder.setOrderStatus(orderStatus);
        workOrder.setMaintenanceType(maintenanceType);
        workOrder.setPriority(priority);
        workOrder.setSummaryOfIssue(summaryOfIssue);
        workOrder.setWorkInstruction(workInstruction);
        workOrder.setEstimatedLabor(estimatedLabor);
        workOrder.setActualLabor(actualLabor);
        workOrder.setCompletionDate(completionDate);
        workOrder.setModifiedTime(System.currentTimeMillis());
        if (assignedToUserId == null) {
            workOrder.setAssignedToUser(null);
        } else {
            workOrder.setAssignedToUser(userService.getById(assignedToUserId));
        }
        if (completedByUserId == null) {
            workOrder.setCompletedByUser(null);
        } else {
            workOrder.setCompletedByUser(userService.getById(completedByUserId));
        }
        laborTaskService.deleteAllByWorkOrderSQL(workOrderId);
        laborTaskService.addLaborTasks(workOrderRequest.getLaborTasks(), workOrderId);
        SQLHelper.deleteAll("work_order_part", "work_order_id", Arrays.asList(workOrderId));
        workOrderPartService.addWorkOrderParts(workOrderId, workOrderRequest.getParts());
        workOrderService.save(workOrder);
        return "Success!";
    }

    @GetMapping(value = "getById/{workOrderId}", produces = "application/json")
    public String getById(@PathVariable Long workOrderId) {
        return workOrderService.getJSONById(workOrderId).toJSONString();
    }

    @GetMapping(value = "getListForTable/{projectId}", produces = "application/json")
    public String getListForTableByProjectId(@PathVariable Integer projectId) {
        return workOrderService.getAllForTableByProjectId(projectId);
    }

    @GetMapping(value = "getListForTable/{projectId}/{orderStatus}", produces = "application/json")
    public String getListForTableByProjectId(@PathVariable Integer projectId, @PathVariable OrderStatus orderStatus) {
        if (orderStatus.toString().equals("CLOSED_COMPLETED") || orderStatus.toString().equals("CLOSED_INCOMPLETE")) {
            return workOrderService.getAllForTableByProjectIdAndStatusClosed(projectId);
        }
        return workOrderService.getAllForTableByProjectIdAndStatus(projectId, orderStatus.toString());
    }

    @GetMapping(value = "getListForTableAndOffsetAndLimit/{projectId}/{offset}/{limit}", produces = "application/json")
    public String getListForTableByProjectId(@PathVariable Integer projectId, @PathVariable Integer offset,
                                             @PathVariable Integer limit) {
        return workOrderService.getAllForTableByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }

    @GetMapping(value = "getCount/{projectId}")
    public Long getCountByProjectId(@PathVariable Integer projectId) {
        return workOrderService.getCountByProjectId(projectId);
    }
}
