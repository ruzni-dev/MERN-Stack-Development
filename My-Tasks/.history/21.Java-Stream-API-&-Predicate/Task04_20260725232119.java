import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Task04 {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Amit", 28, 55000, "HR"),
                new Employee(102, "Sneha", 34, 72000, "IT"),
                new Employee(103, "Rahul", 41, 88000, "Finance"),
                new Employee(104, "Priya", 32, 61000, "IT"),
                new Employee(105, "Sonal", 29, 95000, "Marketing"),
                new Employee(106, "Vikram", 38, 81000, "IT"),
                new Employee(107, "Neha", 45, 53000, "Finance"),
                new Employee(108, "Rohan", 36, 79000, "IT"),
                new Employee(109, "Maya", 31, 66000, "HR"),
                new Employee(110, "Kunal", 27, 47000, "IT")
        );

        System.out.println("=== Employee Processing with Stream API ===\n");

        printHighSalaryEmployees(employees);
        printItEmployeesOlderThan30(employees);
        countEmployeesOlderThan30(employees);
        findHighestPaidEmployee(employees);
        printEmployeeNamesUpperCase(employees);
        calculateAverageSalary(employees);
        groupEmployeesByDepartment(employees);
        sortEmployeesBySalaryDescending(employees);
        findFirstEmployeeWithHighSalary(employees);
        findSecondHighestSalary(employees);
    }

    private static void printHighSalaryEmployees(List<Employee> employees) {
        Predicate<Employee> salaryAbove60k = employee -> employee.getSalary() > 60000;
        System.out.println("Task 01: Employees with salary greater than ₹60,000:");
        employees.stream()
                .filter(salaryAbove60k)
                .forEach(employee -> System.out.println(" - " + employee.getName() + " (₹" + employee.getSalary() + ")"));
        System.out.println();
    }

    private static void printItEmployeesOlderThan30(List<Employee> employees) {
        Predicate<Employee> inItDepartment = employee -> "IT".equalsIgnoreCase(employee.getDepartment());
        Predicate<Employee> olderThan30 = employee -> employee.getAge() > 30;
        System.out.println("Task 02: IT employees older than 30:");
        employees.stream()
                .filter(inItDepartment.and(olderThan30))
                .forEach(employee -> System.out.println(" - " + employee.getName() + " | Age: " + employee.getAge() + " | Salary: ₹" + employee.getSalary()));
        System.out.println();
    }

    private static void countEmployeesOlderThan30(List<Employee> employees) {
        long count = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .count();
        System.out.println("Task 03: Number of employees older than 30: " + count + "\n");
    }

    private static void findHighestPaidEmployee(List<Employee> employees) {
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingInt(Employee::getSalary));
        System.out.println("Task 04: Highest-paid employee:");
        highestPaid.ifPresent(employee -> System.out.println(" - " + employee.getName() + " | Salary: ₹" + employee.getSalary() + " | Department: " + employee.getDepartment()));
        System.out.println();
    }

    private static void printEmployeeNamesUpperCase(List<Employee> employees) {
        System.out.println("Task 05: Employee names in uppercase:");
        employees.stream()
                .map(Employee::getName)
                .map(String::toUpperCase)
                .forEach(name -> System.out.println(" - " + name));
        System.out.println();
    }

    private static void calculateAverageSalary(List<Employee> employees) {
        double averageSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.printf("Task 06: Average salary: ₹%.2f\n\n", averageSalary);
    }

    private static void groupEmployeesByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Task 07: Employees grouped by department:");
        grouped.forEach((department, deptEmployees) -> {
            System.out.println(" - " + department + ":");
            deptEmployees.forEach(employee -> System.out.println("    * " + employee.getName() + " (Age: " + employee.getAge() + ", Salary: ₹" + employee.getSalary() + ")"));
        });
        System.out.println();
    }

    private static void sortEmployeesBySalaryDescending(List<Employee> employees) {
        System.out.println("Task 08: Employees sorted by salary (descending):");
        employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .forEach(employee -> System.out.println(" - " + employee.getName() + " | Salary: ₹" + employee.getSalary()));
        System.out.println();
    }

    private static void findFirstEmployeeWithHighSalary(List<Employee> employees) {
        Optional<Employee> firstHighSalary = employees.stream()
                .filter(employee -> employee.getSalary() > 80000)
                .findFirst();
        System.out.println("Task 09: First employee with salary greater than ₹80,000:");
        firstHighSalary.ifPresentOrElse(
                employee -> System.out.println(" - " + employee.getName() + " | Salary: ₹" + employee.getSalary()),
                () -> System.out.println(" - No employee found with salary greater than ₹80,000")
        );
        System.out.println();
    }

    private static void findSecondHighestSalary(List<Employee> employees) {
        Optional<Integer> secondHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("Task 10: Second-highest salary:");
        secondHighestSalary.ifPresentOrElse(
                salary -> System.out.println(" - ₹" + salary),
                () -> System.out.println(" - Unable to determine second-highest salary")
        );
        System.out.println();
    }

    private static class Employee {
        private final int id;
        private final String name;
        private final int age;
        private final int salary;
        private final String department;

        public Employee(int id, String name, int age, int salary, String department) {
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

        public int getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }
    }
}
