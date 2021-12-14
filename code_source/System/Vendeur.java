package System;

import java.util.*;

/**
 * Utilisateur exercant la vente de produits.
 */
public class Vendeur extends Inscrit {
    /**
     * Donnees bancaires du vendeur.
     */
    public DonneesBancaires donneesBanque;
    /**
     * Annonces du vendeur.
     */
    public ArrayList<Produit> annonces = new ArrayList<>();

    /**
     * Cree un vendeur.
     * @param pseudo Pseudo du vendeur.
     * @param nom Nom du vendeur.
     * @param prenom Prenom du vendeur.
     * @param numTel Numero de telephone du vendeur.
     * @param mail Mail du vendeur.
     * @param mdp Mot de passe du vendeur.
     * @param numRue Numero de la rue du vendeur.
     * @param nomRue Nom de la rue du vendeur.
     * @param codePostal Code postal du vendeur.
     * @param ville Ville du vendeur.
     * @param pays Pays du vendeur.
     * @param bankData Donnees bancaires du vendeur.
     */
    public Vendeur(String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays, DonneesBancaires bankData) {
        super(pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
        this.donneesBanque = bankData;
    }

    /**
     * Cree un vendeur.
     * @param id ID du vendeur.
     * @param pseudo Pseudo du vendeur.
     * @param nom Nom du vendeur.
     * @param prenom Prenom du vendeur.
     * @param numTel Numero de telephone du vendeur.
     * @param mail Mail du vendeur.
     * @param mdp Mot de passe du vendeur.
     * @param numRue Numero de la rue du vendeur.
     * @param nomRue Nom de la rue du vendeur.
     * @param codePostal Code postal du vendeur.
     * @param ville Ville du vendeur.
     * @param pays Pays du vendeur.
     * @param bankData Donnees bancaires du vendeur.
     */
    public Vendeur(int id, String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
                   String nomRue, int codePostal, String ville, String pays, DonneesBancaires bankData) {
        super(id, pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
        this.donneesBanque = bankData;
    }

    /**
     * Cree un vendeur a partir d'un formulaire prerempli par l'utilisateur.
     * @param formulaireRempli Formulaire prerempli par l'utilisateur.
     * @param dataBank Donnees bancaires de l'utilisateur.
     */
    public Vendeur(String[] formulaireRempli, DonneesBancaires dataBank) {
        this(formulaireRempli[0], formulaireRempli[1], formulaireRempli[2], Integer.parseInt(formulaireRempli[3]),
                formulaireRempli[4], formulaireRempli[5], Integer.parseInt(formulaireRempli[6]), formulaireRempli[7],
                Integer.parseInt(formulaireRempli[8]), formulaireRempli[9], formulaireRempli[10], dataBank);
    }

    /**
     * Permet au vendeur d'ajouter un produit.
     * @param p
     */
    public void addProduit(Produit p) {
        annonces.add(p);
    }

    /**
     * Permet au vendeur de retirer un de ses produits.
     */
    public void removeProduit(Produit p) {
        annonces.remove(p);
    }

    /**
     * Recupere les donnees bancaires du vendeur.
     */
    public DonneesBancaires getDonneesBanque() {
        // Automatically generated method. Please do not modify this code.
        return this.donneesBanque;
    }

    /**
     * Recupere le menu du vendeur.
     */
    @Override
    public String[] getMenu() {
        String[] menu = { "Gerer mes ventes", "Creer une vente" };
        String[] menuUser = super.getMenu();
        int menuLen = menu.length;
        int menuUserLen = menuUser.length;
        String[] newMenu = new String[menuLen + menuUserLen];
        System.arraycopy(menu, 0, newMenu, 0, menuLen);
        System.arraycopy(menuUser, 0, newMenu, menuLen, menuUserLen);
        return newMenu;
    }
}
