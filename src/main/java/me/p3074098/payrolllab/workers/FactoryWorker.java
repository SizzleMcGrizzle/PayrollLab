package me.p3074098.payrolllab.workers;

import java.util.Map;

public class FactoryWorker extends Worker {
    
    private static final transient double wageMultiplier = 1.2;
    
    private double wagePerPart;
    private int quantity;
    
    public FactoryWorker(Map<String, Object> map) {
        super(map);
        
        this.wagePerPart = (double) map.get("wagePerPart");
        this.quantity = (int) map.get("quantity");
    }
    
    public FactoryWorker(String firstName, String lastName, double wagePerPart, int quantity) {
        super(firstName, lastName);
        
        this.wagePerPart = wagePerPart;
        this.quantity = quantity;
    }
    
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = super.serialize();
        
        map.put("wagePerPart", wagePerPart);
        map.put("quantity", quantity);
        
        return map;
    }
    
    @Override
    public double earnings() {
        double partsOver1000 = Math.max(0, quantity - 1000);
        double parts = Math.min(1000, quantity);
        
        return parts * wagePerPart + wageMultiplier * partsOver1000 * wagePerPart;
    }
    
    @Override
    public String getColor() {
        return "255, 99, 229";
    }
    
    @Override
    public String toString() {
        return "Factory Worker: " + super.toString();
    }
    
    @Override
    public String getJobTitle() {
        return "Factory Worker";
    }
}
