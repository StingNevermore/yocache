package com.nevermore.yocache.server.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("yocache.server")
public class YocacheServerProperties {
    private int port;
}
