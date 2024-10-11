package org.zs.phm3.controller.maintenance.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.maintenance.part.PartCategory;
import org.zs.phm3.service.maintenance.part.PartCategoryService;

import javax.ws.rs.Path;

@RestController
@RequestMapping(value = "/api/maintenance/part_category/")
public class PartCategoryController {

    @Autowired
    private PartCategoryService partCategoryService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestParam Integer projectId, @RequestParam String name) {
        if (partCategoryService.getCountByName(name) > 0) {
            return "This category already exist!";
        }
        return partCategoryService.save(new PartCategory(name, projectId));
    }

    @GetMapping(value = "getAllByProjectId/{projectId}", produces = "application/json")
    public String getAllByProjectId(@PathVariable Integer projectId) {
        return partCategoryService.getAllByProjectId(projectId);
    }

    @DeleteMapping(value = "deleteById/{categoryId}")
    public void deleteById(@PathVariable Integer categoryId) {
        partCategoryService.deleteByIdSQL(categoryId);
    }

    @PatchMapping(value = "updateById/{categoryId}")
    public void updateById(@PathVariable Integer categoryId, @RequestParam String name) {
        PartCategory partCategory = partCategoryService.getById(categoryId);
        partCategory.setName(name);
        partCategoryService.save(partCategory);
    }


}
