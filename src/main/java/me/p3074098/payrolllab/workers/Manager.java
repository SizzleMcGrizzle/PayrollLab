package me.p3074098.payrolllab.workers;

import java.util.Map;

public class Manager extends HourlyWorker {
    
    public Manager(String firstName, String lastName, double wage, double hours) {
        super(firstName, lastName, wage, hours);
    }
    
    public Manager(Map<String, Object> map) {
        super(map);
    }
    
    @Override
    public String getColor() {
        return "255, 195, 99";
    }
    
    @Override
    public double getOvertimeMultiplier() {
        return 2;
    }
    
    @Override
    public String getJobTitle() {
        return "Manager";
    }
}
