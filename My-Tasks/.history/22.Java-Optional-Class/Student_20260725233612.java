import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(101, "Alice"));
        students.add(new Student(102, "Bob"));
        students.add(new Student(103, "Charlie"));
        return students;
    }

    public static Optional<Student> findStudentById(int id) {
        return getStudents().stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}
