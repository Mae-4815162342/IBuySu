package BDD;
import java.sql.*;
import System.*;

public class API {
    private static Connection con;

    public static void setConnexion() {
        con = connexion();
    }

    public static Connection connexion(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/i_buy_su?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","ibuysubdd", "ibuysubdd");
            System.out.println("Connexion avec la base de données établie");
        } catch (SQLException e) {
            System.out.println(e);
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
            if(con == null) {
                con = connexion();
            }
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
