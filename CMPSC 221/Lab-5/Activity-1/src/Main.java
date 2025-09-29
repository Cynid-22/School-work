public class Main {
    public static void main(String[] args) {
        Office officeA = new Office("Metzgar Center", "201A");
        Office officeB = new Office("Burke Center", "150");

        Teacher teacher1 = new Teacher("Dr. Johnson", officeA);
        Teacher teacher2 = new Teacher("Prof. Davis", officeB);

        PC pc1 = new PC("PSU-001", "Dell OptiPlex");
        PC pc2 = new PC("PSU-002", "HP EliteBook");
        PC pc3 = new PC("PSU-003", "Lenovo ThinkPad");

        teacher1.addPC(pc1);

        teacher2.addPC(pc2);
        teacher2.addPC(pc3);

        teacher1.displayInfo();
        teacher2.displayInfo();
    }
}