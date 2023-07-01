package de.base.math;

public class Math2D {

    private Math2D() {
    }

    public static double sec(double angle) {
        return 1.0d / Math.sin(angle);
    }

    public static double csc(double angle) {
        return 1.0d / Math.cos(angle);
    }

    public static double cot(double angle) {
        return 1.0d / Math.tan(angle);
    }
}
