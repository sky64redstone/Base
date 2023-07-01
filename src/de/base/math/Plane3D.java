package de.base.math;

import java.util.Objects;

/**
 * This class helps with any application of 3 dimensional planes
 *
 * @author Jonathan
 */
// TODO curves in the plane
public class Plane3D {
    public Vector4d point;
    public Vector4d normalVector;

    public Plane3D(Vector4d point, Vector4d normalVector) {
        this.point = point;
        this.normalVector = normalVector;
    }

    /**
     * @param point a point to test if it is on the plane
     * @return true if the point is on the plane
     */
    public boolean contains(Vector4d point) {
        return containValue(point) == 0;
    }

    /**
     * Here is outside when the point is on the other side of the plane than the normal vector
     * it is inside when the point is on the side of plane as the normal vector
     *
     * @param point a point to test how the relation between it and the plane is
     * @return value < 0 outside the plane; value == on the plane; value > 0 inside the plane
     */
    public double containValue(Vector4d point) {
        Objects.requireNonNull(point);

        return normalVector.dotProduct(point.subtract(this.point));
    }

    public Vector4d intersects(Vector4d lineStart, Vector4d lineEnd) {
        Objects.requireNonNull(lineStart);
        Objects.requireNonNull(lineEnd);

        normalVector = normalVector.normalize();
        double plane_d = -normalVector.dotProduct(point);
        double ad = lineStart.dotProduct(normalVector);
        double bd = lineEnd.dotProduct(normalVector);
        double t = (-plane_d - ad) / (bd - ad);
        Vector4d lineStartToEnd = lineEnd.subtract(lineStart);
        Vector4d lineToIntersect = lineStartToEnd.mul(t);
        return lineToIntersect.add(lineStart);
    }

    public Vector3d intersects(Vector3d lineStart, Vector3d lineEnd) {
        Objects.requireNonNull(lineStart);
        Objects.requireNonNull(lineEnd);

        normalVector = normalVector.normalize();
        double plane_d = -normalVector.dotProduct(point);
        double ad = lineStart.dotProduct(normalVector.toVector3d());
        double bd = lineEnd.dotProduct(normalVector.toVector3d());
        double t = (-plane_d - ad) / (bd - ad);
        Vector3d lineStartToEnd = lineEnd.subtract(lineStart);
        Vector3d lineToIntersect = lineStartToEnd.mul(t);
        return lineToIntersect.add(lineStart);
    }
}
