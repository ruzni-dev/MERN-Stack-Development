import java.util.List;
import java.util.function.Predicate;

public class Task02 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();
        Predicate<Employee> itDepartmentAndOlderThan30 = employee ->
                "IT".equalsIgnoreCase(employee.getDepartment()) && employee.getAge() > 30;

        System.out.println("Task 02: Employees from IT department and older than 30");
        employees.stream()
                .filter(itDepartmentAndOlderThan30)
                .forEach(employee -> System.out.println(employee.getName() + " - Age: " + employee.getAge()));
    }
}
