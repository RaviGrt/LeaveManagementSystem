package com.lms.model;

import com.lms.enums.LeaveType;
import com.lms.exception.InsufficientLeaveBalanceException;
import com.lms.exception.InvalidLeaveOperationException;

/**
 * Manager with executive leave privileges and team approval capabilities.
 * Refactored to use custom exceptions.
 *
 * @author Golla Raviteja
 */
public class Manager extends FullTimeEmployee {

    private static final int MANAGER_LEAVE_BALANCE = 30;
    private static final int EXECUTIVE_BONUS_DAYS = 5;
    private int executiveBonusDays;
    private int teamSize;

    public Manager(String employeeId, String name, String department, int teamSize) {
        super(employeeId, name, department, MANAGER_LEAVE_BALANCE + EXECUTIVE_BONUS_DAYS);
        this.executiveBonusDays = EXECUTIVE_BONUS_DAYS;
        this.teamSize = teamSize;
    }

    public int getExecutiveBonusDays() { return executiveBonusDays; }
    public int getTeamSize() { return teamSize; }

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
        System.out.println("  [APPROVED] " + getName() + " (Manager/Executive): " + days
                + " day(s) approved. Remaining: " + leaveBalance);
    }

    /**
     * Manager-specific: Approve a team member's leave.
     */
    public boolean approveTeamLeave(Employee employee, int days) {
        System.out.println("\n  [MANAGER ACTION] " + getName() + " reviewing leave request:");
        System.out.println("  \u2192 Employee: " + employee.getName() + " (" + employee.getEmployeeType() + ")");
        System.out.println("  \u2192 Days Requested: " + days);
        System.out.println("  \u2192 Current Balance: " + employee.getLeaveBalance());

        try {
            employee.applyLeave(days);
            System.out.println("  [MANAGER APPROVED] " + getName() + " approved "
                    + days + " day(s) for " + employee.getName());
            return true;
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("  [REJECTED] " + e.getMessage());
            return false;
        } catch (InvalidLeaveOperationException e) {
            System.out.println("  [REJECTED] " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getEmployeeType() {
        return "Manager";
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("  Executive Bonus: " + executiveBonusDays + " days");
        System.out.println("  Team Size: " + teamSize + " direct reports");
    }
}
