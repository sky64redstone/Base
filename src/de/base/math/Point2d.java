package de.base.math;

import java.util.Objects;

public class Point2d {

    public double x, y;

    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2d(Point2d point2d) {
        Objects.requireNonNull(point2d);
        this.x = point2d.x;
        this.y = point2d.y;
    }

    public double getDist(Point2d point2d) {
        Objects.requireNonNull(point2d);
        return Math.sqrt(this.x * point2d.x + this.y * point2d.y);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + '}';
    }
}
