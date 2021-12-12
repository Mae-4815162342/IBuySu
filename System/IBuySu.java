package System;

import IHM.IHM;
import IHM.PromptUtils;
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

    public Utilisateur getUser() {
        return user;
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
        String[] identifiants = IHM.remplirFormulaire(PromptUtils.b("\u001b[33mFormulaire de connexion"), formulaire);
        Inscrit connecting = null;
        for(Inscrit user: users) {
            if(user.getMail().equalsIgnoreCase(identifiants[0])) {
                connecting = user;
            }
        }
        if(connecting == null) return PromptUtils.red("Erreur: le mail ne correspond à aucun utilisateur");
        if(!connecting.verifMdp(identifiants[1])) return PromptUtils.red("Erreur: mot de passe incorrect");
        user = connecting;
        return PromptUtils.grn("Vous êtes connecté en tant que : " + user.getAffichageMinimal() + "\n");
    }

    public String deconnexion() {
        user = new Utilisateur();
        return PromptUtils.yel("Vous êtes déconnecté");
    }

    public ArrayList<Produit> rechercherParMotClef() {
        String recherche = IHM.getUserIn(PromptUtils.yel("Entrez un mot-clef"));
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
        String choixCateg = IHM.deroulerMenu(PromptUtils.yel("Selectionnez une categorie"), getMenuCateg(categories));
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
            String choixSousCateg = IHM.deroulerMenu(PromptUtils.yel("Selectionnez une sous-categorie"), getMenuCateg(sousCateg));
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
        String choix = IHM.deroulerMenu(PromptUtils.yel("Selectionnez un type de recherche :"), menu);
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
        String[] parametres = IHM.remplirFormulaire(PromptUtils.b("Formulaire d'inscription (acheteur)"), formulaire);
        // on connecte l'acheteur automatiquement
        Acheteur user = new Acheteur(parametres);
        users.add(user);
        API.addAcheteur( user);
        return PromptUtils.grn("Vous êtes connecté en tant que:\n  " + user.getAffichageMinimal() + "\n");
    }

    public String inscriptionVendeur() {
        // remplir les données du vendeur
        String[] formulaire = Vendeur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire(PromptUtils.b("Formulaire d'inscription (vendeur)"), formulaire);

        // remplir les données bancaires
        String[] menuTypeDonnees = { "RIB", "CB" };
        String typeDonnees = IHM.deroulerMenu(
                PromptUtils.yel("Choisissez un type de données bancaires pour la vérification de vos données"), menuTypeDonnees);
        String[] donneesBancaires = DonneesBancaires.getFormulaire(typeDonnees);
        String[] donneesRemplies = IHM.remplirFormulaire(PromptUtils.yel("Entrez vos données bancaires :"), donneesBancaires);

        // vérification : si ce ne sont pas les mêmes noms et prénoms, échec, sinon les
        // Données et le Vendeur sont créés
        Boolean donneesOK = DonneesBancaires.verifierVendeur(formulaire, donneesBancaires);
        if (!donneesOK)
            return PromptUtils.red("Echec : les données bancaires ne correspondent pas au vendeur");

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

    public Categorie getCategorie(){
        API.fetchCategories(this);
        String choixCateg = IHM.deroulerMenu("Selectionnez une categorie pour votre Produit:: ", getMenuCateg(categories));
        Categorie res = null;
        for(Categorie categ : categories) {
            if(categ.getNom() == choixCateg) {
                res = categ;
                break;
            }
        }
        API.fetchSousCategorie(res);
        List<Categorie> sousCateg = res.getSousCategories();
        if(res.getSousCategories() != null) {
            String choixSousCateg = IHM.deroulerMenu("Selectionnez une sous-categorie pour votre Produit: ", getMenuCateg(sousCateg));
            for(Categorie categ : sousCateg) {
                if(categ.getNom() == choixSousCateg) {
                    res = categ;
                    break;
                }
            }
        }
        return res;
    }

    public void addOrCreatMotClef(Produit p){
        String recherche = IHM.getUserIn("Entrer un mot clef:");
        for(MotClef mot: this.motClef) {
            if(!mot.compare(recherche)){
                new MotClef(recherche, p);
            }
        }
    }
    
    public void creerUnVente() { 
        //demander à l'utilisateur si c'est une vente directe où vente aux enchères
        String[] menuTypeDonnees = {"Vente Directe", "Vente Aux Enchères"};
        String typeDonnees = IHM.deroulerMenu("Choisissez un type de vente que vous voulez effectuer", menuTypeDonnees);
        
        //demander le categorie à l'utilisateur
        Categorie categorieProduit = getCategorie();
        Produit produit;
        if(user instanceof Vendeur)
        {
            //création des objets
            if(typeDonnees == "Vente Aux Enchères")
            {
                String[] formulaireEnchere = Enchere.getFormulaire();
                String[] donneesVenteEnchere = IHM.remplirFormulaire("Formulaire de creation de vente aux enchères", formulaireEnchere);
                produit = new Enchere(donneesVenteEnchere, (Vendeur)user, categorieProduit);
            // Vente Directe
            }else{ //may be check typeDonnes
                String[] formulaireDirecte = Produit.getFormulaire();
                String[] donneesVenteDirecte = IHM.remplirFormulaire("Formulaire de creation de vente directe", formulaireDirecte);
                produit = new Produit(donneesVenteDirecte, (Vendeur)user, categorieProduit);
            }
            // Ne marche pas
            // categorieProduit.addProduit(produit);
            addOrCreatMotClef(produit);

        }
    }

}
