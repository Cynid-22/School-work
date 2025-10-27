public class Runner {
    public static void main(String[] args) {
        Rooms rooms = new Rooms(10, 50);

        Rooms.ClassRoom cr = rooms.new ClassRoom(2);
        Rooms.Labs lab = rooms.new Labs(30);
        Rooms.ConferenceRoom conf = rooms.new ConferenceRoom(1);

        System.out.println("Room Tables: " + rooms.getNoOfTables());
        System.out.println("Room Chairs: " + rooms.NoOfChairs);
        System.out.println("ClassRoom Whiteboards: " + cr.getNoOfWhiteBoards());
        System.out.println("Lab Computers: " + lab.getNoOfComputers());
        System.out.println("Conference Projectors: " + conf.getNoOfProjectors());

        rooms.setRooms(12, 60);
        cr.setNoOfWhiteBoards(3);
        lab.setNoOfComputers(35);
        conf.setNoOfProjectors(2);

        System.out.println("\nafter inputing the data");
        System.out.println("Room Tables: " + rooms.getNoOfTables());
        System.out.println("Room Chairs: " + rooms.NoOfChairs);
        System.out.println("ClassRoom Whiteboards: " + cr.getNoOfWhiteBoards());
        System.out.println("Lab Computers: " + lab.getNoOfComputers());
        System.out.println("Conference Projectors: " + conf.getNoOfProjectors());
    }
}