package System;

/**
 * RIB d'un compte bancaire.
 */
public class RIB extends DonneesBancaires {
    private String IBAN;
    private String BIC;

    /**
     * Cree un RIB.
     * @param titulaire Titulaire du compte bancaire.
     * @param banque Banque du compte bancaire.
     * @param iban IBAN du compte bancaire.
     * @param bic BIC du compte bancaire.
     */
    public RIB(String titulaire, String banque, String iban, String bic) {
        this.titulaire = titulaire;
        this.banque = banque;
        this.IBAN = iban;
        this.BIC = bic;
    }

    /**
     * Cree un RIB a l'aide d'un formulaire prerempli par l'utilisateur.
     * @param formulaireRempli Formulaire prerempli par l'utilisateur.
     */
    public RIB(String[] formulaireRempli) {
        this(formulaireRempli[0] + ' ' + formulaireRempli[1], formulaireRempli[2], formulaireRempli[3],
                formulaireRempli[4]);
    }

    @Override
    public String toString() {
        return super.toString() + " IBAN : " + IBAN + " BIC : " + BIC;
    }
}
