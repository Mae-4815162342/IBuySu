package System;

import java.util.ArrayList;
import java.util.List;
import BDD.*;

/**
 * Produit. À ne pas confondre avec Utilisateur.
 */
public class Produit {
    protected int id;
    protected static int nbProd = API.fetchNbProduct();
    protected String titre;
    protected String description;
    protected List<MotClef> motClefs = new ArrayList<MotClef>();
    protected Categorie categorie;
    protected Vendeur vendeur;
    protected String photo;
    protected float prix;
    protected boolean isSold;
    protected boolean isReceived;
    protected Contrat contrat = null;

    /**
     * Crée un produit.
     * 
     * @param titre Titre du produit.
     * @param desc  Description du produit.
     * @param v     Vendeur du produit.
     * @param photo Photo du produit.
     * @param prix  Prix du produit.
     * @param c     Catégorie du produit.
     */
    public Produit(String titre, String desc, Vendeur v, String photo, float prix, Categorie c) {
        nbProd++;
        this.id = nbProd;
        this.titre = titre;
        this.description = desc;
        this.vendeur = v;
        this.photo = photo;
        this.prix = prix;
        this.categorie = c;
        this.isSold = false;
        this.isReceived = false;
    }

    /**
     * Crée un produit.
     * 
     * @param id    ID du produit.
     * @param titre Titre du produit.
     * @param desc  Description du produit.
     * @param v     Vendeur du produit.
     * @param photo Photo du produit.
     * @param prix  Prix du produit.
     * @param c     Catégorie du produit.
     */
    public Produit(int id, String titre, String desc, Vendeur v, String photo, float prix, Categorie c) {
        this.id = id;
        this.titre = titre;
        this.description = desc;
        this.vendeur = v;
        this.photo = photo;
        this.prix = prix;
        this.categorie = c;
        this.isSold = false;
        this.isReceived = false;
    }

    /**
     * Crée un produit à partir d'un formulaire prérempli par l'utilisateur.
     * 
     * @param formulaireRempli Formulaire prérempli par l'utilisateur.
     * @param v                Vendeur du produit.
     * @param c                Catégorie du produit.
     */
    public Produit(String[] formulaireRempli, Vendeur v, Categorie c) {
        this(formulaireRempli[0], formulaireRempli[1], v, formulaireRempli[2], Float.parseFloat(formulaireRempli[3]),
                c);
    }

    /**
     * Ajoute un mot-clef pour le produit.
     * 
     * @param m Mot-clef.
     */
    public void addMotClef(MotClef m) {
        motClefs.add(m);
    }

    @Override
    public String toString() {
        String res = this.titre + "\n" + this.description + '\n' + this.photo + '\n';
        res += "Vendu par " + ((this.vendeur != null) ? this.vendeur.getAffichageMinimal() : "anonyme") + " a "
                + this.prix + "€\n";
        res += "Categorie : " + categorie.getNom() + "\n" + "Mot-clefs : ";
        if (motClefs.size() == 0) {
            res += "Aucun mot-clef";
        } else {
            for (MotClef mot : motClefs) {
                res += mot.getNom() + "\t";
            }
        }
        return res;
    }

    /**
     * Titre du produit.
     */
    public String getTitre() {
        // Automatically generated method. Please do not modify this code.
        return this.titre;
    }

    /**
     * Description du produit.
     */
    public String getDescription() {
        // Automatically generated method. Please do not modify this code.
        return this.description;
    }

    /**
     * Détermine la description du produit.
     * 
     * @param value
     */
    public void setDescription(final String value) {
        // Automatically generated method. Please do not modify this code.
        this.description = value;
    }

    /**
     * Photo du produit.
     * 
     * @return
     */
    public String getPhoto() {
        // Automatically generated method. Please do not modify this code.
        return this.photo;
    }

    /**
     * Détermine la photo du produit.
     */
    public void setPhoto(final String value) {
        // Automatically generated method. Please do not modify this code.
        this.photo = value;
    }

    /**
     * Prix de départ du produit.
     */
    public float getPrix_de_depart() {
        // Automatically generated method. Please do not modify this code.
        return this.prix;
    }

    /**
     * Détermine le prix de départ du produit.
     */
    public void setPrix_de_depart(final int value) {
        // Automatically generated method. Please do not modify this code.
        this.prix = value;
    }

    /**
     * Conclut la vente du produit avec un acheteur.
     * 
     * @param acheteur Utilisateur ayant acheté le produit.
     * @return Le contrat de vente de la transaction.
     */
    public Contrat conclureVente(Acheteur acheteur) {
        this.contrat = new Contrat(acheteur, this.vendeur, this, this.prix);
        this.isSold = true;
        return contrat;
    }

    /**
     * Permet au vendeur de refuser la vente.
     * 
     * @return le contrat précédent.
     */
    public Contrat refuserVente() {
        Contrat previousContract = this.contrat;
        this.contrat = null;
        return previousContract;
    }

    /**
     * Détermine le contrat du produit.
     */
    public void setContrat(Contrat contrat) {
        // Automatically generated method. Please do not modify this code.
        this.contrat = contrat;
    }

    /**
     * Récupère le formulaire permettant de remplir les informations d'un produit.
     */
    public static String[] getFormulaire() {
        String[] res = { "titre", "description", "photo", "prix" };
        return res;
    }

    /**
     * Récupère le vendeur.
     */
    public Vendeur getVendeur() {
        return vendeur;
    }

    /**
     * Si le produit est vendu.
     */
    public boolean getEstVendu() {
        return isSold;
    }

    /**
     * Si le produit a été reçu.
     */
    public boolean getEstRecu() {
        return isReceived;
    }

    /**
     * Catégorie du produit.
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * ID du produit.
     */
    public int getId() {
        return id;
    }
}
