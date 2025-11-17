public class InPerson implements Student, InPersonMeeting {
    private String name;
    private String email;

    public InPerson(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void Get_grade() {
        System.out.println(this.name + " (In-Person) is checking grades.");
    }

    @Override
    public void Attend_In_Person_Meeting() {
        System.out.println(this.name + " (In-Person) is attending an in-person meeting.");
    }
}