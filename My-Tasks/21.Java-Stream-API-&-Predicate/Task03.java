import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 03: Count of employees whose age is greater than 30");
        long countAbove30 = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .count();
        System.out.println("Count: " + countAbove30);
    }
}
