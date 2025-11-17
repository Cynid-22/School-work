public class WorldCampus implements Students {
    private String name;
    private String email;
    private double grade;

    public WorldCampus(String name, String email, double grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    @Override
    public double Get_grade() {
        return this.grade;
    }

    @Override
    public void Attend_Zoom_Meeting() {
        System.out.println(this.name + " is attending a Zoom meeting online.");
    }

    @Override
    public void Attend_In_Person_Meeting() {
        System.out.println(this.name + " does not attend in-person meetings.");
    }

    public String getName() {
        return name;
    }
}