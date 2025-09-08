public class Accountant {
    // Fields
    private String name;
    private int age;
    private String companyName;

    // Constructor
    public Accountant(String n, int a, String c) {
        this.name = n;
        this.age = a;
        this.companyName = c;
    }

    // Method to calculate and display salary
    public void displaySalary() {
        double salary = 40000 * Math.sqrt(Math.exp(0.04295 * age + 0.141));
        System.out.printf("Salary: $%.2f%n", salary);
    }

    // Method to display accountant's information
    public void displayInfo() {
        System.out.printf("Name: %s, Age: %d, Company Name: %s%n", name, age, companyName);
    }
}
