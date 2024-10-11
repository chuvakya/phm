package org.zs.phm3;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.config.MlServiceProperties;
import org.zs.phm3.service.rule.RuleService;

import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest


public class MaintenanceRuleDurdomTest {
    @Autowired
    RuleService ruleService;

    @Test
    public void n1_getJSONForExplorer()  {
//        List<Object[]> result=
                ruleService.getJSONForExplorer(1);
        var a=1;
}
}
