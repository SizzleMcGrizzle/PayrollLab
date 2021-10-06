package me.p3074098.payrolllab.workers;

public abstract class Worker {
    
    private String firstName;
    private String lastName;
    
    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
