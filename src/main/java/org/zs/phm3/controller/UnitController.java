package org.zs.phm3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.DtoIdNameDescription;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.dto.ptl.PtlData;
import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.service.unit.UnitPtlService;
import org.zs.phm3.service.unit.UnitService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class UnitController extends BaseController {
    @Autowired
    private UnitService unitService;

    @Autowired
    UnitPtlService unitPtlService;

    @PostMapping(value = "/unit")
    @ResponseStatus(HttpStatus.CREATED)
    public UnitDto save(@RequestBody UnitDtoInput unitForSaving) throws PhmException, NotFoundException, Exception {
        ActionType actionType = unitForSaving.isNew() ? ActionType.UNITMODEL_ADDED : ActionType.UNITMODEL_UPDATED;
        final String ACTION = unitForSaving.isNew() ? "New Unit " + unitForSaving.getName() + " Saving" :
                "New Unit " + unitForSaving.getId() + ", " + unitForSaving.getName() + " Updating";
        UnitDto unitSavedDto = unitService.save(unitForSaving);
        String unitIdStrShort = UUIDConverter.fromTimeUUID(UUID.fromString(unitSavedDto.getId()));
        logAction(unitIdStrShort, "", "USER", actionType, ActionStatus.SUCCESS,
                ACTION, "");
        String version = "controller=0.11";
        unitSavedDto.componentVersion = unitSavedDto.componentVersion + version;
        return unitSavedDto;
    }

    @GetMapping(value = "/unit/{unitId}")
    public UnitDto getById(@PathVariable("unitId") String unitId) throws PhmException, Exception {
        return unitService.findById(unitId);
    }

    @GetMapping(value = "/units")
    public List<UnitEntity> getAll() {
        return unitService.getAll();
    }

    @GetMapping(value = "/units/project/{projectId}")
    public List<UnitEntity> getAllByProject(@PathVariable("projectId") Integer projectId) throws PhmException, Exception {
        return unitService.getAllByProject(projectId);
    }

    @GetMapping(value = "/units/project_short/{projectId}")
    public List<DtoIdNameDescription> getAllByProjectShort(@PathVariable("projectId") Integer projectId) throws PhmException, Exception {
        return unitService.getAllByProjectShort(projectId);
    }

    @GetMapping(value = "/units/getChilds/{projectId}")
    public List<UnitEntity> getChilds(@PathVariable Integer projectId) {
        List<UnitEntity> unitEntities = unitService.getAllByProject(projectId);
        if (unitEntities.size() > 0) {
            return unitEntities.get(0).getChilds();
        }
        return new ArrayList<>();
    }

    @DeleteMapping(value = "/unit/{unitId}")
    public void delete(@PathVariable("unitId") String unitId) throws PhmException, Exception {
        String unitIdStrShort = UUIDConverter.fromTimeUUID(UUID.fromString(unitId));
        final String ACTION = "Unit " + unitId + "Deleting";

        unitService.deleteWithLinkedObjects(unitIdStrShort);
        logAction(unitIdStrShort, "", "USER", ActionType.PROJECT_DELETED,
                ActionStatus.SUCCESS, ACTION, "");

    }

    @GetMapping(value = "/units/getPtlData/{unitId}")
    public PtlData getPtlData(@PathVariable String unitId) {
        return unitPtlService.getPtlData(unitId);
    }

    @GetMapping(value = "/units/getPtlData/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    List<List<Object>> getAllUnitIdsAndNameAndParentId(Integer projectId){
        return unitService.getAllUnitIdsAndNameAndParentId(projectId);
    }
}
