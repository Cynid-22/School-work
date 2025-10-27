public class HorrorDVD extends MovieDVDs {

    private final double lateFeePerDay = 7.0;

    public HorrorDVD() {
        super();
    }

    public HorrorDVD(String id, String t, String rating) {
        super(id, t, rating);
    }

    @Override
    public double computeFine(int totalMovie, int days) {
        return lateFeePerDay * totalMovie * days;
    }
}