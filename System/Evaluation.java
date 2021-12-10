package System;

public class Evaluation {
    private int note;
    private String avis;
    private Inscrit auteur;
    private Inscrit destinataire;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Inscrit getAuteur() {
        return auteur;
    }

    public void setAuteur(Inscrit auteur) {
        this.auteur = auteur;
    }

    public Inscrit getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Inscrit destinataire) {
        this.destinataire = destinataire;
    }

    public String getTitreProduit() {
        return titreProduit;
    }

    public void setTitreProduit(String titreProduit) {
        this.titreProduit = titreProduit;
    }

    public Evaluation(String titreProduit, int note, String avis, Inscrit auteur, Inscrit destinataire){
        this.note = note;
        this.avis = avis;
        this.auteur = auteur;
        this.destinataire = destinataire;
    }

    public String toString() {
        String res = "Produit : " + this.titreProduit + '\n';
        String roleAuteur = (auteur instanceof Acheteur) ? "(acheteur)" : "(vendeur)";
        String roleDestinataire = (auteur instanceof Acheteur) ? "(acheteur)" : "(vendeur)";
        res += "Evaluation par " + this.auteur + roleAuteur + "de"+ this.destinataire + roleDestinataire + '\n';
        return res+ this.note + "/10\n\"" + this.avis + "\"\n" + this.auteur;
    }
}
