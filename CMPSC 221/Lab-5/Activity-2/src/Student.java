import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Course> enrolledCourses;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public void enrollInCourse(Course course) {
        this.enrolledCourses.add(course);
    }

    public void displayCourses() {
        System.out.println("Courses for " + studentName + ":");
        if (enrolledCourses.isEmpty()) {
            System.out.println("  Not enrolled in any courses.");
        } else {
            for (Course course : enrolledCourses) {
                System.out.println("  - " + course.getCourseName());
            }
        }
        System.out.println();
    }

    public String toString() {
        return "\n  - Student: " + studentName + " (ID: " + studentId + ")";
    }
}