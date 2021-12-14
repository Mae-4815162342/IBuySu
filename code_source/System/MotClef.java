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
     * Cree un mot-clef.
     * 
     * @param mot Nom du mot-clef.
     * @param p   Premier produit possedant ce mot-clef.
     */
    public MotClef(String mot, Produit p) {
        this.nom = mot;
        produits.add(p);
    }

    /**
     * Assigne une liste de produits a ce mot-clef.
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Recupere tous les produits possedant ce mot-clef.
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
