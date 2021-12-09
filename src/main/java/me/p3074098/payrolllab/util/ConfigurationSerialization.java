package me.p3074098.payrolllab.util;

import me.p3074098.payrolllab.workers.Worker;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationSerialization {
    
    private static final String FOLDER_NAME = "PayrollLab";
    private static final Map<String, Class<? extends ConfigurationSerializable>> registered = new HashMap<>();
    
    public static void registerClass(Class<? extends ConfigurationSerializable> clazz) {
        registered.put(clazz.getName(), clazz);
    }
    
    private static Constructor<? extends ConfigurationSerializable> getConstructor(String className) {
        Class<? extends ConfigurationSerializable> clazz = registered.get(className);
        
        try {
            return clazz.getConstructor(Map.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static <T extends ConfigurationSerializable> T load(Constructor<T> constructor, Map<String, Object> map) {
        try {
            return constructor.newInstance(map);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void serialize(ConfigurationSerializable configurationSerializable) {
        File file = new File(getApplicationDirectory(), configurationSerializable.getFilePath());
        
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            
            FileWriter writer = new FileWriter(file);
            
            Map<String, Object> serializedMap = configurationSerializable.serialize();
            serializedMap.put("className", configurationSerializable.getClass().getName());
    
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);
            
            Yaml yaml = new Yaml(options);
            yaml.dump(serializedMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static <T extends ConfigurationSerializable> T deserialize(File file) {
        try {
            InputStream in = new FileInputStream(file);
    
            Yaml yaml = new Yaml();
    
            Map<String, Object> map = yaml.load(in);
    
            String className = (String) map.get("className");
    
            Constructor<T> constructor = (Constructor<T>) getConstructor(className);
    
            return load(constructor, map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static <T extends ConfigurationSerializable> List<T> deserializeDirectory(File directory) {
        List<T> list = new ArrayList<>();
        
        for (File file : directory.listFiles())
            list.add(deserialize(file));
        
        return list;
    }
    
    public static void serialize(List<? extends ConfigurationSerializable> list) {
        list.forEach(ConfigurationSerialization::serialize);
    }
    
    public static File getApplicationDirectory() {
        Path basePath;
        if(System.getProperty("os.name").startsWith("Windows")) {
            basePath = Paths.get(System.getenv("APPDATA")).resolve(FOLDER_NAME);
        } else if(System.getProperty("os.name").startsWith("Mac")) {
            basePath = Paths.get(System.getProperty("user.home")).resolve("Library").resolve("Application Support").resolve(FOLDER_NAME);
        } else {
            basePath = Paths.get(System.getProperty("user.home")).resolve(FOLDER_NAME);
        }
        
        return new File(basePath.toString());
    }
}
