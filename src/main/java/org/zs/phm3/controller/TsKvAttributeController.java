package org.zs.phm3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.ts.TsKvAttrDataTypes;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.ts.TsKvAttributeTypeService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/tsKvAttr/")
public class TsKvAttributeController {

    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    @Autowired
    private TsKvAttributeTypeService tsKvAttributeTypeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private UomService uomService;

    @PostMapping(value = "createAttr")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveAttr(@RequestParam String attrKey, @RequestParam String unitId,
                                  @RequestParam String name, @RequestParam String deviceId,
                                  @RequestParam Integer inputUomId, @RequestParam Integer outputUomId,
                                  @RequestParam TsKvAttrDataTypes dataType, @RequestParam Boolean isTable,
                                  @RequestParam Integer attrTypeId) {
        if (tsKvAttributeService.getCountByNameAndUnitId(name, PhmUtil.toShortUUID(unitId)) > 0) {
            return "This name already exist!";
        }
        UomEntity input = null;
        UomEntity output = null;
        String uomOutputSymbol = "";
        if (inputUomId != 0) {
            input = uomService.getById(inputUomId);
            output = uomService.getById(outputUomId);
            uomOutputSymbol = output.getSymbol();
        }
        tsKvAttributeService.save(new TsKvAttribute(PhmUtil.toShortUUID(unitId), attrKey,
                tsKvAttributeTypeService.getById(attrTypeId), name, deviceId, input, output, dataType, isTable,
                uomOutputSymbol));
        return "Success!";
    }

    @GetMapping(value = "getAll/{unitId}", produces = "application/json")
    public String getAllForUnit(@PathVariable String unitId) {
        return tsKvAttributeService.getAllForUnit(UUIDConverter.fromTimeUUID(UUID.fromString(unitId)));
    }

    @GetMapping(value = "getAllForDataSchema/{unitId}", produces = "application/json")
    public String getAllToView(@PathVariable String unitId) {
        return tsKvAttributeService.getAllForDataSchema(UUIDConverter.fromTimeUUID(UUID.fromString(unitId)));
    }

    @GetMapping(value = "getAllForUI/{unitId}", produces = "application/json")
    public String getAllForUI(@PathVariable String unitId) {
        return tsKvAttributeService.getAllForUnitForUI(UUIDConverter.fromTimeUUID(UUID.fromString(unitId)));
    }

    @GetMapping(value = "getById/{attributeId}", produces = "application/json")
    public String getById(@PathVariable Long attributeId) {
        return tsKvAttributeService.getByIdForUI(attributeId);
    }

    @GetMapping(value = "getAllDeviceId/{unitId}")
    public List<String> getAllDeviceIdByUnitId(@PathVariable String unitId) {
        return tsKvAttributeService.getAllDeviceIdByUnit(PhmUtil.toShortUUID(unitId));
    }

    @PatchMapping(value = "updateKey")
            public void updateAttributeKey(@RequestParam Long attributeId, @RequestParam String newAttributeKey) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        tsKvAttribute.setAttributeKey(newAttributeKey);
        tsKvAttributeService.save(tsKvAttribute);
    }

    @PatchMapping(value = "updateType")
    public void updateAttributeType(@RequestParam Long attributeId, @RequestParam Integer newAttributeTypeId) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        tsKvAttribute.setTsKvAttributeType(tsKvAttributeTypeService.getById(newAttributeTypeId));
        tsKvAttributeService.save(tsKvAttribute);
    }

    @PatchMapping(value = "updateName")
    public String updateAttributeName(@RequestParam Long attributeId, @RequestParam String name) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        if (tsKvAttributeService.getCountByNameAndUnitId(name, tsKvAttribute.getUnitId()) > 0) {
            return "This name already exist!";
        }
        tsKvAttribute.setName(name);
        tsKvAttributeService.save(tsKvAttribute);
        return "Success!";
    }
    @PatchMapping(value = "updateDeviceId")
    public void updateAttributeDeviceId(@RequestParam Long attributeId, @RequestParam String newDeviceId) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        tsKvAttribute.setDeviceId(newDeviceId);
        tsKvAttributeService.save(tsKvAttribute);
    }

    @PatchMapping(value = "updateInputUom")
    public void updateAttributeInputUom(@RequestParam Long attributeId, @RequestParam Integer inputUomId) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        if (inputUomId == 0) {
            tsKvAttribute.setUomInput(null);
        } else {
            tsKvAttribute.setUomInput(uomService.getById(inputUomId));
        }
        tsKvAttributeService.save(tsKvAttribute);
    }

    @PatchMapping(value = "updateOutputUom")
    public void updateAttributeOutputUom(@RequestParam Long attributeId, @RequestParam(required = false) Integer outputUomId) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        if (outputUomId == 0) {
            tsKvAttribute.setUomOutput(null);
            tsKvAttribute.setOutputSymbol("");
        } else {
            UomEntity uomEntity = uomService.getById(outputUomId);
            tsKvAttribute.setUomOutput(uomEntity);
            tsKvAttribute.setOutputSymbol(uomEntity.getSymbol());
        }
        tsKvAttributeService.save(tsKvAttribute);
    }

    @PatchMapping(value = "updateDataType")
    public void updateAttributeDataType(@RequestParam Long attributeId, @RequestParam(required = false) TsKvAttrDataTypes dataType) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        tsKvAttribute.setDataType(dataType);
        tsKvAttributeService.save(tsKvAttribute);
    }

    @PatchMapping(value = "updateIsTable")
    public void updateAttributeIsTable(@RequestParam Long attributeId, @RequestParam Boolean newIsTable) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        tsKvAttribute.setTable(newIsTable);
        tsKvAttributeService.save(tsKvAttribute);
    }

    @DeleteMapping(value = "delete/{attributeId}")
    public void delete(@PathVariable Long attributeId) {
        tsKvAttributeService.deleteByIdSQL(attributeId);
    }

    @PostMapping(value = "changeDeviceId")
    public void changeDeviceId(@RequestParam String oldDeviceId, @RequestParam String newDeviceId,
                               @RequestParam String unitId) {
        List<TsKvAttribute> tsKvAttributes = tsKvAttributeService.getAllByDeviceIdAndUnitId(oldDeviceId,
                PhmUtil.toShortUUID(unitId));
        for (TsKvAttribute tsKvAttribute : tsKvAttributes) {
            tsKvAttribute.setDeviceId(newDeviceId);
            tsKvAttributeService.save(tsKvAttribute);
        }
    }

    @GetMapping(value = "getDataAttributeTable/{attributeId}/{n}", produces = "application/json")
    public String getDataAttributeTable(@PathVariable Long attributeId, @PathVariable Long n) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        return tsKvAttributeService.getDataAttributeTable(tsKvAttribute, n);
    }

    @GetMapping(value = "getDataAttributeChart/{attributeId}/{n}", produces = "application/json")
    public String getDataAttributeChart(@PathVariable Long attributeId, @PathVariable Long n) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        return tsKvAttributeService.getDataAttributeChart(tsKvAttribute, n);
    }

    @GetMapping(value = "getAllNameAndKeyAndSymbolByUnitId/{unitId}", produces = "application/json")
    public String getAllNameAndKeyAndSymbolByUnitId(@PathVariable String unitId) {
        return tsKvAttributeService.getAllNameAndKeyAndSymbolByUnitId(PhmUtil.toShortUUID(unitId));
    }
}
