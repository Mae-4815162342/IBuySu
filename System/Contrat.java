package System;

/**
 * Contrat Acheteur-Vendeur certifiant la transation d'un produit.
 */
public class Contrat {
    private Vendeur vendeur;
    private Acheteur acheteur;
    private Produit produit;
    private float prix_final;
    private boolean isConcluded;

    /**
     * Crée un contrat Acheteur-Vendeur.
     * 
     * @param acheteur Acheteur.
     * @param vendeur  Vendeur.
     * @param produit  Produit en cours d'échange.
     * @param prix     Prix du produit.
     */
    public Contrat(Acheteur acheteur, Vendeur vendeur, Produit produit, float prix) {
        this.vendeur = vendeur;
        vendeur.addContrat(this);
        this.acheteur = acheteur;
        acheteur.addContrat(this);
        this.produit = produit;
        this.prix_final = prix;
        this.isConcluded = false;
        produit.setContrat(this);
    }

    /**
     * Conclut le contrat.
     */
    public void concludeContrat() {
        this.isConcluded = true;
    }

    /**
     * Si le contrat est conclu ou non.
     */
    public boolean getIsConcluded() {
        return this.isConcluded;
    }

    /**
     * Acheteur sur le contrat.
     */
    public Acheteur getAcheteur() {
        return acheteur;
    }

    /**
     * Vendeur sur le contrat.
     */
    public Vendeur getVendeur() {
        return vendeur;
    }

    @Override
    public String toString() {
        String s = "Vendeur : " + vendeur.getNom() + " " + vendeur.getPrenom() + "\nAcheteur : " + acheteur.getNom()
                + " " + acheteur.getPrenom() + "\nProduit : " + produit.toString() + "\nPrix de l'acheteur : "
                + prix_final + "\n";
        if (isConcluded)
            return "CONTRAT CONCLU\n" + s;
        return "CONTRAT EN COURS\n" + s;
    }

}
