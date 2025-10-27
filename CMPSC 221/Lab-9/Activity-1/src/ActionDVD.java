public class ActionDVD extends MovieDVDs {

    private final double lateFeePerDay = 4.0;

    public ActionDVD() {
        super();
    }

    public ActionDVD(String id, String t, String rating) {
        super(id, t, rating);
    }

    @Override
    public double computeFine(int totalMovie, int days) {
        return lateFeePerDay * totalMovie * days;
    }
}