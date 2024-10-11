package org.zs.phm3.controller;

import org.zs.phm3.util.mapping.PhmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.service.calculation.CalculationAttrService;

@RestController
@RequestMapping("/api/calculation/")
public class CalculationController {

    @Autowired
    private CalculationAttrService calculationAttrService;

    @GetMapping(value = "getAll/{unitId}", produces = "application/json")
    public String getAllForUnit(@PathVariable String unitId) {
        return calculationAttrService.getAllByUnit(PhmUtil.toShortUUID(unitId));
    }

}
