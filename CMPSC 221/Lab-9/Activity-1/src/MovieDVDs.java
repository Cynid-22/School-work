public class MovieDVDs {
    protected String ID;
    protected String title;
    protected String movieRating;

    public MovieDVDs() {
        ID = "000";
        title = "Default Title";
        movieRating = "Fair";
    }

    public MovieDVDs(String id, String t, String rating) {
        this.ID = id;
        this.title = t;
        this.movieRating = rating;
    }

    public void setValues(String id, String t, String rating) {
        this.ID = id;
        this.title = t;
        this.movieRating = rating;
    }

    public double computeFine(int totalMovie, int days) {
        return 0.0;
    }

    public void display() {
        System.out.println("ID:" + ID + ", Title:" + title + ", Rating:" + movieRating);
    }
}