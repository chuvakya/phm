package org.zs.phm3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmErrorResponseHandler;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.rpm.RpmDtoIntput;
import org.zs.phm3.models.rpm.RpmDtoOutput;
import org.zs.phm3.models.rpm.TotalCalc;
import org.zs.phm3.service.rpm.RpmCommonService;

import java.util.List;

@RestController
@RequestMapping("api/rpm/")
public class RpmConrtoller extends BaseController{

    @Autowired
    RpmCommonService rpmCommonService;

//    @Autowired
//    private PhmErrorResponseHandler errorResponseHandler;

    @GetMapping(value = "getData/{id}", produces = "application/json")
    public RpmDtoOutput getDataById(@PathVariable("id") String unitId) throws IllegalArgumentException {
        return rpmCommonService.getData(unitId);
    }

    @PostMapping(value = "saveData/")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public RpmDtoOutput saveData(@RequestBody RpmDtoIntput rpmDtoIntput) throws PhmException {
        return rpmCommonService.saveData(rpmDtoIntput);
    }

    @PostMapping(value = "saveTotalCalcData/")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody List<TotalCalc> saveTotalCalaManually(@RequestBody TotalCalc totalCalcData) {
        return rpmCommonService.saveTotalCalcManually(totalCalcData);
    }
    @GetMapping(value = "getPtlData/{id}", produces = "application/json")
    public List<String[]> getAllUnitChildsRpmData(@PathVariable("id") String unitId) throws IllegalArgumentException {
        return rpmCommonService.getAllUnitChildsPtlData(unitId);
    }
}