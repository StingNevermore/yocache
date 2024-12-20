package com.nevermore.yocache.configuration;

import com.nevermore.yocache.storage.StorageService;
import com.nevermore.yocache.storage.impl.LocalCacheStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Configuration
public class ApplicationConfiguration {

    @Autowired
    private ApplicationProperties appProperties;

    @Bean
    public StorageService storageService() {
        ApplicationProperties.StorageService serviceType = appProperties.getStorageService();
        if (serviceType == ApplicationProperties.StorageService.LOCAL_CACHE) {
            return new LocalCacheStorageService();
        }
        return new LocalCacheStorageService();
    }
}
