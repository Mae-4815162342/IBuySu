package System;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilisateur pouvant acheter des produits.
 */
public class Acheteur extends Inscrit {
    /** Liste d'enchères en circulation. */
    public List<Enchere> offres = new ArrayList<Enchere>();

    /**
     * Constructeur pour créer un Acheteur.
     * 
     * @param pseudo     Pseudo de l'utilisateur.
     * @param nom        Nom de l'utilisateur.
     * @param prenom     Prénom de l'utilisateur.
     * @param numTel     Numéro de téléphone de l'utilisateur.
     * @param mail       Adresse mail de l'utilisateur.
     * @param mdp        Mot de passe de l'utilisateur.
     * @param numRue     Numéro de rue de l'utilisateur.
     * @param nomRue     Nom de rue de l'utilisateur.
     * @param codePostal Code postal de l'utilisateur.
     * @param ville      Ville de l'utilisateur.
     * @param pays       Pays de l'utilisateur.
     */
    public Acheteur(String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays) {
        super(pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
    }

    /**
     * Constructeur pour créer un Acheteur.
     * 
     * @param id         ID de l'utilisateur.
     * @param pseudo     Pseudo de l'utilisateur.
     * @param nom        Nom de l'utilisateur.
     * @param prenom     Prénom de l'utilisateur.
     * @param numTel     Numéro de téléphone de l'utilisateur.
     * @param mail       Adresse mail de l'utilisateur.
     * @param mdp        Mot de passe de l'utilisateur.
     * @param numRue     Numéro de rue de l'utilisateur.
     * @param nomRue     Nom de rue de l'utilisateur.
     * @param codePostal Code postal de l'utilisateur.
     * @param ville      Ville de l'utilisateur.
     * @param pays       Pays de l'utilisateur.
     */
    public Acheteur(int id, String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays) {
        super(id, pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
    }

    /**
     * Constructeur pour créer un Acheteur à l'aide d'un formulaire rempli.
     * 
     * @param formulaireRempli Formulaire rempli par l'utilisateur.
     */
    public Acheteur(String[] formulaireRempli) {
        this(formulaireRempli[0], formulaireRempli[1], formulaireRempli[2], Integer.parseInt(formulaireRempli[3]),
                formulaireRempli[4], formulaireRempli[5], Integer.parseInt(formulaireRempli[6]), formulaireRempli[7],
                Integer.parseInt(formulaireRempli[8]), formulaireRempli[9], formulaireRempli[10]);
    }

    /**
     * Fait participer l'Acheteur à une enchère.
     * 
     * @param e    L'enchère à laquelle l'Acheteur va participer.
     * @param prix Le prix que l'acheteur propose pour l'enchère.
     */
    public void participerEnchere(Enchere e, float prix) {
        Offre offre = new Offre(e, prix, this);
        offres.add(e);
        e.addOffre(offre);
    }

    /**
     * Menu des options que l'Acheteur peut sélectionner sur iBuySu.com.
     */
    @Override
    public String[] getMenu() {
        String[] menu = { "Gerer mes achats" };
        String[] menuUser = super.getMenu();
        int menuLen = menu.length;
        int menuUserLen = menuUser.length;
        String[] newMenu = new String[menuLen + menuUserLen];
        System.arraycopy(menu, 0, newMenu, 0, menuLen);
        System.arraycopy(menuUser, 0, newMenu, menuLen, menuUserLen);
        return newMenu;
    }
}
