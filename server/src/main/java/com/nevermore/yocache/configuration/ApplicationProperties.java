package com.nevermore.yocache.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("yocache.app")
public class ApplicationProperties {
    private StorageService storageService = StorageService.LOCAL_CACHE;

    public enum StorageService {
        LOCAL_CACHE,
    }
}
