package me.p3074098.payrolllab.workers;

public class FactoryWorker extends Worker {
    
    private static final double wageMultiplier = 1.5;
    
    private double wagePerPart;
    private int quantity;
    
    public FactoryWorker(String firstName, String lastName, double wagePerPart, int quantity) {
        super(firstName, lastName);
        
        this.wagePerPart = wagePerPart;
        this.quantity = quantity;
    }
    
    @Override
    public double earnings() {
        double partsOver1000 = Math.max(0, quantity - 1000);
        double parts = Math.max(1000, quantity);
        
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
