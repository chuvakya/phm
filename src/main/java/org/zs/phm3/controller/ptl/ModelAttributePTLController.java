package org.zs.phm3.controller.ptl;

import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.ptl.ModelAttrTypePTL;
import org.zs.phm3.models.ptl.ModelAttributePTL;
import org.zs.phm3.models.ptl.ModelPTL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ptl.ModelAttrTypePTLService;
import org.zs.phm3.service.ptl.ModelAttributePTLService;
import org.zs.phm3.service.ptl.ModelPTLService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/modelAttributePTL/")
public class ModelAttributePTLController {

    @Autowired
    private ModelAttributePTLService modelAttributePTLService;

    @Autowired
    private UomService uomService;

    @Autowired
    private ModelPTLService modelPTLService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelAttrTypePTLService modelAttrTypePTLService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAttributePTL save(@RequestParam String name, @RequestParam(required = false) Integer uomId,
                                  @RequestParam Integer modelAttrTypeId, @RequestParam String dataType,
                                  @RequestParam String value, @RequestParam Long modelId) {
        ModelPTL modelPTL = modelPTLService.getById(modelId);
        UomEntity uomEntity = null;
        ModelAttrTypePTL modelAttrTypePTL = modelAttrTypePTLService.getById(modelAttrTypeId);
        ModelAttributePTL modelAttributePTL = null;
        if (uomId == null) {
            modelAttributePTL = new ModelAttributePTL(name, modelPTL, modelAttrTypePTL, dataType, value, null,
                    null, System.currentTimeMillis(), userService.getByLogin("Admin"));
        } else {
            uomEntity = uomService.getById(uomId);
            modelAttributePTL = new ModelAttributePTL(name, modelPTL, modelAttrTypePTL, dataType, value,
                    uomEntity.getSymbol(), uomEntity.getId(), System.currentTimeMillis(), userService.getByLogin("Admin"));
        }
        return modelAttributePTLService.save(modelAttributePTL);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteById(@RequestBody List<Long> modelAttributeIds) {
        SQLHelper.deleteAll("model_attribute_ptl", "id", modelAttributeIds);
    }

    @GetMapping(value = "getAllByModelId/{modelId}", produces = "application/json")
    public String getAllByModelId(@PathVariable Long modelId) {
        return modelAttributePTLService.getAllByModelId(modelId);
    }

    @GetMapping(value = "getById/{modelAttributeId}")
    public ModelAttributePTL getByModelAttributeId(@PathVariable Long modelAttributeId) {
        return modelAttributePTLService.getById(modelAttributeId);
    }

    @PatchMapping(value = "updateName/{modelAttributeId}")
    public void updateName(@RequestParam String newName, @PathVariable Long modelAttributeId) {
        ModelAttributePTL modelAttributePTL = modelAttributePTLService.getById(modelAttributeId);
        modelAttributePTL.setName(newName);
        modelAttributePTL.setModifiedBy(userService.getByLogin("Admin"));
        modelAttributePTL.setModifiedTime(System.currentTimeMillis());
        modelAttributePTLService.save(modelAttributePTL);
    }

    @PatchMapping(value = "updateUom/{modelAttributeId}")
    public void updateUom(@RequestParam Integer newUomId, @PathVariable Long modelAttributeId) {
        ModelAttributePTL modelAttributePTL = modelAttributePTLService.getById(modelAttributeId);
        if (newUomId == 0) {
            modelAttributePTL.setUomId(null);
            modelAttributePTL.setUomSymbol(null);
        } else {
            UomEntity uomEntity = uomService.getById(newUomId);
            modelAttributePTL.setUomId(uomEntity.getId());
            modelAttributePTL.setUomSymbol(uomEntity.getSymbol());
        }
        modelAttributePTL.setModifiedBy(userService.getByLogin("Admin"));
        modelAttributePTL.setModifiedTime(System.currentTimeMillis());
        modelAttributePTLService.save(modelAttributePTL);
    }

    @PatchMapping(value = "updateModelAttributeType/{modelAttributeId}")
    public void updateModelAttributeType(@RequestParam Integer newModelAttributeTypeId, @PathVariable Long modelAttributeId) {
        ModelAttributePTL modelAttributePTL = modelAttributePTLService.getById(modelAttributeId);
        modelAttributePTL.setModelAttrTypePTL(modelAttrTypePTLService.getById(newModelAttributeTypeId));
        modelAttributePTL.setModifiedBy(userService.getByLogin("Admin"));
        modelAttributePTL.setModifiedTime(System.currentTimeMillis());
        modelAttributePTLService.save(modelAttributePTL);
    }

    @PatchMapping(value = "updateDataType/{modelAttributeId}")
    public void updateDataType(@RequestParam String newDataType, @PathVariable Long modelAttributeId) {
        ModelAttributePTL modelAttributePTL = modelAttributePTLService.getById(modelAttributeId);
        modelAttributePTL.setDataType(newDataType);
        modelAttributePTL.setModifiedBy(userService.getByLogin("Admin"));
        modelAttributePTL.setModifiedTime(System.currentTimeMillis());
        modelAttributePTLService.save(modelAttributePTL);
    }

    @PatchMapping(value = "updateValue/{modelAttributeId}")
    public void updateValue(@RequestParam String newValue, @PathVariable Long modelAttributeId) {
        ModelAttributePTL modelAttributePTL = modelAttributePTLService.getById(modelAttributeId);
        modelAttributePTL.setValue(newValue);
        modelAttributePTL.setModifiedBy(userService.getByLogin("Admin"));
        modelAttributePTL.setModifiedTime(System.currentTimeMillis());
        modelAttributePTLService.save(modelAttributePTL);
    }

    @PatchMapping(value = "updateModel/{modelAttributeId}")
    public void updateName(@RequestParam Long newModelId, @PathVariable Long modelAttributeId) {
        ModelAttributePTL modelAttributePTL = modelAttributePTLService.getById(modelAttributeId);
        modelAttributePTL.setModelPTL(modelPTLService.getById(newModelId));
        modelAttributePTL.setModifiedBy(userService.getByLogin("Admin"));
        modelAttributePTL.setModifiedTime(System.currentTimeMillis());
        modelAttributePTLService.save(modelAttributePTL);
    }
}
