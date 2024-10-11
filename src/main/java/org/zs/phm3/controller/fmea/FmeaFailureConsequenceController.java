package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureConsequence;
import org.zs.phm3.service.fmea.CrudCommonService;

import java.util.List;

//import org.zs.phm3.models.fmea.dto.FailureModeDtoInput;
//import org.zs.phm3.service.fmea.FailureModeService;

@RestController
@RequestMapping(value = "/api/fmea-failureConsequence")
public class FmeaFailureConsequenceController {

    private final CrudCommonService<FailureConsequence> failureConsequenceService;

    @Autowired // inject FirstServiceImpl
//    private FailureDetectionMethodService failureDetectionMethodService;
    public FmeaFailureConsequenceController(@Qualifier("failureConsequenceService") CrudCommonService failureConsequenceService) {
        this.failureConsequenceService = failureConsequenceService;
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureConsequence> getAll() {
        return failureConsequenceService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureConsequence save(@RequestBody FailureConsequence failureConsequence) throws PhmException {
        return failureConsequenceService.save(failureConsequence);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureConsequence getById(@PathVariable("id") Integer id) throws PhmException {
        return failureConsequenceService.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") Integer id) {
        failureConsequenceService.deleteById(id);
    }
}
