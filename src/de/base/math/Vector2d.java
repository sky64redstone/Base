package de.base.math;

import java.util.Objects;

/**
 * This class helps with any application of 2 dimensional vectors
 * @author Jonathan
 */
public class Vector2d {
    public double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public double getDist(Vector2d other) {
        Objects.requireNonNull(other);

        double distX = Math.abs(x - other.x);
        double distY = Math.abs(y - other.y);

        return Math.sqrt(distX * distX + distY * distY);
    }

    public double dotProduct(Vector2d other) {
        Objects.requireNonNull(other);

        return this.x * other.x + this.y * other.y;
    }

    public Vector2d normalize() {
        double length = getLength();

        if (length <= 0)
            return new Vector2d(x, y);

        return new Vector2d(x / length, y / length);
    }

    public Vector2d norm() {
        double length = getLength();

        if (length <= 0)
            return this;

        this.x /= length;
        this.y /= length;

        return this;
    }

    public Vector2d addition(Vector2d other) {
        Objects.requireNonNull(other);

        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d add(Vector2d other) {
        Objects.requireNonNull(other);

        this.x += other.x;
        this.y += other.y;

        return this;
    }

    public Vector2d subtract(Vector2d other) {
        Objects.requireNonNull(other);

        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d sub(Vector2d other) {
        Objects.requireNonNull(other);

        this.x -= other.x;
        this.y -= other.y;

        return this;
    }

    public Vector2d multiply(Vector2d other) {
        Objects.requireNonNull(other);

        return new Vector2d(this.x * other.x, this.y * other.y);
    }

    public Vector2d mul(Vector2d other) {
        Objects.requireNonNull(other);

        this.x *= other.x;
        this.y *= other.y;

        return this;
    }

    public Vector2d divide(Vector2d other) {
        Objects.requireNonNull(other);

        return new Vector2d(this.x / other.x, this.y / other.y);
    }

    public Vector2d div(Vector2d other) {
        Objects.requireNonNull(other);

        this.x /= other.x;
        this.y /= other.y;

        return this;
    }
}
