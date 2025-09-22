public class HotDogStandRunner {
    public static void main(String[] args) {
        HotDogStand stand1 = new HotDogStand(101, 0);
        HotDogStand stand2 = new HotDogStand(102, 5);
        HotDogStand stand3 = new HotDogStand(103, 2);

        stand1.justSold();
        stand1.justSold();

        stand2.justSold();
        stand2.justSold();
        stand2.justSold();

        stand3.justSold();

        System.out.println("Stand " + stand1.getStandID() + " sold: " + stand1.getHotDogsSold() + " hot dogs.");
        System.out.println("Stand " + stand2.getStandID() + " sold: " + stand2.getHotDogsSold() + " hot dogs.");
        System.out.println("Stand " + stand3.getStandID() + " sold: " + stand3.getHotDogsSold() + " hot dogs.");
    }
}