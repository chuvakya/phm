package org.zs.phm3;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.FileType;
import org.zs.phm3.models.init.Init;
import org.zs.phm3.repository.init.InitRepository;
import org.zs.phm3.service.FileService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
@EnableCaching
@EnableScheduling

@EnableFeignClients("org.zs.phm3.service.rpm")
@EnableDiscoveryClient
public class Application extends SpringBootServletInitializer implements ApplicationRunner {

    @Autowired
    private InitRepository initRepository;

    @Autowired
    private FileService fileService;

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        Init init = initRepository.getInit();
        if (init == null) {
            runInitSQL();
            initRepository.save(new Init(true));
        } else if (!init.getInitStartApp()) {
            runInitSQL();
            init.setInitStartApp(true);
            initRepository.save(init);
        }
    }

    private void runInitSQL() {
        try {
            /* инициализация иконок UnitTypePTL */
            List<String> iconNames = Arrays.asList("Function2 16", "System of system 16", "System 16",
                    "Subsystem 16", "Equipment group 2 16", "Equipment2 16", "Module 16", "Board2 16",
                    "Component 16", "Microcircuit 16", "Diode 16", "Transistor 16", "Resistor16",
                    "Capacitor 16", "Potentiometer 16", "Optoelectronic devices 2 16",
                    "Vacuum electronic devices (tubes) 16", "Connector 16", "Inductive elements 16", "Relay 16",
                    "Magnetic devices 16", "Resonator and oscillator 16", "Filter 16", "Battery 16", "Laser 16",
                    "Optical fiber connector 16", "PCB, solder joints and SMT 16", "Switch 16", "Rotating appliances 16",
                    "Light 16", "Gyroscope 16", "Other components 16", "Mechanical components 2 16", "Software 16");

            for (String iconName : iconNames) {
                InputStream inputStream = getClass().getResourceAsStream("/icons_unit_type_library/" + iconName + ".png");
                FileEntity fileEntity = new FileEntity(inputStream.readAllBytes(), iconName, null, FileType.IMAGE);
                fileService.save(fileEntity);
                inputStream.close();
            }

            /* Инициализация всего остального */
            InputStream inputStream = getClass().getResourceAsStream("/sql/init.sql");
            String sql = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            entityManager.createNativeQuery(sql).executeUpdate();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}