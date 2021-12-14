package System;

/**
 * Carte bancaire.
 */
public class CarteBancaire extends DonneesBancaires {
    private String dateExpiration;
    private int cvc;
    private String numeroCarte;

    /**
     * Cree une carte bancaire.
     * @param titulaire Titulaire de la carte bancaire.
     * @param banque Banque du titulaire.
     * @param dateExpiration Date d'expiration de la carte bancaire.
     * @param cvc CVC de la carte bancaire.
     * @param numeroCarte Numero de la carte bancaire.
     */
    public CarteBancaire(String titulaire, String banque, String dateExpiration, int cvc, String numeroCarte) {
        this.titulaire = titulaire;
        this.banque = banque;
        this.cvc = cvc;
        this.dateExpiration = dateExpiration;
        this.numeroCarte = numeroCarte;
    }

    /**
     * Cree une carte bancaire a l'aire d'un formulaire rempli par l'utilisateur.
     * @param formulaireRempli Formulaire prerempli par l'utilisateur.
     */
    public CarteBancaire(String[] formulaireRempli) {
        this(formulaireRempli[0] + ' ' + formulaireRempli[1], formulaireRempli[2], formulaireRempli[3],
                Integer.parseInt(formulaireRempli[4]), formulaireRempli[5]);
    }

    @Override
    public String toString() {
        // On n'affiche jamais le cvc et la date d'expiration, qui sont confidentiels.
        // On affiche uniquement les quatre derniers chiffres du numero.
        return super.toString() + " Carte n. : **** **** **** "
                + numeroCarte.substring(numeroCarte.length() - 4, numeroCarte.length());
    }
}
