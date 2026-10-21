package com.lms;

/**
 * Employee class with leave management functionality.
 * Added applyLeave() and cancelLeave() methods.
 *
 * @author Golla Raviteja
 * @version 2.0
 */
public class Employee {
    private String employeeId;
    private String name;
    private String department;
    private int leaveBalance;
    private int maxLeaveAllowance;

    // Constructor
    public Employee(String employeeId, String name, String department, int leaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = leaveBalance;
        this.maxLeaveAllowance = leaveBalance; // Store original max
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getLeaveBalance() { return leaveBalance; }
    public int getMaxLeaveAllowance() { return maxLeaveAllowance; }

    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department;
        }
    }

    protected void setLeaveBalance(int leaveBalance) {
        if (leaveBalance >= 0) {
            this.leaveBalance = leaveBalance;
        }
    }

    /**
     * Apply for leave.
     * @param days Number of leave days requested
     * @return true if leave was approved, false otherwise
     */
    public boolean applyLeave(int days) {
        if (days <= 0) {
            System.out.println("  ✗ Error: Leave days must be positive.");
            return false;
        }
        if (days > leaveBalance) {
            System.out.println("  ✗ Insufficient leave balance! Available: " + leaveBalance + ", Requested: " + days);
            return false;
        }
        leaveBalance -= days;
        System.out.println("  ✓ Leave approved for " + name + ": " + days + " day(s). Remaining: " + leaveBalance);
        return true;
    }

    /**
     * Cancel previously approved leave.
     * @param days Number of leave days to restore
     * @return true if cancellation was successful
     */
    public boolean cancelLeave(int days) {
        if (days <= 0) {
            System.out.println("  ✗ Error: Cancel days must be positive.");
            return false;
        }
        if (leaveBalance + days > maxLeaveAllowance) {
            System.out.println("  ✗ Error: Cannot exceed maximum leave allowance of " + maxLeaveAllowance + " days.");
            return false;
        }
        leaveBalance += days;
        System.out.println("  ✓ Leave cancelled for " + name + ": " + days + " day(s) restored. Balance: " + leaveBalance);
        return true;
    }

    // Display employee details
    public void displayDetails() {
        System.out.println("Employee ID    : " + employeeId);
        System.out.println("Name           : " + name);
        System.out.println("Department     : " + department);
        System.out.println("Leave Balance  : " + leaveBalance + " / " + maxLeaveAllowance + " days");
    }

    // Check leave balance
    public int checkLeaveBalance() {
        return leaveBalance;
    }
}
