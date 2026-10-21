package com.lms.exception;

/**
 * Custom checked exception thrown when an employee attempts to apply
 * for more leave days than their available balance.
 *
 * <p>Refactoring Improvement: Replaces raw System.out.println error messages
 * with proper exception handling, enabling calling code to handle errors
 * gracefully instead of relying on boolean return values.</p>
 *
 * @author Golla Raviteja
 */
public class InsufficientLeaveBalanceException extends Exception {

    private final int requested;
    private final int available;

    /**
     * Constructs an InsufficientLeaveBalanceException.
     *
     * @param employeeName Name of the employee
     * @param requested    Number of days requested
     * @param available    Number of days available
     */
    public InsufficientLeaveBalanceException(String employeeName, int requested, int available) {
        super(String.format("%s: Insufficient leave balance! Requested: %d days, Available: %d days.",
                employeeName, requested, available));
        this.requested = requested;
        this.available = available;
    }

    public int getRequested() { return requested; }
    public int getAvailable() { return available; }
}
