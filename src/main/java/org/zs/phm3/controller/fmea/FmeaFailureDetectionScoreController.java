package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureDetectionScore;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;

import java.util.List;

@RestController
@RequestMapping(value = "/api/fmea-failureDetectionScore")
public class FmeaFailureDetectionScoreController {

    private final CrudCommonServiceWithStringId<FailureDetectionScore> failureDetectionScoreService;

    public FmeaFailureDetectionScoreController(@Qualifier("failureDetectionScoreService") CrudCommonServiceWithStringId failureDetectionScoreService) {
        this.failureDetectionScoreService = failureDetectionScoreService;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureDetectionScore> getAll() {
        return failureDetectionScoreService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureDetectionScore save(@RequestBody FailureDetectionScore failureDetectionScore) throws PhmException {
        return failureDetectionScoreService.save(failureDetectionScore);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureDetectionScore getById(@PathVariable("id") String id) throws PhmException {
        return failureDetectionScoreService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") String id) {
        failureDetectionScoreService.deleteById(id);
    }
}
