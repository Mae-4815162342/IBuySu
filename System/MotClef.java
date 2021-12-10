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

    // méthode de comparaison de mot clef: lors de la recherche, le système compare
    // le mot entré par l'utilisateur avec
    // les mots clef enregistrés. Si la ressemblance est établie à plus de 80%, les
    // produits de ce mot apparaitront
    // dans le résultat de la recherche
    public boolean compare(String motClef) {
        int i = 0, j = 0;
        // compteur du nombre de caractères des deux mots communs et arrivant dans le
        // même ordre
        int compt = 0;
        char[] nomTemp1 = this.nom.toCharArray();
        char[] nomTemp2 = motClef.toCharArray();
        while (i < motClef.length() && j < this.nom.length()) {
            while (nomTemp1[j] != nomTemp2[i]) {
                i++;
                if (i > motClef.length())
                    break;
            }
            if (nomTemp1[j] == nomTemp2[i])
                compt++;
            j++;
        }
        return (compt / this.nom.length()) > 0.8;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }
}
