# Leave Management System

## Student Information
- **Name:** GOLLA RAVITEJA
- **Course:** CAIT / NMIT LMS (SD_B26_Batch_4)
- **Assignment:** Build & Refactor a Leave Management System

---

## Objective
Develop a production-grade Java Leave Management System applying core Object-Oriented Programming (OOP) principles progressively, with refactoring for maintainability.

## Architecture Overview

### Package Structure
```
src/
└── com/
    └── lms/
        ├── Main.java                          # Driver / Demonstration class
        ├── model/
        │   ├── Employee.java                  # Abstract base class
        │   ├── FullTimeEmployee.java          # 25 days/year, carry-over
        │   ├── PartTimeEmployee.java          # 10 days/year, 5-day limit
        │   ├── Intern.java                    # 5 days/year, unpaid
        │   ├── Manager.java                   # 35 days/year, team approval
        │   └── LeaveRequest.java              # Leave request domain object
        ├── enums/
        │   ├── LeaveType.java                 # CASUAL, SICK, PRIVILEGE, UNPAID
        │   └── LeaveStatus.java               # PENDING, APPROVED, REJECTED, CANCELLED
        ├── exception/
        │   ├── InsufficientLeaveBalanceException.java
        │   └── InvalidLeaveOperationException.java
        └── service/
            └── LeaveService.java              # Business logic service layer
```

### Class Hierarchy
```
Employee (abstract)
├── FullTimeEmployee
│   └── Manager
├── PartTimeEmployee
└── Intern
```

## OOP Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| **Encapsulation** | Private fields with validated getters/setters in Employee |
| **Abstraction** | Abstract Employee class with abstract applyLeave() |
| **Inheritance** | FullTimeEmployee, PartTimeEmployee, Intern extend Employee; Manager extends FullTimeEmployee |
| **Polymorphism (Runtime)** | List<Employee> processes different types via overridden applyLeave() |
| **Polymorphism (Compile-time)** | Overloaded applyLeave(int) vs applyLeave(int, LeaveType, String) |
| **Access Modifiers** | private, protected, public used appropriately |
| **Custom Exceptions** | InsufficientLeaveBalanceException, InvalidLeaveOperationException |
| **SRP** | LeaveService separates business logic from Main presentation |
| **OCP** | Manager added without modifying existing classes |

## Leave Rules by Employee Type

| Type | Balance | Special Rules |
|------|---------|---------------|
| Full-Time | 25 days | Can carry over unused leaves |
| Part-Time | 10 days | Max 5 consecutive days per request |
| Intern | 5 days | All leaves unpaid, mentor sign-off required |
| Manager | 35 days | Includes 5 executive bonus days, can approve team leave |

## How to Compile and Run

```bash
# Navigate to project root
cd LeaveManagementSystem

# Compile all Java files
javac -d bin src/com/lms/enums/*.java src/com/lms/exception/*.java src/com/lms/model/*.java src/com/lms/service/*.java src/com/lms/Main.java

# Run the application
java -cp bin com.lms.Main
```

## Git Commit History
1. `Initial employee design` - Project setup and class diagram
2. `Added Employee class` - Basic Employee with fields and display
3. `Implemented leave management` - applyLeave(), cancelLeave() methods
4. `Added inheritance` - Abstract Employee, subclasses (FullTime, PartTime, Intern)
5. `Implemented polymorphism` - Runtime (List<Employee>) + Compile-time (overloading)
6. `Added Manager` - Change Request: Manager with executive perks
7. `Refactored code` - Custom exceptions, service layer, enums, SRP
8. `Final version` - Complete demo, documentation, polished code

## Files
- `src/` - Java source code
- `README.md` - This file
- `REFACTORING_LOG.md` - Detailed refactoring documentation
- `docs/CLASS_DIAGRAM.txt` - ASCII class diagram
- `docs/CLASS_DIAGRAM_MERMAID.md` - Mermaid UML class diagram
