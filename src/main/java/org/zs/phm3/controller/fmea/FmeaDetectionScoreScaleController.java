package org.zs.phm3.controller.fmea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.DetectionScoreScale;
import org.zs.phm3.models.fmea.FailureSeverity;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;

import java.util.List;

//import org.zs.phm3.models.fmea.dto.FailureModeDtoInput;
//import org.zs.phm3.service.fmea.FailureModeService;

@RestController
@RequestMapping(value = "/api/fmea-detectionScoreScale")
public class FmeaDetectionScoreScaleController {

    private final CrudCommonServiceWithStringId<DetectionScoreScale> detectionScoreScaleService;

    @Autowired // inject FirstServiceImpl
    public FmeaDetectionScoreScaleController(@Qualifier("detectionScoreScaleService") CrudCommonServiceWithStringId detectionScoreScaleService) {
        this.detectionScoreScaleService = detectionScoreScaleService;
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<DetectionScoreScale> getAll() {
        return detectionScoreScaleService.getAll();
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public DetectionScoreScale save(@RequestBody DetectionScoreScale detectionScoreScale) throws PhmException {
        return detectionScoreScaleService.save(detectionScoreScale);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DetectionScoreScale getById(@PathVariable("id") String id) throws PhmException {
        return detectionScoreScaleService.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable("id") String id) {
        detectionScoreScaleService.deleteById(id);
    }
}
