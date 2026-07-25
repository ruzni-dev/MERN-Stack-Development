public class Employee {
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

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: ₹" + salary + " | Department: " + department;
    }
}
