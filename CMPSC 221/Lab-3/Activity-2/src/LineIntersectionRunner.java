public class LineIntersectionRunner {
    public static void main(String[] args) {
        // line1: y = 2x + 3
        LineIntersection line1 = new LineIntersection(2, 3);

        // line2: y = -0.5x + 1
        LineIntersection line2 = new LineIntersection(-0.5, 1);

        double[] point = line1.intersection(line2);

        if (point != null) {
            System.out.println("Intersection point: (" + point[0] + ", " + point[1] + ")");
        } else {
            System.out.println("The lines are parallel (no single intersection).");
        }
    }
}
