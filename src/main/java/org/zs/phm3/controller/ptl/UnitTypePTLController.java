package org.zs.phm3.controller.ptl;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ptl.UnitTypePTLService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/unitTypePTL/")
public class UnitTypePTLController {

    @Autowired
    private UnitTypePTLService unitTypePTLService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public UnitTypePTL create(@RequestParam(required = false) Integer parentUnitTypeId, @RequestParam String name,
                              @RequestParam(required = false) Integer pictureId) throws PhmException {
        UnitTypePTL unitTypePTL;
        if (parentUnitTypeId != null) {
            UnitTypePTL unitTypeParent = unitTypePTLService.getById(parentUnitTypeId);
            unitTypePTL = new UnitTypePTL(name, pictureId, unitTypeParent,userService.getByLogin("Admin"), System.currentTimeMillis());
            unitTypePTLService.save(unitTypeParent);
        } else {
            unitTypePTL = new UnitTypePTL(name, pictureId, null, userService.getByLogin("Admin"), System.currentTimeMillis());
        }
        return unitTypePTLService.save(unitTypePTL);
    }

    @GetMapping(value = "getAll")
    public List<UnitTypePTL> getAll() {
        return unitTypePTLService.getAllMainEntity();
    }

    @GetMapping(value = "getAllIdAndName", produces = "application/json")
    public String getAllList() {
        return unitTypePTLService.getAllIdAndName();
    }

    @DeleteMapping(value = "deleteAll")
    public String deleteById(@RequestBody List<Integer> ids) {
        List<List<Object>> lists = unitTypePTLService.getAllIdAndDefaultType(ids);
        for (List<Object> list : lists) {
            if ((Boolean) list.get(1)) {
                return "Can not be deleted!";
            }
        }
        SQLHelper.deleteAll("unit_type_ptl", "id", ids);
        return "Success!";
    }

    @PatchMapping(value = "editById")
    public String editById(@RequestParam Integer id, @RequestParam(required = false) String name,
                           @RequestParam(required = false) Integer pictureId, @RequestParam(required = false) Integer parentId) throws PhmException {
        UnitTypePTL unitTypePTL = unitTypePTLService.getById(id);
        if (!unitTypePTL.getDefaultType()) {
            if (name != null) {
                unitTypePTL.setName(name);
            }
            if (pictureId != null) {
                unitTypePTL.setPictureId(pictureId);
            }
            if (parentId != null) {
                unitTypePTL.setUnitTypePTL(unitTypePTLService.getById(parentId));
            }
            unitTypePTL.setModifiedBy(userService.getByLogin("Admin"));
            unitTypePTLService.save(unitTypePTL);
            return "Success!";
        } else {
            return "This type cannot be edited!";
        }
    }

    @GetMapping(value = "getAllList", produces = "application/json")
    public String getAllListForUI() {
        return unitTypePTLService.getAllList();
    }
}
