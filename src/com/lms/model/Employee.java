package com.lms.model;

import com.lms.enums.LeaveType;
import com.lms.exception.InsufficientLeaveBalanceException;
import com.lms.exception.InvalidLeaveOperationException;

/**
 * Abstract base class representing an Employee in the organization.
 *
 * <p><b>OOP Concepts Demonstrated:</b></p>
 * <ul>
 *   <li><b>Abstraction:</b> Abstract class forcing concrete implementations</li>
 *   <li><b>Encapsulation:</b> Private fields with validated getters/setters</li>
 *   <li><b>Polymorphism (Runtime):</b> Abstract applyLeave() overridden by subclasses</li>
 *   <li><b>Polymorphism (Compile-time):</b> Overloaded applyLeave() methods</li>
 *   <li><b>Custom Exceptions:</b> Domain-specific checked exceptions</li>
 * </ul>
 *
 * @author Golla Raviteja
 * @version 5.0 - Refactored with custom exceptions (replaces boolean error handling)
 */
public abstract class Employee {
    private String employeeId;
    private String name;
    private String department;
    protected int leaveBalance;
    protected int maxLeaveAllowance;

    public Employee(String employeeId, String name, String department, int leaveBalance) {
        if (employeeId == null || employeeId.trim().isEmpty())
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        if (department == null || department.trim().isEmpty())
            throw new IllegalArgumentException("Department cannot be null or empty.");
        if (leaveBalance < 0)
            throw new IllegalArgumentException("Leave balance cannot be negative.");

        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = leaveBalance;
        this.maxLeaveAllowance = leaveBalance;
    }

    // ---- Getters ----
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getLeaveBalance() { return leaveBalance; }
    public int getMaxLeaveAllowance() { return maxLeaveAllowance; }

    // ---- Setters ----
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name;
    }
    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) this.department = department;
    }
    protected void setLeaveBalance(int balance) {
        if (balance >= 0) this.leaveBalance = balance;
    }

    /**
     * Apply for leave - abstract method (RUNTIME POLYMORPHISM).
     * Each subclass implements type-specific validation rules.
     *
     * @param days Number of leave days
     * @throws InsufficientLeaveBalanceException if balance is insufficient
     * @throws InvalidLeaveOperationException if the operation is invalid
     */
    public abstract void applyLeave(int days)
            throws InsufficientLeaveBalanceException, InvalidLeaveOperationException;

    /**
     * METHOD OVERLOADING (COMPILE-TIME POLYMORPHISM):
     * Apply for leave with type and reason.
     */
    public void applyLeave(int days, LeaveType type, String reason)
            throws InsufficientLeaveBalanceException, InvalidLeaveOperationException {
        System.out.println("  [REQUEST] " + getName() + " requesting " + days + " day(s) of "
                + type.getDisplayName() + " - Reason: " + reason);
        applyLeave(days);
    }

    /**
     * Cancel previously approved leave.
     *
     * @param days Number of days to restore
     * @throws InvalidLeaveOperationException if the cancellation is invalid
     */
    public void cancelLeave(int days) throws InvalidLeaveOperationException {
        if (days <= 0) {
            throw new InvalidLeaveOperationException(
                    "Cancel days must be positive. Got: " + days, "CANCEL");
        }
        if (leaveBalance + days > maxLeaveAllowance) {
            throw new InvalidLeaveOperationException(
                    "Cannot exceed maximum allowance of " + maxLeaveAllowance + " days.", "CANCEL");
        }
        leaveBalance += days;
        System.out.println("  [OK] Leave cancelled for " + name + ": +" + days
                + " day(s) restored. Balance: " + leaveBalance);
    }

    public void displayDetails() {
        System.out.println("+--------------------------------------+");
        System.out.printf("| %-12s: %-22s |%n", "ID", employeeId);
        System.out.printf("| %-12s: %-22s |%n", "Name", name);
        System.out.printf("| %-12s: %-22s |%n", "Department", department);
        System.out.printf("| %-12s: %-22s |%n", "Type", getEmployeeType());
        System.out.printf("| %-12s: %-3d / %-17d |%n", "Leave", leaveBalance, maxLeaveAllowance);
        System.out.println("+--------------------------------------+");
    }

    public abstract String getEmployeeType();

    public int checkLeaveBalance() {
        return leaveBalance;
    }

    @Override
    public String toString() {
        return getEmployeeType() + " [" + employeeId + "] " + name
                + " (" + department + ") - Leave: " + leaveBalance + "/" + maxLeaveAllowance;
    }
}
