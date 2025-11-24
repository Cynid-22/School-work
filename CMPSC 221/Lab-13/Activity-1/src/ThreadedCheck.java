class ThreadedCheck extends Thread {
    private int value;
    Checker checkerObj;

    ThreadedCheck(int v, Checker obj) {
        value = v;
        checkerObj = obj;
    }

    public void run() {
        synchronized (checkerObj) {
            checkerObj.check(value);
        }
    }
}