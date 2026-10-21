package com.lms;

import com.lms.model.*;
import com.lms.enums.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main driver class - now including Manager (Change Request).
 *
 * @author Golla Raviteja
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("          Leave Management System v5.0");
        System.out.println("          Student: GOLLA RAVITEJA");
        System.out.println("   Change Request: Manager Employee Type Added");
        System.out.println("========================================================\n");

        // Create all employee types including Manager
        FullTimeEmployee ft = new FullTimeEmployee("FT001", "Ravi Kumar", "Engineering");
        PartTimeEmployee pt = new PartTimeEmployee("PT001", "Priya Sharma", "Marketing");
        Intern intern = new Intern("IN001", "Arjun Reddy", "R&D", "Dr. Venkat");
        Manager manager = new Manager("MGR001", "Lakshmi Devi", "Engineering", 5);

        // Polymorphic list includes Manager
        List<Employee> employees = new ArrayList<>();
        employees.add(ft);
        employees.add(pt);
        employees.add(intern);
        employees.add(manager);

        System.out.println("=== All Employee Details ===\n");
        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println();
        }

        // Polymorphic leave applications
        System.out.println("=== Polymorphic Leave Applications ===\n");
        for (Employee emp : employees) {
            System.out.println(emp.getName() + " (" + emp.getEmployeeType() + ") applying for 3 days:");
            emp.applyLeave(3);
            System.out.println();
        }

        // Method overloading
        System.out.println("=== Method Overloading Demo ===\n");
        System.out.println("Manager applying with LeaveType:");
        manager.applyLeave(2, LeaveType.PRIVILEGE, "Family vacation");

        // Manager-specific: approve team leave
        System.out.println("\n=== Manager Approval (Unique Capability) ===");
        manager.approveTeamLeave(ft, 5);
        manager.approveTeamLeave(intern, 20); // Should fail

        // Final balances
        System.out.println("\n=== Final Balances ===\n");
        for (Employee emp : employees) {
            System.out.println("  " + emp);
        }
    }
}
