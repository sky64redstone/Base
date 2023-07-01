package de.base.math;

import java.util.Objects;

/**
 * This class helps with any application of 3 dimensional vectors
 * @author Jonathan
 */
public class Vector3d {
    public double x, y, z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getDist(Vector3d other) {
        Objects.requireNonNull(other);

        double distX = Math.abs(x - other.x);
        double distY = Math.abs(y - other.y);
        double distZ = Math.abs(z - other.z);

        return Math.sqrt(distX * distX + distY * distY + distZ * distZ);
    }

    public double dotProduct(Vector3d other) {
        Objects.requireNonNull(other);

        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3d normalize() {
        double length = getLength();

        if (length <= 0)
            return new Vector3d(x, y, z);

        return new Vector3d(x / length, y / length, z / length);
    }

    public Vector3d norm() {
        double length = getLength();

        if (length <= 0)
            return this;

        this.x /= length;
        this.y /= length;
        this.z /= length;

        return this;
    }

    public Vector3d crossProduct(Vector3d other) {
        Objects.requireNonNull(other);

        Vector3d crossProduct = new Vector3d(0, 0, 0);

        crossProduct.x = this.y * other.z - this.z * other.y;
        crossProduct.y = this.z * other.x - this.x * other.z;
        crossProduct.z = this.x * other.y - this.y * other.x;

        return crossProduct;
    }

    public Vector3d cross(Vector3d other) {
        Objects.requireNonNull(other);

        this.x = this.y * other.z - this.z * other.y;
        this.y = this.z * other.x - this.x * other.z;
        this.z = this.x * other.y - this.y * other.x;

        return this;
    }

    public Vector3d addition(Vector3d other) {
        Objects.requireNonNull(other);

        return new Vector3d(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3d add(Vector3d other) {
        Objects.requireNonNull(other);

        this.x += other.x;
        this.y += other.y;
        this.z += other.z;

        return this;
    }

    public Vector3d subtract(Vector3d other) {
        Objects.requireNonNull(other);

        return new Vector3d(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3d sub(Vector3d other) {
        Objects.requireNonNull(other);

        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;

        return this;
    }

    public Vector3d multiply(Vector3d other) {
        Objects.requireNonNull(other);

        return new Vector3d(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3d mul(Vector3d other) {
        Objects.requireNonNull(other);

        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;

        return this;
    }

    public Vector3d mul(double number) {
        this.x *= number;
        this.y *= number;
        this.z *= number;

        return this;
    }

    public Vector3d divide(Vector3d other) {
        Objects.requireNonNull(other);

        return new Vector3d(this.x / other.x, this.y / other.y, this.z / other.z);
    }

    public Vector3d div(Vector3d other) {
        Objects.requireNonNull(other);

        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;

        return this;
    }
}
