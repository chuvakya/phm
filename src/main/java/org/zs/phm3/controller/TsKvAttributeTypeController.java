package org.zs.phm3.controller;

import org.zs.phm3.models.ts.TsKvAttributeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.ts.TsKvAttributeTypeService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tsKvAttrType/")
public class TsKvAttributeTypeController {

    @Autowired
    private TsKvAttributeTypeService tsKvAttributeTypeService;

    @PostMapping(value = "create", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestParam String categoryName, @RequestParam Integer projectId) throws PhmException {
        TsKvAttributeType category = tsKvAttributeTypeService.saveIfNotExist(
                new TsKvAttributeType(projectId, categoryName));
        if (category == null) {
            return "{\"error\": \"This category already exists!\"}";
        } else {
            return category;
        }
    }

    @GetMapping(value = "getAll/{projectId}")
    public List<TsKvAttributeType> getCategories(@PathVariable Integer projectId) {
        return tsKvAttributeTypeService.getAllByProjectId(projectId);
    }

    @GetMapping(value = "getAllUsersTypes/{projectId}")
    public List<TsKvAttributeType> getAllUsersTypes(@PathVariable Integer projectId) {
        return tsKvAttributeTypeService.getAllUsersTypesByProjectId(projectId);
    }

    @PatchMapping(value = "update")
    public Object update(@RequestParam Integer categoryId, @RequestParam String newName) {
        TsKvAttributeType tsKvAttributeType = tsKvAttributeTypeService.getById(categoryId);
        tsKvAttributeType.setName(newName);
        tsKvAttributeType = tsKvAttributeTypeService.saveIfNotExist(tsKvAttributeType);
        if (tsKvAttributeType == null) {
            return "This name already exist!";
        }
        return tsKvAttributeType;
    }

    @DeleteMapping(value = "delete/{categoryId}")
    public String delete(@PathVariable Integer categoryId) {
        try {
            tsKvAttributeTypeService.deleteById(categoryId);
        } catch (Exception ex) {
            return "It is impossible to delete a category while it has attributes!";
        }
        return "Success!";
    }


}
