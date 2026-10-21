package com.lms.model;

import com.lms.exception.InsufficientLeaveBalanceException;
import com.lms.exception.InvalidLeaveOperationException;

/**
 * Full-Time Employee with standard leave entitlements.
 * Refactored to use custom exceptions instead of boolean returns.
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

    @Override
    public void applyLeave(int days)
            throws InsufficientLeaveBalanceException, InvalidLeaveOperationException {
        if (days <= 0) {
            throw new InvalidLeaveOperationException(
                    "Leave days must be positive. Got: " + days, "APPLY");
        }
        if (days > leaveBalance) {
            throw new InsufficientLeaveBalanceException(getName(), days, leaveBalance);
        }
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Full-Time): " + days
                + " day(s) approved. Remaining: " + leaveBalance);
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
