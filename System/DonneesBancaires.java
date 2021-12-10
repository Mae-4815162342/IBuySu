package System;

public abstract class DonneesBancaires {
    protected String banque;
    protected String titulaire;

    public static String[] getFormulaire(String type) {
        switch (type) {
            case "RIB":
                String[] rib = { "nom", "prénom", "banque", "iban", "bic" };
                return rib;
            case "CB":
                String[] cb = { "nom", "prénom", "banque", "date d'expiration", "cvc", "numero de carte" };
                return cb;
        }
        return null;
    }

    public static boolean verifierVendeur(String[] formulaireVendeur, String[] formulaireBanque) {
        return formulaireBanque[0] == formulaireVendeur[1] && formulaireBanque[1] == formulaireVendeur[2];
    }

    public String toString() {
        return this.titulaire + "(" + this.banque + ')';
    }
}
