package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.Recommendation;
import org.zs.phm3.service.fmea.CrudCommonService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/fmea-recommendation")
public class RecommendationController {

    private final CrudCommonService<Recommendation> recommendationService;

    @Autowired // inject FirstServiceImpl
//    private FailureDetectionMethodService failureDetectionMethodService;
    public RecommendationController(@Qualifier("recommendationService") CrudCommonService recommendationService) {
        this.recommendationService = recommendationService;
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Recommendation> getAll() {
        return recommendationService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public Recommendation save(@RequestBody Recommendation Recommendation) throws PhmException {
        return recommendationService.save(Recommendation);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recommendation getById(@PathVariable("id") Integer id) throws PhmException {
        return recommendationService.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") Integer id) {
        recommendationService.deleteById(id);
    }
}
