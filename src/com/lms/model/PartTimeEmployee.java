package com.lms.model;

/**
 * Part-Time Employee with pro-rated leave entitlements.
 * Demonstrates: Inheritance, Method Overriding with specialized rules.
 *
 * <p>Leave Rules:
 * <ul>
 *   <li>Pro-rated balance: 10 days/year</li>
 *   <li>Cannot exceed 5 consecutive days per request</li>
 *   <li>No carry-over of unused leaves</li>
 * </ul></p>
 *
 * @author Golla Raviteja
 */
public class PartTimeEmployee extends Employee {

    private static final int DEFAULT_LEAVE_BALANCE = 10;
    private static final int MAX_CONSECUTIVE_DAYS = 5;

    public PartTimeEmployee(String employeeId, String name, String department) {
        super(employeeId, name, department, DEFAULT_LEAVE_BALANCE);
    }

    public PartTimeEmployee(String employeeId, String name, String department, int leaveBalance) {
        super(employeeId, name, department, leaveBalance);
    }

    /**
     * Part-time employees have a 5-day consecutive limit per request.
     * Overrides abstract method from Employee (Runtime Polymorphism).
     */
    @Override
    public boolean applyLeave(int days) {
        if (days <= 0) {
            System.out.println("  [ERROR] Leave days must be positive.");
            return false;
        }
        if (days > MAX_CONSECUTIVE_DAYS) {
            System.out.println("  [DENIED] " + getName() + " (Part-Time): Cannot request more than "
                    + MAX_CONSECUTIVE_DAYS + " consecutive days. Requested: " + days);
            return false;
        }
        if (days > leaveBalance) {
            System.out.println("  [DENIED] " + getName() + ": Insufficient balance! Available: "
                    + leaveBalance + ", Requested: " + days);
            return false;
        }
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Part-Time): " + days
                + " day(s) approved. Remaining: " + leaveBalance);
        return true;
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }

    public int getMaxConsecutiveDays() {
        return MAX_CONSECUTIVE_DAYS;
    }
}
