package de.base.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class StringUtils {

    private StringUtils() {
    }

    public static String @NotNull [] sort(String @NotNull [] toSort, int @NotNull [] by) {
        if (toSort.length != by.length)
            throw new IllegalStateException("The Lengths of the Arrays have to be equal");

        for (int i = 1; i < by.length; i++) {
            for (int j = 0; j < by.length - i; j++) {
                if (by[j] > by[j + 1]) {
                    int temp = by[j];
                    by[j] = by[j + 1];
                    by[j + 1] = temp;

                    String s = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = s;
                }
            }
        }

        return toSort;
    }

    public static String[] decode(String toDecode, String search) {
        String[] toReturn = new String[0];
        int[] counter = new int[0];

        for (int i = 0; i < 26; i++) {
            String decoded = decode(toDecode, i);

            int count = contains(decoded, search);

            if (count > 0) {
                toReturn = ArrayUtils.add(toReturn, decoded);
                counter = ArrayUtils.add(counter, count);
            }
        }

        return ArrayUtils.mirror(sort(toReturn, counter));
    }

    public static @NotNull String decode(String toDecode, int key) {
        char[] text = getLetters(toDecode).toCharArray();

        for (int i = 0; i < text.length; i++) {
            if ((text[i] - key) >= 'a')
                text[i] -= key;
            else
                text[i] = (char) (text[i] - key + 26);
        }

        return new String(text);
    }

    public static @NotNull String encode(String toEncode, int key) {
        char[] text = getLetters(toEncode).toLowerCase(Locale.ROOT).toCharArray();

        for (int i = 0; i < text.length; i++) {
            if ((text[i] + key) <= 'z')
                text[i] += key;
            else
                text[i] += key - 26;
        }

        return new String(text);
    }

    public static @NotNull String getLetters(@NotNull String text) {
        char[] chars = text.toCharArray();
        char[] toReturn = new char[0];

        for (char c : chars)
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                toReturn = ArrayUtils.add(toReturn, c);

        return new String(toReturn);
    }
    @Deprecated
    public static @NotNull String remove(@NotNull String toRemove, @NotNull String remover) {
        return new String(ArrayUtils.remove(toRemove.toCharArray(), remover.toCharArray()));
    }

    public static int contains(@NotNull String text, @NotNull String in) {
        return ArrayUtils.contains(text.toCharArray(), in.toCharArray());
    }
}
