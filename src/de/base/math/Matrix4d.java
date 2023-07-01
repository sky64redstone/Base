package de.base.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class helps with any application of 4 dimensional matrices
 * @author Jonathan
 */
public class Matrix4d {

    double[] values = new double[16];

    public Matrix4d(double[] values) {
        Objects.requireNonNull(values);

        if (values.length != 16)
            throw new IndexOutOfBoundsException(values.length);

        this.values = values;
    }

    public Matrix4d(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) {
        values[0] = m00;
        values[1] = m01;
        values[2] = m02;
        values[3] = m03;
        values[4] = m10;
        values[5] = m11;
        values[6] = m12;
        values[7] = m13;
        values[8] = m20;
        values[9] = m21;
        values[10] = m22;
        values[11] = m23;
        values[12] = m30;
        values[13] = m31;
        values[14] = m32;
        values[15] = m33;
    }

    /**
     * @param other multiplys this {@link Matrix4d} with this {@code Class} and overwrites {@code this.values} with the product
     */
    public Matrix4d mul(Matrix4d other) {
        double[] result = new double[16];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int i = 0; i < 4; i++) {
                    result[row * 4 + col] += this.values[row * 4 + i] * other.values[i * 4 + col];
                }
            }
        }
        this.values = result;
        return this;
    }

    /**
     * @param a multiplies this {@link Matrix4d}
     * @param b with this {@link Matrix4d} and overwrites {@code this.values} with the product
     */
    public Matrix4d mul(Matrix4d a, Matrix4d b) {
        double[] result = new double[16];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int i = 0; i < 4; i++) {
                    result[row * 4 + col] += a.values[row * 4 + i] * b.values[i * 4 + col];
                }
            }
        }
        this.values = result;
        return this;
    }

    /**
     * @param other needs another {@link Matrix4d} to multiply them
     * @return a third {@link Matrix4d}, witch is the product of the first and second {@link Matrix4d}
     */
    public Matrix4d multiply(Matrix4d other) {
        double[] result = new double[16];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int i = 0; i < 4; i++) {
                    result[row * 4 + col] += this.values[row * 4 + i] * other.values[i * 4 + col];
                }
            }
        }
        return new Matrix4d(result);
    }

    /**
     * @param in needs a {@link Vector4d} to transform it
     * @return a new {@link Vector4d}, which is multiplied with this {@link Matrix4d}
     * @see Vector4d
     */
    public Vector4d transform(Vector4d in) {
        Objects.requireNonNull(in);

        return new Vector4d(
                in.x * values[0] + in.y * values[4] + in.z * values[8] + in.w * values[12],
                in.x * values[1] + in.y * values[5] + in.z * values[9] + in.w * values[13],
                in.x * values[2] + in.y * values[6] + in.z * values[10] + in.w * values[14],
                in.x * values[3] + in.y * values[7] + in.z * values[11] + in.w * values[15]
        );
    }

    /**
     * @param x the x value of the given vector
     * @param y the y value of the given vector
     * @param z the z value of the given vector
     * @param w the w value of the given vector
     * @return a new {@link Vector4d}, which is multiplied with this {@link Matrix4d}
     * @see Vector4d
     */
    public Vector4d transform(double x, double y, double z, double w) {
        return new Vector4d(
                x * values[0] + y * values[4] + z * values[8] + w * values[12],
                x * values[1] + y * values[5] + z * values[9] + w * values[13],
                x * values[2] + y * values[6] + z * values[10] + w * values[14],
                x * values[3] + y * values[7] + z * values[11] + w * values[15]
        );
    }

    /**
     * {@code NOTE: This inverse methode is only for rotation and translation matrices useful!!}
     * @return the inverse matrix of this instance
     */
    public Matrix4d quickInverse() {
        Matrix4d matrix4d = new Matrix4d(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        matrix4d.values[0] = this.values[0];
        matrix4d.values[1] = this.values[4];
        matrix4d.values[2] = this.values[8];
        matrix4d.values[3] = 0.0d;

        matrix4d.values[4] = this.values[1];
        matrix4d.values[5] = this.values[5];
        matrix4d.values[6] = this.values[9];
        matrix4d.values[7] = 0.0d;

        matrix4d.values[8] = this.values[2];
        matrix4d.values[9] = this.values[6];
        matrix4d.values[10] = this.values[10];
        matrix4d.values[11] = 0.0d;

        matrix4d.values[12] = -(this.values[12] * matrix4d.values[0] + this.values[13] * matrix4d.values[4] + this.values[14] * matrix4d.values[8]);
        matrix4d.values[13] = -(this.values[12] * matrix4d.values[1] + this.values[13] * matrix4d.values[5] + this.values[14] * matrix4d.values[9]);
        matrix4d.values[14] = -(this.values[12] * matrix4d.values[2] + this.values[13] * matrix4d.values[6] + this.values[14] * matrix4d.values[10]);
        matrix4d.values[15] = 1.0d;

        return matrix4d;
    }

    /**
     * @return a sorted {@link String}, which shows, what in the {@link Matrix4d} is
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName()).append("[\n");
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                sb.append(values[row * 4 + col]);
                if (col != 3) {
                    sb.append(",");
                }
            }
            if (row != 3) {
                sb.append("\n");
            }
        }
        sb.append("\n]");
        return sb.toString();
    }

    /**
     * @param in the same Format as in {@link Matrix4d#toString()}
     * @return a new {@link Matrix4d}
     * @throws IllegalStateException when the String has a wrong Format
     */
    public static Matrix4d fromString(String in) {
        Objects.requireNonNull(in);
        char[] split = in.toCharArray();
        List<Double> values = new ArrayList<>();

        for (char c : split)
            if (c != '[' && c != ',' && c != ' ' && c != '\n' && c != ']')
                values.add(Double.parseDouble(String.valueOf(c)));

        if (values.size() == 16)
            return new Matrix4d(
                    values.get(0), values.get(1), values.get(2), values.get(3),
                    values.get(4), values.get(5), values.get(6), values.get(7),
                    values.get(8), values.get(9), values.get(10), values.get(11),
                    values.get(12), values.get(13), values.get(14), values.get(15)
            );
        else
            throw new IllegalStateException();
    }
}
