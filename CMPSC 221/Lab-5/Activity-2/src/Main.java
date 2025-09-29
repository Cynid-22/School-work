public class Main {

    // Helper method to handle the many-to-many relationship
    public static void enroll(Student student, Course course) {
        student.enrollInCourse(course);
        course.registerStudent(student);
    }

    public static void main(String[] args) {
        // 1. Create instances of Students
        Student student1 = new Student("1001", "Alice");
        Student student2 = new Student("1002", "Bob");
        Student student3 = new Student("1003", "Charlie");

        // 2. Create instances of Courses
        Course course1 = new Course("CMPSC221", "OOP with Web-Based Apps");
        Course course2 = new Course("IST210", "Database Management");
        Course course3 = new Course("MATH140", "Calculus I");

        // 3. Enroll students in courses (establishing the many-to-many links)
        enroll(student1, course1); // Alice takes CMPSC221
        enroll(student1, course2); // Alice also takes IST210

        enroll(student2, course1); // Bob takes CMPSC221
        enroll(student2, course3); // Bob also takes MATH140

        enroll(student3, course2); // Charlie takes IST210

        System.out.println("--- Enrollment Details by Student ---");
        student1.displayCourses();
        student2.displayCourses();
        student3.displayCourses();

        System.out.println("--- Course Rosters ---");
        course1.displayRoster();
        course2.displayRoster();
        course3.displayRoster();
    }
}