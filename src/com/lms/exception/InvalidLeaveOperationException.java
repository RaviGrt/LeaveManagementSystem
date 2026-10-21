package com.lms.exception;

/**
 * Custom checked exception thrown when an invalid leave operation is attempted.
 * Examples: negative days, exceeding consecutive limit, invalid cancellation.
 *
 * <p>Refactoring Improvement: Provides structured error reporting for
 * domain-specific leave policy violations.</p>
 *
 * @author Golla Raviteja
 */
public class InvalidLeaveOperationException extends Exception {

    private final String operation;

    /**
     * Constructs an InvalidLeaveOperationException.
     *
     * @param message   Detailed error message
     * @param operation The operation that was attempted (e.g., "APPLY", "CANCEL")
     */
    public InvalidLeaveOperationException(String message, String operation) {
        super(message);
        this.operation = operation;
    }

    public InvalidLeaveOperationException(String message) {
        this(message, "UNKNOWN");
    }

    public String getOperation() { return operation; }
}
