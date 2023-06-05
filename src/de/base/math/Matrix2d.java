package de.base.math;

import org.jetbrains.annotations.NotNull;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The first number is at the top left.
 * <p>The second below it and the third is in the first line but in the second column.</p>
 * <p>The fourth number is at the bottom right.</p>
 * @see Matrix4d
 * @see Matrix3d
 * @see Tuple2d
 */
public class Matrix2d {

    double[] values = new double[4];

    public Matrix2d(double[] values) {
        Objects.requireNonNull(values);
        if (values.length != 4)
            throw new IndexOutOfBoundsException(values.length);
        this.values = values;
    }

    public Matrix2d(double m00, double m01, double m10, double m11) {
        values[0] = m00;
        values[1] = m01;
        values[2] = m10;
        values[3] = m11;
    }

    /**
     * @param other needs another {@link Matrix2d} to multiply it with this Class
     * @return a third {@link Matrix2d}, witch is the product of the first and second {@link Matrix2d}
     */
    public Matrix2d multiply(@NotNull Matrix2d other) {
        double[] result;
        double first = values[1] * other.values[1] + values[3] * other.values[2];
        double second = values[2] * other.values[1] + values[4] * other.values[2];
        double third = values[1] * other.values[3] + values[3] * other.values[4];
        double fourth = values[2] * other.values[3] + values[4] * other.values[4];
        result = new double[]{first, second, third, fourth};
        return new Matrix2d(result);
    }

    /**
     * @param in needs a {@link Tuple2d} to transform it
     * @return a new {@link Tuple2d}, which is multiplied with this {@link Matrix2d}
     * @see Tuple2d
     */
    public Tuple2d transform(@NotNull Tuple2d in) {
        return new Tuple2d(values[1] * in.x + values[3] * in.y,
                            values[2] * in.x + values[4] * in.y);
    }

    /**
     * @return a sorted {@link String}, which shows, what in the {@link Matrix2d} is
     */
    @Override
    public String toString() {
        return "[" + values[1] + "," + values[3] + ",\n" + values[2] + "," + values[4] + "]";
    }

    /**
     * @param in the same Format as in {@link Matrix2d#toString()}
     * @return a new {@link Matrix2d}
     * @throws IllegalStateException when the String has a wrong Format
     */
    public static Matrix2d fromString(String in) {
        Objects.requireNonNull(in);
        char[] splitted = in.toCharArray();
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i] != '[' || splitted[i] != ',' || splitted[i] != ' ' || splitted[i] != '\n' || splitted[i] != ']') {
                values.add(Double.parseDouble(String.valueOf(splitted[i])));
            }
        }
        if (values.size() == 4)
            return Objects.requireNonNull(new Matrix2d(values.get(0), values.get(1), values.get(2), values.get(3)));
        else
            throw new IllegalStateException();
    }
}
