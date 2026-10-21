package com.lms;

import com.lms.model.*;

/**
 * Main driver class demonstrating inheritance with different employee types.
 *
 * @author Golla Raviteja
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Leave Management System v3.0");
        System.out.println("   Student: GOLLA RAVITEJA");
        System.out.println("========================================\n");

        // Create different employee types - Inheritance demonstration
        FullTimeEmployee ft = new FullTimeEmployee("FT001", "Ravi Kumar", "Engineering");
        PartTimeEmployee pt = new PartTimeEmployee("PT001", "Priya Sharma", "Marketing");
        Intern intern = new Intern("IN001", "Arjun Reddy", "R&D", "Dr. Venkat");

        System.out.println("=== Employee Details (Inheritance Demo) ===\n");
        ft.displayDetails();
        System.out.println();
        pt.displayDetails();
        System.out.println();
        intern.displayDetails();

        // Test type-specific leave rules
        System.out.println("\n=== Type-Specific Leave Rules ===\n");

        System.out.println("Full-Time - Apply 10 days:");
        ft.applyLeave(10);

        System.out.println("\nPart-Time - Apply 3 days:");
        pt.applyLeave(3);

        System.out.println("\nPart-Time - Apply 7 days (exceeds consecutive limit):");
        pt.applyLeave(7);

        System.out.println("\nIntern - Apply 2 days:");
        intern.applyLeave(2);

        System.out.println("\nIntern - Apply 20 days (exceeds balance):");
        intern.applyLeave(20);

        // Leave cancellation
        System.out.println("\n=== Leave Cancellation ===\n");
        System.out.println("Full-Time - Cancel 5 days:");
        ft.cancelLeave(5);

        System.out.println("\n=== Final Balances ===\n");
        System.out.println(ft);
        System.out.println(pt);
        System.out.println(intern);
    }
}
