package System;

import IHM.PromptUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Enchere.
 */
public class Enchere extends Produit {
    private int duree;
    private float meilleurPrix;
    private boolean estRecu;
    private boolean estVendu;
    private Acheteur meilleurAcheteur;
    private List<Offre> offres = new ArrayList<Offre>();

    /**
     * Cree une Enchere.
     * 
     * @param duree Duree de l'enchere.
     * @param titre Titre de l'enchere.
     * @param desc  Description de l'enchere.
     * @param v     Vendeur de l'enchere.
     * @param photo Photo de l'enchere.
     * @param prix  Prix du produit de l'enchere.
     * @param c     Categorie du produit de l'enchere.
     */
    public Enchere(int duree, String titre, String desc, Vendeur v, String photo, float prix, Categorie c) {
        super(titre, desc, v, photo, prix, c);
        this.duree = duree;
        estRecu = false;
        estVendu = false;
    }

    /**
     * Cree une Enchere.
     * 
     * @param duree      Duree de l'enchere.
     * @param titre      Titre de l'enchere.
     * @param desc       Description de l'enchere.
     * @param v          Vendeur de l'enchere.
     * @param photo      Photo de l'enchere.
     * @param prix       Prix du produit de l'enchere.
     * @param c          Categorie du produit de l'enchere.
     * @param isSold     Si l'enchere a ete conclue.
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
     * Si l'enchere is est recue.
     */
    public boolean isEstRecu() {
        return estRecu;
    }

    /**
     * Determine si l'enchere est recue.
     */
    public void setEstRecu(boolean estRecu) {
        this.estRecu = estRecu;
    }

    /**
     * Si le produit de l'enchere is est vendu.
     */
    public boolean isEstVendu() {
        return estVendu;
    }

    /**
     * Determine si le produit de l'enchere est vendu.
     */
    public void setEstVendu(boolean estVendu) {
        this.estVendu = estVendu;
    }

    /**
     * Cree une Enchere a l'aide d'un formulaire prerempli par l'utilisateur.
     * 
     * @param formulaireRempli Formulaire prerempli par l'utilisateur.
     * @param v                Vendeur de l'enchere.
     * @param c                Categorie du produit.
     */
    public Enchere(String[] formulaireRempli, Vendeur v, Categorie c) {
        this(Integer.parseInt(formulaireRempli[0]), formulaireRempli[1], formulaireRempli[2], v, formulaireRempli[3],
                Float.parseFloat(formulaireRempli[4]), c);
    }

    /**
     * Ajoute une offre.
     * 
     * @param offre Offre a ajouter.
     */
    public void addOffre(Offre offre) {
        if (offre.getSomme() > this.meilleurPrix) {
            this.meilleurAcheteur = offre.getAcheteur();
            this.meilleurPrix = offre.getSomme();
        }
        offres.add(offre);
    }

    /**
     * Duree de l'enchere.
     * 
     * @return
     */
    private int getDuree() {
        // Automatically generated method. Please do not modify this code.
        return this.duree;
    }

    /**
     * Determine la duree de l'enchere.
     * 
     * @param value Nouvelle duree de l'enchere.
     */
    private void setDuree(final int value) {
        // Automatically generated method. Please do not modify this code.
        this.duree = value;
    }

    @Override
    public String toString() {
        String res = "Enchere (encore " + this.duree + " jours)\n";
        res += super.description + '\n' + super.photo + '\n';
        res += "Vendu par " + this.vendeur.getAffichageMinimal() + " a " + this.prix + " euros";
        res += PromptUtils.b(
                PromptUtils.mag("(prix de depart des encheres)\nEnchere la plus haute : " + this.meilleurPrix + " euros\n"));
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
     * Formulaire permettant a l'utilisateur de saisir les informations d'une
     * enchere.
     * 
     * @return
     */
    public static String[] getFormulaire() {
        String[] res = { "duree", "titre", "description", "photo", "prix" };
        return res;
    }
}
