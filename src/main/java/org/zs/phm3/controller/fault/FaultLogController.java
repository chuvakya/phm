package org.zs.phm3.controller.fault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.models.fault.FaultLog;
import org.zs.phm3.service.fault.FaultLogService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/fault_log/")
public class FaultLogController {

    @Autowired
    private FaultLogService faultLogService;

    @GetMapping(value = "getAllByFaultId/{faultId}", produces = "application/json")
    public String getAllByFaultId(@PathVariable Long faultId) {
        return faultLogService.getAllByFaultId(faultId);
    }

    @GetMapping(value = "getAllByIntervalAndFaultId/{startTime}/{endTime}/{faultId}")
    public List<FaultLog> getAllByIntervalAndFaultId(@PathVariable Long startTime, @PathVariable Long endTime,
                                                     @PathVariable Long faultId) {
        return faultLogService.getAllByIntervalAndFaultId(startTime, endTime, faultId);
    }

    @GetMapping(value = "getAllByIntervalAndFaultId/{time}/{faultId}")
    public List<FaultLog> getByMoreTimestampAndFaultId(@PathVariable Long time, @PathVariable Long faultId) {
        return faultLogService.getByMoreTimestampAndFaultId(System.currentTimeMillis() - time, faultId);
    }

    @GetMapping(value = "getMinMaxForTimestampByFaultId/{faultId}", produces = "application/json")
    public String getMinMaxForStartTime(@PathVariable Long faultId) {
        return faultLogService.getMinMaxTimestampByFaultId(faultId);
    }

}
