import java.util.Comparator;
import java.util.List;

public class Task10 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 10: Second-highest salary");
        double secondHighestSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0.0);
        System.out.printf("Second Highest Salary: ₹%.2f%n", secondHighestSalary);
    }
}
