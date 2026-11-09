package com.rentlog;

public abstract class User {
    protected String userID;
    protected String fullName;
    protected String contactNumber;

    public User(String userID, String fullName, String contactNumber) {
        this.userID = userID;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Abstract method to be overridden by subclasses (Polymorphism)
    public abstract String getRoleDetails();
}