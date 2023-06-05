package de.base.math;

public class Point3d {

    public double x, y, z;

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d(Point3d point3d) {
        this.x = point3d.x;
        this.y = point3d.y;
        this.z = point3d.z;
    }

    public double getDist(Point3d point3d) {
        return Math.sqrt(this.x * point3d.x + this.y * point3d.y + this.z * point3d.z);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
