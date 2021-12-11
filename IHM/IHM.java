package IHM;

import System.IBuySu;
import java.util.*;

public class IHM {
    private static boolean exit = false;
    private static IBuySu system;
    private static String[] menuCourant;
    private static Scanner scan = new Scanner(System.in);

    private static void initSystem() throws Exception {
        try {
            system = IBuySu.getSystem();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getUserIn(String message) {
        System.out.print(message + " ");
        System.out.flush();
        return waitAnswer();
    }

    private static int waitAnswerMenu() {
        System.out.print(PromptUtils.yel("Entrez une option: "));
        System.out.flush();

        int answer = -1;
        while (answer < 0 || answer > menuCourant.length) {
            answer = scan.nextInt();
        }

        return answer;
    }

    private static String getMenuUtilisateur(String[] menu) {
        setMenuCourant(menu);
        String res = "";
        for (int i = 0; i < menuCourant.length; i++) {
            res += (i + 1) + ". " + menuCourant[i] + "\n";
        }
        return res;
    }

    private static void traiterChoix(String choix) {
        switch (choix) {
            case "Recherche":
                system.rechercher();
                break;
            case "Inscription Vendeur":
                System.out.println(system.inscriptionVendeur());
                break;
            case "Inscription Acheteur":
                System.out.println(system.inscriptionAcheteur());
                break;
            case "Connexion":
                System.out.println(system.connexion());
                break;
            case "Déconnexion":
                System.out.println(system.deconnexion());
                break;
            case "Participer à une enchère":
                system.acheterObjetEnchere();
                break;
            case "Acheter un objet":
                system.acheterUnObjet();
                break;
            case "Evaluer un utilisateur":
                system.evaluerUnUtilisateur();
                break;
            case "Quitter":
                quitter();
                break;
            default:
                break;
        }
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

    public static void setMenuCourant(String[] menu) {
        menuCourant = menu;
    }

    public static String deroulerMenu(String message, String[] menu) {
        System.out.println(message);
        setMenuCourant(menu);
        System.out.println(getMenuUtilisateur(menu));
        int index = waitAnswerMenu();
        return menuCourant[index - 1];
    }

    public static void quitter() {
        exit = true;
    }

    public static void main(String[] args) {
        System.out.println(PromptUtils.b("Bienvenue sur IBuySu.com, votre site d'achat-vente en ligne !"));
        System.out.println(PromptUtils.yel("Connexion en cours..."));
        try {
            initSystem();
        } catch (Exception e) {
            PromptUtils.printError("Echec de la connexion\nFermeture du système");
            quitter();
        }
        while (!exit) {
            traiterChoix(deroulerMenu(PromptUtils.yel("Que désirez-vous faire ?"), system.getMenu()));
        }
        System.out.println(PromptUtils.b("A bientôt sur IBuySu.com!"));
        return;
    }
}
