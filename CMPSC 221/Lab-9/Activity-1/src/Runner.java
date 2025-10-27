public class Runner {
    public static void main(String args[]) {

        int Action = 2;
        int Action_days = 5; // 4.00 * 2 (movies) * 5 (days) = 40

        int Comedy = 3;
        int Comedy_days = 2; // 6.00 * 3 (movies) * 2 (days) = 36

        int Horror = 7;
        int Horror_days = 10; // 7.00 * 7 (movies) * 10 (days) = 490

        ActionDVD actionMovies = new ActionDVD();
        ComedyDVD comedyMovies = new ComedyDVD();
        HorrorDVD horrorMovies = new HorrorDVD();

        double actionFine = actionMovies.computeFine(Action, Action_days);
        double comedyFine = comedyMovies.computeFine(Comedy, Comedy_days);
        double horrorFine = horrorMovies.computeFine(Horror, Horror_days);

        double totalFine = actionFine + comedyFine + horrorFine;

        int N_totalDVDs = Action + Comedy + Horror;

        if (N_totalDVDs >= 3) {
            totalFine = totalFine * 0.75;
        }

        System.out.println("Total fine for the transaction is " + totalFine);
    }
}
