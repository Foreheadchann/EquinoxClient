package me.equinox.utils;

public class MathUtils {
    public static double lerp(double a, double b, double f) {
        return a + f * (b - a);
    }
}
