public class Main {
    public static void main(String[] args) {

        System.out.println("Creating In-Person student:");
        InPerson student1 = new InPerson("Khang", "knv@psu.edu");
        student1.Get_grade();
        student1.Attend_In_Person_Meeting();


        System.out.println("-----------------------------------");

        System.out.println("Creating World Campus student:");
        WorldCampus student2 = new WorldCampus("Maaz", "mmm@psu.edu");
        student2.Get_grade();
        student2.Attend_Zoom_Meeting();
    }
}