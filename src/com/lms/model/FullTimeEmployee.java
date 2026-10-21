package com.lms.model;

/**
 * Full-Time Employee with standard leave entitlements.
 * Demonstrates: Inheritance, Method Overriding.
 *
 * <p>Leave Rules:
 * <ul>
 *   <li>Standard balance: 25 days/year (casual + sick + privilege)</li>
 *   <li>Can carry over unused leaves</li>
 *   <li>No consecutive day limit per request</li>
 * </ul></p>
 *
 * @author Golla Raviteja
 */
public class FullTimeEmployee extends Employee {

    private static final int DEFAULT_LEAVE_BALANCE = 25;
    private boolean canCarryOver;

    public FullTimeEmployee(String employeeId, String name, String department) {
        super(employeeId, name, department, DEFAULT_LEAVE_BALANCE);
        this.canCarryOver = true;
    }

    public FullTimeEmployee(String employeeId, String name, String department, int leaveBalance) {
        super(employeeId, name, department, leaveBalance);
        this.canCarryOver = true;
    }

    public boolean isCanCarryOver() { return canCarryOver; }
    public void setCanCarryOver(boolean canCarryOver) { this.canCarryOver = canCarryOver; }

    /**
     * Full-time employees can apply for any number of days up to their balance.
     * Overrides abstract method from Employee (Runtime Polymorphism).
     */
    @Override
    public boolean applyLeave(int days) {
        if (days <= 0) {
            System.out.println("  [ERROR] Leave days must be positive.");
            return false;
        }
        if (days > leaveBalance) {
            System.out.println("  [DENIED] " + getName() + ": Insufficient balance! Available: "
                    + leaveBalance + ", Requested: " + days);
            return false;
        }
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Full-Time): " + days
                + " day(s) approved. Remaining: " + leaveBalance);
        return true;
    }

    @Override
    public String getEmployeeType() {
        return "Full-Time";
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("  Carry Over: " + (canCarryOver ? "Yes" : "No"));
    }
}
