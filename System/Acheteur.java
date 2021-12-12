package System;

import java.util.ArrayList;
import java.util.List;

public class Acheteur extends Inscrit {
    public List<Enchere> offres = new ArrayList<Enchere>();

    public Acheteur(String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
            String nomRue, int codePostal, String ville, String pays) {
        super(pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
    }

    public Acheteur(int id, String pseudo, String nom, String prenom, int numTel, String mail, String mdp, int numRue,
                    String nomRue, int codePostal, String ville, String pays) {
        super(id, pseudo, nom, prenom, numTel, mail, mdp, numRue, nomRue, codePostal, ville, pays);
    }

    public Acheteur(String[] formulaireRempli) {
        this(formulaireRempli[0], formulaireRempli[1], formulaireRempli[2], Integer.parseInt(formulaireRempli[3]),
                formulaireRempli[4], formulaireRempli[5], Integer.parseInt(formulaireRempli[6]), formulaireRempli[7],
                Integer.parseInt(formulaireRempli[8]), formulaireRempli[9], formulaireRempli[10]);
    }

    public void participerEnchere(Enchere e, float prix) {
        Offre offre = new Offre(e, prix, this);
        offres.add(e);
        e.addOffre(offre);
    }

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
