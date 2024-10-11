package org.zs.phm3.ftamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/fta/model/"})
public class FtaModelController {
    @Autowired
    FtaModelService ftaModelService;
    @Autowired
    FtaDiagramService ftaDiagramService;

    public FtaModelController() {
    }

    @GetMapping("{id}")
    public String getFtaModel(@PathVariable("id") Long projectId){
        return ftaModelService.findByProjectId(projectId).getBody();
    }

    @GetMapping("is-exist{id}")
    public boolean isExistFtaModel(@PathVariable("id") Long projectId){
        boolean result = false;
        FtaModelEntity ftaModelEntity = ftaModelService.findByProjectId(projectId);
        if(ftaModelEntity != null){
            result = true;
        }
        return result;
    }

}

