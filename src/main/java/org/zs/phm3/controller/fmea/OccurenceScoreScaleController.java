package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.OccurrenceScoreScale;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;

import java.util.List;

@RestController
@RequestMapping(value = "/api/fmea-occurrenceScoreScale")
public class OccurenceScoreScaleController {

    private final CrudCommonServiceWithStringId<OccurrenceScoreScale> occurrenceScoreScaleService;

    public OccurenceScoreScaleController(@Qualifier("occurrenceScoreScaleService") CrudCommonServiceWithStringId occurrenceScoreScaleService) {
        this.occurrenceScoreScaleService = occurrenceScoreScaleService;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<OccurrenceScoreScale> getAll() {
        return occurrenceScoreScaleService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceScoreScale save(@RequestBody OccurrenceScoreScale failureDetectionScore) throws PhmException {
        return occurrenceScoreScaleService.save(failureDetectionScore);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OccurrenceScoreScale getById(@PathVariable("id") String id) throws PhmException {
        return occurrenceScoreScaleService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") String id) {
        occurrenceScoreScaleService.deleteById(id);
    }
}
