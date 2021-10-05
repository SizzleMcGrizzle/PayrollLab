package me.p3074098.payrolllab.workers;

public class CommissionWorker extends Worker {

    private double baseSalary;
    private double perItemCommission;
    private int quantity;

    public CommissionWorker(String firstName, String lastName, double baseSalary, double perItemCommission, int quantity) {
        super(firstName,lastName);

        this.baseSalary = baseSalary;
        this.perItemCommission = perItemCommission;
        this.quantity = quantity;
    }

    @Override
    public double earnings() {
        return baseSalary + (perItemCommission*quantity);
    }

    @Override
    public String toString() {
        return "Commission Worker: " + super.toString();
    }
}
