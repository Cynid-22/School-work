public class Runner {
    public static void main(String[] args) {

        Students inPersonStudent = new InPerson("Khang", "knv@psu.edu", 100.0);
        Students worldCampusStudent = new WorldCampus("Maaz", "mmm@psu.edu", 98.5);

        System.out.println("Student Name: " + inPersonStudent.getName());
        System.out.println("Grade: " + inPersonStudent.Get_grade());
        inPersonStudent.Attend_In_Person_Meeting();
        inPersonStudent.Attend_Zoom_Meeting();

        System.out.println();

        System.out.println("Student Name: " + worldCampusStudent.getName());
        System.out.println("Grade: " + worldCampusStudent.Get_grade());
        worldCampusStudent.Attend_In_Person_Meeting();
        worldCampusStudent.Attend_Zoom_Meeting();
    }
}