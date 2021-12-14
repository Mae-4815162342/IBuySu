package System;

/**
 * Utilisateur de iBuySu.com. N'est pas forcément inscrit.
 */
public class Utilisateur {
    private static int id = 0;

    /**
     * Crée un utilisateur.
     */
    public Utilisateur() {
        id++;
    }

    /**
     * Récupère le menu de l'utilisateur.
     */
    public String[] getMenu() {
        String[] menu = { "Recherche", "Connexion", "Inscription Acheteur", "Inscription Vendeur", "Quitter" };
        return menu;
    }

    /**
     * Récupère le menu de recherche pour l'utilisateur.
     * @return
     */
    public String[] getMenuRecherche() {
        String[] menu = { "Rechercher par mot clef", "Rechercher par catégorie" };
        return menu;
    }

    /**
     * Récupère l'affichage minimal d'un utilisateur.
     */
    public String getAffichageMinimal() {
        return null;
    }

}
