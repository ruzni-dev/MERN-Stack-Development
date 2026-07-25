import java.util.Optional;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

class EmployeeService {
    private Employee[] employees;

    public EmployeeService() {
        employees = new Employee[] {
            new Employee(101, "David", 50000),
            new Employee(102, "Emma", 70000),
            new Employee(103, "Frank", 90000)
        };
    }

    public Optional<Employee> findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
}

public class task02 {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        Employee defaultEmployee = new Employee(0, "No Employee Found", 0.0);

        Employee employee = employeeService.findEmployee(105)
                .orElse(defaultEmployee);

        System.out.println("Employee found: " + employee.getName());
        System.out.println("Salary: " + employee.getSalary());
    }
}
