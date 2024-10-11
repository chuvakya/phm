package org.zs.phm3.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@ComponentScan("org.zs.phm3")
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
/*                new ConcurrentMapCache("directory"),
                new ConcurrentMapCache("projects"),
                new ConcurrentMapCache("addresses")));*/
        new ConcurrentMapCache("projects"),
        new ConcurrentMapCache("phm")));
        return cacheManager;
    }
}
