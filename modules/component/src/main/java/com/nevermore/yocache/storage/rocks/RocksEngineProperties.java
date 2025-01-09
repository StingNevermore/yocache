package com.nevermore.yocache.storage.rocks;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("yocache.rocks")
public class RocksEngineProperties {
    private File path;
    private long ttlSeconds;
}
