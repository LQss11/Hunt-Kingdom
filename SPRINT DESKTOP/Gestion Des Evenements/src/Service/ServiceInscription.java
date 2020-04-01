/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Evenement;
import Entities.Inscription;
import Entities.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author aziz9
 */
public class ServiceInscription 
{

    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    
public ServiceInscription() 
{
    con =DataSource.getInstance().getCnx();
}  

public static void ajouterInscription (Evenement E, User U) throws SQLException
    {
         Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into `Inscription` (`idevent`,`iduser`)"
                    + " values (?,?)";
            
            PreparedStatement pre = cnx.prepareStatement(req);
                      
            pre.setInt(2,U.getId()) ; 
            pre.setInt(1,E.getId()) ;
            pre.executeUpdate() ; 
            System.out.println("DONE Inscription");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }
    
public int deleteInscription(int idev, int idusr) throws SQLException  {
            int i = 0;
            try {
                  ste=con.createStatement();
                  String sql="DELETE FROM inscription WHERE id="+idev;   
                  i=ste.executeUpdate(sql);
                }
            catch (SQLException ex) {
                  Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            finally{ste.close();}
            return i;  
    }

}
