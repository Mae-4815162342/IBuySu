package IHM;

import System.IBuySu;

/**
 * Entree du programme.
 */
public class Main {
    private static boolean exiting = false;
    private static IBuySu system;

    /**
     * Entree entrale du programme. 
     * @param args Chose jamais utilisee.
     */
    public static void main(String[] args) {
        System.out.println(PromptUtils.b("Bienvenue sur IBuySu.com, votre site d'achat-vente en ligne !"));
        System.out.println(PromptUtils.yel("Connexion en cours..."));

        try {
            system = IBuySu.getSystem();
        } catch (Exception e) {
            PromptUtils.printError("Echec de la connexion\nFermeture du systeme");
            prepareExit();
        }

        while (!exiting) {
            traiterChoix(IHM.deroulerMenu(PromptUtils.yel("Que desirez-vous faire ?"), system.getMenu()));
        }
        System.out.println(PromptUtils.b("A bientot sur IBuySu.com!"));
        return;
    }

    /**
     * Dit au programme qu'il faut quitter apres la fin de l'iteration courante.
     */
    public static void prepareExit() {
        exiting = true;
    }

    /**
     * Traite une option de menu selectionnee.
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
            case "Deconnexion":
                System.out.println(system.deconnexion());
                break;
            case "Participer a une enchere":
                system.acheterObjetEnchere();
                break;
            case "Acheter un objet":
                // system.acheterUnObjet();
                break;
            case "Evaluer un utilisateur":
                system.evaluerUnUtilisateur();
                break;
            case "Creer une vente":
                system.creerUnVente();
                break;
            case "Gerer mes ventes":
                System.out.println(system.gererMesVentes());
                break;
            case "Quitter":
                prepareExit();
                break;
            default:
                System.out.println("Fonctionnalite non implementee");
                break;
        }
    }
}
