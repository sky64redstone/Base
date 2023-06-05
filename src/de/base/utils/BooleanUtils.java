package de.base.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;

public class BooleanUtils {

    private BooleanUtils() {
    }

    public static @NotNull String translate(boolean bool, Locale language) {
        if (language == Locale.GERMAN)
            return bool ? "wahr" : "falsch";

        return Boolean.toString(bool);
    }

    public static boolean and(boolean... booleans) {
        Objects.requireNonNull(booleans);
        boolean bool = true;

        for (boolean b : booleans)
            bool = bool && b;

        return bool;
    }

    public static boolean or(boolean... booleans) {
        Objects.requireNonNull(booleans);
        boolean bool = false;

        for (boolean b : booleans)
            bool = bool || b;

        return bool;
    }
}
