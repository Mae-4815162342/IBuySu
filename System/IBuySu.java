package System;

import IHM.IHM;
import BDD.API;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Produit> rechercherParCategorie() {
        String choixCateg = IHM.deroulerMenu("Selectionnez une categorie : ", getMenuCateg(categories));
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
            String choixSousCateg = IHM.deroulerMenu("Selectionnez une sous-categorie : ", getMenuCateg(sousCateg));
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

    private String[] displayListOfProduct(List<Produit> products){
        if(products == null || products.size()==0) return null;
        String[] res = new String[products.size()];
        int i=0;
        for(Produit product : products){
            res[i]= product.toString();
            i++;
        }
        return res;
    }

    public String rechercher() {
        // selection du type de recherche
        String[] menu = user.getMenuRecherche();
        String choix = IHM.deroulerMenu("Selectionnez un type de recherche", menu);
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
                return "Nous n'avons pas compris votre demande\n";
        }
        String[] researchList = displayListOfProduct(res);
        if(researchList == null) return "Aucun produit n'a été trouvé\n";
        String selectedOption = IHM.deroulerMenu("Choisissez un produit :", researchList);
        int index = Arrays.asList(researchList).indexOf(selectedOption);
        Produit selectedProduct = res.get(index);
       
        return buyOrBack(selectedProduct);
    }

    private String buyOrBack(Produit p){
        // Acheter ou retourner à l'Accueil
        String s = "Vous voulez acheter "+ p.getTitre() +" ou retourner à l'accueil ?";
        String[] buyMenu = {"Acheter","Retourner à l'accueil"};
        switch (IHM.deroulerMenu(s, buyMenu)){
            case "Acheter":
                if(user instanceof Acheteur){
                    System.out.println("Achat en cours");
                    acheterUnObjet(p);
                }
                else{
                    return "Vous n'êtes pas inscrit ou connecté en tant qu'acheteur.\nReconnectez-vous.";
                }
                break;
            case "Retourner à l'accueil":
                return "Retour à l'accueil.";
            default:
                System.out.println("Nous n'avons pas compris votre demande");
                buyOrBack(p);
        }
        return "Achat effectué";
    }

    public void acheterObjetEnchere() {
        System.out.println("acheter objet enchère");
    }

    public void acheterUnObjet(Produit p) {
        System.out.println("acheter un objet");
    }

    public void evaluerUnUtilisateur() {
        System.out.println("evaluer un utilisateur");
    }

    public String inscriptionAcheteur() {
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (acheteur)", formulaire);
        // on connecte l'acheteur automatiquement
        user = new Acheteur(parametres);
        users.add((Inscrit) user);
        System.out.println("Acheteur ajouté : "+user.toString());
        API.addAcheteur((Acheteur) user);
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
        System.out.println("Vendeur ajouté : "+user.toString());
        API.addVendeur((Vendeur) user);
        return "Données bancaires correctes: " + dataBank.toString() + "\nVous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }

    private String[] dislpayVendeurAnnonces(Vendeur vendeur){
        String[] res = new String[vendeur.annonces.size()];
        int i=0;
        for(Produit annonce : vendeur.annonces){
            if(annonce.contrat != null){
                if(annonce.contrat.getIsConcluded()) res[i]="\t\tVendu"+annonce.toString()+"\n";
                else res[i]="\t\tOffre de vente"+annonce.toString()+"\n";
            }
            else res[i]= annonce.toString()+"\n";
            i++;
        }
        return res;
    }

    public String gererMesVentes(){
        if(!(user instanceof Vendeur)){return "Vous n'êtes pas inscrit ou connecté en tant que vendeur.\nReconnectez-vous.";}
        //TOCOMPLETE

        Vendeur myUser = (Vendeur) user;
        if(myUser.annonces.isEmpty()) return "\nVous n'avez aucune annonce\n";
        //System.out.println("");
        String[] vendeurAnnonces = dislpayVendeurAnnonces(myUser);
        String choixAnnonce = IHM.deroulerMenu("\nVos annonces :\n", vendeurAnnonces);
        int index = Arrays.asList(vendeurAnnonces).indexOf(choixAnnonce);
        Produit selectedProduct = myUser.annonces.get(index);

        if(selectedProduct.contrat == null){
            System.out.println(selectedProduct.toString()+"\nProduit toujours en vente\n");
        }
        else{
            accepterRefuserVente(selectedProduct);
        }

        String[] s = {"retour","accueil"};
        switch(IHM.deroulerMenu("\n", s)){
            case "retour":
                gererMesVentes();
                break;
            default :
                break;
        }

        return "";
    }

    private void accepterRefuserVente(Produit produit){
        System.out.println(produit.contrat.toString());
        if(!produit.contrat.getIsConcluded()){
            String[] s = {"Valider la vente","Refuser la vente", "Reflechir"};
            switch(IHM.deroulerMenu("\n", s)){
                case "Valider la vente":
                    produit.contrat.concludeContrat();
                    produit.conclureVente(produit.contrat.getAcheteur());
                    System.out.println("Contrat conclu !");
                    //TODO : Informer l'Acheteur
                    break;
                case "Refuser la vente":
                    gererMesVentes();
                    break;
                case "Reflechir":
                    break;
                default :
                    break;
            }
        }

    }

}
