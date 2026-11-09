# RentLog: Java-Based Apartment Rental & Tenant Management System

## 1. Project Title
RentLog: Java-Based Apartment Rental & Tenant Management System

## 2. Project Description
RentLog is a Java-based application designed to streamline apartment rental operations and tenant management through a secure, dual-portal architecture. The application automates tenant profile tracking, room occupancy monitoring, rental payment calculations, security deposit tracking, and monthly financial reporting, ensuring reliable local data persistence using JSON file handling via the Gson library.

## 3. Problem Being Addressed
Managing apartment rooms and tenant records manually can lead to problems such as misplaced information, incorrect balance calculations, and unorganized payment and deposit records. These issues affect apartment owners, landlords, and property managers who are responsible for handling tenants and rental payments. RentLog addresses these problems by automating record-keeping, reducing human error, and making apartment management easier, faster, and more organized.

## 4. Project Objectives
* **General Objective:** To develop a Java-based application that streamlines apartment management by tracking tenant information, rental contracts, payment histories, and security deposits using Object-Oriented Programming principles[cite: 15].
* **Specific Objectives:**
  * Implement the four core OOP principles (Abstraction, Encapsulation, Inheritance, and Polymorphism) across core system classes[cite: 16].
  * Provide CRUD functionality (Add, View, Update, Delete) for tenant profiles and room statuses[cite: 16].
  * Automatically calculate running balances based on monthly rent, payments made, and payment status (`Paid`, `Partial`, `Unpaid`)[cite: 16].
  * Track security deposits and advance payments along with their current status[cite: 16].
  * Store payment records and generate monthly financial and occupancy reports using Java collections and file I/O[cite: 16].
  * Provide secure dual-portal authentication (Landlord/Admin and Tenant sign-in) with role-based access control[cite: 16].
  * Implement persistent data storage using the Google Gson library to automatically save and load system state across application sessions[cite: 16].

## 5. Target Users
* **Landlord / Property Manager:** Securely logs into the admin portal using administrator credentials, manages apartment room listings, adds/updates/deletes tenant records, records rental payments, tracks security deposits, and generates monthly summary reports[cite: 16].
* **Tenant (View Mode):** Securely logs into the individual tenant portal using personal credentials to view personal profile details, assigned rooms, monthly rent prices, remaining balances, and historical payment receipts[cite: 16].

## 6. Main Features
* **Add Tenant:** Registers a new tenant by entering their personal and contact information, assigned apartment room, and move-in date[cite: 16].
* **View Tenant Records:** Shows all currently active tenant records, including their assigned room numbers and rental agreement details[cite: 16].
* **Update Tenant Information:** Allows users to update existing tenant information, contact numbers, and assigned apartment rooms[cite: 16].
* **Delete Tenant:** Allows users to remove a tenant's record when they move out and automatically changes the assigned room status to vacant[cite: 17].
* **Record Rental Payment:** Allows users to record a payment by entering the amount, payment method, and date, assigning each payment a unique ID for tracking[cite: 17].
* **Calculate Remaining Balance:** Automatically computes the balance due and updates status (`Paid`, `Partial`, `Unpaid`)[cite: 17].
* **Dual-Portal Sign-In Authentication:** Provides secure login screens for both administrators and tenants with credential validation and failed attempt limits[cite: 17].
* **Security Deposit & Advance Tracking:** Tracks security deposits and advance payments, maintaining their holding status (`Held`, `Applied`, `Refunded`)[cite: 17].
* **Monthly Financial & Occupancy Reporting:** Aggregates total revenue collected, lists room occupancy statuses (`Occupied` vs. `Vacant`), and calculates total registered tenants[cite: 17].
* **JSON Data Persistence (Gson):** Automatically serializes and saves all system data (`admin.json`, `tenants.json`, `apartments.json`, `payments.json`, `deposits.json`) for seamless recovery upon restart[cite: 17].

## 7. Technologies Used
* **Language:** Java (JDK)
* **Libraries:** Google Gson (for JSON file serialization and persistence)
* **Tools:** Visual Studio Code, Git & GitHub, Apache Maven
* **Interface:** Command-Line Interface (CLI)

## 8. Team Members and Roles
* **Macaraig, Austine Jay U.** – Team Leader (Oversees project development and coordinates tasks)[cite: 21]
* **Cepillo, Steven Nicholas A.** – Documentation (Handles project documentation and user guides)[cite: 21]
* **Evangelista, Haira A.** – Programmer (Handles payment, deposit, and authentication features)[cite: 21]
* **Sanchez, Althea P.** – Programmer (Handles Tenant, Apartment, and Rental classes)[cite: 21]
* **Mendoza, Mark Airon O.** – Tester (Tests system, debugs errors, and verifies features)[cite: 21]

## 9. OOP Concepts Demonstrated
* **Classes and Objects:** Instantiating core objects across primary and supporting classes (`Tenant`, `Apartment`, `Rental`, `Payment`, `Deposit`, `Admin`, `User`)[cite: 17].
* **Methods:** Encapsulating specific behaviors like `calculateBalance()`, `markOccupied()`, `markVacant()`, `updateContact()`, `getPaymentReceipt()`, `saveData()`, and `loadData()`[cite: 17].
* **Encapsulation:** Keeping entity attributes private or protected and exposing them securely via public getters, setters, and controlled mutation methods[cite: 17].
* **Inheritance:** Extending the abstract base class `User` to derive specialized child roles (`Admin` and `Tenant`)[cite: 18].
* **Polymorphism:** Overriding abstract methods (`getRoleDetails()`) dynamically across subclasses to provide specific role behaviors[cite: 18].
* **Abstraction:** Utilizing abstract classes (`User`) and interfaces (`Reportable`) to enforce structural contracts[cite: 18].
* **Exception Handling:** Managing runtime errors gracefully using `try-catch` blocks for input mismatches (`NumberFormatException`) and file I/O errors (`IOException`)[cite: 18].
* **Arrays/Data Handling:** Utilizing dynamic Java collections (`ArrayList`) alongside Google Gson for local JSON data persistence[cite: 18].

## 10. Instructions for Running the Program
1. Clone the repository or download the source code into your local machine.
2. Open the project folder in **Visual Studio Code**.
3. Ensure the Gson library dependency is configured in your project path/Maven configuration.
4. Compile and run `Main.java` inside the `src/` directory.
5. Select your portal choice (Admin Portal or Tenant Portal) and follow the interactive console prompts.

## 11. Sample Screenshots
*(Sample terminal execution screenshots are located in the `screenshots/` directory of this repository.)*

## 12. Known Limitations
* **Graphical User Interface (GUI):** Operates solely as a command-line interface (CLI) rather than a graphical desktop or web interface[cite: 22].
* **Database Management System:** Relies on local JSON files instead of a hosted relational database (e.g., MySQL)[cite: 22].
* **Online Payment Gateway:** Payments must be manually recorded by the administrator rather than processed through live online payment channels[cite: 22].

## 13. Future Improvements
* Migrate the command-line interface to a modern Java GUI (such as JavaFX or Swing) or a web-based interface (Spring Boot).
* Integrate a fully hosted relational database system (MySQL / PostgreSQL).
* Add integration for real-time digital payment channels and automated email notification receipts.

## 14. Project Contributors
* **Sir Jayson Pogi Team** (Section: IT 2104)[cite: 15]
  * Steven Nicholas A. Cepillo[cite: 15]
  * Haira A. Evangelista[cite: 15]
  * Austine Jay U. Macaraig[cite: 15]
  * Mark Airon O. Mendoza[cite: 15]
  * Althea P. Sanchez[cite: 15]