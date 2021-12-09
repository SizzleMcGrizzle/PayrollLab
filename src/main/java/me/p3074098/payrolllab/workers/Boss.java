package me.p3074098.payrolllab.workers;

import java.util.Map;

public class Boss extends Worker {
    
    private double yearlySalary;
    
    public Boss(Map<String, Object> map) {
        super(map);
        
        this.yearlySalary = (double) map.get("yearlySalary");
    }
    
    public Boss(String firstName, String lastName, double yearlySalary) {
        super(firstName, lastName);
        
        this.yearlySalary = yearlySalary;
    }
    
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = super.serialize();
        
        map.put("yearlySalary", yearlySalary);
        
        return map;
    }
    
    @Override
    public double earnings() {
        return (7 * yearlySalary) / 365;
    }
    
    @Override
    public String toString() {
        return "Boss: " + super.toString();
    }
    
    @Override
    public String getColor() {
        return "255, 99, 99";
    }
    
    @Override
    public String getJobTitle() {
        return "The Boss";
    }
}
