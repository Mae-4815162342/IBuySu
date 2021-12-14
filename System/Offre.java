package System;

/**
 * Offre d'un Acheteur pour une Enchère.
 */
public class Offre {
    private float somme;
    private Enchere enchere;
    private Acheteur acheteur;

    /**
     * Crée une Offre.
     * 
     * @param e        Enchère concernée par l'Offre.
     * @param prix     Prix proposé de l'Offre.
     * @param acheteur Acheteur proposant l'Offre.
     */
    public Offre(Enchere e, float prix, Acheteur acheteur) {
        this.somme = prix;
        this.enchere = e;
        this.acheteur = acheteur;
    }

    /**
     * Acheteur proposant l'Offre.
     */
    public Acheteur getAcheteur() {
        return acheteur;
    }

    /**
     * Enchère concernée par l'Offre.
     */
    public Enchere getEnchere() {
        return enchere;
    }

    /**
     * Prix de l'Offre.
     */
    public float getSomme() {
        return somme;
    }

    @Override
    public String toString() {
        return acheteur.getAffichageMinimal() + " fait une offre de " + somme + "€ pour acheter " + enchere.getTitre();
    }
}
