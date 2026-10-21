package com.lms.model;

/**
 * Abstract base class representing an Employee in the organization.
 * Demonstrates: Abstraction, Encapsulation, Access Modifiers.
 *
 * <p>This class defines the common structure and behavior for all employee types.
 * Subclasses must implement type-specific leave validation rules.</p>
 *
 * @author Golla Raviteja
 * @version 3.0
 */
public abstract class Employee {
    // Encapsulation: private fields with controlled access
    private String employeeId;
    private String name;
    private String department;

    // Protected: accessible by subclasses for specialized leave logic
    protected int leaveBalance;
    protected int maxLeaveAllowance;

    /**
     * Constructs an Employee with validated parameters.
     *
     * @param employeeId    Unique identifier for the employee
     * @param name          Full name of the employee
     * @param department    Department the employee belongs to
     * @param leaveBalance  Initial leave balance (days)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Employee(String employeeId, String name, String department, int leaveBalance) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty.");
        }
        if (leaveBalance < 0) {
            throw new IllegalArgumentException("Leave balance cannot be negative.");
        }
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

    // ---- Setters with defensive validation ----
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

    protected void setLeaveBalance(int balance) {
        if (balance >= 0) {
            this.leaveBalance = balance;
        }
    }

    /**
     * Abstract method: each employee type implements its own leave validation rules.
     * Demonstrates: Abstraction & Polymorphism (runtime/dynamic dispatch).
     *
     * @param days Number of leave days requested
     * @return true if leave was approved, false otherwise
     */
    public abstract boolean applyLeave(int days);

    /**
     * Cancel previously approved leave, restoring days to balance.
     *
     * @param days Number of days to restore
     * @return true if cancellation was successful
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
        System.out.println("  [OK] Leave cancelled for " + name + ": +" + days + " day(s) restored. Balance: " + leaveBalance);
        return true;
    }

    /**
     * Display employee details. Can be overridden for type-specific info.
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

    /**
     * Returns the employee type as a string. Overridden by subclasses.
     */
    public abstract String getEmployeeType();

    /**
     * Check current leave balance.
     * @return remaining leave days
     */
    public int checkLeaveBalance() {
        return leaveBalance;
    }

    @Override
    public String toString() {
        return getEmployeeType() + " [" + employeeId + "] " + name + " (" + department + ") - Leave: " + leaveBalance + "/" + maxLeaveAllowance;
    }
}
