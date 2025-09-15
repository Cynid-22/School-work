import java.util.Scanner;

public class PercentageMarksRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter marks for Student 1:");
        PercentageMarks student1 = readStudent(input);

        System.out.println("\nEnter marks for Student 2:");
        PercentageMarks student2 = readStudent(input);

        student1.computePercentage();
        student2.computePercentage();

        System.out.println("\nStudent 1:");
        student1.printRecord();
        System.out.println("Sum of obtained marks: " + student1.getSumObtainedMarks());

        System.out.println("\nStudent 2:");
        student2.printRecord();
        System.out.println("Sum of obtained marks: " + student2.getSumObtainedMarks());

        comparePercentages(student1, student2);
    }

    private static PercentageMarks readStudent(Scanner input) {
        System.out.print("Total marks SWENG-311: ");
        float t311 = input.nextFloat();
        System.out.print("Obtained marks SWENG-311: ");
        float o311 = input.nextFloat();

        System.out.print("Total marks CMPSC-411: ");
        float t411 = input.nextFloat();
        System.out.print("Obtained marks CMPSC-411: ");
        float o411 = input.nextFloat();

        System.out.print("Total marks SWENG-431: ");
        float t431 = input.nextFloat();
        System.out.print("Obtained marks SWENG-431: ");
        float o431 = input.nextFloat();

        System.out.print("Total marks CMPSC-221: ");
        float t221 = input.nextFloat();
        System.out.print("Obtained marks CMPSC-221: ");
        float o221 = input.nextFloat();

        return new PercentageMarks(t311, o311, t411, o411, t431, o431, t221, o221);
    }

    private static void comparePercentages(PercentageMarks s1, PercentageMarks s2) {
        if (s1.getPercentage() > s2.getPercentage()) {
            System.out.println("\nStudent 1 has the higher percentage: " + s1.getPercentage());
        } else if (s2.getPercentage() > s1.getPercentage()) {
            System.out.println("\nStudent 2 has the higher percentage: " + s2.getPercentage());
        } else {
            System.out.println("\nBoth students have the same percentage: " + s1.getPercentage());
        }
    }
}
