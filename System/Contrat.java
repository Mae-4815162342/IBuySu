package System;

public class Contrat {
    private Vendeur vendeur;
    private Acheteur acheteur;
    private Produit produit;
    private float prix_final;
    private boolean isConcluded;

    public Contrat(Acheteur acheteur, Vendeur vendeur, Produit produit, float prix){
        this.vendeur = vendeur;
        vendeur.addContrat(this);
        this.acheteur = acheteur;
        acheteur.addContrat(this);
        this.produit = produit;
        this.prix_final = prix;
        this.isConcluded = false;
        produit.setContrat(this);
    }

    public void concludeContrat(){
        this.isConcluded = true;
    }

    public boolean getIsConcluded(){
        return this.isConcluded;
    }

}
