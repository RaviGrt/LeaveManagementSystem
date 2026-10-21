package com.lms.model;

import com.lms.exception.InsufficientLeaveBalanceException;
import com.lms.exception.InvalidLeaveOperationException;

/**
 * Intern with minimal leave entitlements and strict policies.
 * Refactored to use custom exceptions.
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
        // Unpaid leave warning
        if (!unpaidLeaveWarningShown) {
            System.out.println("  [NOTICE] " + getName() + ": All intern leaves are UNPAID. Stipend will be deducted.");
            unpaidLeaveWarningShown = true;
        }
        System.out.println("  [INFO] Mentor sign-off required from: " + mentorName);
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Intern): " + days
                + " day(s) approved (unpaid). Remaining: " + leaveBalance);
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
