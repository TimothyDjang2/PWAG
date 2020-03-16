package pwag.util;

public class MathUtils {

    /**
     * Makes sure the input number is within a certain domain.
     */
    public static double clamp(double a, double min, double max) {
        if (a < min) return min;
        if (a > max) return max;
        return a;
    }

    /**
     * Returns 0 unless the value is outside a specified bound.
     */
    public static double inverseClamp(double a, double boundMin, double boundMax) {
        if (a <= boundMax && a >= boundMin) return 0;
        return a;
    }

}