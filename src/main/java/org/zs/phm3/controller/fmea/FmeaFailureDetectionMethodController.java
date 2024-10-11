package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureDetectionMethod;
import org.zs.phm3.service.fmea.CrudCommonService;

import java.util.List;

//import org.zs.phm3.service.fmea.FailureDetectionMethodService;

@RestController
@RequestMapping(value = "/api/fmea-failureDetectionMethod")
public class FmeaFailureDetectionMethodController {

    private final CrudCommonService<FailureDetectionMethod> failureDetectionMethodService;

    @Autowired // inject FirstServiceImpl
//    private FailureDetectionMethodService failureDetectionMethodService;
    public FmeaFailureDetectionMethodController(@Qualifier("failureDetectionMethodService") CrudCommonService failureDetectionMethodService) {
        this.failureDetectionMethodService = failureDetectionMethodService;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureDetectionMethod> getAll() {
        return failureDetectionMethodService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureDetectionMethod save(@RequestBody FailureDetectionMethod failureDetectionMethod) throws PhmException {
        return failureDetectionMethodService.save(failureDetectionMethod);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureDetectionMethod getById(@PathVariable("id") Integer id) throws PhmException {
        return failureDetectionMethodService.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") Integer id) {
        failureDetectionMethodService.deleteById(id);
    }
}
