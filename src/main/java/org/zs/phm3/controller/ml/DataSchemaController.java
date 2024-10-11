package org.zs.phm3.controller.ml;

import org.zs.phm3.models.ml.DataSchema;
import org.zs.phm3.models.ml.DataSchemaRegistry;
import org.zs.phm3.models.ml.DataSchemaTsKvAttribute;
import org.zs.phm3.models.ptl.BIT;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.service.ml.DataSchemaRegistryService;
import org.zs.phm3.service.ml.DataSchemaService;
import org.zs.phm3.service.ml.DataSchemaTsKvAttributeService;
import org.zs.phm3.service.ptl.BITService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.mapping.PhmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dataSchema/")
public class DataSchemaController {

    @Autowired
    private DataSchemaService dataSchemaService;

    @Autowired
    private BITService bitService;

    @Autowired
    private DataSchemaRegistryService dataSchemaRegistryService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    @Autowired
    private UserService userService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private DataSchemaTsKvAttributeService dataSchemaTsKvAttributeService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody List<Long> attributeIds, @RequestParam String unitId,
                         @RequestParam String name, @RequestParam(required = false) Long bitAttributeId,
                         @RequestParam(required = false) Long bitId) {
        UnitEntity unitEntity = unitService.getUnitById(PhmUtil.toShortUUID(unitId));
        if (dataSchemaService.getAllByNameAndProjectId(name, unitEntity.getProject().getId()).size() > 0) {
            return "This name already exist!";
        }
        TsKvAttribute tsKvAttribute = null;
        if (bitAttributeId != null) {
            tsKvAttribute = tsKvAttributeService.getById(bitAttributeId);
        }
        BIT bitErrorCode = null;
        if (bitId != null) {
            bitErrorCode = bitService.getById(bitId);
        }
        DataSchema dataSchema = new DataSchema(name, PhmUtil.toShortUUID(unitId), unitEntity.getProject().getId(),
                userService.getByLogin("Admin"), System.currentTimeMillis(), tsKvAttribute, bitErrorCode);
        dataSchemaService.save(dataSchema);
        dataSchemaRegistryService.save(new DataSchemaRegistry(name, dataSchema.getProjectId()));
        for (Long attributeId : attributeIds) {
            dataSchemaTsKvAttributeService.save(new DataSchemaTsKvAttribute(dataSchema,
                    tsKvAttributeService.getById(attributeId)));
        }
        return "Success";
    }

    @DeleteMapping(value = "delete/{dataSchemaId}")
    public void delete(@PathVariable Long dataSchemaId) {
        dataSchemaService.deleteByIdSQL(dataSchemaId);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> dataSchemaIds) {
        SQLHelper.deleteAll("data_schema", "id", dataSchemaIds);
    }

    @GetMapping(value = "getAllByProjectId/{projectId}")
    public List<DataSchema> getAll(@PathVariable Integer projectId) {
        return dataSchemaService.getAllDataSchemasByProject(projectId);
    }

    @GetMapping(value = "getAllByUnitId/{unitId}", produces = "application/json")
    public String getAllByUnitId(@PathVariable String unitId) {
        return dataSchemaService.getAllIdAndNameByUnitId(PhmUtil.toShortUUID(unitId));
    }

    @GetMapping(value = "getDataSchemaPreview/{dataSchemaId}", produces = "application/json")
    public String getDataSchemaPreview(@PathVariable Long dataSchemaId) {
        return dataSchemaService.getDataSchemaPreview(dataSchemaId);
    }

    @GetMapping(value = "getDatasetPreview/{dataSchemaId}/{n}", produces = "application/json")
    public String getDatasetPreview(@PathVariable Long dataSchemaId, @PathVariable Long n) {
        return dataSchemaService.getDatasetPreview(dataSchemaId, n);
    }

    @GetMapping(value = "getFullPeriodForDataSchemaAttribute/{attributeId}", produces = "application/json")
    public String getFullPeriodForDataSchema(@PathVariable Long attributeId) {
        return dataSchemaService.getFullPeriodForDataSchema(attributeId);
    }

    @PostMapping(value = "getMaxPeriodForDataSchema/{dataSchemaId}", produces = "application/json")
    public String getMaxPeriodForDataSchema(@PathVariable Long dataSchemaId) {
        return dataSchemaService.getMaxPeriodForDataSchema(dataSchemaService.getById(dataSchemaId));
    }

    @GetMapping(value = "getCount/{projectId}")
    public Integer getCountByProjectId(@PathVariable Integer projectId) {
        return dataSchemaService.getCountByProjectId(projectId);
    }

    @GetMapping(value = "getByOffsetAndLimit/{offset}/{limit}/{projectId}", produces = "application/json")
    public String getByOffsetAndLimitAndProjectId(@PathVariable Integer offset, @PathVariable Integer limit,
                                                  @PathVariable Integer projectId) {
        return dataSchemaService.getByOffsetAndLimitAndProjectId(offset, limit, projectId);
    }

    @GetMapping(value = "getSaveDataSchema/{dataSchemaId}", produces = "application/json")
    public String getSaveDataSchema(@PathVariable Long dataSchemaId) {
        return dataSchemaService.getSaveDataSchemaById(dataSchemaId);
    }

    @GetMapping(value = "getNameForNewDataSchema/{projectId}")
    public String getNameForNewDataSchema(@PathVariable Integer projectId) {
        return dataSchemaService.getNameForNewDataSchema(projectId);
    }

    @PatchMapping(value = "update")
    public String update(@RequestParam Long dataSchemaId, @RequestBody List<Long> attributeIds, @RequestParam String unitId,
                       @RequestParam String name, @RequestParam(required = false) Long bitAttributeId,
                       @RequestParam(required = false) Long bitId) {
        String shortUnitId = PhmUtil.toShortUUID(unitId);
        DataSchema dataSchema = dataSchemaService.getById(dataSchemaId);
        UnitEntity unitEntity = unitService.getUnitById(shortUnitId);
        if (!dataSchema.getName().equals(name) &&
                dataSchemaService.getAllByNameAndProjectId(name, unitEntity.getProject().getId()).size() > 0) {
            return "This name already exist!";
        }

        dataSchemaTsKvAttributeService.deleteByDataSchemaIdSQL(dataSchemaId);
        dataSchema.setName(name);
        dataSchema.setUnitId(shortUnitId);
        dataSchema.setModifiedBy(userService.getByLogin("Admin"));
        dataSchema.setProjectId(unitEntity.getProject().getId());
        if (bitAttributeId != null) {
            dataSchema.setBitAttribute(tsKvAttributeService.getById(bitAttributeId));
            dataSchema.setBitErrorCode(bitService.getById(bitId));
        } else {
            dataSchema.setBitAttribute(null);
            dataSchema.setBitErrorCode(null);
        }

        for (Long attributeId : attributeIds) {
            dataSchemaTsKvAttributeService.save(new DataSchemaTsKvAttribute(dataSchema,
                    tsKvAttributeService.getById(attributeId)));
        }
        dataSchemaService.save(dataSchema);
        return "Success!";
    }

}
