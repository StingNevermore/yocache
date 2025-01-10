package com.nevermore.yocache.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.nevermore.yocache.storage.impl.LocalCacheStorageService;

/**
 * @author nevermore
 * @since 0.0.1
 */
public class LocalStorageTests {

    private final LocalCacheStorageService localCacheStorageService;
    private final Map<String, byte[]> storage;

    @SuppressWarnings("unchecked")
    public LocalStorageTests() {
        this.localCacheStorageService = new LocalCacheStorageService();
        storage = mock(Map.class);
        ReflectionTestUtils.setField(localCacheStorageService, "storage", storage);
    }

    @Test
    public void testGet() {
        when(storage.get(anyString())).thenReturn(new byte[]{0});
        byte[] data = localCacheStorageService.getObject("/test");
        assertArrayEquals(new byte[]{0}, data);
    }

    @Test
    public void testPut() {
        localCacheStorageService.saveObject("/test", "test".getBytes());
    }
}
