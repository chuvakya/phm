package org.zs.phm3.controller.ptl;

import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.service.maintenance.supplier.SupplierService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ptl.ManufacturerPTLService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/manufacturer_ptl/")
public class ManufacturerPTLController {

    @Autowired
    private ManufacturerPTLService manufacturerPTLService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private SupplierService supplierService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create")
    public Object create(@RequestParam String name) {
        if (manufacturerPTLService.getCountByName(name) > 0) {
            return "This name already exist!";
        }
        return manufacturerPTLService.save(new ManufacturerPTL(name, userService.getByLogin("Admin"),
                System.currentTimeMillis()));
    }

    @GetMapping(value = "getAll")
    public List<ManufacturerPTL> getAll() {
        return manufacturerPTLService.getAll();
    }

    @Transactional
    @DeleteMapping(value = "deleteAll")
    public void deleteById(@RequestBody List<Long> manufacturerIds) {
        supplierService.updateIsManufacturerPTLByName(false,
                manufacturerPTLService.getAllNamesByManufacturerIds(manufacturerIds));
        SQLHelper.deleteAll("manufacturer_ptl", "id", manufacturerIds);
    }

    @PatchMapping(value = "edit")
    public void editById(@RequestParam Long manufacturerId, @RequestParam String name) {
        ManufacturerPTL manufacturerPTL = manufacturerPTLService.getById(manufacturerId);
        supplierService.updateName(name, manufacturerPTL.getName());
        manufacturerPTL.setName(name);
        manufacturerPTL.setModifiedBy(userService.getByLogin("Admin"));
        manufacturerPTLService.save(manufacturerPTL);

    }

    @GetMapping(value = "getAllByOffsetAndLimit/{offset}/{limit}", produces = "application/json")
    public String getAllByOffsetAndLimit(@PathVariable Integer offset, @PathVariable Integer limit) {
        return manufacturerPTLService.getAllByOffsetAndLimit(offset, limit);
    }

    @GetMapping(value = "getCount")
    public Long getCount() {
        return manufacturerPTLService.getCount();
    }
}
