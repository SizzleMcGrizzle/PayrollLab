package me.p3074098.payrolllab.util;

import java.io.File;
import java.util.Map;

public interface ConfigurationSerializable {
    
    Map<String, Object> serialize();
    
    String getFilePath();
    
    default boolean deleteFile() {
        return new File(ConfigurationSerialization.getApplicationDirectory(), getFilePath()).delete();
    }
    
    default void serializeToFile() {
        ConfigurationSerialization.serialize(this);
    }
    
}
