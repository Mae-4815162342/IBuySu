package System;

import java.util.ArrayList;
import java.util.List;
import BDD.API;

/**
 * Utilisateur possédant un compte dans la base de donnée de iBuySu.com.
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
     * Crée un utilisateur inscrit.
     * 
     * @param pseudo     Pseudo de l'utilisateur inscrit.
     * @param nom        Nom de l'utilisateur inscrit.
     * @param prenom     Prénom de l'utilisateur inscrit.
     * @param numTel     Numéro de téléphone de l'utilisateur inscrit.
     * @param mail       Adresse mail de l'utilisateur inscrit.
     * @param mdp        Mot de passe de l'utilisateur inscrit.
     * @param numRue     Numéro de rue de l'utilisateur inscrit.
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
     * Crée un utilisateur inscrit.
     * 
     * @param id         ID de l'utilisateur inscrit.
     * @param pseudo     Pseudo de l'utilisateur inscrit.
     * @param nom        Nom de l'utilisateur inscrit.
     * @param prenom     Prénom de l'utilisateur inscrit.
     * @param numTel     Numéro de téléphone de l'utilisateur inscrit.
     * @param mail       Adresse mail de l'utilisateur inscrit.
     * @param mdp        Mot de passe de l'utilisateur inscrit.
     * @param numRue     Numéro de rue de l'utilisateur inscrit.
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
     * Vérifie que le mot de passe est bien celui de l'utilisateur inscrit.
     * 
     * @param mdp Mot de passe à vérifier.
     * @return si le mot de passe est bien celui de l'utilisateur inscrit.
     */
    public boolean verifMdp(String mdp) {
        return mdp.equals(motdepasse);
    }

    /**
     * Récupère le formulaire permettant à l'utilisateur de se connecter.
     */
    public static String[] getFormulaireConnexion() {
        String[] res = { "mail", "mot de passe" };
        return res;
    }

    /**
     * Ajoute un contrat.
     * 
     * @param c Contrat à ajouter.
     */
    public void addContrat(Contrat c) {
        contrats.add(c);
    }

    /**
     * Récupère tous les contrats qui ont été conclus.
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
     * Permet à l'utilisateur inscrit de créer une évaluation.
     * 
     * @param titreProduit Titre du produit à évaluer.
     * @param note         Note de l'évaluation.
     * @param description  Description de l'évaluation.
     * @param destinataire Destinataire de l'évaluation.
     */
    public void creerEvaluation(String titreProduit, int note, String description, Inscrit destinataire) {
        Evaluation evaluation = new Evaluation(titreProduit, note, description, this, destinataire);
        this.evaluations.add(evaluation);
    }

    /**
     * Récupère le menu d'un utilisateur inscrit.
     */
    public String[] getMenu() {
        String[] menu = { "Recherche", "Déconnexion", "Quitter" };
        return menu;
    }

    /**
     * @return Les données publiques de l'utilisateur, c'est-à-dire son identifiant
     *         et son pseudo.
     */
    @Override
    public String getAffichageMinimal() {
        return pseudo + " (id:" + id + ")";
    }

    /**
     * Affichage complet, qui ne peut être vu que par l'inscrit lui-même (les
     * coordonnées partagées lors des ventes sont gérées dans les sous-classes
     * Acheteur et Vendeur)
     * !!! Le mot de passe n'est JAMAIS affiché !!!
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
     * Prénom de l'utilisateur inscrit.
     */
    public String getPrenom() {
        // Automatically generated method. Please do not modify this code.
        return this.prenom;
    }

    /**
     * Numéro de téléphone de l'utilisateur inscrit.
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
     * Formulaire permettant à un utilisateur de s'inscrire sur iBuySu.com.
     */
    public static String[] getFormulaireInscription() {
        String[] res = { "pseudo", "nom", "prénom", "numéro de teléphone", "mail", "mot de passe", "n°rue", "nom rue",
                "code postal", "ville", "pays" };
        return res;
    }
}
