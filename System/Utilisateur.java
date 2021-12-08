package System;

public class Utilisateur {
    private static int id = 0;

    public Utilisateur(){
        id++;
    }

    public String[] getMenu(){
        String[] menu = {"Recherche", "Connexion", "Inscription Acheteur", "Inscription Vendeur", "Quitter"};
        return menu;
    }

    public String[] getMenuRecherche() {
        String[] menu = {"Rechercher par mot clef", "Rechercher par catégorie"};
        return menu;
    }

    public String getAffichageMinimal() { return null; }

}
