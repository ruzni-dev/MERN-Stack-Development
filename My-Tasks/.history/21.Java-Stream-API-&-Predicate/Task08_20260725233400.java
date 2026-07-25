import java.util.Comparator;
import java.util.List;

public class Task08 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 08: Employees sorted by salary in descending order");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(employee -> System.out.println(employee.getName() + " - ₹" + employee.getSalary()));
    }
}
