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

    /**
     * Recupere la liste de mot-cle
     * 
     * @return liste de mot-cle
     */
    public List<MotClef> getMotClef() {
        return motClef;
    }

    /**
     * Defini la liste de mot-cle
     * 
     * @param motClef liste de mot-cle
     */
    public void setMotClef(List<MotClef> motClef) {
        this.motClef = motClef;
    }

    /**
     * Recupere la liste d'inscrit
     * 
     * @return liste d'inscrit
     */
    public List<Inscrit> getUsers() {
        return users;
    }

    /**
     * Defini l'utilisateur
     */
    public void setUsers(List<Inscrit> users) {
        this.users = users;
    }

    /**
     * Recupere l'utilisateur
     * 
     * @return Utilisateur
     */
    public Utilisateur getUser() {
        return user;
    }

    /**
     * Recupere la liste de categories
     * 
     * @return liste de categories
     */
    public List<Categorie> getCategories() {
        return categories;
    }

    /**
     * Defini la liste de categories
     * 
     * @param categories nouvelle liste de categorie
     */
    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    /**
     * Constructeur de la classe (Singleton)
     * 
     * @throws Exception
     */
    private IBuySu() throws Exception {
        user = new Utilisateur();
        try {
            API.setConnexion();
            API.fetchUsers(this);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retourne une instance du systeme IBuySu
     * 
     * @return instance du systeme IBuySu
     */
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

    /**
     * Retourne le menu principal
     * 
     * @return array string, le menu principal
     */
    public String[] getMenu() {
        return user.getMenu();
    }

    /**
     * Connexion d'un utilisateur a partir d'un formulaire
     * 
     * @return message de succes
     */
    public String connexion() {
        String[] formulaire = Inscrit.getFormulaireConnexion();
        String[] identifiants = IHM.remplirFormulaire(PromptUtils.b("\u001b[33mFormulaire de connexion"), formulaire);
        Inscrit connecting = getUser(identifiants[0]);
        if (connecting == null)
            return PromptUtils.red("Erreur: le mail ne correspond a aucun utilisateur");
        if (!connecting.verifMdp(identifiants[1]))
            return PromptUtils.red("Erreur: mot de passe incorrect");
        user = connecting;
        // TODO : if (user instanceof Vendeur){ Vendeur v = (Vendeur) user; v.annonces =
        // GetInAPIUserAnonces ;} -- Pareil pour Acheteur avec offres
        return PromptUtils.grn("Vous etes connecte en tant que : " + user.getAffichageMinimal() + "\n");
    }

    /**
     * Deconnecte le user
     * 
     * @return message de succes
     */
    public String deconnexion() {
        user = new Utilisateur();
        return PromptUtils.yel("Vous etes deconnecte");
    }

    /**
     * Rechercher un produit en entrant un mot-cle
     * 
     * @return Liste de produit
     */
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

    /**
     * Transforme une liste de Categorie en Array de String
     * 
     * @param categs liste de categorie
     * @return Liste de categorie en array de string
     */
    public String[] getMenuCateg(List<Categorie> categs) {
        String[] res = new String[categs.size()];
        for (int i = 0; i < categs.size(); i++) {
            res[i] = categs.get(i).getNom();
        }
        return res;
    }

    /**
     * Rechercher tous les produits par categorie
     * 
     * @return Liste de Produit
     */
    public List<Produit> rechercherParCategorie() {
        Categorie res = getChoiceCategorie();
        API.fetchProductByCategorie(this, res);
        return res.getProduits();
    }

    /**
     * Retourne une instance d'un Inscrit correspondant a l'Id en parametre
     * 
     * @param id id de l'utilisateur
     * @return retourne un Inscrit s'il existe, sinon null
     */
    public Inscrit getUserById(int id) {
        API.fetchUsers(this);
        for (Inscrit user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    /**
     * Retourne une instance d'un Inscrit correspondant au mail en parametre
     * 
     * @param mail adresse mail de l'utilisateur
     * @return un Inscrit s'il existe, sinon null
     */
    public Inscrit getUser(String mail) {
        for (Inscrit user : users) {
            if (user.getMail().equalsIgnoreCase(mail)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Transforme une liste de Produit en Array de String pour la compatiblilite
     * avec deroulerMenu()
     * 
     * @param products liste de Produits
     * @return liste de produit au format array de String
     */
    private String[] displayListOfProduct(List<Produit> products) {
        if (products == null || products.size() == 0)
            return null;
        String[] res = new String[products.size()];
        int i = 0;
        for (Produit product : products) {
            res[i] = product.toString();
            i++;
        }
        return res;
    }

    /**
     * Permet a l'utilisateur de faire une recherche par mot-cle ou categorie, et
     * d'acheter un des produits
     * 
     * @return retourne un message
     */
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
            case "Rechercher par categorie":
                API.fetchCategories(this);
                res = rechercherParCategorie();
                break;
            default:
                return "Nous n'avons pas compris votre demande\n";
        }
        String[] researchList = displayListOfProduct(res);
        if (researchList == null)
            return "Aucun produit n'a ete trouve\n";
        String selectedOption = IHM.deroulerMenu("Choisissez un produit :", researchList);
        int index = Arrays.asList(researchList).indexOf(selectedOption);
        Produit selectedProduct = res.get(index);

        return buyOrBack(selectedProduct);
    }

    /**
     * Acheter ou non un produit si le user et un Acheteur
     * 
     * @param p produit a acheter
     * @return retourne un message
     */
    public String buyOrBack(Produit p) {
        // Acheter ou retourner a l'Accueil
        String s = "Vous voulez acheter " + p.getTitre() + " ou retourner a l'accueil ?";
        String[] buyMenu = { "Acheter", "Retourner a l'accueil" };
        switch (IHM.deroulerMenu(s, buyMenu)) {
            case "Acheter":
                if (user instanceof Acheteur) {
                    System.out.println("Achat en cours");
                    acheterUnObjet(p);
                } else {
                    return "Vous n'etes pas inscrit ou connecte en tant qu'acheteur.\nReconnectez-vous.";
                }
                break;
            case "Retourner a l'accueil":
                return "Retour a l'accueil.";
            default:
                System.out.println("Nous n'avons pas compris votre demande");
                buyOrBack(p);
        }
        return "Achat pris en compte";
    }

    public void acheterObjetEnchere() {
        System.out.println("acheter objet enchere");
    }

    /**
     * cree un contrat et l'attache au produit
     * 
     * @param p produit a acheter
     */
    public void acheterUnObjet(Produit p) {
        if (!(user instanceof Acheteur))
            return; // condition enlevable si cette methode est mise en private

        Contrat contrat = new Contrat((Acheteur) user, p.getVendeur(), p, p.getPrix_de_depart());
        p.setContrat(contrat);

        System.out.println("En attente de la confirmation du vendeur.");
    }

    public void evaluerUnUtilisateur() {
        System.out.println("evaluer un utilisateur");
    }

    /**
     * Cree un Acheteur a partir des donnees d'un formulaire et l'enregistre dans
     * l'API
     * 
     * @return retourne un message de succes
     */
    public String inscriptionAcheteur() {
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire(PromptUtils.b("Formulaire d'inscription (acheteur)"), formulaire);
        // on connecte l'acheteur automatiquement
        Acheteur acheteur = new Acheteur(parametres);
        this.user = acheteur;
        users.add((Inscrit) user);
        API.addAcheteur((Acheteur) user);
        return PromptUtils.grn("Vous etes connecte en tant que:\n  " + user.getAffichageMinimal() + "\n");
    }

    /**
     * Cree un vendeur a partir des donnees d'un formulaire et l'enregistre dans
     * l'API
     * 
     * @return retourne un message de succes
     */
    public String inscriptionVendeur() {
        // remplir les donnees du vendeur
        String[] formulaire = Vendeur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire(PromptUtils.b("Formulaire d'inscription (vendeur)"), formulaire);

        // remplir les donnees bancaires
        String[] menuTypeDonnees = { "RIB", "CB" };
        String typeDonnees = IHM.deroulerMenu(
                PromptUtils.yel("Choisissez un type de donnees bancaires pour la verification de vos donnees"),
                menuTypeDonnees);
        String[] donneesBancaires = DonneesBancaires.getFormulaire(typeDonnees);
        String[] donneesRemplies = IHM.remplirFormulaire(PromptUtils.yel("Entrez vos donnees bancaires :"),
                donneesBancaires);

        // verification : si ce ne sont pas les memes noms et prenoms, echec, sinon les
        // Donnees et le Vendeur sont crees
        Boolean donneesOK = DonneesBancaires.verifierVendeur(formulaire, donneesBancaires);
        if (!donneesOK)
            return PromptUtils.red("Echec : les donnees bancaires ne correspondent pas au vendeur");

        // creation des objets
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
        return "\u001b[32mDonnees bancaires correctes : " + dataBank.toString()
                + "\nVous etes connecte en tant que :\n  " + user.getAffichageMinimal() + "\u001b[0m\n";
    }

    /**
     * Permet a l'utilisateur de choisir une categorie et sous-categorie de produit.
     * 
     * @return instance de Categorie choisit par utilisateur
     */
    public Categorie getChoiceCategorie() {
        API.fetchCategories(this);
        String choixCateg = IHM.deroulerMenu("Selectionnez une categorie: ", getMenuCateg(categories));
        Categorie res = null;
        for (Categorie categ : categories) {
            if (categ.getNom() == choixCateg) {
                res = categ;
                break;
            }
        }
        API.fetchSousCategorie(res);
        List<Categorie> sousCateg = res.getSousCategories();
        if (sousCateg.size() > 0) {
            String choixSousCateg = IHM.deroulerMenu("Selectionnez une sous-categorie:", getMenuCateg(sousCateg));
            for (Categorie categ : sousCateg) {
                if (categ.getNom() == choixSousCateg) {
                    res = categ;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Transforme la liste d'annonces du vendeur en Array de String pour la
     * compatiblilite avec deroulerMenu()
     * 
     * @param vendeur utilisateur vendeur
     * @return array de string
     */
    private String[] dislpayVendeurAnnonces(Vendeur vendeur) {
        String[] res = new String[vendeur.annonces.size()];
        int i = 0;
        for (Produit annonce : vendeur.annonces) {
            if (annonce.contrat != null) {
                if (annonce.contrat.getIsConcluded())
                    res[i] = "\t\tVendu" + annonce.toString() + "\n";
                else
                    res[i] = "\t\tOffre de vente" + annonce.toString() + "\n";
            } else
                res[i] = annonce.toString() + "\n";
            i++;
        }
        return res;
    }

    /**
     * Si mot-cle deja existant,
     * Rajoute un produit a la liste des produits relie a ce mot-cle
     * sinon, cree un nouveau mot-cle, s'il existe pas
     * 
     * @param p Produit a rajouter a liste
     */
    public void addOrCreatMotClef(Produit p) {
        String recherche = IHM.getUserIn("Entrer un mot clef");
        MotClef res = null;
        for (MotClef mot : this.motClef) {
            if (mot.getNom() == recherche) {
                res = mot;
            }
        }
        if (res != null) {
            res.addProduit(p);
        } else {
            res = new MotClef(recherche, p);
        }
        API.addMotClef(res, p);
        p.addMotClef(res);
    }

    /**
     * Renvoie rien.
     * Demande un choix entre "Vente directe" et "Vente aux encheres" a
     * l'utilisateur.
     * Cree une instance d'Enchere ou de Produit (Vente Directe) en fonction du
     * choix.
     */
    public void creerUnVente() {
        // demander a l'utilisateur si c'est une vente directe ou vente aux encheres
        String[] menuTypeDonnees = { "Vente Directe", "Vente Aux Encheres" };
        String typeDonnees = IHM.deroulerMenu("Choisissez un type de vente que vous voulez effectuer", menuTypeDonnees);

        // demander le categorie a l'utilisateur
        Categorie categorieProduit = getChoiceCategorie();
        Produit produit;
        if (user instanceof Vendeur) {
            // creation vente aux encheres
            if (typeDonnees == "Vente Aux Encheres") {
                String[] formulaireEnchere = Enchere.getFormulaire();
                String[] donneesVenteEnchere = IHM.remplirFormulaire("Formulaire de creation de vente aux encheres",
                        formulaireEnchere);
                produit = new Enchere(donneesVenteEnchere, (Vendeur) user, categorieProduit);
                // creation vente directe
            } else {
                String[] formulaireDirecte = Produit.getFormulaire();
                String[] donneesVenteDirecte = IHM.remplirFormulaire("Formulaire de creation de vente directe",
                        formulaireDirecte);
                produit = new Produit(donneesVenteDirecte, (Vendeur) user, categorieProduit);
            }
            API.addProduit(this, produit);
            categorieProduit.addProduit(produit);
            addOrCreatMotClef(produit);
            Vendeur v = (Vendeur) user;
            v.addProduit(produit);
        }
    }

    /**
     * Permet au vendeur de gerer ses annonces et accepter ou refuser les offres de
     * vente
     * 
     * @return retourne un message d'etat
     */
    public String gererMesVentes() {
        if (!(user instanceof Vendeur)) {
            return "Vous n'etes pas inscrit ou connecte en tant que vendeur.\nReconnectez-vous.";
        }

        Vendeur myUser = (Vendeur) user;
        if (myUser.annonces.isEmpty())
            return "\nVous n'avez aucune annonce\n";
        String[] vendeurAnnonces = dislpayVendeurAnnonces(myUser);
        String choixAnnonce = IHM.deroulerMenu("\nVos annonces :\n", vendeurAnnonces);
        int index = Arrays.asList(vendeurAnnonces).indexOf(choixAnnonce);
        Produit selectedProduct = myUser.annonces.get(index);

        if (selectedProduct.contrat == null) {
            System.out.println(selectedProduct.toString() + "\nProduit toujours en vente\n");
        } else {
            accepterRefuserVente(selectedProduct);
        }

        String[] s = { "retour", "accueil" };
        switch (IHM.deroulerMenu("\n", s)) {
            case "retour":
                gererMesVentes();
                break;
            default:
                break;
        }

        return "";
    }

    /**
     * Le vendeur accepte ou refuse la vente du produit en parametre. Il peut
     * egalement ne pas choisir pour reflechir un peu plus.
     * 
     * @param produit produit avec un contrat de vente
     */
    public void accepterRefuserVente(Produit produit) {
        System.out.println(produit.contrat.toString());
        if (!produit.contrat.getIsConcluded()) {
            String[] s = { "Valider la vente", "Refuser la vente", "Reflechir" };
            switch (IHM.deroulerMenu("\n", s)) {
                case "Valider la vente":
                    produit.conclureVente(produit.contrat.getAcheteur());
                    produit.contrat.concludeContrat();
                    System.out.println("Contrat conclu !");
                    // TODO : Informer l'Acheteur
                    break;
                case "Refuser la vente":
                    produit.refuserVente();
                    System.out.println("Vente refusee. En attente de nouvelles offres.\n");
                    gererMesVentes();
                    break;
                case "Reflechir":
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Vente deja conclue");
        }
    }
}
