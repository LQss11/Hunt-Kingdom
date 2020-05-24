/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.DataBase;
import Entities.Categorie;
import Entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Commandes;
import Entities.Promotion;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author skand
 */
public class ServiceCommandes {
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceCommandes() {
        con =DataBase.getInstance().getConnection();
    }

    public  void ajouterCommande (Commandes c) throws SQLException
    {
        Connection cnx = DataBase.getInstance().getConnection();
         Statement st = cnx.createStatement();
            String req = "insert into Commandes ( `utilisateur_id`, `valider`,"
                    + "`date`,`reference`,`commande`)"
                    + " values (?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
                      
           
             pre.setInt(1,c.getUtilisateur_id()) ; 
            pre.setBoolean(2, c.isValider()); ;
            pre.setDate(3,c.getDate()) ;
            
            pre.setInt(4,c.getReference()) ;
            pre.setString(5,c.getCommande());
          
        
            pre.executeUpdate() ; 

    }
    
    
    
    public  List<Commandes> sortedbyId() throws SQLException {
        List<Commandes> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commandes order by id");
     while (rs.next()) {                
               Commandes com = new Commandes(rs.getInt("id"), rs.getInt("utilisateur_id"), rs.getBoolean("valider") , rs.getDate("date"), rs.getInt("reference") ,rs.getString("commande"));
               arr.add(com);                     
     }
    return arr;
    }
    
     public int deleteCommande(int id) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM produit WHERE id="+id;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
           
     
     
     
       public int updateCommande(Commandes c) throws SQLException {
         
           String requestUpdate="UPDATE `UtilisateursAdresses` SET `nom`=?, `description`=?, "
                   + "`image`=? ,`quantite`=?,`prix`=?, `garantie`=?  WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,c.getId()) ; 
        statement.setBoolean(2, c.isValider()); ;
        statement.setDate(3,c.getDate()) ;
        statement.setInt(4,c.getReference()) ;
        statement.setString(5,c.getCommande());
        
         return statement.executeUpdate();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
