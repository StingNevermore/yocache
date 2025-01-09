package com.nevermore.yocache.storage.rocks;

import jakarta.annotation.PostConstruct;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.TtlDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
    public void init() {
        RocksDB.loadLibrary();
        try (final Options options = new Options()) {
            options.setCreateIfMissing(true);
            options.setTtl(3600);
            db = TtlDB.open(options, rocksEngineProperties.getPath().getAbsolutePath(),
                    (int) rocksEngineProperties.getTtlSeconds(), false);
        } catch (RocksDBException e) {
            throw new RuntimeException(e);
        }
    }
}
