import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private Office office; // 1-1
    private List<PC> personalComputers; // 1-many

    public Teacher(String name, Office office) {
        this.name = name;
        this.office = office;
        this.personalComputers = new ArrayList<>();
    }

    public void addPC(PC computer) {
        this.personalComputers.add(computer);
    }

    public void displayInfo() {
        System.out.println("------------------------------------");
        System.out.println("Teacher Name: " + this.name);
        System.out.println(this.office);
        System.out.println("Assigned Personal Computers:" + this.personalComputers);
        System.out.println("------------------------------------");
    }
}