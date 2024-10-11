package org.zs.phm3.controller.fault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.fault.Fault;
import org.zs.phm3.models.fault.FaultFilter;
import org.zs.phm3.models.fault.UnitStatus;
import org.zs.phm3.service.fault.FaultService;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.List;

@RestController
@RequestMapping(value = "/api/fault/")
public class FaultController {

    @Autowired
    private FaultService faultService;

    @GetMapping(value = "getAllByUnitId/{unitId}")
    public List<Fault> getAllByUnitId(@PathVariable String unitId) {
        return faultService.getFaultsByUnitId(PhmUtil.toShortUUID(unitId));
    }

    @GetMapping(value = "getAllByUnitIdAndOffsetAndLimit/{unitId}/{offset}/{limit}", produces = "application/json")
    public String getAllByUnitId(@PathVariable String unitId, @PathVariable Integer offset, @PathVariable Integer limit) {
        return faultService.getFaultsByUnitIdAndOffsetAndLimit(PhmUtil.toShortUUID(unitId), offset, limit);
    }

    @GetMapping(value = "getMenu/{projectId}", produces = "application/json")
    public String getMenu(@PathVariable Integer projectId) {
        return faultService.getMenu(projectId);
    }

    @GetMapping(value = "getCount/{unitId}")
    public Long getCountByProjectId(@PathVariable String unitId) {
        return faultService.getCountByUnitId(PhmUtil.toShortUUID(unitId));
    }

    @GetMapping(value = "getUnitStatuses/{projectId}")
    public List<UnitStatus> getUnitStatusesByProjectId(@PathVariable Integer projectId) {
        return faultService.getAllUnitStatusByProjectId(projectId);
    }

    @PostMapping(value = "getAllByFilter", produces = "application/json")
    public String getAllByFilter(@RequestBody FaultFilter faultFilter) {
        return faultService.getAllByFilter(faultFilter);
    }

    @GetMapping(value = "getMinMaxForErrors/{projectId}", produces = "application/json")
    public String getMinMaxForErrors(@PathVariable Integer projectId) {
        return faultService.getMinAndMaxTimestampFaultLogByProjectId(projectId);
    }

    @GetMapping(value = "getMinMaxForStartTime/{projectId}", produces = "application/json")
    public String getMinMaxForStartTime(@PathVariable Integer projectId) {
        return faultService.getMinMaxStartTimeByProjectId(projectId);
    }

    @GetMapping(value = "getMinMaxForEndTime/{projectId}", produces = "application/json")
    public String getMinMaxForEndTime(@PathVariable Integer projectId) {
        return faultService.getMinMaxEndTimeByProjectId(projectId);
    }

    @GetMapping(value = "getMessageByFaultId/{faultId}")
    public String getMessageByFaultId(@PathVariable Long faultId) {
        return faultService.getMessageByFaultId(faultId);
    }
}
