package BDD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public static void fetchData(IBuySu system){
        try{
            if(con == null) {
                con = connexion();
            }
            List<Produit> newProducts = new ArrayList<Produit>(); 
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from Vente_directe");
            while(res.next()){
                byte isSold = res.getByte("estVendu");
                byte isReceived = res.getByte("estRecu");
                newProducts.add(new Produit(res.getString("titre"), res.getString("description"), null, (float) res.getInt("prix_de_depart"), isSold!=0, isReceived!=0));
            }
            Statement stmt2 = con.createStatement();
            res = stmt2.executeQuery("select * from Enchere");
            while(res.next()){
                byte isSold = res.getByte("estVendu");
                byte isReceived = res.getByte("estRecu");
                newProducts.add(new Produit(res.getString("titre"), res.getString("description"), null, (float) res.getInt("prix_de_depart"), isSold!=0, isReceived!=0));
            }
            List<Categorie> categories = new ArrayList<Categorie>();
            Statement stmt3 = con.createStatement();
            res = stmt3.executeQuery("Select * from categorie") ;
            while(res.next()){
                categories.add(new Categorie(res.getString("nom")));
            }   
            List<MotClef> motcles = new ArrayList<MotClef>();
            Statement stmt4 = con.createStatement();
            res = stmt4.executeQuery("Select * from categorie") ;
            while(res.next()){
                motcles.add(new MotClef(res.getString("nom"), null));
            }   
            List<Inscrit> users = new ArrayList<Inscrit>();
            Statement stmt5 = con.createStatement();
            res = stmt5.executeQuery("select * from Acheteur");
            while(res.next()){
                users.add(new Acheteur(res.getString("pseudo"), res.getString("nom"), res.getString("prenom"), res.getInt("numeroTel"), res.getString("mail"), null, -1, null, -1,null, null));
            }
            Statement stmt6 = con.createStatement();
            res = stmt6.executeQuery("select * from vendeur");
            while(res.next()){
                users.add(new Vendeur(res.getString("pseudo"), res.getString("nom"), res.getString("prenom"), res.getInt("numeroTel"), res.getString("mail"), null, -1, null, -1,null,null, null));
            }
            system.setCategorie(categories);
            system.setMotClef(motcles);
            system.setUsers(users);
            system.setProducts(newProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addAcheteur(Acheteur a) {
        String requete = "INSERT Acheteur (nom, prenom, pseudo, numeroTel, mail, motdepasse) VALUES (";
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
    public static void addVendeur(Vendeur v) {
        String requete = "INSERT Vendeur (nom, prenom, pseudo, numeroTel, mail, motdepasse) VALUES (";
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
    public static void insertEvaluation(Evaluation eval, Inscrit u1, Inscrit i2) {
        // Se placer sur la table de Evaluations
       /*  String requete = "INSERT Evaluation (valeur, avis, authorAcheteur,targetedVendeur, authorVendeur targetedAcheteur) VALUES (";
        requete += eval.getNote()+ ",";
        requete += "'" + eval.getAvis() + "',";
        requete += "'" + a.getPrenom() + "',";
        requete += "'" + a.getPseudo() + "',";
        requete += a.getNumeroTel() + ",";
        requete += "'" + a.getMail() + "',";
        requete += "'" + a.getMotdepasse() + "')"; */
        // Ajouter une nouvelle ligne avec les infos de e
        // Ajouter la clé étrangre de l'utilisateur
        try {
            if(con == null) {
                con = connexion();
            }
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retourne e 
    }
    public static int getMeilleurPrix(int productID) {
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from Enchere where id=" + productID);
            ResultSet res2 = stmt.executeQuery("select * from Offre where id=" + res.getInt("meilleur_offre"));
            return res2.getInt("somme");
        }
        catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    public static void updateEnchere(Enchere bid) {
        String requete = "UPDATE enchere";
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
