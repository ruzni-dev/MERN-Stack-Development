import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getSampleEmployees();

        System.out.println("Task 05: Employee names in uppercase");
        employees.stream()
                .map(employee -> employee.getName().toUpperCase())
                .forEach(System.out::println);
    }
}
