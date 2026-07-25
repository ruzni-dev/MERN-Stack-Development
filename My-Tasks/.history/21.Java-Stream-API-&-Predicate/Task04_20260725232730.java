import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Task04 {

    public static void main(String[] args) {
        List<Employee> employees = createEmployees();

        System.out.println("=== Employee Processing using Stream API & Predicate ===\n");

        performTask01(employees);
        performTask02(employees);
        performTask03(employees);
        performTask04(employees);
        performTask05(employees);
        performTask06(employees);
        performTask07(employees);
        performTask08(employees);
        performTask09(employees);
        performTask10(employees);
    }

    private static List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Amit Sharma", 28, 54000, "IT"));
        employees.add(new Employee(102, "Priya Singh", 34, 76000, "HR"));
        employees.add(new Employee(103, "Rahul Verma", 45, 98000, "IT"));
        employees.add(new Employee(104, "Neha Patel", 32, 62000, "Finance"));
        employees.add(new Employee(105, "Siddharth Rao", 38, 81000, "IT"));
        employees.add(new Employee(106, "Kavita Joshi", 29, 59000, "Marketing"));
        employees.add(new Employee(107, "Vikram Jain", 41, 87000, "Finance"));
        employees.add(new Employee(108, "Anjali Mehta", 27, 72000, "HR"));
        employees.add(new Employee(109, "Manish Kumar", 36, 83000, "IT"));
        employees.add(new Employee(110, "Rina Das", 31, 67000, "Marketing"));
        return employees;
    }

    private static void performTask01(List<Employee> employees) {
        System.out.println("Task 01: Employees with salary greater than ₹60,000");
        Predicate<Employee> salaryGreaterThan60000 = emp -> emp.getSalary() > 60000;
        employees.stream()
                .filter(salaryGreaterThan60000)
                .forEach(emp -> System.out.println("- " + emp));
        System.out.println();
    }

    private static void performTask02(List<Employee> employees) {
        System.out.println("Task 02: Employees in IT department older than 30");
        Predicate<Employee> itDepartment = emp -> "IT".equalsIgnoreCase(emp.getDepartment());
        Predicate<Employee> olderThan30 = emp -> emp.getAge() > 30;
        employees.stream()
                .filter(itDepartment.and(olderThan30))
                .forEach(emp -> System.out.println("- " + emp));
        System.out.println();
    }

    private static void performTask03(List<Employee> employees) {
        System.out.println("Task 03: Count of employees older than 30");
        long count = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .count();
        System.out.println("Total employees older than 30: " + count);
        System.out.println();
    }

    private static void performTask04(List<Employee> employees) {
        System.out.println("Task 04: Highest-paid employee");
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        highestPaid.ifPresentOrElse(
                emp -> System.out.println("Highest paid employee: " + emp),
                () -> System.out.println("No employee data available."));
        System.out.println();
    }

    private static void performTask05(List<Employee> employees) {
        System.out.println("Task 05: Employee names in uppercase");
        employees.stream()
                .map(Employee::getName)
                .map(String::toUpperCase)
                .forEach(name -> System.out.println("- " + name));
        System.out.println();
    }

    private static void performTask06(List<Employee> employees) {
        System.out.println("Task 06: Average salary");
        Optional<Double> averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .stream()
                .findFirst();
        if (averageSalary.isPresent()) {
            System.out.printf("Average salary: ₹%.2f\n", averageSalary.get());
        } else {
            System.out.println("No salary data available to calculate average.");
        }
        System.out.println();
    }

    private static void performTask07(List<Employee> employees) {
        System.out.println("Task 07: Employees grouped by department");
        Map<String, List<Employee>> groupedByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        groupedByDepartment.forEach((department, group) -> {
            System.out.println(department + " Department:");
            group.forEach(emp -> System.out.println("  - " + emp));
        });
        System.out.println();
    }

    private static void performTask08(List<Employee> employees) {
        System.out.println("Task 08: Employees sorted by salary in descending order");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(emp -> System.out.println("- " + emp));
        System.out.println();
    }

    private static void performTask09(List<Employee> employees) {
        System.out.println("Task 09: First employee with salary greater than ₹80,000");
        Optional<Employee> firstHighEarner = employees.stream()
                .filter(emp -> emp.getSalary() > 80000)
                .findFirst();
        firstHighEarner.ifPresentOrElse(
                emp -> System.out.println("First high earner: " + emp),
                () -> System.out.println("No employee found with salary above ₹80,000."));
        System.out.println();
    }

    private static void performTask10(List<Employee> employees) {
        System.out.println("Task 10: Second-highest salary");
        List<Double> distinctSalariesDescending = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        if (distinctSalariesDescending.size() >= 2) {
            System.out.printf("Second-highest salary: ₹%.2f\n", distinctSalariesDescending.get(1));
        } else {
            System.out.println("Cannot determine the second-highest salary with the available data.");
        }
        System.out.println();
    }

    private static class Employee {
        private final int id;
        private final String name;
        private final int age;
        private final double salary;
        private final String department;

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

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %s | Age: %d | Salary: ₹%.2f | Dept: %s",
                    id, name, age, salary, department);
        }
    }
}
