package de.base.math;

public class Point4d {

    public double x, y, z, w;

    public Point4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Point4d(Point4d point4d) {
        this.x = point4d.x;
        this.y = point4d.y;
        this.z = point4d.z;
        this.w = point4d.w;
    }

    public double getDist(Point4d point4d) {
        return Math.sqrt(this.x * point4d.x + this.y * point4d.y + this.z * point4d.z + this.w * point4d.w);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }
}
