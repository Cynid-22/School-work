public class LineIntersection {
    private double m; // slope
    private double b; // y-intercept

    public LineIntersection(double m, double b) {
        this.m = m;
        this.b = b;
    }

    public double getSlope() {
        return m;
    }

    public double getIntercept() {
        return b;
    }

    // compute intersection with another line
    public double[] intersection(LineIntersection other) {
        if (this.m == other.m) {
            return null;
        }
        // solve for x in this equation: m1*x + b1 = m2*x + b2 <=> m1*x - m2*x = b2-b1 <=> x*(m1-m2) = b2-b1 <=> x = (b2-b1)/(m1-m1)
        double x = (other.b - this.b) / (this.m - other.m);
        double y = this.m * x + this.b;
        return new double[]{x, y};
    }
}
