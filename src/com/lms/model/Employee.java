package com.lms.model;

import com.lms.enums.LeaveType;

/**
 * Abstract base class representing an Employee in the organization.
 * Demonstrates: Abstraction, Encapsulation, Access Modifiers, Method Overloading.
 *
 * @author Golla Raviteja
 * @version 4.0 - Added method overloading (compile-time polymorphism)
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
     * Apply for leave (basic version).
     * RUNTIME POLYMORPHISM: Each subclass overrides with type-specific rules.
     *
     * @param days Number of leave days
     * @return true if approved
     */
    public abstract boolean applyLeave(int days);

    /**
     * METHOD OVERLOADING (Compile-time Polymorphism):
     * Apply for leave with type and reason.
     *
     * @param days   Number of leave days
     * @param type   Type of leave (CASUAL, SICK, PRIVILEGE, UNPAID)
     * @param reason Reason for leave
     * @return true if approved
     */
    public boolean applyLeave(int days, LeaveType type, String reason) {
        System.out.println("  [REQUEST] " + getName() + " requesting " + days + " day(s) of "
                + type.getDisplayName() + " - Reason: " + reason);
        return applyLeave(days);
    }

    /**
     * Cancel previously approved leave.
     */
    public boolean cancelLeave(int days) {
        if (days <= 0) {
            System.out.println("  [ERROR] Cancel days must be positive.");
            return false;
        }
        if (leaveBalance + days > maxLeaveAllowance) {
            System.out.println("  [ERROR] Cannot exceed maximum allowance of " + maxLeaveAllowance + " days.");
            return false;
        }
        leaveBalance += days;
        System.out.println("  [OK] Leave cancelled for " + name + ": +" + days
                + " day(s) restored. Balance: " + leaveBalance);
        return true;
    }

    /**
     * Display employee details.
     */
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
