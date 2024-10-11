package org.zs.phm3.controller.ptl;

import org.zs.phm3.models.ptl.ModelAttrTypePTL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.ptl.ModelAttrTypePTLService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/modelAttrTypePTL/")
public class ModelAttrTypePTLController {

    @Autowired
    private ModelAttrTypePTLService modelAttrTypePTLService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAttrTypePTL create(@RequestParam String name) {
        ModelAttrTypePTL modelAttrTypePTL = new ModelAttrTypePTL(name);
        return modelAttrTypePTLService.save(modelAttrTypePTL);
    }

    @DeleteMapping(value = "deleteById/{modelAttrTypeId}")
    public void deleteById(@PathVariable Integer modelAttrTypeId) {
        modelAttrTypePTLService.deleteByIdSQL(modelAttrTypeId);
    }

    @PatchMapping(value = "updateName/{modelAttrTypeId}")
    public void editById(@RequestParam String newName, @PathVariable Integer modelAttrTypeId) {
        ModelAttrTypePTL modelAttrTypePTL = modelAttrTypePTLService.getById(modelAttrTypeId);
        modelAttrTypePTL.setName(newName);
        modelAttrTypePTLService.save(modelAttrTypePTL);
    }

    @GetMapping(value = "getAll")
    public List<ModelAttrTypePTL> getAll() {
        return modelAttrTypePTLService.getAll();
    }

}
