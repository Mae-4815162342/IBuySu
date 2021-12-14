package IHM;

import java.util.*;

/**
 * Classe statique contenant des fonctions utilitaires pour tout ce qui est interface et interaction avec l'utilisateur dans la console.
 */
public class IHM {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Attend une saisie de l'utilisateur.
     * 
     * @param message Message specifiant a l'utilisateur ce qu'il doit saisir.
     * @return ce que l'utilisateur a saisit.
     */
    public static String getUserIn(String message) {
        System.out.print(message + ": ");
        System.out.flush();
        return waitAnswer();
    }

    /**
     * Prend une liste de Strings comme menu et demande a l'utilisateur de
     * selectionner une des entrees.
     * 
     * @param menu Menu - groupe d'entrees individuellement selectionnables.
     * @return l'index de l'option choisie.
     */
    private static int waitAnswerMenu(String[] menu) {
        System.out.print(PromptUtils.yel("Entrez une option: "));
        System.out.flush();

        int answer = -1;
        while (answer < 0 || answer > menu.length) {
            answer = scan.nextInt();
        }

        return answer;
    }

    /**
     * Cree l'affichage d'un menu (sans l'afficher dans la console).
     * 
     * @param menu Le menu dont il fait creer l'affichage.
     * @return L'affichage du menu.
     */
    private static String getMenuString(String[] menu) {
        String res = "";
        for (int i = 0; i < menu.length; i++) {
            res += (i + 1) + ". " + menu[i] + "\n";
        }
        return res;
    }

    /**
     * Attend une reponse textuelle de l'utilisateur.
     * @return La reponse de l'utilisateur.
     */
    private static String waitAnswer() {
        String res = "";
        while (res.length() == 0) {
            res = scan.nextLine();
        }
        return res;
    }

    /**
     * Demande a l'utilisateur de remplir un formulaire.
     * @param titreFormulaire Titre du formulaire a remplir.
     * @param formulaire Formulaire - liste de champs a remplir individuellement. Tous les champs sont remplis avec du texte.
     * @return Les reponses de l'utilisateur, l'indexe de la reponse correspondant a l'indexe du champ du formulaire.
     */
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

    /**
     * Deroule le menu et demande a l'utilisateur de selectionner une des entrees.
     * @param message Message du menu.
     * @param menu Menu - groupe d'entrees individuellement selectionnables.
     * @return Entree du menu selectionnee.
     */
    public static String deroulerMenu(String message, String[] menu) {
        System.out.println(message);
        System.out.println(getMenuString(menu));
        int index = waitAnswerMenu(menu);
        return menu[index - 1];
    }
}