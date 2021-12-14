package System;

import java.util.ArrayList;
import java.util.List;
import BDD.API;

/**
 * Utilisateur possedant un compte dans la base de donnee de iBuySu.com.
 */
public abstract class Inscrit extends Utilisateur {
    protected String nom;
    protected String prenom;
    protected int numeroTel;
    protected String mail;
    protected final int id;
    protected String pseudo;
    protected Adresse adresse;
    protected String motdepasse;
    protected List<Evaluation> evaluations = new ArrayList<Evaluation>();
    protected ArrayList<Contrat> contrats = new ArrayList<Contrat>();

    /**
     * Cree un utilisateur inscrit.
     * 
     * @param pseudo     Pseudo de l'utilisateur inscrit.
     * @param nom        Nom de l'utilisateur inscrit.
     * @param prenom     Prenom de l'utilisateur inscrit.
     * @param numTel     Numero de telephone de l'utilisateur inscrit.
     * @param mail       Adresse mail de l'utilisateur inscrit.
     * @param mdp        Mot de passe de l'utilisateur inscrit.
     * @param numRue     Numero de rue de l'utilisateur inscrit.
     * @param nomRue     Nom de rue de l'utilisateur inscrit.
     * @param codePostal Code postal de l'utilisateur inscrit.
     * @param ville      Ville de l'utilisateur inscrit.
     * @param pays       Pays de l'utilisateur inscrit.
     */
    public Inscrit(String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays) {
        this.pseudo = pseudo;
        this.id = API.getNbInscrit() + 1;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numTel;
        this.mail = mail;
        this.adresse = new Adresse(numRue, nomRue, codePostal, ville, pays);
        this.motdepasse = mdp;
    }

    /**
     * Cree un utilisateur inscrit.
     * 
     * @param id         ID de l'utilisateur inscrit.
     * @param pseudo     Pseudo de l'utilisateur inscrit.
     * @param nom        Nom de l'utilisateur inscrit.
     * @param prenom     Prenom de l'utilisateur inscrit.
     * @param numTel     Numero de telephone de l'utilisateur inscrit.
     * @param mail       Adresse mail de l'utilisateur inscrit.
     * @param mdp        Mot de passe de l'utilisateur inscrit.
     * @param numRue     Numero de rue de l'utilisateur inscrit.
     * @param nomRue     Nom de rue de l'utilisateur inscrit.
     * @param codePostal Code postal de l'utilisateur inscrit.
     * @param ville      Ville de l'utilisateur inscrit.
     * @param pays       Pays de l'utilisateur inscrit.
     */
    public Inscrit(int id, String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays) {
        this.pseudo = pseudo;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numTel;
        this.mail = mail;
        this.adresse = new Adresse(numRue, nomRue, codePostal, ville, pays);
        this.motdepasse = mdp;
    }

    /**
     * Verifie que le mot de passe est bien celui de l'utilisateur inscrit.
     * 
     * @param mdp Mot de passe a verifier.
     * @return si le mot de passe est bien celui de l'utilisateur inscrit.
     */
    public boolean verifMdp(String mdp) {
        return mdp.equals(motdepasse);
    }

    /**
     * Recupere le formulaire permettant a l'utilisateur de se connecter.
     */
    public static String[] getFormulaireConnexion() {
        String[] res = { "mail", "mot de passe" };
        return res;
    }

    /**
     * Ajoute un contrat.
     * 
     * @param c Contrat a ajouter.
     */
    public void addContrat(Contrat c) {
        contrats.add(c);
    }

    /**
     * Recupere tous les contrats qui ont ete conclus.
     */
    public ArrayList<Contrat> getClosedContracts() {
        ArrayList<Contrat> res = new ArrayList<Contrat>();
        for (Contrat contrat : contrats) {
            if (contrat.getIsConcluded()) {
                res.add(contrat);
            }
        }
        return res;
    }

    /**
     * Permet a l'utilisateur inscrit de creer une evaluation.
     * 
     * @param titreProduit Titre du produit a evaluer.
     * @param note         Note de l'evaluation.
     * @param description  Description de l'evaluation.
     * @param destinataire Destinataire de l'evaluation.
     */
    public void creerEvaluation(String titreProduit, int note, String description, Inscrit destinataire) {
        Evaluation evaluation = new Evaluation(titreProduit, note, description, this, destinataire);
        this.evaluations.add(evaluation);
    }

    /**
     * Recupere le menu d'un utilisateur inscrit.
     */
    public String[] getMenu() {
        String[] menu = { "Recherche", "Deconnexion", "Quitter" };
        return menu;
    }

    /**
     * @return Les donnees publiques de l'utilisateur, c'est-a-dire son identifiant
     *         et son pseudo.
     */
    @Override
    public String getAffichageMinimal() {
        return pseudo + " (id:" + id + ")";
    }

    /**
     * Affichage complet, qui ne peut etre vu que par l'inscrit lui-meme (les
     * coordonnees partagees lors des ventes sont gerees dans les sous-classes
     * Acheteur et Vendeur)
     * !!! Le mot de passe n'est JAMAIS affiche !!!
     */
    @Override
    public String toString() {
        return this.getAffichageMinimal() + '\n' + nom + ' ' + prenom + '\n' + mail + ' ' + numeroTel + '\n'
                + adresse.toString();
    }

    /**
     * ID de l'utilisateur inscrit.
     */
    public int getId() {
        // Automatically generated method. Please do not modify this code.
        return this.id;
    }

    /**
     * Nom de l'utilisateur inscrit.
     */
    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }

    /**
     * Prenom de l'utilisateur inscrit.
     */
    public String getPrenom() {
        // Automatically generated method. Please do not modify this code.
        return this.prenom;
    }

    /**
     * Numero de telephone de l'utilisateur inscrit.
     */
    public int getNumeroTel() {
        // Automatically generated method. Please do not modify this code.
        return this.numeroTel;
    }

    /**
     * Pseudo de l'utilisateur inscrit.
     */
    public String getPseudo() {
        // Automatically generated method. Please do not modify this code.
        return this.pseudo;
    }

    /**
     * Adresse mail de l'utilisateur inscrit.
     */
    public String getMail() {
        // Automatically generated method. Please do not modify this code.
        return this.mail;
    }

    /**
     * Mot de passe de l'utilisateur inscrit.
     */
    public String getMotdepasse() {
        // Automatically generated method. Please do not modify this code.
        return this.motdepasse;
    }

    /**
     * Adresse de l'utilisateur inscrit.
     */
    public Adresse getAdresse() {
        // Automatically generated method. Please do not modify this code.
        return this.adresse;
    }

    /**
     * Formulaire permettant a un utilisateur de s'inscrire sur iBuySu.com.
     */
    public static String[] getFormulaireInscription() {
        String[] res = { "pseudo", "nom", "prenom", "numero de telephone", "mail", "mot de passe", "n.rue", "nom rue",
                "code postal", "ville", "pays" };
        return res;
    }
}
