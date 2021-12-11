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
        System.out.print("Entrez une option: ");
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
                System.out.println(system.rechercher());
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
            case "Deconnexion":
                System.out.println(system.deconnexion());
                break;
            case "Participer à une enchère":
                system.acheterObjetEnchere();
                break;
            case "Acheter un objet":
                //system.acheterUnObjet();
                break;
            case "Evaluer un utilisateur":
                system.evaluerUnUtilisateur();
                break;
            case "Gérer mes ventes":
                System.out.println(system.gererMesVentes());
                break;
            //TODO
            case "Créer une vente":
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
            System.out.print("Entrez votre " + parametre + ": ");
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
        System.out.println("Bienvenue sur IBuySu.com, votre site d'achat-vente en ligne !");
        System.out.println("Connexion en cours...");
        try {
            initSystem();
        } catch (Exception e) {
            System.out.println("Echec de la connexion\nFermeture du système");
            quitter();
        }
        while (!exit) {
            traiterChoix(deroulerMenu("Que désirez-vous faire ?", system.getMenu()));
        }
        System.out.println("A bientôt sur IBuySu.com!");
        return;
    }
}