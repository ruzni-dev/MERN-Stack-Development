import java.util.Arrays;
import java.util.List;

public class task03 {
    public static void main(String[] args) {
        List<Employee> employees = getEmployees();
        long count = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .count();

        System.out.println("Task 03: Number of employees older than 30: " + count);
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
