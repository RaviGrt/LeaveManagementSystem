package com.lms;

/**
 * Main driver class for the Leave Management System.
 * Initial version - creating and displaying employee objects.
 *
 * @author Golla Raviteja
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Leave Management System v1.0");
        System.out.println("   Student: GOLLA RAVITEJA");
        System.out.println("========================================\n");

        // Create three employee objects
        Employee emp1 = new Employee("EMP001", "Ravi Kumar", "Engineering", 25);
        Employee emp2 = new Employee("EMP002", "Priya Sharma", "Marketing", 10);
        Employee emp3 = new Employee("EMP003", "Arjun Reddy", "Finance", 5);

        System.out.println("--- Employee 1 ---");
        emp1.displayDetails();
        System.out.println();

        System.out.println("--- Employee 2 ---");
        emp2.displayDetails();
        System.out.println();

        System.out.println("--- Employee 3 ---");
        emp3.displayDetails();
        System.out.println();

        System.out.println("\nLeave Balance Check:");
        System.out.println(emp1.getName() + ": " + emp1.checkLeaveBalance() + " days");
        System.out.println(emp2.getName() + ": " + emp2.checkLeaveBalance() + " days");
        System.out.println(emp3.getName() + ": " + emp3.checkLeaveBalance() + " days");
    }
}
