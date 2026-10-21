package com.lms.model;

import com.lms.exception.InsufficientLeaveBalanceException;
import com.lms.exception.InvalidLeaveOperationException;

/**
 * Part-Time Employee with pro-rated leave and consecutive day limits.
 * Refactored to use custom exceptions.
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

    @Override
    public void applyLeave(int days)
            throws InsufficientLeaveBalanceException, InvalidLeaveOperationException {
        if (days <= 0) {
            throw new InvalidLeaveOperationException(
                    "Leave days must be positive. Got: " + days, "APPLY");
        }
        if (days > MAX_CONSECUTIVE_DAYS) {
            throw new InvalidLeaveOperationException(
                    getName() + " (Part-Time): Cannot request more than "
                    + MAX_CONSECUTIVE_DAYS + " consecutive days. Requested: " + days, "APPLY");
        }
        if (days > leaveBalance) {
            throw new InsufficientLeaveBalanceException(getName(), days, leaveBalance);
        }
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Part-Time): " + days
                + " day(s) approved. Remaining: " + leaveBalance);
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }

    public int getMaxConsecutiveDays() {
        return MAX_CONSECUTIVE_DAYS;
    }
}
