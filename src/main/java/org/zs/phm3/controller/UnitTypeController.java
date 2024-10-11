package org.zs.phm3.controller;

import org.zs.phm3.data.Validator;
import org.zs.phm3.models.unit.UnitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.unit.UnitTypeService;

import java.util.List;

@RestController
@RequestMapping("api/unit_type/")
public class UnitTypeController extends BaseController{
    @Autowired
    UnitTypeService unitTypeService;
    private static final String UNIT_TYPE_ID = "unitTypeId";

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
//    public ProjectEntity save(@RequestBody @Valid ProjectEntity savingProject) throws PhmException {
    public UnitType save(@RequestBody UnitType savingUnitType) throws PhmException {
        ActionType actionType = savingUnitType.getId()!=null ? ActionType.UNITTYPE_UPDATED : ActionType.UNITTYPE_ADDED;
        final String ACTION = savingUnitType.getId()==null ? "New UnitType " + savingUnitType.getName() + " Saving" :
                "UnitType " + savingUnitType.getId() + ", " + savingUnitType.getName() + " Updating";
//        try {

            UnitType savedUnitType = unitTypeService.save(savingUnitType);
            logAction(savingUnitType.getId().toString(), "", "USER", actionType, ActionStatus.SUCCESS,
                    ACTION, "");
            return savedUnitType;
//        } catch (Exception e) {
//            logAction("", "", "USER",
//                    actionType, ActionStatus.FAILURE, ACTION, e.toString());
//            throw handleException(e);
//        }
    }

    @DeleteMapping(value = "/{unitTypeId}")
    public void delete(@PathVariable(UNIT_TYPE_ID) Integer unitTypeId) throws PhmException {
        Validator.validateId(unitTypeId, "");
        checkIntParameter(UNIT_TYPE_ID,unitTypeId);
        final String ACTION = "UnitType " + unitTypeId + " Deleting";
//        try {
            unitTypeService.deleteById(unitTypeId);
            logAction(unitTypeId.toString(), "", "USER", ActionType.UNITTYPE_DELETED,
                    ActionStatus.SUCCESS, ACTION, "");
//        } catch (Exception e) {
//            logAction(unitTypeId.toString(), "", "USER", ActionType.UNITTYPE_DELETED,
//                    ActionStatus.FAILURE, ACTION, e.getMessage());
//            throw handleException(e);
//        }
    }

    @GetMapping(value = "/{unitTypeId}")
    public UnitType getById(@PathVariable(UNIT_TYPE_ID) Integer unitTypeId) throws PhmException {
        checkIntParameter(UNIT_TYPE_ID,unitTypeId);
        return unitTypeService.findById(unitTypeId);
    }

    @GetMapping(value = "/all")
    public List<UnitType> getAll() throws PhmException {
//        try {
            List<UnitType> unitTypesListAll = unitTypeService.getAll();
            System.out.println("size=" + unitTypesListAll.size());
            return unitTypesListAll;
//        } catch (Exception e) {
//            throw handleException(e);
//        }
    }
}
