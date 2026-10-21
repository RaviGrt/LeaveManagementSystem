package com.lms;

/**
 * Employee class representing basic employee information.
 * This is the initial version with basic fields and methods.
 *
 * @author Golla Raviteja
 * @version 1.0
 */
public class Employee {
    private String employeeId;
    private String name;
    private String department;
    private int leaveBalance;

    // Constructor
    public Employee(String employeeId, String name, String department, int leaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = leaveBalance;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getLeaveBalance() { return leaveBalance; }

    // Setters with basic validation
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

    public void setLeaveBalance(int leaveBalance) {
        if (leaveBalance >= 0) {
            this.leaveBalance = leaveBalance;
        }
    }

    // Display employee details
    public void displayDetails() {
        System.out.println("Employee ID  : " + employeeId);
        System.out.println("Name         : " + name);
        System.out.println("Department   : " + department);
        System.out.println("Leave Balance: " + leaveBalance + " days");
    }

    // Check leave balance
    public int checkLeaveBalance() {
        return leaveBalance;
    }
}
