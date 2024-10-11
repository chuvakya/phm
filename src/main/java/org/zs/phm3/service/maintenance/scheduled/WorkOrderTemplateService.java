package org.zs.phm3.service.maintenance.scheduled;

import org.zs.phm3.models.maintenance.scheduled.LaborTaskTemplate;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderPartTemplate;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;
import org.zs.phm3.models.maintenance.workorder.LaborTaskRequest;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPartRequest;

import java.util.List;

public interface WorkOrderTemplateService {
    WorkOrderTemplate save(WorkOrderTemplate workOrderTemplate);
    void addLaborTasks(List<LaborTaskTemplate> laborTasks, Long workOrderTemplateId);
    void addWorkOrderParts(List<WorkOrderPartTemplate> parts, Long workOrderTemplateId);
}
