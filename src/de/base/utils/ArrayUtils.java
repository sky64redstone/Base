package de.base.utils;

import org.jetbrains.annotations.NotNull;

public class ArrayUtils {

    private ArrayUtils() {
    }

    public static short[] sort(short @NotNull [] toSort) {
        for (int i = 1; i < toSort.length; i++) {
            for (int j = 0; j < toSort.length - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    short temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }

        return toSort;
    }

    public static int[] sort(int @NotNull [] toSort) {
        for (int i = 1; i < toSort.length; i++) {
            for (int j = 0; j < toSort.length - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    int temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }

        return toSort;
    }

    public static long[] sort(long @NotNull [] toSort) {
        for (int i = 1; i < toSort.length; i++) {
            for (int j = 0; j < toSort.length - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    long temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }

        return toSort;
    }

    public static float[] sort(float @NotNull [] toSort) {
        for (int i = 1; i < toSort.length; i++) {
            for (int j = 0; j < toSort.length - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    float temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }

        return toSort;
    }

    public static double[] sort(double @NotNull [] toSort) {
        for (int i = 1; i < toSort.length; i++) {
            for (int j = 0; j < toSort.length - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    double temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }

        return toSort;
    }

    public static String[] mirror(String @NotNull [] in) {

        for (int i = 0; i < in.length / 2; i++) {
            String temp = in[i];
            in[i] = in[in.length - i - 1];
            in[in.length - i - 1] = temp;
        }

        return in;
    }

    public static String @NotNull [] add(String @NotNull [] to, String add) {
        String[] toReturn = new String[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static char @NotNull [] add(char @NotNull [] to, char add) {
        char[] toReturn = new char[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static int @NotNull [] add(int @NotNull [] to, int add) {
        int[] toReturn = new int[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static long @NotNull [] add(long @NotNull [] to, long add) {
        long[] toReturn = new long[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static double @NotNull [] add(double @NotNull [] to, double add) {
        double[] toReturn = new double[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static byte @NotNull [] add(byte @NotNull [] to, byte add) {
        byte[] toReturn = new byte[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static short @NotNull [] add(short @NotNull [] to, short add) {
        short[] toReturn = new short[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static boolean @NotNull [] add(boolean @NotNull [] to, boolean add) {
        boolean[] toReturn = new boolean[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;

        return toReturn;
    }

    public static float @NotNull [] add(float @NotNull [] to, float add) {
        float[] toReturn = new float[to.length + 1];

        System.arraycopy(to, 0, toReturn, 0, to.length);

        toReturn[to.length] = add;


        return toReturn;
    }

    public static short[] remove(short @NotNull [] from, short toRemove) {
        short[] toReturn = new short[from.length - contains(from, toRemove)];

        for (short j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }

    public static int[] remove(int @NotNull [] from, int toRemove) {
        int[] toReturn = new int[from.length - contains(from, toRemove)];

        for (int j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }

    public static long[] remove(long @NotNull [] from, long toRemove) {
        long[] toReturn = new long[from.length - contains(from, toRemove)];

        for (long j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }

    public static float[] remove(float @NotNull [] from, float toRemove) {
        float[] toReturn = new float[from.length - contains(from, toRemove)];

        for (float j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }

    public static double[] remove(double @NotNull [] from, double toRemove) {
        double[] toReturn = new double[from.length - contains(from, toRemove)];

        for (double j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }

    public static byte[] remove(byte @NotNull [] from, byte toRemove) {
        byte[] toReturn = new byte[from.length - contains(from, toRemove)];

        for (byte j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }

    public static char[] remove(char @NotNull [] from, char toRemove) {
        char[] toReturn = new char[from.length - contains(from, toRemove)];

        for (char j : from) {
            if (toRemove != j)
                toReturn = add(toReturn, j);
        }

        return toReturn;
    }
    @Deprecated //TODO This Methode is not ready yet!!
    public static char[] remove(char @NotNull [] text, char @NotNull [] remover) {
        char[] toReturn = new char[0];

        for (int i = 0; i < text.length - remover.length; i++) {
            boolean contains = true;
            for (int j = 0; j < remover.length; j++) {
                if (text[i + j] != remover[j]) {
                    contains = false;
                    break;
                }
            }
            if (!contains)
                toReturn = add(toReturn, text[i]);
            else
                i += remover.length - 1;
        }
        return toReturn;
    }

    public static int contains(char @NotNull [] text, char @NotNull [] in) {
        int count = 0;

        for (int i = 0; i < text.length - in.length; i++) {
            boolean contains = true;
            for (int j = 0; j < in.length; j++) {
                if (text[i + j] != in[j]) {
                    contains = false;
                    break;
                }
            }
            if (contains)
                count++;
            //i += in.length; // todo >>NOT CHECKED YET<<
        }

        return count;
    }

    public static int contains(short @NotNull [] array, short in) {
        int count = 0;

        for (short j : array) {
            if (j == in)
                count++;
        }

        return count;
    }

    public static int contains(int @NotNull [] array, int in) {
        int count = 0;

        for (int j : array) {
            if (j == in)
                count++;
        }

        return count;
    }

    public static int contains(long @NotNull [] array, long in) {
        int count = 0;

        for (long j : array) {
            if (j == in)
                count++;
        }

        return count;
    }

    public static int contains(double @NotNull [] array, double in) {
        int count = 0;

        for (double j : array) {
            if (j == in)
                count++;
        }

        return count;
    }

    public static int contains(float @NotNull [] array, float in) {
        int count = 0;

        for (float j : array) {
            if (j == in)
                count++;
        }

        return count;
    }

    public static int contains(byte @NotNull [] array, byte in) {
        int count = 0;

        for (int j : array) {
            if (j == in)
                count++;
        }

        return count;
    }

    public static int contains(char @NotNull [] array, char in) {
        int count = 0;

        for (char j : array) {
            if (j == in)
                count++;
        }

        return count;
    }
}
