package com.rentlog;

public class Deposit {
    private String tenantID;
    private double securityDeposit;
    private double advancePayment;
    private String depositStatus; // "Held", "Applied", "Refunded"

    public Deposit(String tenantID, double securityDeposit, double advancePayment) {
        this.tenantID = tenantID;
        this.securityDeposit = securityDeposit;
        this.advancePayment = advancePayment;
        this.depositStatus = "Held";
    }

    public String getTenantID() { return tenantID; }
    public void setTenantID(String tenantID) { this.tenantID = tenantID; }

    public double getSecurityDeposit() { return securityDeposit; }
    public void setSecurityDeposit(double securityDeposit) { this.securityDeposit = securityDeposit; }

    public double getAdvancePayment() { return advancePayment; }
    public void setAdvancePayment(double advancePayment) { this.advancePayment = advancePayment; }

    public String getDepositStatus() { return depositStatus; }
    public void setDepositStatus(String depositStatus) { this.depositStatus = depositStatus; }

    public void applyDeposit() { this.depositStatus = "Applied"; }
    public void refundDeposit() { this.depositStatus = "Refunded"; }

    public String getDepositSummary() {
        return "Tenant ID: " + tenantID + 
               " | Security Deposit: ₱" + securityDeposit + 
               " | Advance Payment: ₱" + advancePayment + 
               " | Status: " + depositStatus;
    }
}