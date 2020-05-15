package pwag.util;

/**
 * Useful math functions I desire.
 */
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

    /**
     * Gets the sign of a number.
     */
    public static double getSign(double a) {
        if (a == 0) return 1;
        return a / Math.abs(a);
    }

    /**
     * Returns the decimal component of a double.
     * An input of 1.234 would return 0.234.
     */
    public static double getDecimal(double a) {
        return a - smartFloor(a);
    }

    /**
     * Makes it so that the floor function doesn't return -2 when I do floor(-1.6).
     */
    public static double smartFloor(double a) {
        if (a < 0) return Math.ceil(a);
        return Math.floor(a);
    }
}