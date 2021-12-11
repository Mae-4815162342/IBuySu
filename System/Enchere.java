package System;

import IHM.PromptUtils;
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
        res += PromptUtils.b(PromptUtils.mag("(prix de départ des enchères)\nEnchère la plus haute : " + this.meilleurPrix + "€\n"));
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

}
