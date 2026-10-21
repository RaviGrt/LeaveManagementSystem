package com.lms.enums;

/**
 * Enum representing the status of a leave request.
 * Part of refactoring: introducing domain-specific enums.
 *
 * @author Golla Raviteja
 */
public enum LeaveStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled");

    private final String displayName;

    LeaveStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }

    @Override
    public String toString() {
        return displayName;
    }
}
