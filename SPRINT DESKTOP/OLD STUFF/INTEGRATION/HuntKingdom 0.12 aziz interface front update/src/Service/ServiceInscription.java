/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Evenement;
import Entities.Inscription;
import Entities.Utilisateurs;
import Util.DataSource;
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

public static void ajouterInscription (Evenement E, Utilisateurs U) throws SQLException
    {
         Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into `Inscription` (`idevent`,`iduser`)"
                    + " values (?,?)";
            
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1,E.getId()) ; 
            pre.setInt(2,U.getID()) ; 
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
            String sql = "DELETE FROM `inscription` WHERE `idevent` = "+ idev +" And `iduser` = "+idusr ;
                  i=ste.executeUpdate(sql);
                  System.out.println("Service.ServiceInscription.deleteInscription()");
                }
            catch (SQLException ex) {
                  Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            finally{ste.close();}
            return i;  
    }
public List<Inscription> showInscription() throws SQLException 
{
    List<Inscription> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `inscription`");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idevent=rs.getInt(2);
               int isuser=rs.getInt(3);
               Inscription e=new Inscription(id,idevent,isuser);
     arr.add(e);
     System.out.println(e.getId());
     System.out.println(e.getId_event());
     System.out.println(e.getId_prod());
     }
    return arr;  
}

}
