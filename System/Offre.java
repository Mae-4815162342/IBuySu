package System;

public class Offre {
    private float somme;
    private Acheteur acheteur;
    
    public Offre(float prix) {
        this.somme = prix;
    }

    public Acheteur getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Acheteur acheteur) {
        this.acheteur = acheteur;
    }

    public float getSomme() {
        return somme;
    }
    public void setSomme(int somme) {
        this.somme = somme;
    }


    public String toString() {
        return acheteur.getAffichageMinimal() + " fait une offre de " + somme + "€ pour acheter " + enchere.getTitre();
    } 
}
