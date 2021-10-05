package me.p3074098.payrolllab.workers;

public abstract class Worker {

    private String firstName;
    private String lastName;

    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public abstract double earnings();

    public String toString() {
        return firstName + " " + lastName;
    }
}
