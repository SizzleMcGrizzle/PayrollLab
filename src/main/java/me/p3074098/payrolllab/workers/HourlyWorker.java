package me.p3074098.payrolllab.workers;

public class HourlyWorker extends Worker {
    
    private double wage;
    private double hours;
    
    public HourlyWorker(String firstName, String lastName, double wage, double hours) {
        super(firstName, lastName);
        
        this.wage = wage;
        this.hours = hours;
    }
    
    @Override
    public double earnings() {
        double overtime = Math.max(0, hours - 40);
        double normal = Math.min(40, hours);
        
        return normal * wage + getOvertimeMultiplier() * wage * overtime;
    }
    
    @Override
    public String getColor() {
        return "99, 216, 255";
    }
    
    public double getOvertimeMultiplier() {
        return 1.5;
    }
    
    @Override
    public String toString() {
        return "Hourly Worker: " + super.toString();
    }
    
    @Override
    public String getJobTitle() {
        return "Hourly Worker";
    }
}
