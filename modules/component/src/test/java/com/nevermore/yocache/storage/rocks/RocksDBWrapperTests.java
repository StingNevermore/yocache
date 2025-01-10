package com.nevermore.yocache.storage.rocks;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class RocksDBWrapperTests {

    @Test
    public void testInit() {
        RocksEngineProperties properties = new RocksEngineProperties();
        properties.setPath("/tmp/yocache/rocks");
        properties.setTtlSeconds(3600);
        RocksDBWrapper rocksDBWrapper = new RocksDBWrapper();
        ReflectionTestUtils.setField(rocksDBWrapper, "rocksEngineProperties", properties);
        ReflectionTestUtils.invokeMethod(rocksDBWrapper, "init");
    }
}
