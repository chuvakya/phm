package org.zs.phm3;

import org.zs.phm3.config.MlServiceProperties;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest


public class YamlTest {
    @Autowired
    MlServiceProperties mlServiceProperties;

    @Test
    public void n1_read1()  {

//        mlServiceProperties.
        var a=1;
}
}
