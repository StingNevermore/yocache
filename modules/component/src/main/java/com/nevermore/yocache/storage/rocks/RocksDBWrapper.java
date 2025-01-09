package com.nevermore.yocache.storage.rocks;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.TtlDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Lazy
@Component
public class RocksDBWrapper {

    @Autowired
    private RocksEngineProperties rocksEngineProperties;

    private RocksDB db;

    @PostConstruct
    private void init() {
        RocksDB.loadLibrary();
        System.out.println(rocksEngineProperties.getPath());
        try (final Options options = new Options()) {
            options.setCreateIfMissing(true);
            options.setTtl(3600);
            db = TtlDB.open(options, rocksEngineProperties.getPath(),
                    (int) rocksEngineProperties.getTtlSeconds(), false);
        } catch (RocksDBException e) {
            throw new RuntimeException(e);
        }
    }

    public String readValue(String key) {
        try {
            byte[] value = db.get(key.getBytes());
            return value != null ? new String(value) : null;
        } catch (RocksDBException e) {
            throw new RuntimeException("Error reading from RocksDB", e);
        }
    }

    public void writeValue(String key, String value) {
        try {
            db.put(key.getBytes(), value.getBytes());
        } catch (RocksDBException e) {
            throw new RuntimeException("Error writing to RocksDB", e);
        }
    }
}
