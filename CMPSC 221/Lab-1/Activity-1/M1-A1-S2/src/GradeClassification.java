public class GradeClassification {
    public String letterGrade(int marks) {
        if (marks < 50) {
            return "F";
        } else if (marks <= 60) {
            return "E";
        } else if (marks <= 70) {
            return "D";
        } else if (marks <= 80) {
            return "C";
        } else if (marks <= 90) {
            return "B";
        } else if (marks <= 100) {
            return "A";
        } else {
            return "Invalid marks";
        }
    }
}
