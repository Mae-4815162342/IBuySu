package System;

import java.util.ArrayList;
import java.util.List;

public class MotClef {
    private String nom;
    private List<Produit> produits = new ArrayList<Produit>();

    public MotClef(String mot, Produit p) {
        this.nom = mot;
        produits.add(p);
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public ArrayList<Produit> getProduits() {
        return (ArrayList<Produit>) produits;
    }

    public void addProduit(Produit p) {
        produits.add(p);
    }

    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }
}
