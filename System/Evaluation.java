package System;

public class Evaluation {
    private int note;
    private String avis;
    private Inscrit auteur;
    private Inscrit destinataire;
    private String titreProduit;

    public Evaluation(String titreProduit, int note, String avis, Inscrit auteur, Inscrit destinataire) {
        this.note = note;
        this.avis = avis;
        this.auteur = auteur;
        this.destinataire = destinataire;
    }

    public String toString() {
        String res = "\u001b[35mProduit :\u001b[0m " + this.titreProduit + '\n';
        String roleAuteur = (auteur instanceof Acheteur) ? "(acheteur)" : "(vendeur)";
        String roleDestinataire = (auteur instanceof Acheteur) ? "(acheteur)" : "(vendeur)";
        res += "Evaluation par " + this.auteur + roleAuteur + "de" + this.destinataire + roleDestinataire + '\n';
        return res + this.note + "/10\n\"" + this.avis + "\"\n" + this.auteur;
    }
}
