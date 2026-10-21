package com.lms.enums;

/**
 * Enum representing different types of leave.
 * Used for method overloading demonstration (compile-time polymorphism).
 *
 * @author Golla Raviteja
 */
public enum LeaveType {
    CASUAL("Casual Leave", "Short-duration personal leave"),
    SICK("Sick Leave", "Medical / health-related leave"),
    PRIVILEGE("Privilege Leave", "Pre-planned vacation / earned leave"),
    UNPAID("Unpaid Leave", "Leave without pay");

    private final String displayName;
    private final String description;

    LeaveType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return displayName;
    }
}
