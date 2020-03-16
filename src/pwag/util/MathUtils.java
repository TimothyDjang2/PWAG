package pwag.util;

public class MathUtils {

    public static double smartCeil(double a) {
        if (a < 0) return Math.floor(a);
        return Math.ceil(a);
    }

}