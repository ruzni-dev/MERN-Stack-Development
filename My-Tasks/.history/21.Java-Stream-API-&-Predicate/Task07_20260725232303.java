import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task07 {

    public static void main(String[] args) {
        List<Employee> employees = getEmployees();
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("Task 07: Employees grouped by department:");
        employeesByDepartment.forEach((department, group) -> {
            System.out.println(" - " + department + ":");
            group.forEach(employee -> System.out.println("    * " + employee));
        });
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
