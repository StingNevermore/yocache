package com.nevermore.yocache.storage.impl;

import com.nevermore.yocache.storage.StorageService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nevermore
 * @since 0.0.1
 */
public class LocalCacheStorageService implements StorageService {

    private final Map<String, byte[]> storage = new ConcurrentHashMap<>();

    @Override
    public byte[] getObject(String path) {
        return storage.get(path);
    }

    @Override
    public void saveObject(String path, byte[] data) {
        storage.put(path, data);
    }
}
