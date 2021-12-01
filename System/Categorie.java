package System;
import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private final String nom;
    private List<Categorie> sousCategories;
    private ArrayList<Produit> produits = new ArrayList<Produit> ();

    public Categorie(String nom) {
        this.nom = nom;
        sousCategories = null;
    }

    public void addSousCategorie(Categorie c) {
        if(sousCategories == null) {
            sousCategories = new ArrayList<Categorie>();
        }
        sousCategories.add(c);
    }

    public void addProduit(Produit p) {
        produits.add(p);
    }

    public void deleteProduit(Produit p) {
        produits.remove(p);
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public String toString() {
        String res = this.nom + " :\n";
        for(Produit p : produits) {
            res += p.toString();
        }
        if(sousCategories != null) {
            for(Categorie c : sousCategories) {
                res += c.toString();
            }
        }
        return res;
    }

    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }

}
