package System;

import IHM.IHM;
import IHM.PromptUtils;
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
        Inscrit connecting = getUser(identifiants[0]);
        if(connecting == null) return PromptUtils.red("Erreur: le mail ne correspond à aucun utilisateur");
        if(!connecting.verifMdp(identifiants[1])) return PromptUtils.red("Erreur: mot de passe incorrect");
        user = connecting;
        //TODO : if (user instanceof Vendeur){ Vendeur v = (Vendeur) user; v.annonces = GetInAPIUserAnonces ;} -- Pareil pour Acheteur avec offres
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
            if (mot.getNom() == recherche) {
                API.fetchProductByMotcle(mot);
                for (Produit p : mot.getProduits()) {
                    System.out.println(p);
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
        Categorie res = getChoiceCategorie();
        API.fetchProductByCategorie(this, res);
        return res.getProduits();
    }

    public Inscrit getUserById(int id) {
        API.fetchUsers(this);
        for(Inscrit user : users) {
            if(user.getId() == id) return user;
        }
        return null;
    }

    public Inscrit getUser(String mail) {
        for(Inscrit user: users) {
            if(user.getMail().equalsIgnoreCase(mail)) {
                return user;
            }
        }
        return null;
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
        return "Achat pris en compte";
    }

    public void acheterObjetEnchere() {
        System.out.println("acheter objet enchère");
    }

    public void acheterUnObjet(Produit p) {
        //TODO : voir s'il faut changer le produit dans la BDD
        if(!(user instanceof Acheteur)) return;  //condition enlevable si cette methode est mise en private

        Contrat contrat = new Contrat((Acheteur) user, p.getVendeur(), p, p.getPrix_de_depart());
        p.setContrat(contrat);

        System.out.println("En attente de la confirmation du vendeur.");
    }

    public void evaluerUnUtilisateur() {
        System.out.println("evaluer un utilisateur");
    }

    public String inscriptionAcheteur() {
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire(PromptUtils.b("Formulaire d'inscription (acheteur)"), formulaire);
        // on connecte l'acheteur automatiquement
        Acheteur acheteur = new Acheteur(parametres);
        this.user = acheteur;
        users.add((Inscrit) user);
        API.addAcheteur((Acheteur) user);
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
        this.user = vendeur;
        users.add((Inscrit) user);
        API.addVendeur((Vendeur) user);
        return "\u001b[32mDonnées bancaires correctes : " + dataBank.toString()
            + "\nVous êtes connecté en tant que :\n  " + user.getAffichageMinimal() + "\u001b[0m\n";
    }

    public Categorie getChoiceCategorie(){
        API.fetchCategories(this);
        String choixCateg = IHM.deroulerMenu("Selectionnez une categorie: ", getMenuCateg(categories));
        Categorie res = null;
        for(Categorie categ : categories) {
            if(categ.getNom() == choixCateg) {
                res = categ;
                break;
            }
        }
        API.fetchSousCategorie(res);
        List<Categorie> sousCateg = res.getSousCategories();
        if(sousCateg.size() > 0) {
            String choixSousCateg = IHM.deroulerMenu("Selectionnez une sous-categorie:", getMenuCateg(sousCateg));
            for(Categorie categ : sousCateg) {
                if(categ.getNom() == choixSousCateg) {
                    res = categ;
                    break;
                }
            }
        }
        return res;
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

    public void addOrCreatMotClef(Produit p){
        String recherche = IHM.getUserIn("Entrer un mot clef");
        MotClef res = null;
        for(MotClef mot: this.motClef) {
            if(mot.getNom() == recherche){
                res = mot;
            }
        }
        if(res != null) {
            res.addProduit(p);
        } else {
            res = new MotClef(recherche, p);
        }
        API.addMotClef(res, p);
        p.addMotClef(res);
    }
    
    public void creerUnVente() { 
        //demander à l'utilisateur si c'est une vente directe où vente aux enchères
        String[] menuTypeDonnees = {"Vente Directe", "Vente Aux Enchères"};
        String typeDonnees = IHM.deroulerMenu("Choisissez un type de vente que vous voulez effectuer", menuTypeDonnees);
        
        //demander le categorie à l'utilisateur
        Categorie categorieProduit = getChoiceCategorie();
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
            API.addProduit(this, produit);
            categorieProduit.addProduit(produit);
            addOrCreatMotClef(produit);
            Vendeur v = (Vendeur) user;
            v.addProduit(produit);
        }
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
                    produit.conclureVente(produit.contrat.getAcheteur());
                    produit.contrat.concludeContrat();
                    System.out.println("Contrat conclu !");
                    //TODO : Informer l'Acheteur
                    break;
                case "Refuser la vente":
                    produit.refuserVente();
                    System.out.println("Vente refusée. En attente de nouvelles offres.\n");
                    gererMesVentes();
                    break;
                case "Reflechir":
                    break;
                default :
                    break;
            }
        }
        else{
            System.out.println("Vente déjà conclue");
        }
    }

}
