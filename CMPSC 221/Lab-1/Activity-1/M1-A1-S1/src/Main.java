import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student score (1-100): ");
        int score = input.nextInt();

        if (score < 50)
            System.out.println("Grade: F");
        else if (score <= 60)
            System.out.println("Grade: E");
        else if (score <= 70)
            System.out.println("Grade: D");
        else if (score <= 80)
            System.out.println("Grade: D");
        else if (score <= 90)
            System.out.println("Grade: B");
        else if (score <= 100)
            System.out.println("Grade: A");
        else
            System.out.println("Invalid score");
        input.close();
    }
}