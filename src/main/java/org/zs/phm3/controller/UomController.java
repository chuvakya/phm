package org.zs.phm3.controller;

import org.zs.phm3.models.UomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.UomService;
import java.util.List;

@RestController
@RequestMapping("api/uom/")
public class UomController {

    @Autowired
    private UomService uomService;

    @GetMapping(value = "getAll")
    public List<UomEntity> getAll() {
        return uomService.getAll();
    }

    @GetMapping(value = "getById/{id}")
    public UomEntity getById(@PathVariable Integer id) {
        return uomService.getById(id);
    }


}

