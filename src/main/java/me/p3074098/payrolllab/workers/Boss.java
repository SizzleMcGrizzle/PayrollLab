package me.p3074098.payrolllab.workers;

public class Boss extends Worker {
    
    private double yearlySalary;
    
    public Boss(String firstName, String lastName, double yearlySalary) {
        super(firstName, lastName);
        
        this.yearlySalary = yearlySalary;
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
