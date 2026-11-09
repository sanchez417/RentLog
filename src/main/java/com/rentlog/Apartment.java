package com.rentlog;

public class Apartment {
    private String roomNumber;
    private double monthlyRentalPrice;
    private String roomStatus; // "Occupied" or "Vacant"

    public Apartment(String roomNumber, double monthlyRentalPrice) {
        this.roomNumber = roomNumber;
        this.monthlyRentalPrice = monthlyRentalPrice;
        this.roomStatus = "Vacant";
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getMonthlyRentalPrice() {
        return monthlyRentalPrice;
    }

    public void setMonthlyRentalPrice(double monthlyRentalPrice) {
        this.monthlyRentalPrice = monthlyRentalPrice;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void markOccupied() {
        this.roomStatus = "Occupied";
    }

    public void markVacant() {
        this.roomStatus = "Vacant";
    }

    public String getRoomDetails() {
        return "Room Number: " + roomNumber + 
               " | Monthly Rent: ₱" + monthlyRentalPrice + 
               " | Status: " + roomStatus;
    }
}