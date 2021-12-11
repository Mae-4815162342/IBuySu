package IHM;

import System.IBuySu;
import System.Inscrit;

public class Main {
    private static boolean exiting = false;
    private static IBuySu system;

    public static void main(String[] args) {
        System.out.println(PromptUtils.b("Bienvenue sur IBuySu.com, votre site d'achat-vente en ligne !"));
        System.out.println(PromptUtils.yel("Connexion en cours..."));

        try {
            system = IBuySu.getSystem();
        } catch (Exception e) {
            PromptUtils.printError("Echec de la connexion\nFermeture du système");
            exit();
        }

        while (!exiting) {
            traiterChoix(IHM.deroulerMenu(PromptUtils.yel("Que désirez-vous faire ?"), system.getMenu()));
        }
        System.out.println(PromptUtils.b("A bientôt sur IBuySu.com!"));
        return;
    }

    public static void exit() {
        exiting = true;
    }

    public static void traiterChoix(String choix) {
        switch (choix) {
            case "Recherche":
                system.rechercher();
                break;
            case "Inscription Vendeur":
                system.inscriptionVendeur();
                break;
            case "Inscription Acheteur":
                system.inscriptionAcheteur();
                break;
            case "Connexion":
                String[] formulaire = Inscrit.getFormulaireConnexion();
                String[] identifiants = IHM.remplirFormulaire(PromptUtils.b("Formulaire de connexion"), formulaire);
                if (system.connect(identifiants))
                    PromptUtils.printSuccess("Vous êtes connecté en tant que : " + system.getUser().getAffichageMinimal());
                else
                    PromptUtils.printError("Identifiants incorrects");
                break;
            case "Déconnexion":
                if (system.deconnexion())
                    PromptUtils.printSuccess("Vous êtes déconnecté");
                else
                    PromptUtils.printError("Impossible de se déconnecter");
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
                exit();
                break;
            default:
                break;
        }
    }
}
