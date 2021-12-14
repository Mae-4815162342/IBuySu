package System;

import IHM.PromptUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Catégorie. Permet de trier les produits en circulation.
 */
public class Categorie {
    private final String nom;
    private List<Categorie> sousCategories;
    private List<Produit> produits = new ArrayList<Produit>();

    /**
     * Assigne à la Catégorie une liste de Produits.
     * 
     * @param produits Liste de produits.
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Assigne des sous-catégories à cette Catégorie.
     * 
     * @param sousCategories Liste de sous-catégories.
     */
    public void setSousCategories(List<Categorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    /**
     * Crée une Catégorie.
     * 
     * @param nom Nom de la Catégorie.
     */
    public Categorie(String nom) {
        this.nom = nom;
        sousCategories = null;
    }

    /**
     * Ajoute une sous-catégorie.
     * 
     * @param c Sous-catégorie à ajouter.
     */
    public void addSousCategorie(Categorie c) {
        if (sousCategories == null) {
            sousCategories = new ArrayList<Categorie>();
        }
        sousCategories.add(c);
    }

    /**
     * Ajoute un produit à la Catégorie.
     * 
     * @param p Produit à ajouter.
     */
    public void addProduit(Produit p) {
        produits.add(p);
    }

    /**
     * Supprime un produit de la Catégorie.
     * 
     * @param p Produit à supprimer.
     */
    public void deleteProduit(Produit p) {
        produits.remove(p);
    }

    /**
     * Liste de produits rangés sous cette Catégorie.
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * Sous-catégories de cette Catégorie.
     */
    public List<Categorie> getSousCategories() {
        return sousCategories;
    }

    @Override
    public String toString() {
        String res = this.nom + " :\n";
        if (produits == null)
            res += PromptUtils.red("Aucun produit dans cette catégorie");
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
     * Nom de la catégorie.
     */
    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }

}
