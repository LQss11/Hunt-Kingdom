/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import entities.Produit;
import entities.Promotion;

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
public class ServicePromotion {
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServicePromotion() {
        con =DataSource.getInstance().getCnx();
    }
    
    public static void ajouterPromotion (Promotion p) throws SQLException
    {
        Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into `promotion` (`pourcentage`, `idProduit`, `dateFin`,"
                    + "`dateDebut`,`prix`,``active)"
                    + " values (?,?,?,"
                    + "?,?)";
           
            String req2="update produit set etatPromo=1 where id='"+p.getProduit().getId()+"'";
        PreparedStatement pre2 = cnx.prepareStatement(req2);      
        pre2.executeUpdate() ; 
        
        
        ServiceProduit pc=new ServiceProduit();
       Float px= pc.PrixForPromo(p.getProduit().getId());
        
       
        PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1,p.getPourcentage()) ; 
            pre.setInt(2,p.getProduit().getId()) ;
            pre.setDate(3,p.getDateFin()) ;
            pre.setDate(4,p.getDateDebut()) ;
            px*=p.getPourcentage();
            pre.setFloat(5,px) ;           
          pre.setInt(6,1) ;
  
            pre.executeUpdate() ; 
            System.out.println("DONE Promotion");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }
     
     public List<Promotion> sortedbyId() throws SQLException {
        List<Promotion> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from promotion order by id");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int pourcentage=rs.getInt("pourcentage");           
               int idp =rs.getInt("idProduit");     
               Date datefin=rs.getDate("dateFin");
               Date dateDebut=rs.getDate("dateDebut");
               Float prix =rs.getFloat("Prix");
               int active =rs.getInt("active");
               
               
              ServiceProduit pc=new ServiceProduit();
       Produit p= new Produit();  
               p=pc.ProduitReturn(idp);
               
               Promotion pp=new Promotion(id,pourcentage,p,datefin,dateDebut,prix,active);
              
            
              
               
     arr.add(pp);
     }
    return arr;
    }
    
     
           public int deletePromotion(int id) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM promotion WHERE id="+id;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
           
       public int updatePromotion(Promotion p) throws SQLException {
         
           String requestUpdate="UPDATE `produit` SET `pourcentage`=?, `dateFin`=? WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,p.getPourcentage());
        statement.setDate(2, p.getDateFin());
        statement.setInt(3,p.getId());
        
         return statement.executeUpdate();
    }
         public int ActiverPromotion(Promotion p) throws SQLException {
         
           String requestUpdate="UPDATE `produit` SET `active`=? WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,p.getActive());
      
        statement.setInt(2,p.getId());
        
         return statement.executeUpdate();
    }
    
}
