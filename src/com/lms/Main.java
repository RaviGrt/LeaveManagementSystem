package com.lms;

import com.lms.model.*;
import com.lms.enums.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main driver class demonstrating polymorphism (both runtime and compile-time).
 *
 * @author Golla Raviteja
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("          Leave Management System v4.0");
        System.out.println("          Student: GOLLA RAVITEJA");
        System.out.println("   Demonstrating: Polymorphism (Runtime + Compile-time)");
        System.out.println("========================================================\n");

        // ---- POLYMORPHISM (RUNTIME / DYNAMIC DISPATCH) ----
        // Using List<Employee> - same method call, different behavior per type
        List<Employee> employees = new ArrayList<>();
        employees.add(new FullTimeEmployee("FT001", "Ravi Kumar", "Engineering"));
        employees.add(new PartTimeEmployee("PT001", "Priya Sharma", "Marketing"));
        employees.add(new Intern("IN001", "Arjun Reddy", "R&D", "Dr. Venkat"));

        System.out.println("=== RUNTIME POLYMORPHISM: Processing List<Employee> ===\n");
        System.out.println("--- Initial Details ---");
        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println();
        }

        System.out.println("--- Applying 3 Days Leave (Polymorphic dispatch) ---");
        for (Employee emp : employees) {
            System.out.println("\n" + emp.getName() + " (" + emp.getEmployeeType() + "):");
            emp.applyLeave(3);
        }

        // ---- POLYMORPHISM (COMPILE-TIME / METHOD OVERLOADING) ----
        System.out.println("\n\n=== COMPILE-TIME POLYMORPHISM: Method Overloading ===\n");

        Employee ravi = employees.get(0);

        System.out.println("Overload 1 - applyLeave(int days):");
        ravi.applyLeave(2);

        System.out.println("\nOverload 2 - applyLeave(int days, LeaveType, String reason):");
        ravi.applyLeave(3, LeaveType.SICK, "Medical appointment");

        // ---- LEAVE CANCELLATION ----
        System.out.println("\n\n=== Leave Cancellation ===\n");
        ravi.cancelLeave(2);

        // ---- FINAL STATUS ----
        System.out.println("\n=== Final Balances (using toString - Polymorphism) ===\n");
        for (Employee emp : employees) {
            System.out.println("  " + emp);
        }
    }
}
