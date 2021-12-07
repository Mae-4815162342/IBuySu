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
    public static insert(Offre o) {
        // Se placer sur la table de Offres
        // Ajouter une nouvelle ligne avec les infos de o
        // Ajouter la clé étrangre de l'utilisateur
        // Ajouter la clé étrangre de l'enchère
        // Retourne eo
    }
}
