# Leave Management System (Java OOP)

**Student Name:** Golla Raviteja  
**Batch:** CAIT / NMIT LMS (SD_B26_Batch_4)  
**Month:** Month 2 Assignment  
**Topic:** Object-Oriented Programming in Core Java  

---

## About the Project

This is a console-based Leave Management System built using Core Java. The goal of this project is to model how workplace leave policies work in a real company, while applying all key Object-Oriented Programming (OOP) concepts step-by-step.

Instead of writing everything inside a single `Main` class with big `if-else` blocks, the system is designed with a proper class hierarchy, custom checked exceptions for error handling, and a dedicated service layer that keeps business logic separate from the console output.

---

## Key OOP Concepts Used

Here is how each OOP concept is actually applied in the codebase:

1. **Encapsulation:**
   - Fields in `Employee` (like `employeeId`, `name`, `department`, `leaveBalance`) are kept `private` and `protected`.
   - All access happens through getters and setters with validation (e.g., preventing negative balance or blank names).

2. **Abstraction:**
   - `Employee` is an `abstract` base class. It defines common attributes and shared behavior (`cancelLeave`, `displayDetails`), while forcing subclasses to provide their own implementation of `applyLeave(int days)`.

3. **Inheritance:**
   - `FullTimeEmployee`, `PartTimeEmployee`, and `Intern` directly extend `Employee`.
   - `Manager` extends `FullTimeEmployee`, gaining all full-time benefits plus extra managerial capabilities (like executive bonus days and team approval).

4. **Polymorphism:**
   - **Runtime (Dynamic Dispatch):** Storing different employee types inside a generic `List<Employee>` and calling `.applyLeave()` on them. Java automatically calls the right subclass version at runtime.
   - **Compile-time (Method Overloading):** The `Employee` class provides two versions of `applyLeave`:
     - `applyLeave(int days)` for quick requests.
     - `applyLeave(int days, LeaveType type, String reason)` for detailed requests.

5. **Custom Exception Handling:**
   - Instead of returning simple `true`/`false` boolean flags or printing error messages directly in calculation methods, two custom checked exceptions are used:
     - `InsufficientLeaveBalanceException`: Thrown when an employee requests more days than they have available.
     - `InvalidLeaveOperationException`: Thrown when someone enters invalid values (negative days, exceeding consecutive day limits, or trying to cancel more days than allowed).

6. **Single Responsibility Principle (SRP) / Service Layer:**
   - `LeaveService.java` manages employee records, processes requests, and tracks leave history.
   - `Main.java` only acts as the driver / entry point to demonstrate all scenarios.

---

## Employee Types & Leave Rules

| Employee Type | Annual Balance | Special Rules |
| :--- | :---: | :--- |
| **Full-Time** | 25 days | Can carry over unused leaves to the next year. |
| **Part-Time** | 10 days | Cannot take more than 5 consecutive days per request; no carry-over. |
| **Intern** | 5 days | All leaves are marked as unpaid; requires mentor sign-off. |
| **Manager** | 35 days | Base 30 days + 5 executive bonus days; can review and approve team leaves. |

---

## Leave Types & Status

- **Leave Types:** `CASUAL`, `SICK`, `PRIVILEGE`, `UNPAID`
- **Statuses:** `PENDING`, `APPROVED`, `REJECTED`, `CANCELLED`

---

## Project Structure

```
LeaveManagementSystem/
├── src/
│   └── com/
│       └── lms/
│           ├── Main.java                          # Driver program demonstrating test cases
│           ├── model/
│           │   ├── Employee.java                  # Abstract base class
│           │   ├── FullTimeEmployee.java          # Full-time logic & carry-over
│           │   ├── PartTimeEmployee.java          # Part-time logic & 5-day cap
│           │   ├── Intern.java                    # Intern logic & mentor details
│           │   ├── Manager.java                   # Manager logic & team approvals
│           │   └── LeaveRequest.java              # Request object tracking transactions
│           ├── enums/
│           │   ├── LeaveType.java                 # Enum for leave categories
│           │   └── LeaveStatus.java               # Enum for request lifecycle
│           ├── exception/
│           │   ├── InsufficientLeaveBalanceException.java
│           │   └── InvalidLeaveOperationException.java
│           └── service/
│               └── LeaveService.java              # Central business logic service
├── GOLLA RAVITEJA.pdf                             # Full project report
└── README.md                                      # Documentation
```

---

## How to Compile and Run

Make sure you are in the project root directory (`LeaveManagementSystem`):

### 1. Compile the code:
```bash
# Create bin folder if it doesn't exist
mkdir -p bin

# Compile all packages
javac -d bin src/com/lms/enums/*.java src/com/lms/exception/*.java src/com/lms/model/*.java src/com/lms/service/*.java src/com/lms/Main.java
```

### 2. Run the program:
```bash
java -cp bin com.lms.Main
```

---

## Sample Test Scenarios in `Main.java`

When you run `Main.java`, it tests the following test cases in sequence:
1. **Full-time leave deduction** (25 - 3 = 22 days).
2. **Part-time valid leave** (within the 5-day consecutive limit).
3. **Intern leave application** (notifying mentor and logging unpaid status).
4. **Manager leave application** (checking bonus balance).
5. **Method overloading** (submitting leave with `LeaveType.SICK` and reason).
6. **Leave cancellation** (restoring leave balance).
7. **Negative days exception check** (catches `InvalidLeaveOperationException`).
8. **Exceeding balance check** (catches `InsufficientLeaveBalanceException`).
9. **Part-time consecutive limit breach** (> 5 days throws `InvalidLeaveOperationException`).
10. **Manager approving a team member's leave request**.
