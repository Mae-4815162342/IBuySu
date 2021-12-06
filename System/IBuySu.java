package System;
import IHM.IHM;
import BDD.API;
import java.util.ArrayList;
import java.util.List;

public class IBuySu {
    private List<Categorie> categorie = new ArrayList<Categorie>();
    private List<MotClef> motClef = new ArrayList<>();
    private List<Utilisateur> users = new ArrayList<>();
    private Utilisateur user;
    private static IBuySu system;
    private IBuySu() throws Exception {
        user = new Utilisateur();
        try {
            API.setConnexion();
        } catch(Exception e) {
            throw e;
        }
    }

    public static IBuySu getSystem(){
        if(system == null){
            try {
                system = new IBuySu();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return system;
    }

    public String[] getMenu() {
        return user.getMenu();
    }

    public void connexion(){
        System.out.println("Connexion");
    }

    public void rechercher(){
        //selection du type de recherche
        String[] menu = user.getMenuRecherche();
        String choix = IHM.deroulerMenu(menu);
        switch(choix) {
            case "Rechercher par mot clef":

            case "Rechercher par catégorie":
        }
    }

    public void acheterObjetEnchere() { System.out.println("acheter objet enchère");}

    public void acheterUnObjet() { System.out.println("acheter un objet");}

    public void evaluerUnUtilisateur() { System.out.println("evaluer un utilisateur");}

    public String inscriptionAcheteur(){
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (acheteur)", formulaire);
        //on connecte l'acheteur automatiquement
        user = new Acheteur(parametres);
        users.add(user);
        API.addAcheteur((Acheteur)user);
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
        if(typeDonnees == "RIB") dataBank = new RIB(donneesRemplies);
        else dataBank = new CarteBancaire(donneesRemplies);
        Vendeur vendeur = new Vendeur(parametres, dataBank);

        //connexion de l'utilisateur
        user = vendeur;
        users.add(user);
        API.addVendeur((Vendeur)user);
        return "Données bancaires correctes: " + dataBank.toString() + "\nVous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }
}
