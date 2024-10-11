package org.zs.phm3.controller.ml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.service.ml.MlServiceResultService;

@RestController
@RequestMapping(value = "/api/serice-result/")
public class MlServiceResultController {

    @Autowired
    private MlServiceResultService mlServiceResultService;

}
