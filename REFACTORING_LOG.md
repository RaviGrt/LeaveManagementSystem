# Refactoring Log - Leave Management System
## Student: GOLLA RAVITEJA

---

### Refactoring 1: Polymorphic Dispatch over Conditional Logic
**Code Smell:** Type-checking conditional (if/switch on employee type)
**Before:** A single `applyLeave()` method in `Employee` used `if-else` or `switch-case` to determine leave rules based on employee type string.
**After:** Each subclass (`FullTimeEmployee`, `PartTimeEmployee`, `Intern`, `Manager`) overrides `applyLeave()` with its own type-specific validation logic. The runtime dynamically dispatches to the correct implementation.
**Principle Applied:** Polymorphism (Runtime/Dynamic Dispatch), Open/Closed Principle (OCP)
**Benefit:** Adding new employee types requires zero modification to existing classes; just extend `Employee` and override methods.

---

### Refactoring 2: Custom Checked Exceptions over Boolean Returns
**Code Smell:** Boolean return values with `System.out.println` error messages for failure cases
**Before:** Methods like `applyLeave()` returned `boolean` and printed error messages directly. Callers had no structured way to handle different error types.
**After:** Introduced `InsufficientLeaveBalanceException` and `InvalidLeaveOperationException` as custom checked exceptions. Methods now `throw` specific exceptions, forcing callers to handle errors explicitly.
**Principle Applied:** Exception Handling best practices, Fail-Fast principle
**Benefit:** Structured error handling; callers can distinguish between insufficient balance vs. policy violations; cleaner separation of error reporting from business logic.

---

### Refactoring 3: Service Layer Separation (SRP)
**Code Smell:** `Main` class contained both business logic (leave processing, record-keeping) and presentation logic (console I/O, formatting)
**Before:** Leave processing, employee management, and console output were all mixed in `Main.java`.
**After:** Introduced `LeaveService` class to handle business operations (employee registration, leave processing, cancellation, queries). `Main` now only handles user interaction and delegates to `LeaveService`.
**Principle Applied:** Single Responsibility Principle (SRP)
**Benefit:** Business logic is testable independently; presentation can change without affecting core logic; cleaner architecture.

---

### Refactoring 4: Domain-Specific Enums over Raw Strings
**Code Smell:** Leave types and statuses represented as raw `String` values
**Before:** Leave type was passed as a plain `String` (e.g., "casual", "sick"), prone to typos and inconsistencies.
**After:** Introduced `LeaveType` and `LeaveStatus` enums with display names and descriptions.
**Principle Applied:** Type Safety, DRY (Don't Repeat Yourself)
**Benefit:** Compile-time type checking prevents invalid leave types; centralized display names; enum methods provide additional functionality.

---

### Refactoring 5: Encapsulation Enhancement
**Code Smell:** Fields directly accessible or insufficiently validated
**Before:** `leaveBalance` was `public` or had no validation in setters. Constructor parameters were not validated.
**After:** Fields are `private` with validated getters/setters. `leaveBalance` is `protected` for controlled inheritance access. Constructor throws `IllegalArgumentException` for invalid inputs.
**Principle Applied:** Encapsulation, Defensive Programming
**Benefit:** Data integrity guaranteed; impossible to create invalid employee objects; clear access hierarchy via modifiers.
