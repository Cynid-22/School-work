public class InPerson implements Students {
    private String name;
    private String email;
    private double grade;

    public InPerson(String name, String email, double grade) {
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
        System.out.println(this.name + " is not attending a Zoom meeting.");
    }

    @Override
    public void Attend_In_Person_Meeting() {
        System.out.println(this.name + " is attending an in-person meeting on campus.");
    }

    public String getName() {
        return name;
    }
}