/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.Feedback;
import Util.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LQss
 */
public class ServiceFeedback {
    
    
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceFeedback() {
        con = DataSource.getInstance().getCnx();
    }

    public static void ajouterFeedback(Feedback o) throws SQLException {
        
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            Statement st = cnx.createStatement();
            String req = "INSERT INTO `feedback` (`description`, `rate`, `idU`,"
                    + "`date`) values (?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, o.getDescription());
            pre.setInt(2, o.getRate());
            pre.setInt(3, o.getUser());
            pre.setDate(4, o.getDate());

            pre.executeUpdate();
            System.out.println("DONE");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    
        public List<Feedback> sortedbyId() throws SQLException {
        List<Feedback> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from feedback order by id desc");
        while (rs.next()) {
            int id = rs.getInt(1);

            String description = rs.getString("description");
            int rate = rs.getInt("rate");
            Date date = rs.getDate("date");
            int idU = rs.getInt("idU");

            System.out.println("jbed we7ed");
            Feedback o = new Feedback(id, description, rate, date, idU);
            arr.add(o);
        }
        return arr;
    }
}
