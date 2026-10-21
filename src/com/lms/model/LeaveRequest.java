package com.lms.model;

import com.lms.enums.LeaveType;
import com.lms.enums.LeaveStatus;
import java.time.LocalDate;

/**
 * Domain class representing a leave request.
 * Refactoring: separating leave request data from employee processing.
 * Demonstrates: Single Responsibility Principle (SRP).
 *
 * @author Golla Raviteja
 */
public class LeaveRequest {
    private static int idCounter = 1;

    private final String requestId;
    private final String employeeId;
    private final String employeeName;
    private final int days;
    private final LeaveType leaveType;
    private final String reason;
    private final LocalDate requestDate;
    private LeaveStatus status;
    private String approvedBy;

    /**
     * Constructs a new LeaveRequest.
     */
    public LeaveRequest(String employeeId, String employeeName, int days,
                        LeaveType leaveType, String reason) {
        this.requestId = "LR" + String.format("%04d", idCounter++);
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.days = days;
        this.leaveType = leaveType;
        this.reason = reason;
        this.requestDate = LocalDate.now();
        this.status = LeaveStatus.PENDING;
    }

    // Getters
    public String getRequestId() { return requestId; }
    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public int getDays() { return days; }
    public LeaveType getLeaveType() { return leaveType; }
    public String getReason() { return reason; }
    public LocalDate getRequestDate() { return requestDate; }
    public LeaveStatus getStatus() { return status; }
    public String getApprovedBy() { return approvedBy; }

    // Status management
    public void approve(String approverName) {
        this.status = LeaveStatus.APPROVED;
        this.approvedBy = approverName;
    }

    public void reject(String approverName) {
        this.status = LeaveStatus.REJECTED;
        this.approvedBy = approverName;
    }

    public void cancel() {
        this.status = LeaveStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %d day(s) | %s | %s | Reason: %s",
                requestId, employeeName, leaveType, days, status,
                requestDate, reason);
    }
}
