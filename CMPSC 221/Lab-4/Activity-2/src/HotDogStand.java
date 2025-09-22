public class HotDogStand {
    private final int standID;
    private int hotDogsSold;

    public HotDogStand(int id, int sold) {
        this.standID = id;
        this.hotDogsSold = sold;
    }

    //sold count increase by 1 everytime someone buys a hot dog
    public void justSold() {
        hotDogsSold++;
    }

    public int getStandID() {
        return standID;
    }

    public int getHotDogsSold() {
        return hotDogsSold;
    }
}

