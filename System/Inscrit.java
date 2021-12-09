package System;

import java.util.ArrayList;
import java.util.List;
import BDD.API;

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

    public Inscrit(String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays) {
        this.pseudo = pseudo;
        this.id = API.getNbInscrit();
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numTel;
        this.mail = mail;
        this.adresse = new Adresse(numRue, nomRue, codePostal, ville, pays);
        this.motdepasse = mdp;
    }

    public boolean verifMdp(String mdp) {
        return mdp == motdepasse;
    }

    public void addContrat(Contrat c) {
        contrats.add(c);
    }

    public ArrayList<Contrat> getClosedContracts() {
        ArrayList<Contrat> res = new ArrayList<Contrat>();
        for (Contrat contrat : contrats) {
            if (contrat.getIsConcluded()) {
                res.add(contrat);
            }
        }
        return res;
    }

    public void creerEvaluation(String titreProduit, int note, String description, Inscrit destinataire) {
        Evaluation evaluation = new Evaluation(titreProduit, note, description, this, destinataire);
        this.evaluations.add(evaluation);
    }

    public String[] getMenu() {
        String[] menu = { "Recherche", "Evaluer un utilisateur", "Quitter" };
        return menu;
    }

    @Override
    // cette méthode retourne les données publiques de l'utilisateur, c'est-à-dire
    // son identifiant et son pseudonyme
    public String getAffichageMinimal() {
        return pseudo + " (id:" + id + ")";
    }

    // affichage complet, qui ne peut être vu que par l'inscrit lui-même (les
    // coordonnées partagées lors des ventes
    // sont gérées dans les sous-classes Acheteur et Vendeur)
    // !!! le mot de passe n'est JAMAIS affiché
    public String toString() {
        return this.getAffichageMinimal() + '\n' + nom + ' ' + prenom + '\n' + mail + ' ' + numeroTel + '\n'
                + adresse.toString();
    }

    public int getId() {
        // Automatically generated method. Please do not modify this code.
        return this.id;
    }

    public String getNom() {
        // Automatically generated method. Please do not modify this code.
        return this.nom;
    }

    public String getPrenom() {
        // Automatically generated method. Please do not modify this code.
        return this.prenom;
    }

    public int getNumeroTel() {
        // Automatically generated method. Please do not modify this code.
        return this.numeroTel;
    }

    public String getPseudo() {
        // Automatically generated method. Please do not modify this code.
        return this.pseudo;
    }

    public String getMail() {
        // Automatically generated method. Please do not modify this code.
        return this.mail;
    }

    public String getMotdepasse() {
        // Automatically generated method. Please do not modify this code.
        return this.motdepasse;
    }

    public static String[] getFormulaireInscription() {
        String[] res = { "pseudo", "nom", "prénom", "numéro de teléphone", "mail", "mot de passe", "n°rue", "nom rue",
                "code postal", "ville", "pays" };
        return res;
    }

}
