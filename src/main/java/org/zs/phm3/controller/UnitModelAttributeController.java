package org.zs.phm3.controller;

import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitModelAttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.unit.UnitModelAttributeService;

import java.util.List;

//@RestController
@RequestMapping("api/unit_model_attribute/")
public class UnitModelAttributeController extends BaseController {

    @Autowired
    private UnitModelAttributeService unitModelAttributeService;

    @PostMapping(value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public UnitModelAttribute save(@RequestBody UnitModelAttribute unitModelAttributeForSaving) throws PhmException {
        final ActionType actionType;
        final String ACTION;
        if (unitModelAttributeService.isNew(unitModelAttributeForSaving.getId().getAttributeKey(),
                unitModelAttributeForSaving.getId().getUnitModelId())) {
            actionType = ActionType.UNITMODEL_ATTRIBUTE_ADDED;
            ACTION = "New UnitModelAttribute " + unitModelAttributeForSaving.getStrValue() + " saving";
        } else {
            actionType = ActionType.ATTRIBUTE_UPDATED;
            ACTION = "New UnitModelAttribute " + unitModelAttributeForSaving.getStrValue() + " updating";
        }
//        try {
            UnitModelAttribute unitModelAttributeSaved = unitModelAttributeService.save(unitModelAttributeForSaving);
            logAction(unitModelAttributeForSaving.getId().getUnitModelId()+"/"+
                            unitModelAttributeForSaving.getId().getAttributeKey(), "", "USER", actionType, ActionStatus.SUCCESS,
                    ACTION, "");
            return unitModelAttributeSaved;
//        } catch (Exception e) {
//            logAction("", "", "USER",
//                    actionType, ActionStatus.FAILURE, ACTION, e.toString());
//            System.out.println(e.getMessage());
//            throw handleException(e);
//        }
    }

//    @GetMapping(value = "/{projectId}")
    @GetMapping(value = "")
    public UnitModelAttribute getById(@RequestBody UnitModelAttributeKey unitModelAttributeKey) throws PhmException {
//        checkParameter(CUSTOMER_ID, strCustomerId);
//        Validator.validateId(projectId,"");
//        try {
            UnitModelAttribute unitModelAttributeReadedOpt = unitModelAttributeService.findById(unitModelAttributeKey);
            return unitModelAttributeReadedOpt;
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//        return unitModelAttributeService.findById(unitModelAttributeKey);
    }

    @GetMapping(value = "/{unitModelId}")
    public List<UnitModelAttribute> getAllByUnitModelId(@PathVariable("unitModelId") Integer unitModelId) throws PhmException {
        return unitModelAttributeService.getAllByUnitModelId(unitModelId);
    }

    @DeleteMapping(value = "")
    public void delete(@RequestBody UnitModelAttributeKey unitModelAttributeKey) throws PhmException {
        final String ACTION = "UnitModelAttribute " + unitModelAttributeKey.getAttributeKey() + " Deleting";
//        try {
            unitModelAttributeService.deleteById(unitModelAttributeKey);
            logAction(unitModelAttributeKey.toString(), "", "USER", ActionType.UNITMODEL_ATTRIBUTE_DELETED,
                    ActionStatus.SUCCESS, ACTION, "");

//        }catch (EmptyResultDataAccessException e) {
//            logAction(unitModelAttributeKey.toString(), "", "USER", ActionType.UNITMODEL_ATTRIBUTE_DELETED,
//                    ActionStatus.FAILURE, ACTION, e.getMessage());
//            throw handleException(e);
//        }
    }
}
