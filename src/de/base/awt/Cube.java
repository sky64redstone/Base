package de.base.awt;

import de.base.math.Maths2D;
import de.base.math.Point3d;
import de.base.math.Tuple4d;
import de.base.utils.BooleanUtils;
import org.jetbrains.annotations.NotNull;

public class Cube extends Tuple4d {

    public Cube() {
        super(0, 0, 0, 1);
    }

    public Cube(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Cube(Cube cube) {
        super(cube.x, cube.y, cube.z, cube.w);
    }

    public Cube(Cuboid cuboid) {
        super(cuboid.x, cuboid.y, cuboid.z, cuboid.w);
        if (cuboid.w == cuboid.h && cuboid.w == cuboid.l)
            throw new IllegalStateException("The Cuboids{" + cuboid + "} width isn't the same as height and length");
    }

    public boolean intersects(double x, double y, double z, double w, double h, double l) {
        boolean intersectsX = Maths2D.intersectsRect(this.x, this.y, this.w, this.w, x, y, w, h);
        boolean intersectsY = Maths2D.intersectsRect(this.y, this.z, this.w, this.w, y, z, h, l);
        boolean intersectsZ = Maths2D.intersectsRect(this.x, this.z, this.w, this.w, x, z, w, l);
        return BooleanUtils.and(intersectsX, intersectsY, intersectsZ);
    }

    public boolean intersects(@NotNull Cuboid c) {
        boolean intersectsX = Maths2D.intersectsRect(this.x, this.y, this.w, this.w, c.x, c.y, c.w, c.h);
        boolean intersectsY = Maths2D.intersectsRect(this.y, this.z, this.w, this.w, c.y, c.z, c.h, c.l);
        boolean intersectsZ = Maths2D.intersectsRect(this.x, this.z, this.w, this.w, c.x, c.z, c.w, c.l);
        return BooleanUtils.and(intersectsX, intersectsY, intersectsZ);
    }

    public boolean contains(double x, double y, double z) {
        return BooleanUtils.and(this.x < x, this.y < y, this.z < z, this.x + this.w > x, this.y + this.w > y, this.z + this.w > z);
    }

    public boolean contains(@NotNull Point3d point3d) {
        return BooleanUtils.and(this.x < point3d.x, this.y < point3d.y, this.z < point3d.z, this.x + this.w > point3d.x, this.y + this.w > point3d.y, this.z + this.w > point3d.z);
    }

    @Override
    public double getLength() {
        return Math.sqrt(3 * (w * w));
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }
}
