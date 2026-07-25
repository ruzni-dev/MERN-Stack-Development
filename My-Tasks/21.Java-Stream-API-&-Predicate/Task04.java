import java.util.Comparator;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 04: Highest-paid employee");
        Employee highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        if (highestPaidEmployee != null) {
            System.out.println(highestPaidEmployee.getName() + " - ₹" + highestPaidEmployee.getSalary());
        }
    }
}
