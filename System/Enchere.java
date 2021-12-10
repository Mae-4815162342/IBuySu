package System;
import java.util.ArrayList;
import java.util.List;

public class Enchere extends Produit {
    private int duree;
    private float meilleurPrix;
    private Acheteur meilleurAcheteur;
    private List<Offre> offres = new ArrayList<Offre>();

    public Enchere(int duree,String titre, String desc, Vendeur v, int prix, boolean estVendu, boolean estReçu){
        super(titre, desc, v, prix, estVendu, estReçu);
        this.duree = duree;
    }

    public void addOffre(Offre offre) {
        if(offre.getSomme() > this.meilleurPrix) {
            this.meilleurAcheteur = offre.getAcheteur();
            this.meilleurPrix = offre.getSomme();
        }
        offres.add(offre);
    }
    public float getMeilleurPrix() {
        // Automatically generated method. Please do not modify this code.
        return this.meilleurPrix;
    }

    public void setMeilleurPrix(final float value) {
        // Automatically generated method. Please do not modify this code.
        this.meilleurPrix = value;
    }
    public Acheteur getMeilleurAcheteur() {
        // Automatically generated method. Please do not modify this code.
        return this.meilleurAcheteur;
    }

    public void setMeilleurAcheteur(final Acheteur ach) {
        // Automatically generated method. Please do not modify this code.
        this.meilleurAcheteur = ach;
    }
    public int getDuree() {
        // Automatically generated method. Please do not modify this code.
        return this.duree;
    }

    public void setDuree(final int value) {
        // Automatically generated method. Please do not modify this code.
        this.duree = value;
    }

    public String toString() {
        String res = "Enchère(encore " + this.duree + " jours)\n";
        res += super.description + '\n';
        res += "Vendu par " + this.vendeur.getAffichageMinimal() + " a " + this.prix + "€";
        res += "(prix de départ des enchères)\nEnchère la plus haute : " + this.meilleurPrix + "€\n";
        res += "Categorie : " + categorie.getNom() + "\n" + "Mot-clefs : ";
        if(motClefs.size() == 0) {
            res+= "Aucun mot-clef";
        } else {
            for (MotClef mot : motClefs) {
                res += mot.getNom() + "\t";
            }
        }
        return res;
    }

}
