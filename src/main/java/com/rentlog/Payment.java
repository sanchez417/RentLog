package com.rentlog;

public class Payment {
    private String paymentID;
    private String tenantID;
    private double amountPaid;
    private String paymentMethod;
    private double remainingBalance;
    private String paymentStatus; // "Paid", "Partial", "Unpaid"
    private String paymentDate;

    public Payment(String paymentID, String tenantID, double amountPaid, String paymentMethod, double totalDue, String paymentDate) {
        this.paymentID = paymentID;
        this.tenantID = tenantID;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        calculateBalance(totalDue);
    }

    public void calculateBalance(double totalDue) {
        this.remainingBalance = totalDue - amountPaid;
        if (remainingBalance <= 0) {
            this.remainingBalance = 0;
            this.paymentStatus = "Paid";
        } else if (amountPaid > 0) {
            this.paymentStatus = "Partial";
        } else {
            this.paymentStatus = "Unpaid";
        }
    }

    public String getPaymentID() { return paymentID; }
    public void setPaymentID(String paymentID) { this.paymentID = paymentID; }

    public String getTenantID() { return tenantID; }
    public void setTenantID(String tenantID) { this.tenantID = tenantID; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getRemainingBalance() { return remainingBalance; }
    public void setRemainingBalance(double remainingBalance) { this.remainingBalance = remainingBalance; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentReceipt() {
        return "Payment ID: " + paymentID + " | Tenant ID: " + tenantID + 
               " | Paid: ₱" + amountPaid + " | Method: " + paymentMethod + 
               " | Balance Due: ₱" + remainingBalance + " | Status: " + paymentStatus + 
               " | Date: " + paymentDate;
    }
}