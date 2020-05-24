/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Produit;
import entities.Promotion;
import entities.Categorie;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author khalil
 */
public class ServiceCategorie {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceCategorie() {
        con = DataSource.getInstance().getCnx();
    }

    public static void ajouterCategorie(Categorie c) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st = cnx.createStatement();

        try {
            String req = "insert into `categorie` (`nom`, `description`, `image`) values (?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, c.getNom());
            pre.setString(2, c.getDescription());
            pre.setString(3, c.getImage());

            pre.executeUpdate();
            System.out.println("DONE");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public List<Categorie> sortedbyId() throws SQLException {
        List<Categorie> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from categorie order by id desc");
        while (rs.next()) {
            int id = rs.getInt(1);
            String Name = rs.getString("nom");
            String description = rs.getString("description");
            String image = rs.getString("image");

            System.out.println("jbed we7ed");
            Categorie cat = new Categorie(id, Name, description, image);
            arr.add(cat);
        }
        return arr;
    }

    public int deletecategory(int id) throws SQLException {
        int i = 0;
        try {
            ste = con.createStatement();
            String sql = "DELETE FROM `categorie` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }

    public int updatecategory(Categorie cat) throws SQLException {

        String requestUpdate = "UPDATE `categorie` SET `nom`=?, `description`=?, `image`=?   WHERE `id`=?";
        PreparedStatement statement = con.prepareStatement(requestUpdate);
        statement.setString(1, cat.getNom());
        statement.setString(2, cat.getDescription());
        statement.setString(3, cat.getImage());
        statement.setInt(4, cat.getId());

        return statement.executeUpdate();
    }

    public List<Produit> ShowProducts(int idD) throws SQLException {
        List<Produit> arr = new ArrayList<>();
        ste = con.createStatement();
        Statement stee;
        stee = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from produit where categorie='" + idD + "'");
        while (rs.next()) {
            int id = rs.getInt(1);
            int idCat = rs.getInt("categorie");
            String nom = rs.getString("nom");
            int quantite = rs.getInt("quantite");
            String description = rs.getString("description");
            float prix = rs.getFloat("Prix");
            int etatPromo = rs.getInt("etatPromo");
            int garantie = rs.getInt("garantie");
            String image = rs.getString("image");

            Categorie c = new Categorie();
            c.setId(idCat);
            Produit p = new Produit(id, c, nom, quantite, prix, description, garantie, image);
            p.setEtatPromo(etatPromo);
            if (etatPromo != 0) {

                List<Promotion> arpp = new ArrayList<>();

                ResultSet rspp = stee.executeQuery("select * from promotion where idProduit='" + p.getId() + "'");
                while (rspp.next()) {
                    Promotion ppp = new Promotion();

                    int idp = rspp.getInt(1);
                    ppp.setId(idp);
                    int pourcentage = rspp.getInt("pourcentage");
                    ppp.setPourcentage(pourcentage);
                    Float prixpromo = rspp.getFloat("prix");
                    ppp.setPrix(prixpromo);
                    Date date = rspp.getDate("dateFin");
                    ppp.setDateFin(date);

                    p.setPromotion(ppp);
                }
                rspp.close();
            }

            System.out.println("jbed produit");

            arr.add(p);
        }
        return arr;
    }

    public List<Categorie> show(int idd) throws SQLException {
        List<Categorie> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from categorie where id='" + idd + "'");
        while (rs.next()) {
            int id = rs.getInt(1);
            String Name = rs.getString("nom");
            String description = rs.getString("description");
            String image = rs.getString("image");

            System.out.println("jbed we7ed");
            Categorie cat = new Categorie(id, Name, description, image);
            arr.add(cat);
        }
        return arr;
    }
}
