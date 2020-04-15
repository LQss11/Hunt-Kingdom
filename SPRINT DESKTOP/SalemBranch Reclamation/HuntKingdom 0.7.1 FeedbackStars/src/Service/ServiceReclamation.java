/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Reclamation;
import Util.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LQss
 */
public class ServiceReclamation {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceReclamation() {
        con = DataSource.getInstance().getCnx();
    }

    public static void ajouterReclamation(Reclamation r) throws SQLException {

        /*  
        try
        {
            Connection cnx = DataSource.getInstance().getCnx();
             Statement st = cnx.createStatement();
             String Sujet ="jav";
             String Description ="jav";
             String insert = "INSERT INTO `reclamation` (`id`, `type`, `ido`, `sujet`, `description`, `date`, `etat`, `idU`) VALUES (NULL, 'Bug', '12', '"+Sujet+"','"+Description+"', '2020-03-09 06:11:00', 'TEST', '1') ";
             st.executeUpdate(insert);
        }catch (SQLException e){
            System.err.println(e);
        }
         */
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            Statement st = cnx.createStatement();
            String req = "INSERT INTO `reclamation` (`type`, `ido`, `sujet`,"
                    + "`description`, `date`, `etat`, `idU`) values (?,?,?,?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, r.getType());
            pre.setInt(2, 0);
            pre.setString(3, r.getSujet());
            pre.setString(4, r.getDescription());
            pre.setDate(5, r.getDate());
            pre.setString(6, r.getEtat());
            pre.setInt(7, r.getUser());

            pre.executeUpdate();
            System.out.println("DONE");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public List<Reclamation> sortedbyId() throws SQLException {
        List<Reclamation> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from reclamation order by id desc");
        while (rs.next()) {
            int id = rs.getInt(1);

            String type = rs.getString("type");
            int ido = rs.getInt("ido");
            String sujet = rs.getString("sujet");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            String etat = rs.getString("etat");
            int idU = rs.getInt("idU");

            System.out.println("jbed we7ed");
            Reclamation rec = new Reclamation(id, type, ido, sujet, description, date, etat, idU);
            arr.add(rec);
        }
        return arr;
    }

    /*
    public int updatereclamation(Reclamation r) throws SQLException {

        String requestUpdate = "UPDATE `reclamation` SET `type`=?, `ido`=?, `sujet`=?, `description`=?, `date`=?, `etat`=?, `idU`=?    WHERE `id`=?";
        PreparedStatement pre = con.prepareStatement(requestUpdate);
        pre.setString(1, r.getType());
        pre.setInt(2, 0);
        pre.setString(3, r.getSujet());
        pre.setString(4, r.getDescription());
        pre.setDate(5, r.getDate());
        pre.setString(6, r.getEtat());
        pre.setInt(7, r.getUser());
        pre.setInt(8, r.getId());

        return pre.executeUpdate();
    }
     */
    public int updatereclamation(Reclamation r) throws SQLException {

        String requestUpdate = "UPDATE `reclamation` SET `type`=?, `sujet`=?, `description`=?  WHERE `id`=?";
        PreparedStatement pre = con.prepareStatement(requestUpdate);
        pre.setString(1, r.getType());
        pre.setString(2, r.getSujet());
        pre.setString(3, r.getDescription());
        pre.setInt(4, r.getId());
        System.out.println(r);
        return pre.executeUpdate();
    }

    public int updateEtat(Reclamation r) throws SQLException {

        String requestUpdate = "UPDATE `reclamation` SET `etat`=?  WHERE `id`=?";
        PreparedStatement pre = con.prepareStatement(requestUpdate);
        pre.setString(1, r.getEtat());
        pre.setInt(2, r.getId());
        System.out.println(r);
        return pre.executeUpdate();
    }
        public int updateDescription(Reclamation r) throws SQLException {

        String requestUpdate = "UPDATE `reclamation` SET `description`=?  WHERE `id`=?";
        PreparedStatement pre = con.prepareStatement(requestUpdate);
        pre.setString(1, r.getDescription());
        pre.setInt(2, r.getId());
        System.out.println(r);
        return pre.executeUpdate();
    }
    

    public int deletereclamation(int id) throws SQLException {
        int i = 0;
        try {
            ste = con.createStatement();
            String sql = "DELETE FROM `reclamation` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }

    public List<Reclamation> show(int idr) throws SQLException {
        List<Reclamation> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from reclamation where id='" + idr + "'");
        while (rs.next()) {
            int id = rs.getInt(1);

            String type = rs.getString("type");
            int ido = rs.getInt("ido");
            String sujet = rs.getString("sujet");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            String etat = rs.getString("etat");
            int idU = rs.getInt("idU");

            System.out.println("jbed we7ed");
            Reclamation rec = new Reclamation(id, type, ido, sujet, description, date, etat, idU);
            arr.add(rec);
        }
        return arr;
    }

    public List<Reclamation> own(int idr) throws SQLException {
        List<Reclamation> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from reclamation where idU='" + idr + "'");
        while (rs.next()) {
            int id = rs.getInt(1);

            String type = rs.getString("type");
            int ido = rs.getInt("ido");
            String sujet = rs.getString("sujet");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            String etat = rs.getString("etat");
            int idU = rs.getInt("idU");

            System.out.println("jbed we7ed");
            Reclamation rec = new Reclamation(id, type, ido, sujet, description, date, etat, idU);
            arr.add(rec);
        }
        return arr;
    }
}
