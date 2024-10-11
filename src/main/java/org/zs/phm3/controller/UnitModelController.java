package org.zs.phm3.controller;

import org.zs.phm3.data.Validator;
import org.zs.phm3.models.unit.UnitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.unit.UnitModelService;
import org.zs.phm3.service.unit.UnitTypeService;

//import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/unit_model/")

public class UnitModelController extends BaseController {
    @Autowired
    private UnitModelService unitModelService;

    @Autowired
    private UnitTypeService unitTypeService;

    @PostMapping(value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public UnitModel save(@RequestParam Integer typeId, @RequestBody UnitModel unitModelForSaving) throws PhmException {
        ActionType actionType = unitModelForSaving.isNew() ? ActionType.UNITMODEL_ADDED : ActionType.UNITMODEL_UPDATED;
        final String ACTION = unitModelForSaving.isNew() ? "New UnitModel " + unitModelForSaving.getModel() + " Saving" :
                "New UnitModel " + unitModelForSaving.getId() + ", " + unitModelForSaving.getModel() + " Updating";
//        try {
            unitModelForSaving.setType(unitTypeService.findById(typeId));
            UnitModel unitModelSaved = unitModelService.save(unitModelForSaving);
            logAction(unitModelSaved.getId().toString(), "", "USER", actionType, ActionStatus.SUCCESS,
                    ACTION, "");
            return unitModelSaved;
//        } catch (Exception e) {
//            logAction("", "", "USER",
//                    actionType, ActionStatus.FAILURE, ACTION, e.toString());
//            throw handleException(e);
//        }
    }


    @GetMapping(value = "/{unitModelId}")
    public UnitModel getById(@PathVariable("unitModelId") Integer unitModelId) throws PhmException {
        checkIntParameter("unitModelId", unitModelId);
        Validator.validateId(unitModelId,"");
//        try {
            UnitModel UnitModelReadedOpt = unitModelService.findById(unitModelId);
            return UnitModelReadedOpt;
//        } catch (Exception e) {
//            throw handleException(e);
//        }
    }

    @GetMapping(value = "/all")
    public List<UnitModel> getAll() throws PhmException {
//        try {
            List<UnitModel> unitModelsListAll = unitModelService.getAll();
            System.out.println("size=" + unitModelsListAll.size());
            return unitModelsListAll;
//        } catch (Exception e) {
//            throw handleException(e);
//        }
    }

    @GetMapping(value = "/all/{unitTypeId}")
    public List<UnitModel> getAllByUnitType(@PathVariable Integer unitTypeId) {
        return unitModelService.getAllByTypeId(unitTypeId);
    }

    @DeleteMapping(value = "/{unitModelId}")
    public void delete(@PathVariable("unitModelId") Integer unitModelId) throws PhmException {
        Validator.validateId(unitModelId,"");
                checkIntParameter("unitModelId", unitModelId);
        final String ACTION = "UnitModel " + unitModelId + " Deleting";
//        try {
            unitModelService.deleteById(unitModelId);
            logAction(unitModelId.toString(), "", "USER", ActionType.UNITMODEL_DELETED,
                    ActionStatus.SUCCESS, ACTION, "");
//        } catch (Exception e) {
//            logAction(unitModelId.toString(), "", "USER", ActionType.UNITMODEL_DELETED,
//                    ActionStatus.FAILURE, ACTION, e.getMessage());
//            throw handleException(e);
//        }
    }
}
