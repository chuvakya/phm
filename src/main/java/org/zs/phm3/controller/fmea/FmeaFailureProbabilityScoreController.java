package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureProbabilityScore;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;

import java.util.List;

@RestController
@RequestMapping(value = "/api/fmea-failureProbabilityScore")
public class FmeaFailureProbabilityScoreController {

    private final CrudCommonServiceWithStringId<FailureProbabilityScore> failureProbabilityScoreService;

    public FmeaFailureProbabilityScoreController(@Qualifier("failureProbabilityScoreService") CrudCommonServiceWithStringId failureProbabilityScoreService) {
        this.failureProbabilityScoreService = failureProbabilityScoreService;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FailureProbabilityScore> getAll() {
        return failureProbabilityScoreService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public FailureProbabilityScore save(@RequestBody FailureProbabilityScore failureDetectionScore) throws PhmException {
        return failureProbabilityScoreService.save(failureDetectionScore);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FailureProbabilityScore getById(@PathVariable("id") String id) throws PhmException {
        return failureProbabilityScoreService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") String id) {
        failureProbabilityScoreService.deleteById(id);
    }
}
