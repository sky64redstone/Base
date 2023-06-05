package de.base.math;

import org.jetbrains.annotations.NotNull;

/**
 * @see Matrix4d#transform(Tuple4d)
 */
public class Tuple4d implements Tuple<Tuple4d> {

    public double x;
    public double y;
    public double z;
    public double w;

    public Tuple4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public Tuple4d add(@NotNull Tuple4d tuple4d) {
        return new Tuple4d(this.x + tuple4d.x, this.y + tuple4d.y, this.z + tuple4d.z, this.w + tuple4d.w);
    }

    public Tuple4d multiply(@NotNull Tuple4d tuple4d) {
        return new Tuple4d(this.x * tuple4d.x, this.y * tuple4d.y, this.z * tuple4d.z, this.w * tuple4d.w);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }
}
