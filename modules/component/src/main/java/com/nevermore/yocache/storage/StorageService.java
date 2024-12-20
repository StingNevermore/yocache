package com.nevermore.yocache.storage;

/**
 * @author nevermore
 * @since 0.0.1
 */
public interface StorageService {

    byte[] getObject(String path);

    void saveObject(String path, byte[] data);

}
