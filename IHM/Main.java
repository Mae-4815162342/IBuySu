package IHM;

import System.IBuySu;

/**
 * Entrée du programme.
 */
public class Main {
    private static boolean exiting = false;
    private static IBuySu system;

    /**
     * Entrée entrale du programme. 
     * @param args Chose jamais utilisée.
     */
    public static void main(String[] args) {
        System.out.println(PromptUtils.b("Bienvenue sur IBuySu.com, votre site d'achat-vente en ligne !"));
        System.out.println(PromptUtils.yel("Connexion en cours..."));

        try {
            system = IBuySu.getSystem();
        } catch (Exception e) {
            PromptUtils.printError("Echec de la connexion\nFermeture du système");
            prepareExit();
        }

        while (!exiting) {
            traiterChoix(IHM.deroulerMenu(PromptUtils.yel("Que désirez-vous faire ?"), system.getMenu()));
        }
        System.out.println(PromptUtils.b("A bientôt sur IBuySu.com!"));
        return;
    }

    /**
     * Dit au programme qu'il faut quitter après la fin de l'itération courante.
     */
    public static void prepareExit() {
        exiting = true;
    }

    /**
     * Traite une option de menu sélectionnée.
     * @param choix
     */
    public static void traiterChoix(String choix) {
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
            case "Déconnexion":
                System.out.println(system.deconnexion());
                break;
            case "Participer à une enchère":
                system.acheterObjetEnchere();
                break;
            case "Acheter un objet":
                // system.acheterUnObjet();
                break;
            case "Evaluer un utilisateur":
                system.evaluerUnUtilisateur();
                break;
            case "Créer une vente":
                system.creerUnVente();
                break;
            case "Gérer mes ventes":
                System.out.println(system.gererMesVentes());
                break;
            case "Quitter":
                prepareExit();
                break;
            default:
                System.out.println("Fonctionnalité non implémentée");
                break;
        }
    }
}
