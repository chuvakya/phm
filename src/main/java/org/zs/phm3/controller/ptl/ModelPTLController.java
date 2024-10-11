package org.zs.phm3.controller.ptl;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.zs.phm3.models.ptl.ModelPTL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ptl.ManufacturerPTLService;
import org.zs.phm3.service.ptl.ModelPTLService;
import org.zs.phm3.service.ptl.UnitTypePTLService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/model_ptl/")
public class ModelPTLController {

    @Autowired
    private ModelPTLService modelPTLService;

    @Autowired
    private ManufacturerPTLService manufacturerPTLService;

    @Autowired
    private UnitTypePTLService unitTypePTLService;

    @Autowired
    private UserService userService;

    @Autowired
    private SQLHelper SQLHelper;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelPTL create(@RequestParam Integer unitTypeId, @RequestParam Long manufacturerId,
                           @RequestParam String name, @RequestParam String revision) throws PhmException {
        ModelPTL modelPTL = new ModelPTL(name, manufacturerPTLService.getById(manufacturerId),
                unitTypePTLService.getById(unitTypeId), userService.getByLogin("Admin"), System.currentTimeMillis(), revision);
        return modelPTLService.save(modelPTL);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteById(@RequestBody List<Long> modelIds) {
        SQLHelper.deleteAll("model_ptl", "id", modelIds);
    }

    @PatchMapping(value = "edit")
    public void editById(@RequestParam Long modelId, @RequestParam(required = false) String name,
                         @RequestParam(required = false) Integer unitTypeId,
                         @RequestParam(required = false) Long manufacturerId,
                         @RequestParam(required = false) String revision) throws PhmException {
        ModelPTL modelPTL = modelPTLService.getById(modelId);
        if (name != null) {
            modelPTL.setName(name);
        }
        if (unitTypeId != null) {
            modelPTL.setUnitTypePTL(unitTypePTLService.getById(unitTypeId));
        }
        if (manufacturerId != null) {
            modelPTL.setManufacturerPTL(manufacturerPTLService.getById(manufacturerId));
        }
        if (revision != null) {
            modelPTL.setRevision(revision);
        }
        modelPTL.setModifiedBy(userService.getByLogin("Admin"));
        modelPTLService.save(modelPTL);
    }

    @GetMapping(value = "getListManufacturersAndModels/{unitTypeId}", produces = "application/json")
    public String getListManufacturersAndModelsByUnitTypeId(@PathVariable Integer unitTypeId) {
        return modelPTLService.getAllManufacturersAndModelsByUnitTypeId(unitTypeId);
    }

    @GetMapping(value = "getList/{offset}/{limit}", produces = "application/json")
    public String getList(@PathVariable Integer offset, @PathVariable Integer limit) {
        return modelPTLService.getListByOffsetAndLimit(offset, limit);
    }

    @GetMapping(value = "getAllByManufacturerIdAndUnitTypeId/{manufacturerId}/{unitTypeId}", produces = "application/json")
    public List<ModelPTL> getAllByManufacturerIdAndUnitTypeId(@PathVariable Long manufacturerId, @PathVariable Integer unitTypeId) {
        return modelPTLService.getAllByManufacturerIdAndUnitTypeId(manufacturerId, unitTypeId);
    }

    @GetMapping(value = "getAllIdAndNameByManufacturerIdAndUnitTypeId/{manufacturerId}/{unitTypeId}", produces = "application/json")
    public String getAllIdAndNameByManufacturerIdAndUnitTypeId(@PathVariable Long manufacturerId, @PathVariable Integer unitTypeId) {
        return modelPTLService.getAllIdAndNameByManufacturerIdAndUnitTypeId(manufacturerId, unitTypeId);
    }

    @GetMapping(value = "getAllManufacturersByUnitTypeId/{unitTypeId}", produces = "application/json")
    public List<ManufacturerPTL> getAllManufacturersByUnitTypeId(@PathVariable Integer unitTypeId) {
        return modelPTLService.getAllManufacturersByUnitTypeId(unitTypeId);
    }

    @GetMapping(value = "getCount")
    public Long getCount() {
        return modelPTLService.getCount();
    }

    @GetMapping(value = "getAllIdAndRevisionByModelName", produces = "application/json")
    public String getAllIdAndRevisionByModelName(@RequestParam String modelName) {
        return modelPTLService.getAllIdAndRevisionByModelName(modelName);
    }

}
