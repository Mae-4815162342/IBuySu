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

    public static IBuySu getSystem() {
        if (system == null) {
            try {
                system = new IBuySu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return system;
    }

    public String[] getMenu() {
        return user.getMenu();
    }

    public String connexion(){
        String[] formulaire = Inscrit.getFormulaireConnexion();
        String[] identifiants = IHM.remplirFormulaire("Formulaire de connexion", formulaire);
        Inscrit connecting = null;
        for(Inscrit user: users) {
            if(user.getMail() == identifiants[0]) {
                connecting = user;
            }
        }
        if(connecting == null) return "Erreur: le mail ne correspond à aucun utilisateur";
        if(!connecting.verifMdp(identifiants[1])) return "Erreur: mot de passe incorrect";
        user = connecting;
        return "Vous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }

    public String deconnexion() {
        user = new Utilisateur();
        return "Vous êtes déconnecté";
    }

    public ArrayList<Produit> rechercherParMotClef(){
        String recherche = IHM.getUserIn("Entrer un mot clef:");
        ArrayList<Produit> resultats = new ArrayList<Produit>();
        for (MotClef mot : this.motClef) {
            if (mot.compare(recherche)) {
                List<Produit> temp = mot.getProduits();
                for (Produit p : temp) {
                    resultats.add(p);
                }
            }
        }
        return resultats;
    }

    public String[] getMenuCateg(List<Categorie> categs) {
        String[] res = new String[categs.size()];
        for (int i = 0; i < categs.size(); i++) {
            res[i] = categs.get(i).getNom();
        }
        return res;
    }

    public ArrayList<Produit> rechercherParCategorie() {
        String choixCateg = IHM.deroulerMenu("Selectionnez une categorie : ", getMenuCateg(categories));
        Categorie res = null;
        for (Categorie categ : categories) {
            if (categ.getNom() == choixCateg) {
                res = categ;
                break;
            }
        }
        List<Categorie> sousCateg = res.getSousCategories();
        if (res.getSousCategories() != null) {
            String choixSousCateg = IHM.deroulerMenu("Selectionnez une sous-categorie : ", getMenuCateg(sousCateg));
            for (Categorie categ : sousCateg) {
                if (categ.getNom() == choixSousCateg) {
                    res = categ;
                    break;
                }
            }
        }
        return res.getProduits();
    }

    public void rechercher() {
        // selection du type de recherche
        String[] menu = user.getMenuRecherche();
        String choix = IHM.deroulerMenu("Selectionnez un type de recherche", menu);
        ArrayList<Produit> res = null;
        switch (choix) {
            case "Rechercher par mot clef":
                res = rechercherParMotClef();
                break;
            case "Rechercher par catégorie":
                res = rechercherParCategorie();
                break;
            default:
                return;
        }
    }

    public void acheterObjetEnchere() {
        System.out.println("acheter objet enchère");
    }

    public void acheterUnObjet() {
        System.out.println("acheter un objet");
    }

    public void evaluerUnUtilisateur() {
        System.out.println("evaluer un utilisateur");
    }

    public String inscriptionAcheteur() {
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (acheteur)", formulaire);
        //on connecte l'acheteur automatiquement
        Acheteur user = new Acheteur(parametres);
        users.add(user);
        API.addAcheteur(user);
        return "Vous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }

    public String inscriptionVendeur() {
        // remplir les données du vendeur
        String[] formulaire = Vendeur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (vendeur)", formulaire);

        // remplir les données bancaires
        String[] menuTypeDonnees = { "RIB", "CB" };
        String typeDonnees = IHM.deroulerMenu(
                "Choisissez un type de données bancaires pour la vérification de vos données", menuTypeDonnees);
        String[] donneesBancaires = DonneesBancaires.getFormulaire(typeDonnees);
        String[] donneesRemplies = IHM.remplirFormulaire("Entrez vos données bancaires :", donneesBancaires);

        // vérification : si ce ne sont pas les mêmes noms et prénoms, échec, sinon les
        // Données et le Vendeur sont créés
        Boolean donneesOK = DonneesBancaires.verifierVendeur(formulaire, donneesBancaires);
        if (!donneesOK)
            return "Echec : les données bancaires ne correspondent pas au vendeur";

        // création des objets
        DonneesBancaires dataBank = null;
        if (typeDonnees == "RIB")
            dataBank = new RIB(donneesRemplies);
        else
            dataBank = new CarteBancaire(donneesRemplies);
        Vendeur vendeur = new Vendeur(parametres, dataBank);

        // connexion de l'utilisateur
        user = vendeur;
        users.add((Inscrit) user);
        API.addVendeur((Vendeur) user);
        return "Données bancaires correctes: " + dataBank.toString() + "\nVous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }
}
