package org.zs.phm3.controller.ptl;

import org.zs.phm3.models.ptl.SubGroupPTL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.service.ptl.SubGroupPTLService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/subGroupPTL/")
public class SubGroupPTLController {

    @Autowired
    private SubGroupPTLService subGroupPTLService;

    @GetMapping(value = "getAllSubGroupByUnitTypeId/{unitTypeId}")
    public List<SubGroupPTL> getAllSubGroupByUnitTypeId(@PathVariable Integer unitTypeId) {
        return subGroupPTLService.getAllByUnitTypeId(unitTypeId);
    }

}
