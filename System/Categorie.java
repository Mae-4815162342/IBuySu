package System;

import IHM.PromptUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Categorie. Permet de trier les produits en circulation.
 */
public class Categorie {
    private final String nom;
    private List<Categorie> sousCategories;
    private List<Produit> produits = new ArrayList<Produit>();

    /**
     * Assigne a la Categorie une liste de Produits.
     * 
     * @param produits Liste de produits.
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Assigne des sous-categories a cette Categorie.
     * 
     * @param sousCategories Liste de sous-categories.
     */
    public void setSousCategories(List<Categorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    /**
     * Cree une Categorie.
     * 
     * @param nom Nom de la Categorie.
     */
    public Categorie(String nom) {
        this.nom = nom;
        sousCategories = null;
    }

    /**
     * Ajoute une sous-categorie.
     * 
     * @param c Sous-categorie a ajouter.
     */
    public void addSousCategorie(Categorie c) {
        if (sousCategories == null) {
            sousCategories = new ArrayList<Categorie>();
        }
        sousCategories.add(c);
    }

    /**
     * Ajoute un produit a la Categorie.
     * 
     * @param p Produit a ajouter.
     */
    public void addProduit(Produit p) {
        produits.add(p);
    }

    /**
     * Supprime un produit de la Categorie.
     * 
     * @param p Produit a supprimer.
     */
    public void deleteProduit(Produit p) {
        produits.remove(p);
    }

    /**
     * Liste de produits ranges sous cette Categorie.
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * Sous-categories de cette Categorie.
     */
    public List<Categorie> getSousCategories() {
        return sousCategories;
    }

    @Override
    public String toString() {
        String res = this.nom + " :\n";
        if (produits == null)
            res += PromptUtils.red("Aucun produit dans cette categorie");
        else
            for (Produit p : produits) {
                res += p.toString();
            }
        if (sousCategories != null) {
            for (Categorie c : sousCategories) {
                res += c.toString();
            }
        }
        return res;
    }

    /**
     * Nom de la categorie.
     */
    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }

}
