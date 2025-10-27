public class ComedyDVD extends MovieDVDs {

    private final double lateFeePerDay = 6.0;

    public ComedyDVD() {
        super();
    }

    public ComedyDVD(String id, String t, String rating) {
        super(id, t, rating);
    }

    @Override
    public double computeFine(int totalMovie, int days) {
        return lateFeePerDay * totalMovie * days;
    }
}