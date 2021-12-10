package System;

public class CarteBancaire extends DonneesBancaires {
    private String dateExpiration;
    private int cvc;
    private String numeroCarte;

    public CarteBancaire(String titulaire, String banque, String dateExpiration, int cvc, String numeroCarte) {
        this.titulaire = titulaire;
        this.banque = banque;
        this.cvc = cvc;
        this.dateExpiration = dateExpiration;
        this.numeroCarte = numeroCarte;
    }

    public CarteBancaire(String[] formulaireRempli) {
        this(formulaireRempli[0] + ' ' + formulaireRempli[1], formulaireRempli[2], formulaireRempli[3],
                Integer.parseInt(formulaireRempli[4]), formulaireRempli[5]);
    }

    // on n'affiche jamais le cvc et la date d'expiration, qui sont confidentiels.
    // On affiche uniquement les quatre
    // dernier chiffre du numéro.
    public String toString() {
        return super.toString() + "\nCarte n° : **** **** **** "
                + numeroCarte.substring(numeroCarte.length() - 4, numeroCarte.length());
    }
}
