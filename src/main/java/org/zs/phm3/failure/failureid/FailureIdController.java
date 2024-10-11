package org.zs.phm3.failure.failureid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/failure-id/")
public class FailureIdController {

    @Autowired
    FailureIdService failureIdService;

    @GetMapping
    public Iterable<FailureIdEntity> getAll(){
        return failureIdService.findAll();
    }

    @GetMapping("{id}")
    public FailureIdEntity getOne(@PathVariable Integer id){
        return failureIdService.findById(id);
    }

    @PostMapping
    public FailureIdEntity save(@RequestBody FailureIdEntity failureIdEntity){
        return failureIdService.save(failureIdEntity);
    }

    @PutMapping("{id}")
    public FailureIdEntity update(@PathVariable Integer id, @RequestBody FailureIdEntity failureIdEntityForSave){
        FailureIdEntity failureIdEntity = failureIdService.findById(id);
        BeanUtils.copyProperties(failureIdEntityForSave, failureIdEntity);
        return failureIdService.save(failureIdEntity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        failureIdService.delete(id);
    }
}
