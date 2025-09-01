import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student score (1-100): ");
        int score = input.nextInt();

        GradeClassification grade = new GradeClassification();
        System.out.println("Grade: " + grade.letterGrade(score));

        input.close();
    }
}