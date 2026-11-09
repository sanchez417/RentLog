package com.rentlog;

public class Rental {
    private double monthlyRent;
    private String rentalDueDate;
    private String leaseExpirationDate;

    public Rental(double monthlyRent, String rentalDueDate, String leaseExpirationDate) {
        this.monthlyRent = monthlyRent;
        this.rentalDueDate = rentalDueDate;
        this.leaseExpirationDate = leaseExpirationDate;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getRentalDueDate() {
        return rentalDueDate;
    }

    public void setRentalDueDate(String rentalDueDate) {
        this.rentalDueDate = rentalDueDate;
    }

    public String getLeaseExpirationDate() {
        return leaseExpirationDate;
    }

    public void setLeaseExpirationDate(String leaseExpirationDate) {
        this.leaseExpirationDate = leaseExpirationDate;
    }

    public String getRentalSummary() {
        return "Monthly Rent: ₱" + monthlyRent + " | Due Date: " + rentalDueDate + " | Lease Expiration: " + leaseExpirationDate;
    }
}