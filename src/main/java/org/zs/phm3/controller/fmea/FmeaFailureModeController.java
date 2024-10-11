package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.service.fmea.CrudCommonService;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/fmea-failureMode")
public class FmeaFailureModeController {

    private final CrudCommonService<FailureMode> failureModeService;

    @Autowired
    public FmeaFailureModeController(@Qualifier("failureModeService") CrudCommonService failureModeService) {
        this.failureModeService = failureModeService;
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureMode> getAll() {
        return failureModeService.getAll();
    }

    @GetMapping(value = "/findAllByName", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureMode> findAllByName(String searchedName) {
        return failureModeService.findAllByName(searchedName);
    }

    @GetMapping(value = "/findAllByCategoryTypeId", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureMode> findAllByCategoryTypeId(Integer searchedId) {
        return failureModeService.findByCategoryTypeId(searchedId);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureMode save(@RequestBody @Validated FailureMode failureMode) throws PhmException {
        //        try {
        return failureModeService.save(failureMode);
/*        } catch (SqlExceptionHelper exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);
        }*/
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureMode getById(@PathVariable("id") Integer id) throws PhmException {
        return failureModeService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") Integer id) {
        failureModeService.deleteById(id);
    }
}
