package me.p3074098.payrolllab.workers;

public class HourlyWorker extends Worker {

    private static final double overtimeMultiplier = 1.5;

    private double wage;
    private double hours;

    public HourlyWorker(String firstName, String lastName, double wage, double hours) {
        super(firstName,lastName);

        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public double earnings() {
        double overtime = Math.max(0,hours-40);
        double normal = Math.max(40,hours);

        return normal*wage + overtimeMultiplier*wage*overtime;
    }

    @Override
    public String toString() {
        return "Hourly Worker: " + super.toString();
    }
}
