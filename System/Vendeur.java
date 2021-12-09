package System;

import java.util.*;

public class Vendeur extends Inscrit {
    public DonneesBancaires donneesBanque;
    public ArrayList<Produit> annonces = new ArrayList<>();

    public Vendeur(String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays, DonneesBancaires bankData) {
        super(pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
        this.donneesBanque = bankData;
    }

    public Vendeur(String[] formulaireRempli, DonneesBancaires dataBank) {
        this(formulaireRempli[0], formulaireRempli[1], formulaireRempli[2], Integer.parseInt(formulaireRempli[3]),
                formulaireRempli[4], formulaireRempli[5], Integer.parseInt(formulaireRempli[6]), formulaireRempli[7],
                Integer.parseInt(formulaireRempli[8]), formulaireRempli[9], formulaireRempli[10], dataBank);
    }

    public void addProduit(Produit p) {
        annonces.add(p);
    }

    public void removeProduit(Produit p) {
        annonces.remove(p);
    }

    public DonneesBancaires getDonneesBanque() {
        // Automatically generated method. Please do not modify this code.
        return this.donneesBanque;
    }

    @Override
    public String[] getMenu() {
        String[] menu = { "Gérer mes ventes", "Créer une vente" };
        String[] menuUser = super.getMenu();
        int menuLen = menu.length;
        int menuUserLen = menuUser.length;
        String[] newMenu = new String[menuLen + menuUserLen];
        System.arraycopy(menu, 0, newMenu, 0, menuLen);
        System.arraycopy(menuUser, 0, newMenu, menuLen, menuUserLen);
        return newMenu;
    }
}
