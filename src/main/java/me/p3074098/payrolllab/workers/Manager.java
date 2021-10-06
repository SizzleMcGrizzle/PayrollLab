package me.p3074098.payrolllab.workers;

public class Manager extends HourlyWorker {
    
    public Manager(String firstName, String lastName, double wage, double hours) {
        super(firstName, lastName, wage, hours);
    }
    
    @Override
    public String getColor() {
        return "140, 97, 232";
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
