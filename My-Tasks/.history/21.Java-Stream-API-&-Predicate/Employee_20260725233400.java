import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
    private String department;

    public Employee(int id, String name, int age, double salary, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public static List<Employee> getSampleEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice Johnson", 28, 75000, "IT"));
        employees.add(new Employee(102, "Bob Smith", 35, 62000, "HR"));
        employees.add(new Employee(103, "Charlie Brown", 41, 90000, "IT"));
        employees.add(new Employee(104, "Diana Wilson", 29, 58000, "Finance"));
        employees.add(new Employee(105, "Ethan Clark", 33, 82000, "IT"));
        employees.add(new Employee(106, "Fiona Davis", 37, 71000, "Marketing"));
        return employees;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}
