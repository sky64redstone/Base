package de.base.math;

import java.util.Objects;

public class Vector4d {
    public double x, y, z, w;

    public Vector4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public double getDist(Vector4d other) {
        Objects.requireNonNull(other);

        double distX = Math.abs(x - other.x);
        double distY = Math.abs(y - other.y);
        double distZ = Math.abs(z - other.z);
        double distW = Math.abs(w - other.w);

        return Math.sqrt(distX * distX + distY * distY + distZ * distZ + distW * distW);
    }

    public double dotProduct(Vector4d other) {
        Objects.requireNonNull(other);

        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    public Vector4d crossProduct(Vector4d other) {
        Objects.requireNonNull(other);

        Vector4d crossProduct = new Vector4d(0, 0, 0, 0);

        crossProduct.x = this.y * other.z - this.z * other.y;
        crossProduct.y = this.z * other.x - this.x * other.z;
        crossProduct.z = this.x * other.y - this.y * other.x;
        crossProduct.w = this.w;

        return crossProduct;
    }

    public Vector4d cross(Vector4d other) {
        Objects.requireNonNull(other);

        this.x = this.y * other.z - this.z * other.y;
        this.y = this.z * other.x - this.x * other.z;
        this.z = this.x * other.y - this.y * other.x;

        return this;
    }

    public Vector4d normalize() {
        double length = getLength();

        if (length <= 0)
            return new Vector4d(x, y, z, w);

        return new Vector4d(x / length, y / length, z / length, w / length);
    }

    public Vector4d norm() {
        double length = getLength();

        if (length <= 0)
            return this;

        this.x /= length;
        this.y /= length;
        this.z /= length;
        this.w /= length;

        return this;
    }

    public Vector4d addition(Vector4d other) {
        Objects.requireNonNull(other);

        return new Vector4d(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
    }

    public Vector4d add(Vector4d other) {
        Objects.requireNonNull(other);

        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        this.w += other.w;

        return this;
    }

    public Vector4d add(double number) {
        this.x += number;
        this.y += number;
        this.z += number;
        this.w += number;

        return this;
    }

    public Vector4d subtract(Vector4d other) {
        Objects.requireNonNull(other);

        return new Vector4d(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
    }

    public Vector4d sub(Vector4d other) {
        Objects.requireNonNull(other);

        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        this.w -= other.w;

        return this;
    }

    public Vector4d multiply(Vector4d other) {
        Objects.requireNonNull(other);

        return new Vector4d(this.x * other.x, this.y * other.y, this.z * other.z, this.w * other.w);
    }

    public Vector4d multiply(double number) {
        return new Vector4d(this.x * number, this.y * number, this.z * number, this.w * number);
    }

    public Vector4d mul(Vector4d other) {
        Objects.requireNonNull(other);

        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
        this.w *= other.w;

        return this;
    }

    public Vector4d mul(double number) {
        this.x *= number;
        this.y *= number;
        this.z *= number;
        this.w *= number;

        return this;
    }

    public Vector4d divide(Vector4d other) {
        Objects.requireNonNull(other);

        return new Vector4d(this.x / other.x, this.y / other.y, this.z / other.z, this.w / other.w);
    }

    public Vector4d div(Vector4d other) {
        Objects.requireNonNull(other);

        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;
        this.w /= other.w;

        return this;
    }

    public Vector3d toVector3d() {
        if (this.w == 0)
            return new Vector3d(this.x, this.y, this.z);

        return new Vector3d(this.x / this.w, this.y / this.w, this.z / this.w);
    }
}
