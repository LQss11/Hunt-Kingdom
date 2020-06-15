/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Produit;
import Entities.Promotion;
import Entities.Whishlist;
import Entities.Categorie;
import Util.DataBase;
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
 * @author khalil
 */
public class ServiceWhishlist {
      private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceWhishlist() {
        con =DataBase.getInstance().getConnection(); 
    
    }
     public static void ajouterWishlist (Whishlist w) throws SQLException
    {
        Connection cnx = DataBase.getInstance().getConnection();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into `whishlist` (`idProduit`, `idClient`) values (?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
                      
            pre.setInt(1,w.getProduit().getId()) ; 
            pre.setInt(2,w.getUser().getID()) ; 
   
            pre.executeUpdate() ; 
            System.out.println("DONE");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }  
    //list o products for a user
     public List<Produit> Display(int idUser) throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
                 ServiceProduit sp= new ServiceProduit();
                 Produit pr=new Produit();
                 
    ResultSet rs=ste.executeQuery("select * from whishlist where idClient='"+idUser+"'");
         
     while (rs.next()) {                              
             int idPro=rs.getInt("idProduit");  
             
             pr= sp.ProduitReturn(idPro);              
                     
             arr.add(pr);
                        }
    return arr;
    }
     
     public int deleteFromWhishlist(int idUser,int idp) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM `whishlist` WHERE idClient='"+idUser+"'and idProduit='"+idp+"'";   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
     
     
}
