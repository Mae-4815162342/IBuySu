package System;
import IHM.IHM;
import java.util.ArrayList;
import java.util.List;

public class IBuySu {
    private List<Categorie> categorie = new ArrayList<Categorie>();
    private List<MotClef> motClef = new ArrayList<>();
    private List<Utilisateur> users = new ArrayList<>();
    private Utilisateur user;
    private static IBuySu system;
    private IBuySu(){
        user = new Utilisateur();
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

    public void evaluerUnUtilisateur() { System.out.println("evaluer un utilisateur");}

    public String inscriptionAcheteur(){
        String[] formulaire = Acheteur.getFormulaireInscription();
        String[] parametres = IHM.remplirFormulaire("Formulaire d'inscription (acheteur)", formulaire);
        //on connecte l'acheteur automatiquement
        user = new Acheteur(parametres);
        users.add(user);
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

        //connexion de l'utilisateur
        user = vendeur;
        users.add(user);
        return "Données bancaires correctes: " + dataBank.toString() + "\nVous êtes connecté en tant que:\n " + user.getAffichageMinimal() + "\n";
    }
}
