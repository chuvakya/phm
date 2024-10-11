package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.models.fmea.FailureReason;
import org.zs.phm3.service.fmea.CrudCommonService;

import java.util.List;

//import org.zs.phm3.models.fmea.dto.FailureModeDtoInput;
//import org.zs.phm3.service.fmea.FailureModeService;

@RestController
@RequestMapping(value = "/api/fmea-failureReason")
public class FmeaFailureReasonController {

    private final CrudCommonService<FailureReason> failureReasonService;

    @Autowired // inject FirstServiceImpl
//    private FailureDetectionMethodService failureDetectionMethodService;
    public FmeaFailureReasonController(@Qualifier("failureReasonService") CrudCommonService failureReasonService) {
        this.failureReasonService = failureReasonService;
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureReason> getAll() {
        return failureReasonService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureReason save(@RequestBody FailureReason failureReason) throws PhmException {
        return failureReasonService.save(failureReason);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureReason getById(@PathVariable("id") Integer id) throws PhmException {
        return failureReasonService.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") Integer id) {
        failureReasonService.deleteById(id);
    }
}
