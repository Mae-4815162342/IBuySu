package System;

/**
 * Donnees bancaires.
 */
public abstract class DonneesBancaires {
    protected String banque;
    protected String titulaire;

    /**
     * Formulaire permettant a l'utilisateur de saisir ses donnees bancaires.
     * @param type Type de donnees bancaires. Soit "RIB" pour un compte, soit "CB" pour une carte.
     */
    public static String[] getFormulaire(String type) {
        switch (type) {
            case "RIB":
                String[] rib = { "nom", "prenom", "banque", "iban", "bic" };
                return rib;
            case "CB":
                String[] cb = { "nom", "prenom", "banque", "date d'expiration", "cvc", "numero de carte" };
                return cb;
        }
        return null;
    }

    /**
     * Verifie les donnees bancaires du vendeur.
     * @param formulaireVendeur Formulaire de vendeur.
     * @param formulaireBanque Formulaire de donnees bancaires.
     * @return true si les donnees bancaires du vendeur sont valides, false sinon.
     */
    public static boolean verifierVendeur(String[] formulaireVendeur, String[] formulaireBanque) {
        return formulaireBanque[0] == formulaireVendeur[1] && formulaireBanque[1] == formulaireVendeur[2];
    }

    @Override
    public String toString() {
        return this.titulaire + "(" + this.banque + ')';
    }
}
