package de.base.math;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * @see Matrix2d#transform(Tuple2d)
 */
public class Tuple2d implements Tuple<Tuple2d> {

    public double x, y;

    public Tuple2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Tuple2d(@NotNull Tuple2d tuple2d) {
        this.x = tuple2d.x;
        this.y = tuple2d.y;
    }

    public Tuple2d(@NotNull Point2d point2d) {
        this.x = point2d.x;
        this.y = point2d.y;
    }

    public Tuple2d(@NotNull Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Tuple2d(@NotNull Point2D point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public Tuple2d add(@NotNull Point2d point2d) {
        return new Tuple2d(this.x + point2d.x, this.y + point2d.y);
    }

    public Tuple2d add(@NotNull Tuple2d tuple2d) {
        return new Tuple2d(this.x + tuple2d.x, this.y + tuple2d.y);
    }

    public Tuple2d multiply(@NotNull Tuple2d tuple2d) {
        return new Tuple2d(y * tuple2d.y, x * tuple2d.x);
    }

    public Tuple2d multiply(double x, double y) {
        return new Tuple2d(this.y * y, this.x * x);
    }

    public void mul(@NotNull Tuple2d tuple2d) {
        this.x = this.x * tuple2d.x;
        this.y = this.y * tuple2d.y;
    }

    public void mul(double x, double y) {
        this.x = this.x * x;
        this.y = this.y * y;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + '}';
    }
}
