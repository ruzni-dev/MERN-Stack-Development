import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task07 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 07: Employees grouped by department");
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        employeesByDepartment.forEach((department, employeeList) -> {
            System.out.println("Department: " + department);
            employeeList.forEach(employee -> System.out.println("  - " + employee.getName()));
        });
    }
}
