package System;

import IHM.IHM;
import BDD.API;
import java.util.ArrayList;
import java.util.List;

public class IBuySu {
    private List<Categorie> categories = new ArrayList<Categorie>();
    private List<MotClef> motClef = new ArrayList<>();
    private List<Inscrit> users = new ArrayList<>();
    private Utilisateur user;
    private static IBuySu system;

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

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    private IBuySu() throws Exception {
        user = new Utilisateur();
        try {
            API.setConnexion();
            API.fetchUsers(this);
        } catch (Exception e) {
            throw e;
        }
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
        String[] identifiants = IHM.remplirFormulaire("\u001b[1m\u001b[33mFormulaire de connexion\u001b[0m", formulaire);
        Inscrit connecting = null;
        for(Inscrit user: users) {
            if(user.getMail().equalsIgnoreCase(identifiants[0])) {
                connecting = user;
            }
        }
        if(connecting == null) return "\u001b[31mErreur: le mail ne correspond à aucun utilisateur\u001b[0m";
        if(!connecting.verifMdp(identifiants[1])) return "\u001b[31mErreur: mot de passe incorrect\u001b[0m";
        user = connecting;
        return "\u001b[32mVous êtes connecté en tant que : " + user.getAffichageMinimal() + "\u001b[0m\n";
    }

    public String deconnexion() {
        user = new Utilisateur();
        return "\u001b[33mVous êtes déconnecté\u001b[0m";
    }

    public ArrayList<Produit> rechercherParMotClef(){
        String recherche = IHM.getUserIn("\u001b[33mEntrer un mot-clef :\u001b[0m ");
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

    public List<Produit> rechercherParCategorie() {
        String choixCateg = IHM.deroulerMenu("\u001b[33mSelectionnez une categorie :\u001b[0m", getMenuCateg(categories));
        Categorie res = null;
        for (Categorie categ : categories) {
            if (categ.getNom() == choixCateg) {
                res = categ;
                break;
            }
        }
        API.fetchSousCategorie(res);
        List<Categorie> sousCateg = res.getSousCategories();
        if (res.getSousCategories() != null) {
            String choixSousCateg = IHM.deroulerMenu("\u001b[33mSelectionnez une sous-categorie :\u001b[0m", getMenuCateg(sousCateg));
            for (Categorie categ : sousCateg) {
                if (categ.getNom() == choixSousCateg) {
                    res = categ;
                    break;
                }
            }
        }
        API.fetchProductByCategorie(res);
        return res.getProduits();
    }

    public void rechercher() {
        // selection du type de recherche
        String[] menu = user.getMenuRecherche();
        String choix = IHM.deroulerMenu("\u001b[33mSelectionnez un type de recherche :\u001b[0m", menu);
        List<Produit> res = null;
        switch (choix) {
            case "Rechercher par mot clef":
                API.fetchMotClef(this);
                res = rechercherParMotClef();
                break;
            case "Rechercher par catégorie":
                API.fetchCategories(this);
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
        String[] parametres = IHM.remplirFormulaire("\u001b[1mFormulaire d'inscription\u001b[0m (acheteur)", formulaire);
        // on connecte l'acheteur automatiquement
        Acheteur user = new Acheteur(parametres);
        users.add(user);
        API.addAcheteur( user);
        return "\u001b[32mVous êtes connecté en tant que:\n  " + user.getAffichageMinimal() + "\u001b[0m\n";
    }

    public String inscriptionVendeur() {
        // remplir les données du vendeur
        String[] formulaire = Vendeur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("\u001b[1mFormulaire d'inscription\u001b[0m (vendeur)", formulaire);

        // remplir les données bancaires
        String[] menuTypeDonnees = { "RIB", "CB" };
        String typeDonnees = IHM.deroulerMenu(
                "\u001b[33mChoisissez un type de données bancaires pour la vérification de vos données\u001b[0m", menuTypeDonnees);
        String[] donneesBancaires = DonneesBancaires.getFormulaire(typeDonnees);
        String[] donneesRemplies = IHM.remplirFormulaire("\u001b[33mEntrez vos données bancaires :\u001b[0m", donneesBancaires);

        // vérification : si ce ne sont pas les mêmes noms et prénoms, échec, sinon les
        // Données et le Vendeur sont créés
        Boolean donneesOK = DonneesBancaires.verifierVendeur(formulaire, donneesBancaires);
        if (!donneesOK)
            return "\u001b[31mEchec : les données bancaires ne correspondent pas au vendeur\u001b[0m";

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
        return "\u001b[32mDonnées bancaires correctes : " + dataBank.toString()
            + "\nVous êtes connecté en tant que :\n  " + user.getAffichageMinimal() + "\u001b[0m\n";
    }
}
