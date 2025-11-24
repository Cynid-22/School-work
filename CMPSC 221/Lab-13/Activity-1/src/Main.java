public class Main {
    public static void main(String args[]) {
        Checker sharedChecker = new Checker();

        ThreadedCheck t1 = new ThreadedCheck(10, sharedChecker);
        ThreadedCheck t2 = new ThreadedCheck(22, sharedChecker);
        ThreadedCheck t3 = new ThreadedCheck(7, sharedChecker);
        ThreadedCheck t4 = new ThreadedCheck(13, sharedChecker);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}