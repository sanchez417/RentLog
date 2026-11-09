package com.rentlog;

public class Tenant extends User implements Reportable {
    private String roomNumber;
    private String moveInDate;
    private String username;
    private String password;

    public Tenant(String tenantID, String fullName, String contactNumber, String roomNumber, String moveInDate, String username, String password) {
        super(tenantID, fullName, contactNumber); // Inheritance from User
        this.roomNumber = roomNumber;
        this.moveInDate = moveInDate;
        this.username = username;
        this.password = password;
    }

    public String getTenantID() { return userID; }
    public String getRoomNumber() { return roomNumber; }
    public String getMoveInDate() { return moveInDate; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void updateContact(String newContact) { this.contactNumber = newContact; }

    @Override
    public String getRoleDetails() { // Polymorphism (Overriding abstract method)
        return "Role: Tenant | ID: " + userID + " | Name: " + fullName;
    }

    @Override
    public String getReportSummary() { // Abstraction (Interface implementation)
        return "Tenant Report -> ID: " + userID + ", Name: " + fullName + ", Room: " + roomNumber + ", Move-In: " + moveInDate;
    }

    public String getTenantInfo() {
        return getRoleDetails() + " | Contact: " + contactNumber + " | Room: " + roomNumber + " | Move-In: " + moveInDate + " | Username: " + username;
    }
}