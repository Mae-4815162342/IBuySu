package System;

/**
 * Données bancaires.
 */
public abstract class DonneesBancaires {
    protected String banque;
    protected String titulaire;

    /**
     * Formulaire permettant à l'utilisateur de saisir ses données bancaires.
     * @param type Type de données bancaires. Soit "RIB" pour un compte, soit "CB" pour une carte.
     */
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

    /**
     * Vérifie les données bancaires du vendeur.
     * @param formulaireVendeur Formulaire de vendeur.
     * @param formulaireBanque Formulaire de données bancaires.
     * @return true si les données bancaires du vendeur sont valides, false sinon.
     */
    public static boolean verifierVendeur(String[] formulaireVendeur, String[] formulaireBanque) {
        return formulaireBanque[0] == formulaireVendeur[1] && formulaireBanque[1] == formulaireVendeur[2];
    }

    @Override
    public String toString() {
        return this.titulaire + "(" + this.banque + ')';
    }
}
