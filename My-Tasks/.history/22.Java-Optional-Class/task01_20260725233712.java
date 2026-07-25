import java.util.Optional;

class Student {
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
}

class StudentService {
    private Student[] students;

    public StudentService() {
        students = new Student[] {
            new Student(1, "Alice"),
            new Student(2, "Bob"),
            new Student(3, "Charlie")
        };
    }

    public Optional<Student> findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}

public class task01 {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();

        Optional<Student> foundStudent = studentService.findStudentById(2);

        if (foundStudent.isPresent()) {
            System.out.println("Student found: " + foundStudent.get().getName());
        } else {
            System.out.println("Student not found");
        }
    }
}
