package me.p3074098.payrolllab.workers;

import me.p3074098.payrolllab.util.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Worker implements ConfigurationSerializable {
    
    private UUID id;
    private String firstName;
    private String lastName;
    
    public Worker(Map<String, Object> map) {
        this.id = UUID.fromString((String) map.get("uuid"));
        this.firstName = (String) map.get("firstName");
        this.lastName = (String) map.get("lastName");
    }
    
    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = UUID.randomUUID();
    }
    
    public abstract double earnings();
    
    public abstract String getColor();
    
    public abstract String getJobTitle();
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public UUID getId() {
        return id;
    }
    
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("uuid", id.toString());
        
        return map;
    }
    
    @Override
    public String getFilePath() {
        return "workers/" + id.toString() + ".yml";
    }
}
