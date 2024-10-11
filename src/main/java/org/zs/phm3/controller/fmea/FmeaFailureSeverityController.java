package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.models.fmea.FailureSeverity;
import org.zs.phm3.service.fmea.CrudCommonService;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;

import java.util.List;

//import org.zs.phm3.models.fmea.dto.FailureModeDtoInput;
//import org.zs.phm3.service.fmea.FailureModeService;

@RestController
@RequestMapping(value = "/api/fmea-failureSeverity")
public class FmeaFailureSeverityController {

    private final CrudCommonServiceWithStringId<FailureSeverity> failureSeverityService;

    @Autowired // inject FirstServiceImpl
    public FmeaFailureSeverityController(@Qualifier("failureSeverityService") CrudCommonServiceWithStringId failureSeverityService) {
        this.failureSeverityService = failureSeverityService;
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureSeverity> getAll() {
        return failureSeverityService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureSeverity save(@RequestBody FailureSeverity failureMode) throws PhmException {
        return failureSeverityService.save(failureMode);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureSeverity getById(@PathVariable("id") String id) throws PhmException {
        return failureSeverityService.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") String id) {
        failureSeverityService.deleteById(id);
    }
}
