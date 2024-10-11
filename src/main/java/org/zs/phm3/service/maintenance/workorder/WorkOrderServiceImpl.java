package org.zs.phm3.service.maintenance.workorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.maintenance.workorder.*;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.repository.maintenance.part.PartStockRepository;
import org.zs.phm3.repository.maintenance.workorder.LaborTaskRepository;
import org.zs.phm3.repository.maintenance.workorder.WorkOrderPartRepository;
import org.zs.phm3.repository.maintenance.workorder.WorkOrderRepository;
import org.zs.phm3.service.user.UserServiceImpl;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private LaborTaskRepository laborTaskRepository;

    @Autowired
    private WorkOrderPartRepository workOrderPartRepository;

    @Autowired
    private PartStockRepository partStockRepository;

    @Autowired
    private UserServiceImpl userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    @Override
    public WorkOrder getById(Long workOrderId) {
        return workOrderRepository.findById(workOrderId).get();
    }

    @Override
    public void deleteByIdSQL(Long workOrderId) {
        workOrderRepository.deleteByIdSQL(workOrderId);
    }

    @Override
    public List<WorkOrder> getListByProjectId(Integer projectId) {
        return workOrderRepository.getAllByProjectId(projectId);
    }

    @Override
    public JSONObject getJSONById(Long workOrderId) {
        List<List<Object>> workOrders = workOrderRepository.getAllById(workOrderId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workOrderId", workOrders.get(0).get(0));
        jsonObject.put("unitId", PhmUtil.toLongUUID((String) workOrders.get(0).get(1)));
        jsonObject.put("actualLabor", workOrders.get(0).get(2));
        jsonObject.put("assignedToUserId", workOrders.get(0).get(3));
        jsonObject.put("completedByUserId", workOrders.get(0).get(4));
        jsonObject.put("completionDate", workOrders.get(0).get(5));
        jsonObject.put("estimatedLabor", workOrders.get(0).get(6));
        jsonObject.put("maintenanceType", workOrders.get(0).get(7));
        jsonObject.put("orderName", workOrders.get(0).get(8));
        jsonObject.put("orderStatus", workOrders.get(0).get(9));
        jsonObject.put("priority", workOrders.get(0).get(10));
        jsonObject.put("summaryOfIssue", workOrders.get(0).get(11));
        jsonObject.put("workInstruction", workOrders.get(0).get(12));
        JSONArray jsonArrayLaborTasks = new JSONArray();
        List<List<Object>> laborTasks = laborTaskRepository.getAllByWorkOrderIdForTable(workOrderId);
        for (List<Object> laborTask : laborTasks) {
            JSONObject objectLaborTask = new JSONObject();
            objectLaborTask.put("laborTaskId", laborTask.get(0));
            objectLaborTask.put("taskDescription", laborTask.get(1));
            objectLaborTask.put("unitName", laborTask.get(2));
            objectLaborTask.put("userAssignedName", laborTask.get(3));
            objectLaborTask.put("completedByUserName", laborTask.get(4));
            objectLaborTask.put("completedTime", laborTask.get(5));
            objectLaborTask.put("completionNotes", laborTask.get(6));
            objectLaborTask.put("timeEstimate", laborTask.get(7));
            objectLaborTask.put("timeSpent", laborTask.get(8));
            objectLaborTask.put("completedByUserId", laborTask.get(9));
            objectLaborTask.put("assignedToUserId", laborTask.get(10));
            objectLaborTask.put("unitId", PhmUtil.toLongUUID((String) laborTask.get(11)));
            jsonArrayLaborTasks.add(objectLaborTask);
        }
        JSONArray jsonArrayParts = new JSONArray();
        List<List<Object>> parts = workOrderPartRepository.getAllIdAndNamePartByWorkOrderId(workOrderId);
        List<List<Object>> partsWithStock = workOrderPartRepository.getAllByWorkOrderId(workOrderId);
        JSONArray jsonArrayStockIds = new JSONArray();

        for (List<Object> part : parts) {
            JSONObject objectPart = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            objectPart.put("partId", part.get(0));
            objectPart.put("partName", part.get(1));
            Long actualQuantityUsed = 0L;
            for (List<Object> objects : partsWithStock) {
                if (objects.get(1) == part.get(0)) {
                    actualQuantityUsed += (Integer) objects.get(0);
                }
            }
            objectPart.put("actualQuantityUsed", actualQuantityUsed);
            jsonArrayParts.add(objectPart);
        }
        for (List<Object> objects : partsWithStock) {
            JSONObject jsonObjectPartWithStock = new JSONObject();
            jsonObjectPartWithStock.put("actualQuantityUsed", objects.get(0));
            jsonObjectPartWithStock.put("partId", objects.get(1));
            jsonObjectPartWithStock.put("partStockId", objects.get(2));
            jsonArrayStockIds.add(jsonObjectPartWithStock);
        }

        jsonObject.put("parts", jsonArrayParts);
        jsonObject.put("partStocks", jsonArrayStockIds);
        jsonObject.put("laborTasks", jsonArrayLaborTasks);
        return jsonObject;
    }

    @Override
    public String getAllForTableByProjectId(Integer projectId) {
        return getArrayFromWorkOrders(workOrderRepository.getAllForTableByProjectId(projectId)).toJSONString();

    }

    @Override
    public String getAllForTableByProjectIdAndStatus(Integer projectId, String orderStatus) {
        return getArrayFromWorkOrders(workOrderRepository.getAllForTableByProjectIdAndStatus(projectId,
                orderStatus)).toJSONString();
    }

    @Override
    public String getAllForTableByProjectIdAndStatusClosed(Integer projectId) {
        return getArrayFromWorkOrders(workOrderRepository.getAllForTableByProjectIdAndStatusClosed(projectId)).toJSONString();
    }

    private JSONArray getArrayFromWorkOrders(List<List<Object>> lists) {
        JSONArray jsonArray = new JSONArray();
        for (List<Object> objects : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", objects.get(0));
            jsonObject.put("orderStatus", objects.get(1));
            jsonObject.put("orderName", objects.get(2));
            jsonObject.put("type", objects.get(3));
            jsonObject.put("unitName", objects.get(4));
            jsonObject.put("priority", objects.get(5));
            jsonObject.put("assignedTo", objects.get(6));
            jsonObject.put("completedBy", objects.get(7));
            jsonObject.put("completionDate", objects.get(8));
            jsonObject.put("modifiedTime", objects.get(9));
            jsonObject.put("isScheduled", objects.get(10));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public String getAllForTableByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        return getArrayFromWorkOrders(workOrderRepository.getAllForTableByProjectIdAndOffsetAndLimit(projectId,
                offset, limit)).toJSONString();
    }

    @Override
    public void addLaborTaskFromRequest(WorkOrderRequest workOrderRequest, WorkOrder workOrder) {
        workOrderRepository.save(workOrder);
        for (LaborTaskRequest laborTask : workOrderRequest.getLaborTasks()) {
            UserEntity laborTaskAssignerUser = null;
            UserEntity laborTaskCompletedUser = null;
            if (laborTask.getAssignedToUserId() != null) {
                laborTaskAssignerUser = userService.getById(laborTask.getAssignedToUserId());
            }
            if (laborTask.getCompletedByUserId() != null) {
                laborTaskCompletedUser = userService.getById(laborTask.getCompletedByUserId());
            }
            LaborTask task = new LaborTask(laborTask.getTaskDescription(),
                    PhmUtil.toShortUUID(laborTask.getUnitId()), laborTask.getCompletionNotes(), laborTaskAssignerUser,
                    laborTaskCompletedUser, laborTask.getTimeEstimate(), laborTask.getTimeSpent(),
                    laborTask.getCompletedTime(), workOrder);
            laborTaskRepository.save(task);
        }
    }

    @Override
    public Long getCountByProjectId(Integer projectId) {
        return workOrderRepository.getCountByProjectId(projectId);
    }

    @Override
    public String writeOffFromTheStocks(List<WorkOrderPartRequest> workOrderPartRequests) {
        List<Long> partStockIds = new ArrayList<>();
        for (WorkOrderPartRequest part : workOrderPartRequests) {
            partStockIds.add(part.getPartStockId());
        }
        List<String> partStockNames = new ArrayList<>();

        List<List<Object>> partStocks = partStockRepository.getIdNameQuantityByPartStockIds(partStockIds);
        for (List<Object> partStock : partStocks) {
            for (WorkOrderPartRequest workOrderPartRequest : workOrderPartRequests) {
                if (workOrderPartRequest.getPartStockId() == partStock.get(0)) {
                    if (((Integer) partStock.get(2)) - ((Integer) workOrderPartRequest.getActualQuantityUsed()) < 0) {
                        if (!partStockNames.contains(partStock.get(1))) {
                            partStockNames.add((String) partStock.get(1));
                        }
                    }
                }
            }
        }
        if (partStockNames.isEmpty()) {
            StringBuilder query = new StringBuilder();
            for (List<Object> partStock : partStocks) {
                for (WorkOrderPartRequest workOrderPartRequest : workOrderPartRequests) {
                    if (workOrderPartRequest.getPartStockId() == partStock.get(0)) {
                       query.append("UPDATE part_stock SET quantity = " +
                               (((Integer) partStock.get(2)) - workOrderPartRequest.getActualQuantityUsed()) +
                               " WHERE id = " + partStock.get(0) +  ";");
                    }
                }
            }
            entityManager.createNativeQuery(query.toString()).executeUpdate();
        } else {
            StringBuilder messages = new StringBuilder("Not enough in stock: " + partStockNames.get(0));
            for (int i = 1; i < partStockNames.size(); i++) {
                messages.append(", " + partStockNames.get(i));
            }
            return messages.toString();
        }
        return "Success!";
    }
}
