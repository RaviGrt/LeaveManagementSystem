package com.lms.service;

import com.lms.model.*;
import com.lms.enums.*;
import com.lms.exception.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class managing leave operations across the organization.
 * Demonstrates: Single Responsibility Principle (SRP).
 *
 * <p>Refactoring: Separated business logic from presentation (Main class).
 * This service handles all leave processing, record-keeping, and queries.</p>
 *
 * @author Golla Raviteja
 */
public class LeaveService {
    private final List<Employee> employees;
    private final List<LeaveRequest> leaveRequests;

    public LeaveService() {
        this.employees = new ArrayList<>();
        this.leaveRequests = new ArrayList<>();
    }

    /**
     * Register an employee in the system.
     */
    public void registerEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("  [REGISTERED] " + employee.getName() + " (" + employee.getEmployeeType() + ")");
    }

    /**
     * Process a leave request with full validation and record-keeping.
     */
    public LeaveRequest processLeaveRequest(Employee employee, int days,
                                             LeaveType type, String reason)
            throws InsufficientLeaveBalanceException, InvalidLeaveOperationException {

        LeaveRequest request = new LeaveRequest(
                employee.getEmployeeId(), employee.getName(), days, type, reason);

        System.out.println("\n  [PROCESSING] " + request);

        employee.applyLeave(days, type, reason);
        request.approve("SYSTEM");
        leaveRequests.add(request);

        return request;
    }

    /**
     * Process a leave cancellation.
     */
    public void processCancellation(Employee employee, int days)
            throws InvalidLeaveOperationException {
        employee.cancelLeave(days);
    }

    /**
     * Get all registered employees.
     */
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    /**
     * Get all leave requests.
     */
    public List<LeaveRequest> getLeaveRequests() {
        return new ArrayList<>(leaveRequests);
    }

    /**
     * Get leave requests for a specific employee.
     */
    public List<LeaveRequest> getLeaveRequestsByEmployee(String employeeId) {
        return leaveRequests.stream()
                .filter(lr -> lr.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    /**
     * Display summary of all employees and their balances.
     */
    public void displayAllEmployees() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              EMPLOYEE LEAVE BALANCE SUMMARY                 ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-8s %-18s %-12s %-6s %-6s ║%n",
                "ID", "Name", "Type", "Used", "Bal");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        for (Employee emp : employees) {
            int used = emp.getMaxLeaveAllowance() - emp.getLeaveBalance();
            System.out.printf("║ %-8s %-18s %-12s %-6d %-6d ║%n",
                    emp.getEmployeeId(), emp.getName(), emp.getEmployeeType(),
                    used, emp.getLeaveBalance());
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    /**
     * Display all leave request history.
     */
    public void displayLeaveHistory() {
        System.out.println("\n=== Leave Request History ===");
        if (leaveRequests.isEmpty()) {
            System.out.println("  No leave requests recorded.");
        } else {
            for (LeaveRequest lr : leaveRequests) {
                System.out.println("  " + lr);
            }
        }
    }
}
