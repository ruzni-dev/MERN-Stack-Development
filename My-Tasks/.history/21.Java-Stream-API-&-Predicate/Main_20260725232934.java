import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice Johnson", 28, 75000, "IT"));
        employees.add(new Employee(102, "Bob Smith", 35, 62000, "HR"));
        employees.add(new Employee(103, "Charlie Brown", 41, 90000, "IT"));
        employees.add(new Employee(104, "Diana Wilson", 29, 58000, "Finance"));
        employees.add(new Employee(105, "Ethan Clark", 33, 82000, "IT"));
        employees.add(new Employee(106, "Fiona Davis", 37, 71000, "Marketing"));

        Predicate<Employee> salaryGreaterThan60000 = employee -> employee.getSalary() > 60000;
        Predicate<Employee> itDepartmentAndOlderThan30 = employee ->
                "IT".equalsIgnoreCase(employee.getDepartment()) && employee.getAge() > 30;

        System.out.println("================ Employee Processing ================");

        System.out.println("\nTask 01: Employees with salary > ₹60,000");
        employees.stream()
                .filter(salaryGreaterThan60000)
                .forEach(employee -> System.out.println(employee.getName() + " - ₹" + employee.getSalary()));

        System.out.println("\nTask 02: Employees from IT department and older than 30");
        employees.stream()
                .filter(itDepartmentAndOlderThan30)
                .forEach(employee -> System.out.println(employee.getName() + " - Age: " + employee.getAge()));

        System.out.println("\nTask 03: Count of employees whose age is greater than 30");
        long countAbove30 = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .count();
        System.out.println("Count: " + countAbove30);

        System.out.println("\nTask 04: Highest-paid employee");
        Employee highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        if (highestPaidEmployee != null) {
            System.out.println(highestPaidEmployee.getName() + " - ₹" + highestPaidEmployee.getSalary());
        }

        System.out.println("\nTask 05: Employee names in uppercase");
        employees.stream()
                .map(employee -> employee.getName().toUpperCase())
                .forEach(System.out::println);

        System.out.println("\nTask 06: Average salary");
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.printf("Average Salary: ₹%.2f%n", averageSalary);

        System.out.println("\nTask 07: Employees grouped by department");
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        employeesByDepartment.forEach((department, employeeList) -> {
            System.out.println("Department: " + department);
            employeeList.forEach(employee -> System.out.println("  - " + employee.getName()));
        });

        System.out.println("\nTask 08: Employees sorted by salary in descending order");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(employee -> System.out.println(employee.getName() + " - ₹" + employee.getSalary()));

        System.out.println("\nTask 09: First employee with salary > ₹80,000");
        Employee firstHighSalaryEmployee = employees.stream()
                .filter(employee -> employee.getSalary() > 80000)
                .findFirst()
                .orElse(null);
        if (firstHighSalaryEmployee != null) {
            System.out.println(firstHighSalaryEmployee.getName() + " - ₹" + firstHighSalaryEmployee.getSalary());
        }

        System.out.println("\nTask 10: Second-highest salary");
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

class Employee {
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
