package com.lms.model;

/**
 * Intern with minimal leave entitlements and strict rules.
 * Demonstrates: Inheritance, Method Overriding with restrictive policies.
 *
 * <p>Leave Rules:
 * <ul>
 *   <li>Limited balance: 5 days/year (stipend/learning contract)</li>
 *   <li>All leaves are unpaid</li>
 *   <li>Requires mentor sign-off (warning displayed)</li>
 * </ul></p>
 *
 * @author Golla Raviteja
 */
public class Intern extends Employee {

    private static final int DEFAULT_LEAVE_BALANCE = 5;
    private String mentorName;
    private boolean unpaidLeaveWarningShown;

    public Intern(String employeeId, String name, String department, String mentorName) {
        super(employeeId, name, department, DEFAULT_LEAVE_BALANCE);
        this.mentorName = mentorName;
        this.unpaidLeaveWarningShown = false;
    }

    public String getMentorName() { return mentorName; }
    public void setMentorName(String mentorName) { this.mentorName = mentorName; }

    /**
     * Interns have strict limits and unpaid leave warnings.
     * Overrides abstract method from Employee (Runtime Polymorphism).
     */
    @Override
    public boolean applyLeave(int days) {
        if (days <= 0) {
            System.out.println("  [ERROR] Leave days must be positive.");
            return false;
        }
        if (days > leaveBalance) {
            System.out.println("  [DENIED] " + getName() + " (Intern): Insufficient balance! Available: "
                    + leaveBalance + ", Requested: " + days);
            System.out.println("  [WARNING] Interns cannot exceed their " + DEFAULT_LEAVE_BALANCE + "-day limit.");
            return false;
        }
        // Unpaid leave warning
        if (!unpaidLeaveWarningShown) {
            System.out.println("  [NOTICE] " + getName() + ": All intern leaves are UNPAID. Stipend will be deducted.");
            unpaidLeaveWarningShown = true;
        }
        System.out.println("  [INFO] Mentor sign-off required from: " + mentorName);
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Intern): " + days
                + " day(s) approved (unpaid). Remaining: " + leaveBalance);
        return true;
    }

    @Override
    public String getEmployeeType() {
        return "Intern";
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("  Mentor: " + mentorName);
        System.out.println("  Leave Type: Unpaid");
    }
}
