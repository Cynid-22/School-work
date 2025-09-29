import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private List<Student> registeredStudents;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void registerStudent(Student student) {
        this.registeredStudents.add(student);
    }

    public void displayRoster() {
        System.out.println("Student Roster for " + courseCode + " - " + courseName + ":");
        if (registeredStudents.isEmpty()) {
            System.out.println("  No students are registered.");
        } else {
            System.out.println(registeredStudents);
        }
        System.out.println();
    }
}