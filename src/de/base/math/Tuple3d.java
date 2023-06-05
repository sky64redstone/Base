package de.base.math;

import org.jetbrains.annotations.NotNull;

/**
 * @see Matrix3d#transform(Tuple3d)
 */
public class Tuple3d implements Tuple<Tuple3d> {

    public double x, y, z;

    public Tuple3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Tuple3d(@NotNull Tuple3d tuple3d) {
        this.x = tuple3d.x;
        this.y = tuple3d.y;
        this.z = tuple3d.z;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public Tuple3d add(@NotNull Tuple3d tuple3d) {
        return new Tuple3d(this.x + tuple3d.x, this.y + tuple3d.y, this.z + tuple3d.z);
    }

    public Tuple3d add(@NotNull Point3d point3d) {
        return new Tuple3d(this.x + point3d.x, this.y + point3d.y, this.z + point3d.z);
    }

    public Tuple3d multiply(@NotNull Tuple3d tuple3d) {
        return new Tuple3d(this.x * tuple3d.x, this.y * tuple3d.y, this.z * tuple3d.z);
    }

    public Tuple3d crossProduct(@NotNull Tuple3d tuple3d) {
        return new Tuple3d(y * tuple3d.z - z * tuple3d.y, z * tuple3d.x - x * tuple3d.z, x * tuple3d.y - y * tuple3d.x);
    }

    public Tuple3d crossProduct(double x, double y, double z) {
        return new Tuple3d(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
