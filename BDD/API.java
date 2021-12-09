package BDD;

import java.sql.*;
import System.*;

public class API {
    private static Connection con;

    public static void setConnexion() throws Exception {
        try {
            con = connexion();
        } catch (Exception e) {
            throw e;
        }
    }

    public static Connection connexion() throws Exception {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/i_buy_su?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
                    "ibuysubdd", "ibuysubdd");
            System.out.println("Connexion avec la base de données établie");
        } catch (SQLException e) {
            throw e;
        }
        return con;
    }

    public static void addAcheteur(Acheteur a) {
        String requete = "INSERT Acheteur (id, nom, prenom, pseudo, numeroTel, mail, motdepasse) VALUES (";
        requete += a.getId() + ",";
        requete += "'" + a.getNom() + "',";
        requete += "'" + a.getPrenom() + "',";
        requete += "'" + a.getPseudo() + "',";
        requete += a.getNumeroTel() + ",";
        requete += "'" + a.getMail() + "',";
        requete += "'" + a.getMotdepasse() + "')";

        try {
            if (con == null) {
                con = connexion();
            }
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addVendeur(Vendeur v) {
        String requete = "INSERT Vendeur (id, nom, prenom, pseudo, numeroTel, mail, motdepasse) VALUES (";
        requete += v.getId() + ",";
        requete += "'" + v.getNom() + "',";
        requete += "'" + v.getPrenom() + "',";
        requete += "'" + v.getPseudo() + "',";
        requete += v.getNumeroTel() + ",";
        requete += "'" + v.getMail() + "',";
        requete += "'" + v.getMotdepasse() + "')";
        try {
            if (con == null) {
                con = connexion();
            }
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNbInscrit() {
        try {
            if (con == null) {
                con = connexion();
            }
            Statement stmt = con.createStatement();
            ResultSet vendeurs = stmt.executeQuery("SELECT COUNT(*) FROM Vendeur");
            vendeurs.next();
            int nbVendeurs = vendeurs.getRow();
            ResultSet acheteurs = stmt.executeQuery("SELECT COUNT(*) FROM Acheteur");
            acheteurs.next();
            int nbAcheteurs = acheteurs.getRow();
            return nbAcheteurs + nbVendeurs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
