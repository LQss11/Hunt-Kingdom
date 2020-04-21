/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Evenement;

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
public class ServiceEvenement 
{
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    
public ServiceEvenement() 
{
    con =DataSource.getInstance().getCnx();
}  



public static void ajouterEvenement (Evenement E) throws SQLException
    {
         Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into `evenement` (`nom`, `type`, `prix`,"
                    + "`nbrplace`,`date`,`place`,`description`,`duree`,`image`)"
                    + " values (?,?,?,"
                    + "?,?,?,?,?,?)";
            
            PreparedStatement pre = cnx.prepareStatement(req);
                      
            pre.setString(1,E.getNom()) ; 
            pre.setString(2,E.getType()) ;
            pre.setDouble(3,E.getPrix()) ;
            pre.setInt(4,E.getNbrplace()) ;           
            pre.setDate(5,E.getDate()) ;
            pre.setString(6,E.getPlace()) ;
            pre.setString(7,E.getDescription()) ;
            pre.setInt(8,E.getDuree()) ;
            pre.setString(9,E.getImage()) ;
            pre.executeUpdate() ; 
            System.out.println("DONE Produit");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }


public void modifierEvenement(Evenement p) throws SQLException {
         
           String requestUpdate="UPDATE `evenement` SET `nom`=?, `type`=?, "
                   + "`prix`=? ,`nbrplace`=?,`date`=?, `place`=?, `description`=?, `duree`=?, `image`=?  WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setString(1,p.getNom());
        statement.setString(2, p.getType());
        statement.setDouble(3, p.getPrix());
        statement.setInt(4,p.getNbrplace());
        statement.setDate(5,p.getDate());
        statement.setString(6,p.getPlace());
        statement.setString(7,p.getPlace());
        statement.setDouble(8,p.getDuree());
        statement.setString(9,p.getImage());
        statement.setInt(10,p.getId());
        statement.executeUpdate();
        System.out.println("DONE Modif Produit");
    }



public int supprimerEvenement(int id) throws SQLException  {
            int i = 0;
            try {
                  ste=con.createStatement();
                  String sql="DELETE FROM evenement WHERE id="+id;   
                  i=ste.executeUpdate(sql);
                }
            catch (SQLException ex) {
                  Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            finally{ste.close();}
            return i;  
    }

public List<Evenement> afficherEvenement() throws SQLException 
{
    List<Evenement> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `evenement`");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(2);
               String type=rs.getString(3);
               double prix=rs.getDouble(4);
               int nbrplace=rs.getInt(5);
               Date date=rs.getDate(6);
               String place=rs.getString(7);
               String description=rs.getString(8);
               int duree=rs.getInt(9);
               String image=rs.getString(10);
               Evenement e=new Evenement(id,nom,type,prix,nbrplace,date,place,description,duree,image);
     arr.add(e);
     System.out.println(e);
     }
    return arr;  
}

}
