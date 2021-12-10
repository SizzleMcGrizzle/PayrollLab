package me.p3074098.payrolllab.util;

import java.io.File;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ConfigurationSerializable {
    
    Map<String, Object> serialize();
    
    String getFilePath();
    
    default void deleteFile() {
       CompletableFuture.runAsync(() -> new File(ConfigurationSerialization.getApplicationDirectory(), getFilePath()).delete());
    }
    
    default void serializeToFile() {
        CompletableFuture.runAsync(() -> ConfigurationSerialization.serialize(this));
    }
    
}
