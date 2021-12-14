package System;

/**
 * Offre d'un Acheteur pour une Enchere.
 */
public class Offre {
    private float somme;
    private Enchere enchere;
    private Acheteur acheteur;

    /**
     * Cree une Offre.
     * 
     * @param e        Enchere concernee par l'Offre.
     * @param prix     Prix propose de l'Offre.
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
     * Enchere concernee par l'Offre.
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
        return acheteur.getAffichageMinimal() + " fait une offre de " + somme + " euros pour acheter " + enchere.getTitre();
    }
}
