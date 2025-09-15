public class PercentageMarks {
    private float totalSweng311, totalCmpsc411, totalSweng431, totalCmpsc221;
    private float obtainedSweng311, obtainedCmpsc411, obtainedSweng431, obtainedCmpsc221;
    private float totalMarks, obtainedMarks, percentageMarks;

    public PercentageMarks(float t311, float o311,
                           float t411, float o411,
                           float t431, float o431,
                           float t221, float o221) {
        setValues(t311, o311, t411, o411, t431, o431, t221, o221);
    }

    public void setValues(float t311, float o311,
                          float t411, float o411,
                          float t431, float o431,
                          float t221, float o221) {
        this.totalSweng311 = validateTotal(t311);
        this.obtainedSweng311 = validateObtained(o311, t311);

        this.totalCmpsc411 = validateTotal(t411);
        this.obtainedCmpsc411 = validateObtained(o411, t411);

        this.totalSweng431 = validateTotal(t431);
        this.obtainedSweng431 = validateObtained(o431, t431);

        this.totalCmpsc221 = validateTotal(t221);
        this.obtainedCmpsc221 = validateObtained(o221, t221);
    }

    private float validateTotal(float total) {
        if (total < 1 || total > 100) {
            throw new IllegalArgumentException("Total marks must be 1..100");
        }
        return total;
    }

    private float validateObtained(float obtained, float total) {
        if (obtained < 0 || obtained > total) {
            throw new IllegalArgumentException("Obtained marks must be 0..total");
        }
        return obtained;
    }

    public void computePercentage() {
        this.totalMarks = totalSweng311 + totalCmpsc411 + totalSweng431 + totalCmpsc221;
        this.obtainedMarks = obtainedSweng311 + obtainedCmpsc411 + obtainedSweng431 + obtainedCmpsc221;
        this.percentageMarks = (obtainedMarks / totalMarks) * 100;
    }

    public float getPercentage() {
        return percentageMarks;
    }

    public float getSumObtainedMarks() {
        return obtainedMarks;
    }

    public void printRecord() {
        System.out.println("Total: " + totalMarks);
        System.out.println("Obtained: " + obtainedMarks);
        System.out.println("Percentage: " + percentageMarks);
    }
}
