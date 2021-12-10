package System;
import IHM.IHM;
import BDD.API;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.result.FloatValueFactory;

public class IBuySu {
    private List<Categorie> categorie = new ArrayList<Categorie>();
    private List<MotClef> motClef = new ArrayList<MotClef>();
    private List<Inscrit> users = new ArrayList<Inscrit>();
    private List<Produit> products = new ArrayList<Produit>();
    private Inscrit user;
    private static IBuySu system;
    private IBuySu(){
        //user = new Inscrit();
        API.setConnexion();
        API.fetchData(this);
    }

    public List<Categorie> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categorie> categorie) {
        this.categorie = categorie;
    }

    public List<MotClef> getMotClef() {
        return motClef;
    }

    public void setMotClef(List<MotClef> motClef) {
        this.motClef = motClef;
    }

    public List<Inscrit> getUsers() {
        return users;
    }

    public void setUsers(List<Inscrit> users) {
        this.users = users;
    }

    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }

    public Inscrit getUser() {
        return user;
    }

    public void setUser(Inscrit user) {
        this.user = user;
    }

    public static void setSystem(IBuySu system) {
        IBuySu.system = system;
    }

    public static IBuySu getSystem(){
        if(system == null) system = new IBuySu();
        return system;
    }

    public String[] getMenu() {
        return user.getMenu();
    }

    public void connexion(){
        System.out.println("Connexion");
    }

    public void rechercher(){
        System.out.println("rechercher");
    }

    public void acheterObjetEnchere() { System.out.println("acheter objet enchère");}

    public void acheterUnObjet() { System.out.println("acheter un objet");}
    
    public Offre encherir(int productID, Float somme, Acheteur user) {

        // Créer un objet Enchère en cherchant dans la base de donnée l'enchère avec le même productID
        int prix = API.getMeilleurPrix(productID);
        // Créer un objet Offres avec sommme et user 
        Offre offer = new Offre(somme);
        if(prix < offer.getSomme()){
            /* bid.addOffre(offer);
            bid.setMeilleurPrix(offer.getSomme());
            bid.setMeilleurAcheteur(user); */
            API.addOffre(productID, user, somme);
            API.updateEnchere(bid);
            return offer;
        }
        else{
            return null;
        }
        // Comparer la somme et le prix actuel 
            // Si plus grande
                // Ajouter l'objet Offres dans la liste des Offres de l'enchères 
                // Mettre à jour le prix actuel de l'Enchère 
                // Mettre à jour la BDD
                // Retourner succès 
            // Sinon 
                // Retourner échec
    }
    public void evaluer(int valeur, String avis, Inscrit user, Inscrit evalue) {
        // Créer un objet Evaluation avec valeur, avis et user
        // Parcourir la liste d'évaluation de evalue
            // Si présence d'une evaluation du même user
                // Retourner echec 
            // Sinon 
                // Ajouter l'objet Evaluation dans la liste des Evaluation de evalue 
                // Mettre à jour la BDD 
                // Retourner succès
    }
    public void evaluerUnInscrit() { System.out.println("evaluer un Inscrit");}

    public String inscriptionAcheteur(){
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (acheteur)", formulaire);
        //on connecte l'acheteur automatiquement
        Acheteur user = new Acheteur(parametres);
        users.add(user);
        API.addAcheteur(user);
        return "Vous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }

    public String inscriptionVendeur(){
        //remplir les données du vendeur
        String[] formulaire = Vendeur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (vendeur)", formulaire);

        //remplir les données bancaires
        String typeDonnees = IHM.getTypeDonneesBancaires();
        String[] donneesBancaires = DonneesBancaires.getFormulaire(typeDonnees);
        String[] donneesRemplies = IHM.remplirFormulaire("Entrez vos données bancaires :", donneesBancaires);

        //vérification : si ce ne sont pas les mêmes noms et prénoms, échec, sinon les Données et le Vendeur sont créés
        Boolean donneesOK = DonneesBancaires.verifierVendeur(formulaire, donneesBancaires);
        if(!donneesOK) return "Echec : les données bancaires ne correspondent pas au vendeur";

        //création des objets
        DonneesBancaires dataBank = null;
        if(typeDonnees == "RIB") dataBank = new RIB(donneesBancaires);
        else dataBank = new CarteBancaire(donneesBancaires);
        Vendeur vendeur = new Vendeur(formulaire, dataBank);
        
        //connexion de l'Inscrit
        user = vendeur;
        users.add(user);
        return "Données bancaires correctes: " + dataBank.toString() + "\nVous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }
}
