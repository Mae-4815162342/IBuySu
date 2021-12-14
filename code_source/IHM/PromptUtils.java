package IHM;

/**
 * Classe statique utilitaire pour de l'affichage console.
 */
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

    /** Rend le message gras pour la console. */
    public static String b(String message) { return color(B, message); }
    /** Rend le message rouge pour la console. */
    public static String red(String message) { return color(RED, message); }
    /** Rend le message vert pour la console. */
    public static String grn(String message) { return color(GRN, message); }
    /** Rend le message jaune pour la console. */
    public static String yel(String message) { return color(YEL, message); }
    /** Rend le message bleu pour la console. */
    public static String blu(String message) { return color(BLU, message); }
    /** Rend le message magenta pour la console. */
    public static String mag(String message) { return color(MAG, message); }

    /**
     * Affiche un message d'erreur.
     * @param message Le message d'erreur.
     */
    public static void printError(String message) {
        System.err.println(red("Erreur : " + message));
    }

    /**
     * Affiche un message de succes.
     * @param message Le message de succes.
     */
    public static void printSuccess(String message) {
        System.out.println(grn(message));
    }
}
