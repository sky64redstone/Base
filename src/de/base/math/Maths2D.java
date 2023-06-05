package de.base.math;

public class Maths2D {

    private Maths2D() {
    }

    public static boolean intersectsRect(double x1, double y1, double w1, double h1, double x2, double y2, double w2, double h2) {
        if (w2 <= 0 || h2 <= 0 || w1 <= 0 || h1 <= 0)
            return false;

        double wi2 = w2 + x2;
        double hi2 = h2 + y2;
        double wi1 = w1 + x1;
        double hi1 = h1 + y1;
        //      overflow || intersect
        return ((wi2 < x2 || wi2 > x1) && (hi2 < y2 || hi2 > y1) && (wi1 < x1 || wi1 > x2) && (hi1 < y1 || hi1 > y2));
    }

}
