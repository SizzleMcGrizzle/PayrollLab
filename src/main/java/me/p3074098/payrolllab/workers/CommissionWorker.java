package me.p3074098.payrolllab.workers;

import java.util.Map;

public class CommissionWorker extends Worker {
    
    private double baseSalary;
    private double perItemCommission;
    private int quantity;
    
    public CommissionWorker(Map<String, Object> map) {
        super(map);
        
        this.baseSalary = (double) map.get("baseSalary");
        this.perItemCommission = (double) map.get("perItemCommission");
        this.quantity = (int) map.get("quantity");
    }
    
    public CommissionWorker(String firstName, String lastName, double baseSalary, double perItemCommission, int quantity) {
        super(firstName, lastName);
        
        this.baseSalary = baseSalary;
        this.perItemCommission = perItemCommission;
        this.quantity = quantity;
    }
    
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = super.serialize();
        
        map.put("baseSalary", baseSalary);
        map.put("perItemCommission", perItemCommission);
        map.put("quantity", quantity);
        
        return map;
    }
    
    @Override
    public double earnings() {
        return baseSalary + (perItemCommission * quantity);
    }
    
    @Override
    public String getColor() {
        return "99, 255, 115";
    }
    
    @Override
    public String toString() {
        return "Commission Worker: " + super.toString();
    }
    
    @Override
    public String getJobTitle() {
        return "Commission Worker";
    }
}
