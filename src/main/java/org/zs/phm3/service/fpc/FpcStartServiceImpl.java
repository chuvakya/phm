/**
 * S.Zeniuk
 */
package org.zs.phm3.service.fpc;

//import lombok.extern.slf4j.Slf4j;

import org.zs.phm3.config.FpcServiceProperties;
import org.zs.phm3.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//import org.thingsboard.server.common.data.UUIDConverter;
//import org.thingsboard.server.common.data.exception.ThingsboardException;
//import org.thingsboard.server.common.data.id.TenantId;
//import org.thingsboard.server.controller.BaseController;

@Service
//@Slf4j
public class FpcStartServiceImpl extends BaseController implements fpcStartService {
    @Autowired
    FpcService fpcService;
    @Autowired
    FpcServiceProperties fpcServiceProperties;

    private static final Logger logger = LoggerFactory.getLogger(FpcStartServiceImpl.class);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture fpcResultFuture = null;
//    private TenantId tenantId;

    @PostConstruct
    private void init() {//throws ThingsboardException {
//        fpcService.process();
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(new FpcStartServiceImpl(), 0, 5, TimeUnit.SECONDS);
//        final TenantId tenantId = null;//getTenantId();

//        tenantId = (tenantService.findTenantById(new TenantId(UUIDConverter.fromString("1e9f3ce116b06708a242d8b2c9b5073")))).getTenantId();

//        fpcResultFuture = scheduler.scheduleAtFixedRate(fpcStartRunnable, 0, 10, TimeUnit.SECONDS);
        if (this.fpcServiceProperties.getPeriod()!=0) {
            fpcResultFuture = scheduler.scheduleAtFixedRate(fpcStartRunnable, this.fpcServiceProperties.getDelay(),
                    this.fpcServiceProperties.getPeriod(), TimeUnit.SECONDS);
            logger.warn("FPC is started");
        }else{
            logger.warn("FPC service is disabled by settings");
        }
    }

    @PreDestroy
    private void destroy() {
        try {
            if (fpcResultFuture != null) {
                fpcResultFuture.cancel(true);
            }
            scheduler.shutdownNow();
        } catch (Exception e) {
            //Do nothing
        }
    }

    private final Runnable fpcStartRunnable = () -> {

        int day = 0;
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    day = 0;
                    break;
                case 1:
                    day = 7;
                    break;
                case 2:
                    day = 30;
                    break;
            }
            logger.trace("Starting FPC process " + day + " day at: " + new Date());//+" tenantId="+tenantId);
            logger.warn("Starting FPC process " + day + " day at: " + new Date());//+" tenantId="+tenantId);
            try {
//                fpcService.process(tenantId, day);
                System.out.println("Starting FPC process");
                logger.warn("Starting FPC process");
            } catch (Exception e) {
//                System.out.println("Starting FPC process error");
                logger.trace("Starting FPC process error: " + e.getMessage());
                logger.warn("Starting FPC process error: " + e.getMessage());
//                log.trace(e.getMessage());
                e.printStackTrace();
            }
        }

    };

    @Override
    public void getFpcData() {
    }
}
