public class WorldCampus implements Student, OnlineMeeting {
    private String name;
    private String email;

    public WorldCampus(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void Get_grade() {
        System.out.println(this.name + " (World Campus) is checking grades.");
    }

    @Override
    public void Attend_Zoom_Meeting() {
        System.out.println(this.name + " (World Campus) is attending a Zoom meeting.");
    }
}