package org.zs.phm3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.zs.phm3.install",
//        "org.zs.phm3.models",
        "org.zs.phm3.repository",
        "org.zs.phm3.util*",
        "org.zs.phm3.service",
        "org.zs.phm3.config"
})

public class InstallApplication {
    private static final Logger logger = LoggerFactory.getLogger(InstallApplication.class);
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(InstallApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.setAdditionalProfiles("install");
        ConfigurableApplicationContext context = application.run();//(updateArguments(args));
        try {
            context.getBean(PhmInstallService.class).performInstall();
            System.exit(1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
    }
}