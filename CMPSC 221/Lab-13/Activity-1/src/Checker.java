class Checker {
    public void check(int value) {
        String result = (value % 2 == 0) ? "Even" : "Odd";
        System.out.println("Checking value: " + value);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("Value " + value + " is " + result);
    }
}