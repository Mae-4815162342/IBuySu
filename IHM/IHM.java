package IHM;

import java.util.*;

public class IHM {
    private static Scanner scan = new Scanner(System.in);

    public static String getUserIn(String message) {
        System.out.print(message + " ");
        System.out.flush();
        return waitAnswer();
    }

    private static int waitAnswerMenu(String[] menu) {
        System.out.print(PromptUtils.yel("Entrez une option: "));
        System.out.flush();

        int answer = -1;
        while (answer < 0 || answer > menu.length) {
            answer = scan.nextInt();
        }

        return answer;
    }

    private static String getMenuUtilisateur(String[] menu) {
        String res = "";
        for (int i = 0; i < menu.length; i++) {
            res += (i + 1) + ". " + menu[i] + "\n";
        }
        return res;
    }

    private static String waitAnswer() {
        String res = "";
        while (res.length() == 0) {
            res = scan.nextLine();
        }
        return res;
    }

    public static String[] remplirFormulaire(String titreFormulaire, String[] formulaire) {
        System.out.println(titreFormulaire);
        String[] parametres = new String[formulaire.length];
        int i = 0;
        for (String parametre : formulaire) {
            System.out.print(PromptUtils.yel("Entrez votre " + parametre + ": "));
            System.out.flush();
            String res = waitAnswer();
            parametres[i] = res;
            i++;
        }
        return parametres;
    }

    public static String deroulerMenu(String message, String[] menu) {
        System.out.println(message);
        System.out.println(getMenuUtilisateur(menu));
        int index = waitAnswerMenu(menu);
        return menu[index - 1];
    }
}
