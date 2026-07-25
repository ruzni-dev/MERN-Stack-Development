import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class task02 {
    public static void main(String[] args) {
        List<Employee> employees = getEmployees();
        Predicate<Employee> itDepartment = employee -> "IT".equalsIgnoreCase(employee.getDepartment());
        Predicate<Employee> olderThan30 = employee -> employee.getAge() > 30;

        System.out.println("Task 02: IT employees older than 30:");
        employees.stream()
                .filter(itDepartment.and(olderThan30))
                .forEach(employee -> System.out.println(" - " + employee.getName() + " | Age: " + employee.getAge() + " | Department: " + employee.getDepartment()));
    }

    private static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(101, "Amit", 28, 55000, "HR"),
                new Employee(102, "Sneha", 34, 72000, "IT"),
                new Employee(103, "Rahul", 41, 88000, "Finance"),
                new Employee(104, "Priya", 32, 61000, "IT"),
                new Employee(105, "Sonal", 29, 95000, "Marketing"),
                new Employee(106, "Vikram", 38, 81000, "IT"),
                new Employee(107, "Neha", 45, 53000, "Finance"),
                new Employee(108, "Rohan", 36, 79000, "IT"),
                new Employee(109, "Maya", 31, 66000, "HR"),
                new Employee(110, "Kunal", 27, 47000, "IT")
        );
    }
}
