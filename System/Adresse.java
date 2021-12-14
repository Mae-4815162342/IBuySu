package System;

/**
 * Une adresse géographique.
 */
public class Adresse {
    private String ville;
    private int codePostal;
    private String pays;
    private int numeroRue;
    private String nomRue;

    /**
     * Crée une Adresse.
     * 
     * @param numRue Numéro de la rue.
     * @param nomRue Nom de la rue.
     * @param codePostal Code postal.
     * @param ville Ville.
     * @param pays Pays.
     */
    public Adresse(int numRue, String nomRue, int codePostal, String ville, String pays) {
        this.numeroRue = numRue;
        this.nomRue = nomRue;
        this.codePostal = this.verif(codePostal) ? codePostal : 00000;
        this.ville = ville;
        this.pays = pays;
    }

    /**
     * Vérifie que le format du code postal est valide.
     * @param codePostal
     * @return
     */
    public boolean verif(int codePostal) {
        return codePostal >= 0 && codePostal < 100_000;
    }

    @Override
    public String toString() {
        return numeroRue + " " + nomRue + ", " + codePostal + " " + ville + ", " + pays;
    }
}
