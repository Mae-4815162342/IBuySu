package System;

import IHM.PromptUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Enchère.
 */
public class Enchere extends Produit {
    private int duree;
    private float meilleurPrix;
    private boolean estRecu;
    private boolean estVendu;
    private Acheteur meilleurAcheteur;
    private List<Offre> offres = new ArrayList<Offre>();

    /**
     * Crée une Enchère.
     * 
     * @param duree Durée de l'enchère.
     * @param titre Titre de l'enchère.
     * @param desc  Description de l'enchère.
     * @param v     Vendeur de l'enchère.
     * @param photo Photo de l'enchère.
     * @param prix  Prix du produit de l'enchère.
     * @param c     Catégorie du produit de l'enchère.
     */
    public Enchere(int duree, String titre, String desc, Vendeur v, String photo, float prix, Categorie c) {
        super(titre, desc, v, photo, prix, c);
        this.duree = duree;
        estRecu = false;
        estVendu = false;
    }

    /**
     * Crée une Enchère.
     * 
     * @param duree      Durée de l'enchère.
     * @param titre      Titre de l'enchère.
     * @param desc       Description de l'enchère.
     * @param v          Vendeur de l'enchère.
     * @param photo      Photo de l'enchère.
     * @param prix       Prix du produit de l'enchère.
     * @param c          Catégorie du produit de l'enchère.
     * @param isSold     Si l'enchère a été conclue.
     * @param isReceived
     */
    public Enchere(int duree, String titre, String desc, Vendeur v, String photo, float prix, Categorie c,
            boolean isSold, boolean isReceived) {
        super(titre, desc, v, photo, prix, c);
        this.duree = duree;
        estRecu = isReceived;
        estVendu = isSold;
    }

    /**
     * Si l'enchère is est reçue.
     */
    public boolean isEstRecu() {
        return estRecu;
    }

    /**
     * Détermine si l'enchère est reçue.
     */
    public void setEstRecu(boolean estRecu) {
        this.estRecu = estRecu;
    }

    /**
     * Si le produit de l'enchère is est vendu.
     */
    public boolean isEstVendu() {
        return estVendu;
    }

    /**
     * Détermine si le produit de l'enchère est vendu.
     */
    public void setEstVendu(boolean estVendu) {
        this.estVendu = estVendu;
    }

    /**
     * Crée une Enchère à l'aide d'un formulaire prérempli par l'utilisateur.
     * 
     * @param formulaireRempli Formulaire prérempli par l'utilisateur.
     * @param v                Vendeur de l'enchère.
     * @param c                Catégorie du produit.
     */
    public Enchere(String[] formulaireRempli, Vendeur v, Categorie c) {
        this(Integer.parseInt(formulaireRempli[0]), formulaireRempli[1], formulaireRempli[2], v, formulaireRempli[3],
                Float.parseFloat(formulaireRempli[4]), c);
    }

    /**
     * Ajoute une offre.
     * 
     * @param offre Offre à ajouter.
     */
    public void addOffre(Offre offre) {
        if (offre.getSomme() > this.meilleurPrix) {
            this.meilleurAcheteur = offre.getAcheteur();
            this.meilleurPrix = offre.getSomme();
        }
        offres.add(offre);
    }

    /**
     * Durée de l'enchère.
     * 
     * @return
     */
    private int getDuree() {
        // Automatically generated method. Please do not modify this code.
        return this.duree;
    }

    /**
     * Détermine la durée de l'enchère.
     * 
     * @param value Nouvelle durée de l'enchère.
     */
    private void setDuree(final int value) {
        // Automatically generated method. Please do not modify this code.
        this.duree = value;
    }

    @Override
    public String toString() {
        String res = "Enchère (encore " + this.duree + " jours)\n";
        res += super.description + '\n' + super.photo + '\n';
        res += "Vendu par " + this.vendeur.getAffichageMinimal() + " a " + this.prix + "€";
        res += PromptUtils.b(
                PromptUtils.mag("(prix de départ des enchères)\nEnchère la plus haute : " + this.meilleurPrix + "€\n"));
        res += PromptUtils.mag("Categorie : ") + categorie.getNom()
                + PromptUtils.mag("\nMot-clefs : ");
        if (motClefs.size() == 0) {
            res += PromptUtils.red("Aucun mot-clef");
        } else {
            for (MotClef mot : motClefs) {
                res += PromptUtils.b("" + mot.getNom() + "\t");
            }
        }
        return res;
    }

    /**
     * Formulaire permettant à l'utilisateur de saisir les informations d'une
     * enchère.
     * 
     * @return
     */
    public static String[] getFormulaire() {
        String[] res = { "durée", "titre", "description", "photo", "prix" };
        return res;
    }
}
