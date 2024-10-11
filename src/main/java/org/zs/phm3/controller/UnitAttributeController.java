package org.zs.phm3.controller;

import org.zs.phm3.dto.UnitAttributeDtoInput;
import org.zs.phm3.dto.UnitAttributeDtoOutput;
import org.zs.phm3.models.IdTextReturn;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.models.unit.UnitAttributeType;
import org.zs.phm3.repository.unit.UnitAttributeSQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.unit.UnitAttributeService;

import java.util.List;

@RestController
@RequestMapping("api/unit_attribute")

public class UnitAttributeController extends BaseController {
    @Autowired
    UnitAttributeService unitAttributeService;
    @Autowired
    UnitAttributeSQLRepository unitAttributeSQLRepo;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)

    public UnitAttribute save(@RequestBody UnitAttributeDtoInput unitAttributeForSaving) throws PhmException {
        return unitAttributeService.save(unitAttributeForSaving);
    }


/*    @GetMapping(value = "")

        public UnitAttribute getById(@PathVariable UnitAttributeKey unitAttributeKey) throws PhmException {
        return unitAttributeService.findById(unitAttributeKey);
    }*/

    @GetMapping(value = "/{unitId}")
    public List<UnitAttribute> getAllByUnitId(@PathVariable("unitId") String unitId) throws PhmException {
        return unitAttributeService.getAllByUnitId(unitId);

    }

    @DeleteMapping(value = "")
    public void deleteById(@RequestBody UnitAttributeKey unitModelAttributeKey) throws PhmException {
        unitAttributeService.deleteById(unitModelAttributeKey);
    }

    @GetMapping(value = "/uom")
    public List<IdTextReturn> getUomDataForDisplay() {
        return unitAttributeService.getUomDataForDisplay();
    }

    @GetMapping(value = "/keysfromts/{unitId}")
    public List<IdTextReturn> getAllAttrKeysForUnitId(@PathVariable("unitId") String unitId)
    {
        return unitAttributeService.getAllAttrKeysForUnitId(unitId);
    }

    @GetMapping(value = "/customid")
    public List<IdTextReturn> getAllCustomUnitId()
    {
        return unitAttributeService.getAllCustomUnitId();
    }
    @GetMapping(value = "/table/{unitId}")

    public List<UnitAttributeDtoOutput> getAllForTableByUnitId(@PathVariable("unitId") String unitId) throws PhmException {
        return unitAttributeService.getAllForTableByUnitId(unitId);

    }

    @GetMapping(value = "/getypes")
    public List<UnitAttributeType> getTypes()
    {
        return unitAttributeService.getTypes();
    }
}


