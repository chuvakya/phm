package org.zs.phm3.controller.maintenance.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.maintenance.supplier.Contact;
import org.zs.phm3.models.maintenance.supplier.Currency;
import org.zs.phm3.models.maintenance.supplier.Supplier;
import org.zs.phm3.service.maintenance.supplier.ContactService;
import org.zs.phm3.service.maintenance.supplier.SupplierService;
import org.zs.phm3.service.ptl.ManufacturerPTLService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.List;

@RestController
@RequestMapping(value = "/api/maintenance/supplier/")
public class SupplierController {

    @Autowired
    private ManufacturerPTLService manufacturerPTLService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private ContactService contactService;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier create(@RequestBody List<Contact> contacts, @RequestParam Boolean isManufacturerPTL,
                           @RequestParam String name, @RequestParam Boolean isManufacturer,
                           @RequestParam Boolean isSupplier, @RequestParam Boolean isTenant, @RequestParam Currency currency,
                           @RequestParam String notes, @RequestParam Integer projectId) {
        Supplier supplier = new Supplier(name, isManufacturer, isSupplier, isTenant, notes, currency,
                projectId, isManufacturerPTL);
        supplierService.save(supplier);
        contactService.saveAllContact(contacts, supplier.getId());
        return supplier;
    }

    @PatchMapping(value = "updateById")
    public void updateById(@RequestBody List<Contact> contacts, @RequestParam Boolean isManufacturerPTL,
                           @RequestParam String name, @RequestParam Boolean isManufacturer,
                           @RequestParam Boolean isSupplier, @RequestParam Boolean isTenant, @RequestParam Currency currency,
                           @RequestParam String notes, @RequestParam Long supplierId) {
        Supplier supplier = supplierService.getById(supplierId);
        supplier.setName(name);
        supplier.setManufacturerPTL(isManufacturerPTL);
        supplier.setManufacturer(isManufacturer);
        supplier.setTenant(isTenant);
        supplier.setSupplier(isSupplier);
        supplier.setNotes(notes);
        supplier.setCurrency(currency);
        contactService.deleteAllBySupplierId(supplierId);
        contactService.saveAllContact(contacts, supplierId);
        supplierService.save(supplier);
    }

    @DeleteMapping(value = "deleteAllByIds")
    public void deleteAllByIds(@RequestBody List<Long> supplierIds) {
        SQLHelper.deleteAll("supplier", "id", supplierIds);
    }

    @GetMapping(value = "getById/{supplierId}", produces = "application/json")
    public String getById(@PathVariable Long supplierId) {
        return supplierService.getByIdJSON(supplierId);
    }

    @GetMapping(value = "getList/{projectId}", produces = "application/json")
    public String getListByProjectId(@PathVariable Integer projectId) {
        return supplierService.getListForTableByProjectId(projectId);
    }

    @GetMapping(value = "getList/{projectId}/{offset}/{limit}", produces = "application/json")
    public String getListByProjectId(@PathVariable Integer projectId, @PathVariable Integer offset,
                                     @PathVariable Integer limit) {
        return supplierService.getListForTableByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }

    @GetMapping(value = "getCount/{projectId}")
    public Long getCountByProjectId(@PathVariable Integer projectId) {
        return supplierService.getCountByProjectId(projectId);
    }
}
