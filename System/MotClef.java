package System;

import java.util.ArrayList;
import java.util.List;

/**
 * Mot-clef de recherche.
 */
public class MotClef {
    private String nom;
    private List<Produit> produits = new ArrayList<Produit>();

    /**
     * Crée un mot-clef.
     * 
     * @param mot Nom du mot-clef.
     * @param p   Premier produit possédant ce mot-clef.
     */
    public MotClef(String mot, Produit p) {
        this.nom = mot;
        produits.add(p);
    }

    /**
     * Assigne une liste de produits à ce mot-clef.
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Récupère tous les produits possédant ce mot-clef.
     * 
     * @return
     */
    public ArrayList<Produit> getProduits() {
        return (ArrayList<Produit>) produits;
    }

    /**
     * Ajoute un produit sous ce mot-clef.
     * 
     * @param p
     */
    public void addProduit(Produit p) {
        produits.add(p);
    }

    /**
     * Nom du mot-clef.
     */
    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }
}
