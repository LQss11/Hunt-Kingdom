/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Produit;
import Entities.Promotion;

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
        con =DataBase.getInstance().getConnection();
    }
    
    public static void ajouterPromotion (Promotion p) throws SQLException
    {
        Connection cnx = DataBase.getInstance().getConnection();
         Statement st = cnx.createStatement();
          ServiceProduit pc=new ServiceProduit();

          
             String req2="update produit set etatPromo=1 where id='"+p.getProduit().getId()+"'";
        PreparedStatement pre2 = cnx.prepareStatement(req2);      
        pre2.executeUpdate() ; 
   
            String req = "insert into `promotion` (`pourcentage`, `idProduit`, `dateFin`,"
                    + "`dateDebut`,`prix`,`active`)"
                    + " values (?,?,?,"
                    + "?,?,?)";
       
       float px= p.getProduit().getPrix();
        float x=0;
       x=(px*100)-(px*p.getPourcentage());
       x/=100;
       p.setActive(1);
       p.setPrix(x);
        PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1,p.getPourcentage()) ; 
            pre.setInt(2,p.getProduit().getId()) ;
            pre.setDate(3,p.getDateFin()) ;
            pre.setDate(4,p.getDateDebut()) ;
            pre.setFloat(5,x) ;           
          pre.setInt(6,p.getActive()) ;
          System.out.println(p);
  
            pre.executeUpdate() ; 
       
   
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
    
       public Promotion ReturnPRomotionById(int idPromo ) throws SQLException {
       
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    Promotion pp=new Promotion();
    ResultSet rs=ste.executeQuery("select * from promotion where id='"+idPromo+"'");
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
               
               pp=new Promotion(id,pourcentage,p,datefin,dateDebut,prix,active);
      return pp;
     }
    return pp;
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
         
           String requestUpdate="UPDATE `promotion` SET `pourcentage`=?, `dateFin`=? WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,p.getPourcentage());
        statement.setDate(2, p.getDateFin());
        statement.setInt(3,p.getId());
        
         return statement.executeUpdate();
    }
         public int ActiverPromotion(int id) throws SQLException {
         
           String requestUpdate="UPDATE `promotion` SET `active`=? WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,1);
        ServicePromotion spromo=new ServicePromotion();
      Promotion promo=spromo.ReturnPRomotionById(id);
        statement.setInt(2,id);
        ServiceProduit sprod=new ServiceProduit();
        sprod.updateEtatPromoProduit(promo.getProduit().getId(), 1);
         return statement.executeUpdate();
         
    }
            public int DesActiverPromotion(int id) throws SQLException {
         
           String requestUpdate="UPDATE `promotion` SET `active`=? WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setInt(1,0);
      ServicePromotion spromo=new ServicePromotion();
      Promotion promo=spromo.ReturnPRomotionById(id);
        statement.setInt(2,id);
        ServiceProduit sprod=new ServiceProduit();
        sprod.updateEtatPromoProduit(promo.getProduit().getId(), 0);
         return statement.executeUpdate();
    }
    
           public float PrixPromo(int idP) throws SQLException {
      
    ste=con.createStatement();
    Statement stee;
    float prix=0;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from promotion where idProduit ='"+idP+"'");
     while (rs.next()) {                              
                prix =rs.getFloat("Prix");          
     }
    return prix;
    }
            public int ProduitPromo(int idP) throws SQLException {
      
    ste=con.createStatement();
    Statement stee;
    int id=0;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from promotion where id ='"+idP+"'");
     while (rs.next()) {                              
                id =rs.getInt("idProduit");          
     }
    return id;
    }
         
}
