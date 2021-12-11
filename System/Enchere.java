package System;

import java.util.ArrayList;
import java.util.List;

public class Enchere extends Produit {
    private int duree;
    private float meilleurPrix;
    private boolean estRecu;
    private boolean estVendu;
    private Acheteur meilleurAcheteur;
    private List<Offre> offres = new ArrayList<Offre>();

    public Enchere(int duree, String titre, String desc, Vendeur v, String photo, int prix, Categorie c, boolean isSold, boolean isReceived) {
        super(titre, desc, v, photo, prix, c);
        this.duree = duree;
        estRecu = isReceived;
        estVendu = isSold;
    }

    public boolean isEstRecu() {
        return estRecu;
    }

    public void setEstRecu(boolean estRecu) {
        this.estRecu = estRecu;
    }

    public boolean isEstVendu() {
        return estVendu;
    }

    public void setEstVendu(boolean estVendu) {
        this.estVendu = estVendu;
    }

    public void addOffre(Offre offre) {
        if (offre.getSomme() > this.meilleurPrix) {
            this.meilleurAcheteur = offre.getAcheteur();
            this.meilleurPrix = offre.getSomme();
        }
        offres.add(offre);
    }

    private int getDuree() {
        // Automatically generated method. Please do not modify this code.
        return this.duree;
    }

    private void setDuree(final int value) {
        // Automatically generated method. Please do not modify this code.
        this.duree = value;
    }

    public String toString() {
        String res = "Enchère (encore " + this.duree + " jours)\n";
        res += super.description + '\n' + super.photo + '\n';
        res += "Vendu par " + this.vendeur.getAffichageMinimal() + " a " + this.prix + "€";
        res += "(prix de départ des enchères)\n\u001b[35mEnchère la plus haute :\u001b[0m \u001b[1m" + this.meilleurPrix + "€\u001b[0m\n";
        res += "\u001b[35mCategorie :\u001b[0m " + categorie.getNom()
            + "\n\u001b[35mMot-clefs :\u001b[0m ";
        if (motClefs.size() == 0) {
            res += "\u001b[31mAucun mot-clef\u001b[0m";
        } else {
            for (MotClef mot : motClefs) {
                res += "\u001b[1m" + mot.getNom() + "\u001b[0m\t";
            }
        }
        return res;
    }

}
