package com.nevermore.yocache.test.storage.rocks;

import com.nevermore.yocache.storage.rocks.RocksDBWrapper;
import com.nevermore.yocache.storage.rocks.RocksEngineProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author nevermore
 * @since 0.0.1
 */
@SpringBootTest(classes = {RocksDBWrapper.class, RocksEngineProperties.class})
public class RocksDBWrapperTests {

    @Autowired
    private RocksDBWrapper rocksDBWrapper;

    @Test
    public void testInit() {
        System.out.println(rocksDBWrapper);
    }
}
