package de.base.awt;

import org.jetbrains.annotations.NotNull;

import de.base.math.Maths2D;
import de.base.math.Point3d;
import de.base.math.Tuple3d;
import de.base.utils.BooleanUtils;

public class Cuboid extends Tuple3d {

    public double w;
    public double h;
    public double l;

    public Cuboid() {
        super(0, 0, 0);
        this.w = 1;
        this.h = 1;
        this.l = 1;
    }

    public Cuboid(double x, double y, double z, double w, double h, double l) {
        super(x, y, z);
        this.w = w;
        this.h = h;
        this.l = l;
    }

    public Cuboid(@NotNull Cuboid cuboid) {
        super(cuboid.x, cuboid.y, cuboid.z);
        this.w = cuboid.w;
        this.h = cuboid.h;
        this.l = cuboid.l;
    }

    public boolean intersects(double x, double y, double z, double w, double h, double l) {
        boolean intersectsX = Maths2D.intersectsRect(this.x, this.y, this.w, this.h, x, y, w, h);
        boolean intersectsY = Maths2D.intersectsRect(this.y, this.z, this.h, this.l, y, z, h, l);
        boolean intersectsZ = Maths2D.intersectsRect(this.x, this.z, this.w, this.l, x, z, w, l);
        return BooleanUtils.and(intersectsX, intersectsY, intersectsZ);
    }

    public boolean intersects(@NotNull Cuboid c) {
        boolean intersectsX = Maths2D.intersectsRect(this.x, this.y, this.w, this.h, c.x, c.y, c.w, c.h);
        boolean intersectsY = Maths2D.intersectsRect(this.y, this.z, this.h, this.l, c.y, c.z, c.h, c.l);
        boolean intersectsZ = Maths2D.intersectsRect(this.x, this.z, this.w, this.l, c.x, c.z, c.w, c.l);
        return BooleanUtils.and(intersectsX, intersectsY, intersectsZ);
    }

    public boolean contains(double x, double y, double z) {
        return BooleanUtils.and(this.x < x, this.y < y, this.z < z, this.x + this.w > x, this.y + this.h > y, this.z + this.l > z);
    }

    public boolean contains(@NotNull Point3d point3d) {
        return BooleanUtils.and(this.x < point3d.x, this.y < point3d.y, this.z < point3d.z, this.x + this.w > point3d.x, this.y + this.h > point3d.y, this.z + this.l > point3d.z);
    }

    @Override
    public double getLength() {
        return Math.sqrt(w * w + h * h + l * l);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + ", h=" + h + ", l=" + l + '}';
    }
}
