import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the temperature (in Celsius): ");
        int temp = input.nextInt();

        if (temp < 0)
            System.out.println("FREEZING");
        else if (temp <= 15)
            System.out.println("COLD");
        else if (temp <= 30)
            System.out.println("WARM");
        else if (temp <= 40)
            System.out.println("HOT");
        else
            System.out.println("VERY HOT");

        input.close();
    }
}