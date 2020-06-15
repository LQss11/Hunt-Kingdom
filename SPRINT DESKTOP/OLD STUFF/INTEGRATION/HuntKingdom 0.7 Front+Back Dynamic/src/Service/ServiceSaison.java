/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.DataSource;
import Entities.Saison;
import java.sql.Connection;
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
 * @author ilyess
 */
public class ServiceSaison {
      private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    public ServiceSaison(){
       con = DataSource.getInstance().getCnx();
    }
       public static void ajouterSaison(Saison s) throws SQLException {
          Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "INSERT INTO `saison`( `nom`, `periode`, `mode`) VALUES (?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
                      
            pre.setString(1,s.getNom()) ; 
            pre.setString(2,s.getPeriode()) ;
            pre.setString(3,s.getMode()) ;
            
            pre.executeUpdate() ; 
            System.out.println("DONE Saison");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }
        public void modifierSaison (Saison s) throws SQLException {
        
        String requetemodifier = "UPDATE `saison` SET `nom`=?, `periode`=?,`mode`=?";  
        PreparedStatement pre = con.prepareStatement(requetemodifier);
        pre.setString(1,s.getNom()) ; 
            pre.setString(2,s.getPeriode()) ;
            pre.setString(3,s.getMode()) ;
            
             
            System.out.println("Update  Saison");
        
        
    }
          public int deleteSaison(int idSaison) throws SQLException {
       int i = 0;
        try {
            ste = con.createStatement();
            String sql = "DELETE FROM `Saison` WHERE idSaison=" + idSaison;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSaison.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
          public List<Saison> readAll() throws SQLException {
       List<Saison> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet result = ste.executeQuery("select * from `saison`");
        while (result.next()){
            int idSaison=result.getInt(1);
            String nom=result.getString(2);
            String periode=result.getString(3);
            String mode=result.getString(4);
            Saison s = new Saison(nom,periode,mode);
            arr.add(s);
        }
       return arr;
    }

    
          public List<Saison> sortedbyNom(String nom) throws SQLException {
        List<Saison> arr = new ArrayList<>();
        ste = con.createStatement();
          ResultSet result=ste.executeQuery("select * from saison where nom='"+nom+"'");
        while (result.next()) {
             int idSaison=result.getInt(1);
            //String nom=result.getString(2);
            String periode=result.getString(3);
            String mode=result.getString(4);
            
            Saison e= new Saison(idSaison,nom,periode,mode);
            
        

            System.out.println("jbed we7ed");
            arr.add(e);
        }
        return arr;
         }
}
