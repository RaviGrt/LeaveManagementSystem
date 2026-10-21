package com.lms;

import com.lms.model.*;
import com.lms.enums.*;
import com.lms.exception.*;
import com.lms.service.LeaveService;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║         LEAVE MANAGEMENT SYSTEM - FINAL VERSION             ║
 * ║         Student: GOLLA RAVITEJA                             ║
 * ║         Course: CAIT / NMIT LMS (SD_B26_Batch_4)           ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * <p>This driver class demonstrates ALL OOP concepts:</p>
 * <ol>
 *   <li>Encapsulation (private fields, validated accessors)</li>
 *   <li>Abstraction (abstract Employee base class)</li>
 *   <li>Inheritance (FullTimeEmployee, PartTimeEmployee, Intern, Manager)</li>
 *   <li>Polymorphism - Runtime (dynamic dispatch via List&lt;Employee&gt;)</li>
 *   <li>Polymorphism - Compile-time (method overloading)</li>
 *   <li>Custom Exception Handling</li>
 *   <li>Service Layer (SRP separation)</li>
 * </ol>
 *
 * @author Golla Raviteja
 * @version 6.0 - Final Production Version
 */
public class Main {

    private static final String DIVIDER = "════════════════════════════════════════════════════════════";
    private static final String SECTION = "────────────────────────────────────────────────────────────";

