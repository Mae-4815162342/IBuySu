package System;

import java.util.ArrayList;
import java.util.List;
import BDD.*;

public class Produit {
    protected int id;
    protected static int nbProd = API.fetchNbProduct();
    protected String titre;
    protected String description;
    protected List<MotClef> motClefs = new ArrayList<MotClef>();
    protected Categorie categorie;
    protected Vendeur vendeur;
    protected String photo;
    protected float prix;
    protected boolean isSold;
    protected boolean isReceived;
    protected Contrat contrat = null;

    public Produit(String titre, String desc, Vendeur v, String photo, float prix, Categorie c) {
        nbProd ++;
        this.id = nbProd;
        this.titre = titre;
        this.description = desc;
        this.vendeur = v;
        this.photo = photo;
        this.prix = prix;
        this.categorie = c;
        this.isSold = false;
        this.isReceived = false;
    }

    public Produit(int id, String titre, String desc, Vendeur v, String photo, float prix, Categorie c) {
        this.id = id;
        this.titre = titre;
        this.description = desc;
        this.vendeur = v;
        this.photo = photo;
        this.prix = prix;
        this.categorie = c;
        this.isSold = false;
        this.isReceived = false;
    }

    public Produit(String[] formulaireRempli, Vendeur v, Categorie c){
        this(formulaireRempli[0], formulaireRempli[1], v, formulaireRempli[2], Float.parseFloat(formulaireRempli[3]), c);
    }

    public void addMotClef(MotClef m) {
        motClefs.add(m);
    }


    public String toString() {
        String res = this.titre + "\n" + this.description + '\n' + this.photo + '\n';
        res += "Vendu par " + ((this.vendeur != null) ? this.vendeur.getAffichageMinimal() : "anonyme") + " a " + this.prix + "€\n";
        res += "Categorie : " + categorie.getNom() + "\n" + "Mot-clefs : ";
        if (motClefs.size() == 0) {
            res += "Aucun mot-clef";
        } else {
            for (MotClef mot : motClefs) {
                res += mot.getNom() + "\t";
            }
        }
        return res;
    }

    public String getTitre() {
        // Automatically generated method. Please do not modify this code.
        return this.titre;
    }

    public String getDescription() {
        // Automatically generated method. Please do not modify this code.
        return this.description;
    }

    public void setDescription(final String value) {
        // Automatically generated method. Please do not modify this code.
        this.description = value;
    }

    public String getPhoto() {
        // Automatically generated method. Please do not modify this code.
        return this.photo;
    }

    public void setPhoto(final String value) {
        // Automatically generated method. Please do not modify this code.
        this.photo = value;
    }

    public float getPrix_de_depart() {
        // Automatically generated method. Please do not modify this code.
        return this.prix;
    }

    public void setPrix_de_depart(final int value) {
        // Automatically generated method. Please do not modify this code.
        this.prix = value;
    }

    public Contrat conclureVente(Acheteur acheteur) {
        this.contrat = new Contrat(acheteur, this.vendeur, this, this.prix);
        this.isSold = true;
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        // Automatically generated method. Please do not modify this code.
        this.contrat = contrat;
    }


    public static String[] getFormulaire() {
        String[] res = {"titre", "description", "photo", "prix"};
        return res;
    }

    public Vendeur getVendeur(){return vendeur;}

    public boolean getEstVendu() {
        return isSold;
    }

    public boolean getEstRecu() {
        return isReceived;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public int getId() {
        return id;
    }

}
