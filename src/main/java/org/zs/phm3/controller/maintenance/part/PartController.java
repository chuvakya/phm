package org.zs.phm3.controller.maintenance.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.maintenance.part.Part;
import org.zs.phm3.models.maintenance.part.PartStock;
import org.zs.phm3.service.maintenance.part.PartCategoryService;
import org.zs.phm3.service.maintenance.part.PartService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/maintenance/part/")
public class PartController {

    @Autowired
    private PartService partService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private PartCategoryService partCategoryService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public Part create(@RequestBody List<PartStock> partStocks, @RequestParam String name,
                       @RequestParam Integer categoryId, @RequestParam Integer projectId) {
        Part part = new Part(name, projectId, partCategoryService.getById(categoryId));
        Map map = partService.getTotalStockAndStorages(partStocks);
        part.setStorage((String) map.get("storages"));
        part.setTotalStock((Long) map.get("totalStock"));
        partService.save(part);
        partService.addPartStocksForPart(partStocks, part.getId());
        return part;
    }

    @PatchMapping(value = "updateById/{partId}")
    public void updateById(@PathVariable Long partId, @RequestBody List<PartStock> partStocks,
                           @RequestParam String name, @RequestParam Integer categoryId) {
        Part part = partService.getById(partId);
        part.setName(name);
        part.setPartCategory(partCategoryService.getById(categoryId));
        SQLHelper.deleteAll("part_stock", "part_id", Arrays.asList(partId));
        partService.addPartStocksForPart(partStocks, part.getId());
        Map map = partService.getTotalStockAndStorages(partStocks);
        part.setStorage((String) map.get("storages"));
        part.setTotalStock((Long) map.get("totalStock"));
        partService.save(part);
    }

    @GetMapping(value = "getList/{projectId}", produces = "application/json")
    public String getListByProjectId(@PathVariable Integer projectId) {
        return partService.getListByProjectId(projectId);
    }

    @GetMapping(value = "getList/{projectId}/{offset}/{limit}", produces = "application/json")
    public String getListByProjectId(@PathVariable Integer projectId, @PathVariable Integer offset,
                                     @PathVariable Integer limit) {
        return partService.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }

    @GetMapping(value = "getById/{partId}", produces = "application/json")
    public String getById(@PathVariable Long partId) {
        return partService.getByIdJSON(partId);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> partIds) {
        SQLHelper.deleteAll("part", "id", partIds);
    }

    @GetMapping(value = "getCount/{projectId}")
    public Long getCountByProjectId(@PathVariable Integer projectId) {
        return partService.getCountByProjectId(projectId);
    }

    @GetMapping(value = "getListForWorkOrderByProjectIdAndOffsetAndLimit/{projectId}/{offset}/{limit}",
            produces = "application/json")
    public String getListForWorkOrderByProjectIdAndOffsetAndLimit(@PathVariable Integer projectId,
                                                                  @PathVariable Integer offset,
                                                                  @PathVariable Integer limit) {
        return partService.getListForWorkOrderByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }
}
