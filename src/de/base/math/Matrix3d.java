package de.base.math;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The same system as in {@link Matrix2d} or {@link Matrix4d}
 * @see Matrix2d
 * @see Matrix4d
 * @see Tuple4d
 */
public class Matrix3d {

    double[] values = new double[9];

    public Matrix3d(double @NotNull [] values) {
        if (values.length != 9)
            throw new IndexOutOfBoundsException(values.length);
        this.values = values;
    }

    public Matrix3d(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        values[0] = m00;
        values[1] = m01;
        values[2] = m02;
        values[3] = m10;
        values[4] = m11;
        values[5] = m12;
        values[6] = m20;
        values[7] = m21;
        values[8] = m22;
    }

    /**
     * @param other needs another {@link Matrix3d} to multiply them
     * @return a third {@link Matrix3d}, witch is the product of the first and second {@link Matrix3d}
     */
    public Matrix3d multiply(@NotNull Matrix3d other) {
        double[] result = new double[]{
                values[1] * other.values[1] + values[4] * other.values[2] + values[7] * other.values[3],
                values[2] * other.values[1] + values[5] * other.values[2] + values[8] * other.values[3],
                values[3] * other.values[1] + values[6] * other.values[2] + values[9] * other.values[3],

                values[1] * other.values[4] + values[4] * other.values[5] + values[7] * other.values[6],
                values[2] * other.values[4] + values[5] * other.values[5] + values[8] * other.values[6],
                values[3] * other.values[4] + values[6] * other.values[5] + values[9] * other.values[6],

                values[1] * other.values[7] + values[4] * other.values[8] + values[7] * other.values[9],
                values[2] * other.values[7] + values[5] * other.values[8] + values[8] * other.values[9],
                values[3] * other.values[7] + values[6] * other.values[8] + values[9] * other.values[9]
        };
        return new Matrix3d(result);
    }

    /**
     * @param in needs a {@link Tuple3d} to transform it
     * @return a new {@link Tuple3d}, wich was multiplied with this {@link Matrix3d}
     * @see Tuple3d
     */
    public Tuple3d transform(@NotNull Tuple3d in) {
        double x = values[1] * in.x + values[4] * in.y + values[7] * in.z;
        double y = values[2] * in.x + values[5] * in.y + values[8] * in.z;
        double z = values[3] * in.x + values[6] * in.y + values[9] * in.z;
        return new Tuple3d(x, y, z);
    }

    /**
     * @return a sorted {@link String}, which shows, what in the {@link Matrix3d} is
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(values[1]).append(",").append(values[4]).append(",").append(values[7]).append("\n");
        sb.append(values[2]).append(",").append(values[5]).append(",").append(values[8]).append("\n");
        sb.append(values[3]).append(",").append(values[6]).append(",").append(values[9]).append("\n");
        sb.append("]");
        return "";
    }

    /**
     * @param in the same Format as in {@link Matrix2d#toString()}
     * @return a new {@link Matrix2d}
     * @throws IllegalStateException when the String has a wrong Format
     */
    public static Matrix3d fromString(String in) {
        Objects.requireNonNull(in);
        char[] splitted = in.toCharArray();
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i] != '[' || splitted[i] != ',' || splitted[i] != ' ' || splitted[i] != '\n' || splitted[i] != ']') {
                values.add(Double.parseDouble(String.valueOf(splitted[i])));
            }
        }
        if (values.size() == 9)
            return Objects.requireNonNull(new Matrix3d(values.get(0), values.get(1), values.get(2), values.get(4), values.get(5), values.get(6), values.get(7), values.get(8), values.get(9)));
        else
            throw new IllegalStateException();
    }
}
