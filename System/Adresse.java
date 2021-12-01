package System;

public class Adresse {
    private String ville;
    private int codePostal;
    private String pays ;
    private int numeroRue;
    private String nomRue;

    public Adresse(int numRue, String nomRue, int codePostal, String ville, String pays) {
        this.numeroRue = numRue;
        this.nomRue = nomRue;
        this.codePostal = this.verif(codePostal)? codePostal : 00000;
        this.ville = ville;
        this.pays = pays;
    }

    //cette fonction vérifie simplement que le code postal ne dépasse pas 99 999 afin que le format ne soit pas
    //absurde
    public boolean verif(int codePostal) {
        return codePostal/100_000 < 1;
    }

    public String toString() {
        return numeroRue + " " + nomRue + ", " + codePostal + " " + ville + ", " + pays;
    }
}
