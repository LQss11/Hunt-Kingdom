/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Utilisateurs;
import Util.DataBase;
import Entities.UtilisateursAdresses;
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
 * @author skand
 */
public class ServiceUtilisateursAdresses {
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceUtilisateursAdresses() {
        con =DataBase.getInstance().getConnection();
    }

    public static int ajouterUtilisateursAdresses (UtilisateursAdresses a ,int id) throws SQLException
    {
        Connection cnx = DataBase.getInstance().getConnection();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into utilisateurs_adresses (`utilisateur_id`, `nom`,"
                    + "`prenom`,`telephone`,`adresse`,cp,pays,ville,complement)"
                    + " values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
                      
             
            pre.setInt(1,id) ; 
            pre.setString(2, a.getNom()); 
            pre.setString(3,a.getPrenom()) ;
            pre.setString(4,a.getTelephone()) ;
            pre.setString(5,a.getAdresse()) ;
            pre.setString(6,a.getCp()) ;
            pre.setString(7,a.getPays()) ;
            pre.setString(8,a.getVille()) ;
            pre.setString(9,a.getComplement()) ;
            
          
        
            return pre.executeUpdate() ; 
           
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return 0;
  
   
    }
    
    
    
    public List<UtilisateursAdresses> sortedbyId(int id) throws SQLException {
        List<UtilisateursAdresses> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from utilisateurs_adresses where utilisateur_id = "+String.valueOf(id));
     while (rs.next()) {                
               UtilisateursAdresses adrr = new UtilisateursAdresses(rs.getInt("id"), rs.getInt("utilisateur_id"), rs.getString("nom") , rs.getString("prenom"), rs.getString("telephone") ,rs.getString("adresse"),rs.getString("cp"),rs.getString("pays"),rs.getString("ville"),rs.getString("complement"));
               arr.add(adrr);                     
     }
    return arr;
    }
    
     public int deleteUtilisateursAdresses(int id) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM utilisateurs_adresses WHERE id="+id;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
     
      return i;  
    }
           
     
     
     
       public int updateUtilisateursAdresses(UtilisateursAdresses a) throws SQLException {
         
           String requestUpdate="UPDATE `UtilisateursAdresses` SET `nom`=?, `prenom`=?, "
                   + "`telephone`=? ,`adresse`=?,`cp`=?, `pays`=?,`ville`=?,`complement`=?  WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,a.getId()) ;
        statement.setInt(2,a.getUtilisateur_id()) ; 
        statement.setString(3, a.getNom()); 
        statement.setString(4,a.getPrenom()) ;
        statement.setString(5,a.getTelephone()) ;
        statement.setString(6,a.getAdresse()) ;
        statement.setString(7,a.getCp()) ;
        statement.setString(8,a.getPays()) ;
        statement.setString(9,a.getVille()) ;
        statement.setString(10,a.getComplement()) ;
        
        
         return statement.executeUpdate();
    }

    
}
