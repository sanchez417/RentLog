package com.rentlog;

public class Admin extends User {
    private String username;
    private String password;

    public Admin(String userID, String fullName, String contactNumber, String username, String password) {
        super(userID, fullName, contactNumber);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRoleDetails() {
        return "Role: Landlord / Administrator | Username: " + username;
    }
}