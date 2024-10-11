package org.zs.phm3.failure;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/failure/")
@RestController
public class FailureController {

    @Autowired
    FailureService failureService;

    @GetMapping
    public Iterable<FailureEntity> getAll(){
        return failureService.findAll();
    }

    @GetMapping("{id}")
    public FailureEntity getOne(@PathVariable Long id){
        return failureService.findById(id);
    }

    @GetMapping("unit/{unitId}")
    public Iterable<FailureEntity> getFailuresByUnitId(@PathVariable String unitId){
        return failureService.findByUnitId(unitId);
    }

    @GetMapping("unrelated")
    public Iterable<FailureEntity> getAllUnrelatedFailures(){
        return failureService.findAllUnrelatedFailures();
    }

    @PostMapping
    public FailureEntity save(@RequestBody FailureEntity failureEntity){
        return failureService.save(failureEntity);
    }

    @PutMapping("{id}")
    public FailureEntity update(@PathVariable Long id, @RequestBody FailureEntity failureEntityForSave){
        FailureEntity failureEntity = failureService.findById(id);
        BeanUtils.copyProperties(failureEntityForSave, failureEntity);
        return failureService.save(failureEntity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        failureService.delete(id);
    }

    @DeleteMapping("unit/{unitId}")
    public void deleteByUnitId(@PathVariable String unitId){
        failureService.deleteAllByUnitId(unitId);
    }


}
