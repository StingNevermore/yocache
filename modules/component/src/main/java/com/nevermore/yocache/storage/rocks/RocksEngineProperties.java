package com.nevermore.yocache.storage.rocks;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("yocache.rocks")
public class RocksEngineProperties {
    private String path;
    private long ttlSeconds;
}
