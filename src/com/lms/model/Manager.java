package com.lms.model;

import com.lms.enums.LeaveType;

/**
 * Manager employee type with executive leave privileges.
 * Demonstrates: Change Request integration, Open/Closed Principle (OCP).
 *
 * <p>This class was added as a "Change Request" without modifying existing classes,
 * demonstrating the Open/Closed Principle - open for extension, closed for modification.</p>
 *
 * <p>Leave Rules:
 * <ul>
 *   <li>Executive balance: 35 days/year (standard + bonus executive leaves)</li>
 *   <li>Can carry over unused leaves</li>
 *   <li>Additional capability: approve/reject team member leave requests</li>
 *   <li>Bonus: 5 executive days for leadership duties</li>
 * </ul></p>
 *
 * @author Golla Raviteja
 */
public class Manager extends FullTimeEmployee {

    private static final int MANAGER_LEAVE_BALANCE = 30;
    private static final int EXECUTIVE_BONUS_DAYS = 5;
    private int executiveBonusDays;
    private int teamSize;

    /**
     * Constructs a Manager with executive leave entitlements.
     *
     * @param employeeId  Unique manager identifier
     * @param name        Full name
     * @param department  Department managed
     * @param teamSize    Number of direct reports
     */
    public Manager(String employeeId, String name, String department, int teamSize) {
        super(employeeId, name, department, MANAGER_LEAVE_BALANCE + EXECUTIVE_BONUS_DAYS);
        this.executiveBonusDays = EXECUTIVE_BONUS_DAYS;
        this.teamSize = teamSize;
    }

    public int getExecutiveBonusDays() { return executiveBonusDays; }
    public int getTeamSize() { return teamSize; }

    /**
     * Manager-specific leave application with executive privileges.
     * Overrides FullTimeEmployee's applyLeave (Runtime Polymorphism).
     */
    @Override
    public boolean applyLeave(int days) {
        if (days <= 0) {
            System.out.println("  [ERROR] Leave days must be positive.");
            return false;
        }
        if (days > leaveBalance) {
            System.out.println("  [DENIED] " + getName() + " (Manager): Insufficient balance! Available: "
                    + leaveBalance + ", Requested: " + days);
            return false;
        }
        leaveBalance -= days;
        System.out.println("  [APPROVED] " + getName() + " (Manager/Executive): " + days
                + " day(s) approved. Remaining: " + leaveBalance);
        return true;
    }

    /**
     * UNIQUE MANAGER CAPABILITY: Approve team member's leave request.
     * This method demonstrates additional role-specific behavior.
     *
     * @param employee  Team member requesting leave
     * @param days      Number of days to approve
     * @return true if the team member's leave was approved
     */
    public boolean approveTeamLeave(Employee employee, int days) {
        System.out.println("\n  [MANAGER ACTION] " + getName() + " reviewing leave request:");
        System.out.println("  → Employee: " + employee.getName() + " (" + employee.getEmployeeType() + ")");
        System.out.println("  → Days Requested: " + days);
        System.out.println("  → Current Balance: " + employee.getLeaveBalance());

        if (days > employee.getLeaveBalance()) {
            System.out.println("  [REJECTED] Insufficient leave balance for " + employee.getName());
            return false;
        }

        boolean approved = employee.applyLeave(days);
        if (approved) {
            System.out.println("  [MANAGER APPROVED] " + getName() + " approved "
                    + days + " day(s) for " + employee.getName());
        }
        return approved;
    }

    @Override
    public String getEmployeeType() {
        return "Manager";
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("  Executive Bonus: " + executiveBonusDays + " days");
        System.out.println("  Team Size: " + teamSize + " direct reports");
    }
}
