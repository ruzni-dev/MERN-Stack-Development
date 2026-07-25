import java.util.List;

public class Task09 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 09: First employee with salary > ₹80,000");
        Employee firstHighSalaryEmployee = employees.stream()
                .filter(employee -> employee.getSalary() > 80000)
                .findFirst()
                .orElse(null);

        if (firstHighSalaryEmployee != null) {
            System.out.println(firstHighSalaryEmployee.getName() + " - ₹" + firstHighSalaryEmployee.getSalary());
        }
    }
}
