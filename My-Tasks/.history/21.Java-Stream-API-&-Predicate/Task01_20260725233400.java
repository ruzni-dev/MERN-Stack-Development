import java.util.List;
import java.util.function.Predicate;

public class Task01 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();
        Predicate<Employee> salaryGreaterThan60000 = employee -> employee.getSalary() > 60000;

        System.out.println("Task 01: Employees with salary > ₹60,000");
        employees.stream()
                .filter(salaryGreaterThan60000)
                .forEach(employee -> System.out.println(employee.getName() + " - ₹" + employee.getSalary()));
    }
}
