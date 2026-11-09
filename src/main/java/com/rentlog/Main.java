package com.rentlog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Tenant> tenants = new ArrayList<>();
    private static List<Apartment> apartments = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static List<Deposit> deposits = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        
        // Default Admin account (Username: admin, Password: admin123)
        if (admins.isEmpty()) {
            admins.add(new Admin("A001", "Property Manager", "09123456789", "admin", "admin123"));
            saveAdminData();
        }

        // Default Apartment Rooms
        if (apartments.isEmpty()) {
            apartments.add(new Apartment("101", 5000.0));
            apartments.add(new Apartment("102", 5500.0));
            apartments.add(new Apartment("201", 6000.0));
            saveData();
        }

        // Default Tenant (Username: tenant1, Password: password123)
        if (tenants.isEmpty()) {
            tenants.add(new Tenant("T001", "Juan Dela Cruz", "09123456789", "101", "2026-11-08", "tenant1", "password123"));
            for (Apartment apt : apartments) {
                if (apt.getRoomNumber().equals("101")) {
                    apt.markOccupied();
                    break;
                }
            }
            deposits.add(new Deposit("T001", 5000.0, 5000.0));
            saveData();
        }

        int portalChoice = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("       WELCOME TO RENTLOG SYSTEM        ");
            System.out.println("========================================");
            System.out.println("Select Portal:");
            System.out.println("1. Landlord / Admin Portal");
            System.out.println("2. Tenant Portal (View Mode)");
            System.out.println("3. Exit System");
            System.out.print("Enter choice (1-3): ");

            try {
                portalChoice = Integer.parseInt(scanner.nextLine());
                switch (portalChoice) {
                    case 1: runAdminPortal(); break;
                    case 2: runTenantPortal(); break;
                    case 3: 
                        saveData();
                        System.out.println("Exiting RENTLOG. Goodbye!"); 
                        break;
                    default: 
                        System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric input.");
            }
        } while (portalChoice != 3);
    }

    private static void runAdminPortal() {
        boolean isAuthenticated = false;
        int attempts = 3;

        while (attempts > 0 && !isAuthenticated) {
            System.out.println("\n--- LANDLORD / ADMIN LOGIN ---");
            System.out.print("Enter Username (default: admin): ");
            String inputUser = scanner.nextLine();
            System.out.print("Enter Password (default: admin123): ");
            String inputPass = scanner.nextLine();

            for (Admin admin : admins) {
                if (admin.getUsername().equals(inputUser) && admin.getPassword().equals(inputPass)) {
                    isAuthenticated = true;
                    System.out.println("\nLogin Successful! Welcome back, " + admin.getFullName() + ".");
                    break;
                }
            }

            if (!isAuthenticated) {
                attempts--;
                System.out.println("Invalid credentials! Attempts remaining: " + attempts);
            }
        }

        if (!isAuthenticated) {
            System.out.println("\nToo many failed attempts. Returning to main menu.");
            return;
        }

        int choice = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("       ADMIN: APARTMENT MANAGEMENT      ");
            System.out.println("========================================");
            System.out.println("1. Add Tenant Profile & Assign Room");
            System.out.println("2. View All Tenant Records");
            System.out.println("3. Update Tenant Information");
            System.out.println("4. Delete Tenant");
            System.out.println("5. Record Rental Payment");
            System.out.println("6. Track Security Deposits & Advances");
            System.out.println("7. View Payment History Log");
            System.out.println("8. Generate Monthly Financial & Occupancy Report");
            System.out.println("9. Logout / Back to Portal Selection");
            System.out.print("Enter your choice (1-9): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addTenant(); break;
                    case 2: viewTenants(); break;
                    case 3: updateTenant(); break;
                    case 4: deleteTenant(); break;
                    case 5: recordPayment(); break;
                    case 6: trackDeposits(); break;
                    case 7: viewPaymentHistory(); break;
                    case 8: generateMonthlyReport(); break;
                    case 9: 
                        saveData();
                        System.out.println("Logged out of Admin Portal."); 
                        break;
                    default: 
                        System.out.println("Invalid option! Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric input.");
            }
        } while (choice != 9);
    }

    private static void runTenantPortal() {
        Tenant loggedInTenant = null;
        int attempts = 3;

        while (attempts > 0 && loggedInTenant == null) {
            System.out.println("\n--- TENANT PORTAL LOGIN ---");
            System.out.print("Enter Tenant Username (default: tenant1): ");
            String inputUser = scanner.nextLine();
            System.out.print("Enter Password (default: password123): ");
            String inputPass = scanner.nextLine();

            for (Tenant t : tenants) {
                if (t.getUsername() != null && t.getUsername().equals(inputUser) && t.getPassword().equals(inputPass)) {
                    loggedInTenant = t;
                    System.out.println("\nLogin Successful! Welcome, " + t.getFullName() + ".");
                    break;
                }
            }

            if (loggedInTenant == null) {
                attempts--;
                System.out.println("Invalid credentials! Attempts remaining: " + attempts);
            }
        }

        if (loggedInTenant == null) {
            System.out.println("\nToo many failed attempts. Returning to main menu.");
            return;
        }

        int tenantChoice = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("       TENANT PORTAL (VIEW MODE)        ");
            System.out.println("========================================");
            System.out.println("1. View Personal Profile & Room Details");
            System.out.println("2. View My Payment History & Balances");
            System.out.println("3. Logout / Back to Portal Selection");
            System.out.print("Enter your choice (1-3): ");

            try {
                tenantChoice = Integer.parseInt(scanner.nextLine());
                switch (tenantChoice) {
                    case 1: viewTenantProfile(loggedInTenant); break;
                    case 2: viewTenantPayments(loggedInTenant); break;
                    case 3: System.out.println("Logged out of Tenant Portal."); break;
                    default: System.out.println("Invalid option! Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric input.");
            }
        } while (tenantChoice != 3);
    }

    private static void viewTenantProfile(Tenant t) {
        System.out.println("\n--- MY RENTAL INFORMATION ---");
        System.out.println("Tenant ID     : " + t.getTenantID());
        System.out.println("Full Name     : " + t.getFullName());
        System.out.println("Contact Number: " + t.getContactNumber());
        System.out.println("Room Number   : " + t.getRoomNumber());
        System.out.println("Move-In Date  : " + t.getMoveInDate());

        for (Apartment apt : apartments) {
            if (apt.getRoomNumber().equals(t.getRoomNumber())) {
                System.out.println("Monthly Rent  : ₱" + apt.getMonthlyRentalPrice());
                System.out.println("Room Status   : " + apt.getRoomStatus());
                break;
            }
        }
    }

    private static void viewTenantPayments(Tenant t) {
        System.out.println("\n--- MY PAYMENT HISTORY & BALANCES ---");
        boolean hasRecords = false;
        for (Payment p : payments) {
            if (p.getTenantID().equals(t.getTenantID())) {
                System.out.println(p.getPaymentReceipt());
                hasRecords = true;
            }
        }
        if (!hasRecords) {
            System.out.println("No payment transactions recorded for your account yet.");
        }
    }

    private static void addTenant() {
        System.out.println("\n--- ADD NEW TENANT ---");
        System.out.print("Enter Tenant ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Room Number (e.g. 101, 102, 201): ");
        String roomNo = scanner.nextLine();

        Apartment targetRoom = null;
        for (Apartment apt : apartments) {
            if (apt.getRoomNumber().equals(roomNo)) {
                targetRoom = apt;
                break;
            }
        }

        if (targetRoom == null) {
            System.out.println("Error: Room " + roomNo + " does not exist.");
            return;
        }
        if (targetRoom.getRoomStatus().equalsIgnoreCase("Occupied")) {
            System.out.println("Error: Room " + roomNo + " is already occupied!");
            return;
        }

        System.out.print("Enter Move-In Date (YYYY-MM-DD): ");
        String moveInDate = scanner.nextLine();
        System.out.print("Create Tenant Portal Username: ");
        String uname = scanner.nextLine();
        System.out.print("Create Tenant Portal Password: ");
        String pass = scanner.nextLine();

        Tenant newTenant = new Tenant(id, name, contact, roomNo, moveInDate, uname, pass);
        tenants.add(newTenant);
        targetRoom.markOccupied();

        System.out.print("Enter Security Deposit Amount: ₱");
        double secDep = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Advance Payment Amount: ₱");
        double advPay = Double.parseDouble(scanner.nextLine());
        deposits.add(new Deposit(id, secDep, advPay));

        saveData();
        System.out.println("Success: Tenant added and Room " + roomNo + " marked as Occupied!");
    }

    private static void viewTenants() {
        System.out.println("\n--- ALL TENANT RECORDS ---");
        if (tenants.isEmpty()) {
            System.out.println("No tenants registered yet.");
            return;
        }
        for (Tenant t : tenants) {
            System.out.println(t.getTenantInfo());
        }
    }

    private static void updateTenant() {
        System.out.println("\n--- UPDATE TENANT INFORMATION ---");
        System.out.print("Enter Tenant ID to update: ");
        String id = scanner.nextLine();

        Tenant found = null;
        for (Tenant t : tenants) {
            if (t.getTenantID().equals(id)) {
                found = t;
                break;
            }
        }

        if (found == null) {
            System.out.println("Error: Tenant ID not found.");
            return;
        }

        System.out.print("Enter new contact number (" + found.getContactNumber() + "): ");
        String newContact = scanner.nextLine();
        found.updateContact(newContact);
        saveData();
        System.out.println("Success: Tenant contact updated!");
    }

    private static void deleteTenant() {
        System.out.println("\n--- DELETE TENANT (MOVE-OUT) ---");
        System.out.print("Enter Tenant ID to remove: ");
        String id = scanner.nextLine();

        Tenant targetTenant = null;
        for (Tenant t : tenants) {
            if (t.getTenantID().equals(id)) {
                targetTenant = t;
                break;
            }
        }

        if (targetTenant == null) {
            System.out.println("Error: Tenant ID not found.");
            return;
        }

        String roomNum = targetTenant.getRoomNumber();
        for (Apartment apt : apartments) {
            if (apt.getRoomNumber().equals(roomNum)) {
                apt.markVacant();
                break;
            }
        }

        tenants.remove(targetTenant);
        saveData();
        System.out.println("Success: Tenant checked out and Room " + roomNum + " marked as Vacant.");
    }

    private static void recordPayment() {
        System.out.println("\n--- RECORD RENTAL PAYMENT ---");
        System.out.print("Enter Tenant ID: ");
        String tenantId = scanner.nextLine();

        Tenant tenant = null;
        for (Tenant t : tenants) {
            if (t.getTenantID().equals(tenantId)) {
                tenant = t;
                break;
            }
        }

        if (tenant == null) {
            System.out.println("Error: Tenant not found.");
            return;
        }

        double monthlyRent = 0;
        for (Apartment apt : apartments) {
            if (apt.getRoomNumber().equals(tenant.getRoomNumber())) {
                monthlyRent = apt.getMonthlyRentalPrice();
                break;
            }
        }

        System.out.print("Enter Payment ID: ");
        String payId = scanner.nextLine();
        System.out.print("Enter Amount Paid (Monthly Rent is ₱" + monthlyRent + "): ₱");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Payment Method (Cash/Bank Transfer/GCash): ");
        String method = scanner.nextLine();
        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Payment payment = new Payment(payId, tenantId, amount, method, monthlyRent, date);
        payments.add(payment);
        saveData();
        System.out.println("Success: Payment recorded! Status: " + payment.getPaymentStatus() + " | Remaining Balance: ₱" + payment.getRemainingBalance());
    }

    private static void trackDeposits() {
        System.out.println("\n--- SECURITY DEPOSITS & ADVANCES ---");
        if (deposits.isEmpty()) {
            System.out.println("No deposit records found.");
            return;
        }
        for (Deposit d : deposits) {
            System.out.println(d.getDepositSummary());
        }
    }

    private static void viewPaymentHistory() {
        System.out.println("\n--- PAYMENT HISTORY LOG ---");
        if (payments.isEmpty()) {
            System.out.println("No payment transactions recorded.");
            return;
        }
        for (Payment p : payments) {
            System.out.println(p.getPaymentReceipt());
        }
    }

    private static void generateMonthlyReport() {
        System.out.println("\n========================================");
        System.out.println("      RENTLOG MONTHLY FINANCIAL REPORT  ");
        System.out.println("========================================");
        double totalCollected = 0;
        for (Payment p : payments) {
            totalCollected += p.getAmountPaid();
        }
        int occupiedRooms = 0;
        for (Apartment a : apartments) {
            if (a.getRoomStatus().equalsIgnoreCase("Occupied")) {
                occupiedRooms++;
            }
            System.out.println(a.getRoomDetails());
        }
        System.out.println("----------------------------------------");
        System.out.println("Total Registered Tenants: " + tenants.size());
        System.out.println("Occupied Rooms / Total Rooms: " + occupiedRooms + " / " + apartments.size());
        System.out.println("Total Revenue Collected: ₱" + totalCollected);
        System.out.println("========================================");
    }

    private static void saveAdminData() {
        try (FileWriter writer = new FileWriter("admin.json")) {
            gson.toJson(admins, writer);
        } catch (IOException e) { System.out.println("Error saving admin.json: " + e.getMessage()); }
    }

    private static void saveData() {
        try (FileWriter writer = new FileWriter("tenants.json")) {
            gson.toJson(tenants, writer);
        } catch (IOException e) { System.out.println("Error saving tenants.json: " + e.getMessage()); }

        try (FileWriter writer = new FileWriter("apartments.json")) {
            gson.toJson(apartments, writer);
        } catch (IOException e) { System.out.println("Error saving apartments.json: " + e.getMessage()); }

        try (FileWriter writer = new FileWriter("payments.json")) {
            gson.toJson(payments, writer);
        } catch (IOException e) { System.out.println("Error saving payments.json: " + e.getMessage()); }

        try (FileWriter writer = new FileWriter("deposits.json")) {
            gson.toJson(deposits, writer);
        } catch (IOException e) { System.out.println("Error saving deposits.json: " + e.getMessage()); }
    }

    private static void loadData() {
        try (FileReader reader = new FileReader("admin.json")) {
            Type adminListType = new TypeToken<ArrayList<Admin>>(){}.getType();
            List<Admin> loaded = gson.fromJson(reader, adminListType);
            if (loaded != null) admins = loaded;
        } catch (IOException ignored) {}

        try (FileReader reader = new FileReader("tenants.json")) {
            Type tenantListType = new TypeToken<ArrayList<Tenant>>(){}.getType();
            List<Tenant> loaded = gson.fromJson(reader, tenantListType);
            if (loaded != null) tenants = loaded;
        } catch (IOException ignored) {}

        try (FileReader reader = new FileReader("apartments.json")) {
            Type aptListType = new TypeToken<ArrayList<Apartment>>(){}.getType();
            List<Apartment> loaded = gson.fromJson(reader, aptListType);
            if (loaded != null) apartments = loaded;
        } catch (IOException ignored) {}

        try (FileReader reader = new FileReader("payments.json")) {
            Type payListType = new TypeToken<ArrayList<Payment>>(){}.getType();
            List<Payment> loaded = gson.fromJson(reader, payListType);
            if (loaded != null) payments = loaded;
        } catch (IOException ignored) {}

        try (FileReader reader = new FileReader("deposits.json")) {
            Type depListType = new TypeToken<ArrayList<Deposit>>(){}.getType();
            List<Deposit> loaded = gson.fromJson(reader, depListType);
            if (loaded != null) deposits = loaded;
        } catch (IOException ignored) {}
    }
}