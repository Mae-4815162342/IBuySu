package System;

public class Offre {
    private float somme;
    private Enchere enchere;
    private Acheteur acheteur;

    public Offre(Enchere e, float prix, Acheteur acheteur) {
        this.somme = prix;
        this.enchere = e;
        this.acheteur = acheteur;
    }

    public Acheteur getAcheteur() {
        return acheteur;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public float getSomme() {
        return somme;
    }

    public String toString(){
        return acheteur.getAffichageMinimal() + " fait une offre de " + somme + "€ pour acheter " + enchere.getTitre();
    }
}
