package IHM;

public class PromptUtils {
    private static final String E = "\u001b[";
    private static final String C = E + "0m";
    private static final String B = E + "1m";

    private static final String RED = E + "31m";
    private static final String GRN = E + "32m";
    private static final String YEL = E + "33m";
    private static final String BLU = E + "34m";
    private static final String MAG = E + "35m";

    private static String color(String c, String message) {
        return c + message + C;
    }

    public static String b(String message) { return color(B, message); }
    public static String red(String message) { return color(RED, message); }
    public static String grn(String message) { return color(GRN, message); }
    public static String yel(String message) { return color(YEL, message); }
    public static String blu(String message) { return color(BLU, message); }
    public static String mag(String message) { return color(MAG, message); }

    public static void printError(String message) {
        System.err.println(red("Erreur : " + message));
    }

    public static void printSuccess(String message) {
        System.out.println(grn(message));
    }
}