    public static void main(String[] args) {
        printBanner();

        LeaveService leaveService = new LeaveService();

        // ================================================================
        // SECTION 1: Employee Registration & Initial Balances
        // ================================================================
        printSection("1. EMPLOYEE REGISTRATION & INITIAL BALANCES");

        FullTimeEmployee ravi = new FullTimeEmployee("FT001", "Ravi Kumar", "Engineering");
        PartTimeEmployee priya = new PartTimeEmployee("PT001", "Priya Sharma", "Marketing");
        Intern arjun = new Intern("IN001", "Arjun Reddy", "R&D", "Dr. Venkat");
        Manager lakshmi = new Manager("MGR001", "Lakshmi Devi", "Engineering", 5);

        leaveService.registerEmployee(ravi);
        leaveService.registerEmployee(priya);
        leaveService.registerEmployee(arjun);
        leaveService.registerEmployee(lakshmi);

        System.out.println("\n--- Initial Employee Details ---\n");
        for (Employee emp : leaveService.getEmployees()) {
            emp.displayDetails();
            System.out.println();
        }

        leaveService.displayAllEmployees();

        // ================================================================
        // SECTION 2: Runtime Polymorphism - Dynamic Dispatch
        // ================================================================
        printSection("2. RUNTIME POLYMORPHISM (Dynamic Dispatch via List<Employee>)");
        System.out.println("Processing leave for ALL employees using a single loop");
        System.out.println("with List<Employee> - same method call, different behavior:\n");

        List<Employee> allEmployees = leaveService.getEmployees();
        for (Employee emp : allEmployees) {
            System.out.println(emp.getName() + " (" + emp.getEmployeeType() + ") → applyLeave(3):");
            try {
                emp.applyLeave(3);
            } catch (InsufficientLeaveBalanceException | InvalidLeaveOperationException e) {
                System.out.println("  [EXCEPTION] " + e.getMessage());
            }
            System.out.println();
        }

        // ================================================================
        // SECTION 3: Compile-Time Polymorphism - Method Overloading
        // ================================================================
        printSection("3. COMPILE-TIME POLYMORPHISM (Method Overloading)");

        System.out.println("Demonstrating two overloaded applyLeave() signatures:\n");

        System.out.println("Overload 1: applyLeave(int days)");
        try {
            ravi.applyLeave(2);
        } catch (InsufficientLeaveBalanceException | InvalidLeaveOperationException e) {
            System.out.println("  [EXCEPTION] " + e.getMessage());
        }

        System.out.println("\nOverload 2: applyLeave(int days, LeaveType type, String reason)");
        try {
            leaveService.processLeaveRequest(ravi, 3, LeaveType.SICK, "Medical appointment with specialist");
        } catch (InsufficientLeaveBalanceException | InvalidLeaveOperationException e) {
            System.out.println("  [EXCEPTION] " + e.getMessage());
        }

        System.out.println("\nOverload 2 with PRIVILEGE leave:");
        try {
            leaveService.processLeaveRequest(priya, 2, LeaveType.PRIVILEGE, "Family function");
        } catch (InsufficientLeaveBalanceException | InvalidLeaveOperationException e) {
            System.out.println("  [EXCEPTION] " + e.getMessage());
        }

        // ================================================================
        // SECTION 4: Leave Cancellation
        // ================================================================
        printSection("4. LEAVE CANCELLATION (Restoring Balance)");

        System.out.println("Ravi Kumar cancelling 2 days:");
        try {
            leaveService.processCancellation(ravi, 2);
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [EXCEPTION] " + e.getMessage());
        }

        System.out.println("\nPriya Sharma cancelling 1 day:");
        try {
            leaveService.processCancellation(priya, 1);
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [EXCEPTION] " + e.getMessage());
        }

        // ================================================================
        // SECTION 5: Edge Cases & Exception Handling
        // ================================================================
        printSection("5. EDGE CASES & CUSTOM EXCEPTION HANDLING");

        // 5a: Intern trying to take 20 days (exceeds balance)
        System.out.println("5a. Intern (Arjun) attempting 20 days leave:");
        try {
            arjun.applyLeave(20);
            System.out.println("  Unexpected: should have thrown exception!");
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("  [CAUGHT] InsufficientLeaveBalanceException:");
            System.out.println("           " + e.getMessage());
            System.out.println("           Requested: " + e.getRequested() + ", Available: " + e.getAvailable());
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [CAUGHT] InvalidLeaveOperationException: " + e.getMessage());
        }

        // 5b: Part-time exceeding consecutive limit
        System.out.println("\n5b. Part-Time (Priya) requesting 7 consecutive days:");
        try {
            priya.applyLeave(7);
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("  [CAUGHT] InsufficientLeaveBalanceException: " + e.getMessage());
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [CAUGHT] InvalidLeaveOperationException:");
            System.out.println("           " + e.getMessage());
            System.out.println("           Operation: " + e.getOperation());
        }

        // 5c: Applying negative days
        System.out.println("\n5c. Full-Time (Ravi) applying for -3 days:");
        try {
            ravi.applyLeave(-3);
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("  [CAUGHT] InsufficientLeaveBalanceException: " + e.getMessage());
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [CAUGHT] InvalidLeaveOperationException:");
            System.out.println("           " + e.getMessage());
            System.out.println("           Operation: " + e.getOperation());
        }

        // 5d: Cancelling more than used
        System.out.println("\n5d. Ravi cancelling 50 days (exceeds max allowance):");
        try {
            ravi.cancelLeave(50);
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [CAUGHT] InvalidLeaveOperationException:");
            System.out.println("           " + e.getMessage());
        }

        // ================================================================
        // SECTION 6: Manager-Specific Capabilities
        // ================================================================
        printSection("6. MANAGER EXECUTIVE PERKS & TEAM APPROVAL");

        System.out.println("Manager leave balance (35 days = 30 standard + 5 executive bonus):");
        lakshmi.displayDetails();

        System.out.println("\nManager applying for PRIVILEGE leave:");
        try {
            leaveService.processLeaveRequest(lakshmi, 5, LeaveType.PRIVILEGE, "Annual family vacation");
        } catch (InsufficientLeaveBalanceException | InvalidLeaveOperationException e) {
            System.out.println("  [EXCEPTION] " + e.getMessage());
        }

        System.out.println("\n--- Manager Approving Team Leave ---");
        System.out.println("\nLakshmi approving 5 days for Ravi (Full-Time):");
        lakshmi.approveTeamLeave(ravi, 5);

        System.out.println("\nLakshmi attempting to approve 20 days for Arjun (Intern - should fail):");
        lakshmi.approveTeamLeave(arjun, 20);

        // ================================================================
        // SECTION 7: Final Summary
        // ================================================================
        printSection("7. FINAL EMPLOYEE LEAVE SUMMARY");

        leaveService.displayAllEmployees();
        leaveService.displayLeaveHistory();

        System.out.println("\n--- Final Balances (Polymorphic toString) ---\n");
        for (Employee emp : leaveService.getEmployees()) {
            System.out.println("  " + emp);
        }

        // ================================================================
        // OOP CONCEPTS SUMMARY
        // ================================================================
        printSection("OOP CONCEPTS DEMONSTRATED IN THIS APPLICATION");
        System.out.println("  1. ENCAPSULATION     : Private fields, validated getters/setters");
        System.out.println("  2. ABSTRACTION       : Abstract Employee class with abstract applyLeave()");
        System.out.println("  3. INHERITANCE       : FullTimeEmployee, PartTimeEmployee, Intern, Manager");
        System.out.println("  4. POLYMORPHISM      :");
        System.out.println("     a. Runtime        : List<Employee> with overridden applyLeave()");
        System.out.println("     b. Compile-time   : Overloaded applyLeave(int) vs applyLeave(int, LeaveType, String)");
        System.out.println("  5. ACCESS MODIFIERS  : private, protected, public used appropriately");
        System.out.println("  6. CUSTOM EXCEPTIONS : InsufficientLeaveBalanceException, InvalidLeaveOperationException");
        System.out.println("  7. SRP               : LeaveService separates business logic from presentation");
        System.out.println("  8. OCP               : Manager added without modifying existing classes");

        System.out.println("\n" + DIVIDER);
        System.out.println("  Program completed successfully.");
        System.out.println("  Student: GOLLA RAVITEJA | CAIT / NMIT LMS (SD_B26_Batch_4)");
        System.out.println(DIVIDER);
    }

    private static void printBanner() {
        System.out.println("\n" + DIVIDER);
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       LEAVE MANAGEMENT SYSTEM - FINAL VERSION v6.0      ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Student : GOLLA RAVITEJA                                ║");
        System.out.println("║  Course  : CAIT / NMIT LMS (SD_B26_Batch_4)             ║");
        System.out.println("║  Date    : October 2026                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println(DIVIDER + "\n");
    }

    private static void printSection(String title) {
        System.out.println("\n" + SECTION);
        System.out.println("  " + title);
        System.out.println(SECTION + "\n");
    }
}
