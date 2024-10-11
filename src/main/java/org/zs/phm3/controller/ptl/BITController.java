package org.zs.phm3.controller.ptl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.ptl.BIT;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.service.ptl.BITService;
import org.zs.phm3.service.ptl.ModelPTLService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController(value = "/api/bitPTL/")
public class BITController {

    @Autowired
    private BITService bitService;

    @Autowired
    private ModelPTLService modelPTLService;

    @Autowired
    private UserService userService;

    @Autowired
    private SQLHelper SQLHelper;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public BIT create(@RequestParam String name, @RequestParam String errorCode,
                      @RequestParam String description, @RequestParam String cause, @RequestParam String troubleshooting,
                      @RequestParam String severity, @RequestParam String priority, @RequestParam Long modelId) {
        ModelPTL modelPTL = modelPTLService.getById(modelId);
        BIT bit = new BIT(name, modelPTL, errorCode, description, cause, troubleshooting, severity, priority,
                userService.getByLogin("Admin"), System.currentTimeMillis());
        return bitService.save(bit);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteById(@RequestBody List<Long> bitIds) {
        SQLHelper.deleteAll("bit_ptl", "id", bitIds);
    }

    @GetMapping(value = "getCount")
    public Long getCount() {
        return bitService.getCount();
    }

    @GetMapping(value = "getById/{bitId}")
    public BIT getById(@PathVariable Long bitId) {
        return bitService.getById(bitId);
    }

    @GetMapping(value = "getSaveBITById/{bitId}", produces = "application/json")
    public String getSaveBITById(@PathVariable Long bitId) {
        return bitService.getSaveBITById(bitId);
    }

    @GetMapping(value = "getAllByOffsetAndLimit/{offset}/{limit}", produces = "application/json")
    public String getAllByOffsetAndLimit(@PathVariable Integer offset, @PathVariable Integer limit) {
        return bitService.getAllByOffsetAndLimit(offset, limit);
    }

    @PatchMapping(value = "updateById")
    public BIT updateById(@RequestParam Long bitId, @RequestParam String name, @RequestParam String errorCode,
                          @RequestParam String description, @RequestParam String cause, @RequestParam String troubleshooting,
                          @RequestParam String severity, @RequestParam String priority, @RequestParam Long modelId) {
        ModelPTL modelPTL = modelPTLService.getById(modelId);
        BIT bit = bitService.getById(bitId);
        bit.setName(name);
        bit.setErrorCode(errorCode);
        bit.setDescription(description);
        bit.setCause(cause);
        bit.setTroubleshooting(troubleshooting);
        bit.setSeverity(severity);
        bit.setPriority(priority);
        bit.setModelPTL(modelPTL);
        bit.setModifiedBy(userService.getByLogin("Admin"));
        bit.setUpdateTime(System.currentTimeMillis());
        return bitService.save(bit);
    }

    @GetMapping(value = "getPriorities")
    public List<Map<String, String>> getPriorities() {
        List<String> priorities = Arrays.asList("CRITICAL", "HIGH", "MEDIUM", "LOW");
        return bitService.getMapFromList(priorities);
    }

    @GetMapping(value = "getSeverities")
    public List<Map<String, String>> getSeverities() {
        List<String> severities = Arrays.asList("CRITICAL", "HIGH", "MEDIUM", "LOW");
        return bitService.getMapFromList(severities);
    }

    @GetMapping(value = "getBITCodesByTsKvAttributeId/{tsKvAttributeId}", produces = "application/json")
    public String getBITCodesByTsKvAttributeId(@PathVariable Long tsKvAttributeId) {
        return bitService.getBITCodesByTsKvAttributeId(tsKvAttributeId);
    }

}
