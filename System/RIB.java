package System;

public class RIB extends DonneesBancaires {
    private String IBAN;
    private String BIC;

    public RIB(String titulaire, String banque, String iban, String bic) {
        this.titulaire = titulaire;
        this.banque = banque;
        this.IBAN = iban;
        this.BIC = bic;
    }

    public RIB(String[] formulaireRempli) {
        this(formulaireRempli[0] + ' ' + formulaireRempli[1], formulaireRempli[2], formulaireRempli[3],
                formulaireRempli[4]);
    }

    public String toString() {
        return super.toString() + " IBAN : " + IBAN + " BIC : " + BIC;
    }
}
