package com.lms;

/**
 * Main driver class - testing leave management operations.
 *
 * @author Golla Raviteja
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Leave Management System v2.0");
        System.out.println("   Student: GOLLA RAVITEJA");
        System.out.println("========================================\n");

        Employee emp1 = new Employee("EMP001", "Ravi Kumar", "Engineering", 25);
        Employee emp2 = new Employee("EMP002", "Priya Sharma", "Marketing", 10);
        Employee emp3 = new Employee("EMP003", "Arjun Reddy", "Finance", 5);

        // Display initial details
        System.out.println("=== Initial Employee Details ===");
        emp1.displayDetails();
        System.out.println();
        emp2.displayDetails();
        System.out.println();
        emp3.displayDetails();

        // Test leave applications
        System.out.println("\n=== Leave Applications ===");
        System.out.print(emp1.getName() + " applying for 5 days: ");
        emp1.applyLeave(5);

        System.out.print(emp2.getName() + " applying for 3 days: ");
        emp2.applyLeave(3);

        System.out.print(emp3.getName() + " applying for 8 days (exceeds balance): ");
        emp3.applyLeave(8);

        // Test leave cancellation
        System.out.println("\n=== Leave Cancellations ===");
        System.out.print(emp1.getName() + " cancelling 2 days: ");
        emp1.cancelLeave(2);

        // Display updated balances
        System.out.println("\n=== Updated Balances ===");
        System.out.println(emp1.getName() + ": " + emp1.checkLeaveBalance() + " days");
        System.out.println(emp2.getName() + ": " + emp2.checkLeaveBalance() + " days");
        System.out.println(emp3.getName() + ": " + emp3.checkLeaveBalance() + " days");
    }
}
