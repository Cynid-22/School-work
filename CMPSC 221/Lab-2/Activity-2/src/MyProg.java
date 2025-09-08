import java.util.Scanner;

public class MyProg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter company name: ");
        String company = scanner.nextLine();

        // Instantiate Accountant
        Accountant acc = new Accountant(name, age, company);

        // Call methods
        acc.displaySalary();
        acc.displayInfo();

        scanner.close();
    }
}