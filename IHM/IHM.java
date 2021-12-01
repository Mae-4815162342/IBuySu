package IHM;

import System.IBuySu;
import java.util.*;

public class IHM {

    private static boolean exit = false;
    private static IBuySu system;
    private static String[] menuCourant;

    private static void initSystem(){
        system = IBuySu.getSystem();
    }

    private static int waitAnswerMenu(){
        System.out.println("Entrez une option:");
        Scanner scan = new Scanner(System.in);
        int answer = -1;
        while(answer < 0 ) {
            answer = scan.nextInt();
        }
        return answer;
    }

    private static String getMenuUtilisateur() {
        menuCourant = system.getMenu();
        String res = "";
        for(int i = 0 ; i < menuCourant.length; i++){
            res += (i+1) + "/" + menuCourant[i] + "\n";
        }
        return res;
    }

    private static void traiterChoix(int index) {
        String choix = menuCourant[index - 1];
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
                system.connexion();
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

    private static String waitAnswerFormulaire(){
        Scanner scan = new Scanner(System.in);
        String res = "";
        while(res.length() == 0) {
            res = scan.nextLine();
        }
        return res;
    }
    public static String[] remplirFormulaire(String titreFormulaire, String[] formulaire) {
        System.out.println(titreFormulaire);
        String[] parametres = new String[formulaire.length];
        int i = 0;
        for(String parametre : formulaire) {
            System.out.println("Entrez votre " + parametre + ":");
            String res = waitAnswerFormulaire();
            parametres[i] = res;
            i++;
        }
        return parametres;
    }

    public static String getTypeDonneesBancaires() {
        System.out.println("Choisissez un type de données bancaires pour la vérification de vos données :\n1/RIB\n2/Carte Bancaire");
        int res = -1;
        while (res !=1 && res != 2) {
            res = waitAnswerMenu();
        }
        return (res == 1) ? "RIB" : "CB";
    }

    public static void quitter(){
        exit = true;
    }

    public static void main(String [] args){
        initSystem();
        System.out.println("Bienvenue sur IBuySu.com, votre site d'achat-vente en ligne !");
        while(!exit) {
            System.out.println("Que désirez-vous faire ?");
            System.out.println(getMenuUtilisateur());
            int choix = waitAnswerMenu();
            traiterChoix(choix);
        }
        System.out.println("A bientôt sur IBuySu.com!");
        return;
    }
}

