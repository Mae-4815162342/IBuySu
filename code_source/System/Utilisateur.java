package System;

/**
 * Utilisateur de iBuySu.com. N'est pas forcement inscrit.
 */
public class Utilisateur {
    private static int id = 0;

    /**
     * Cree un utilisateur.
     */
    public Utilisateur() {
        id++;
    }

    /**
     * Recupere le menu de l'utilisateur.
     */
    public String[] getMenu() {
        String[] menu = { "Recherche", "Connexion", "Inscription Acheteur", "Inscription Vendeur", "Quitter" };
        return menu;
    }

    /**
     * Recupere le menu de recherche pour l'utilisateur.
     * @return
     */
    public String[] getMenuRecherche() {
        String[] menu = { "Rechercher par mot clef", "Rechercher par categorie" };
        return menu;
    }

    /**
     * Recupere l'affichage minimal d'un utilisateur.
     */
    public String getAffichageMinimal() {
        return null;
    }

}
