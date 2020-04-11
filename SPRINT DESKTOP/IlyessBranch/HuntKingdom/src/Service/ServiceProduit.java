/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import entities.Produit;
import Utils.DataSource;
import entities.Categorie;
import entities.Promotion;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceProduit {
    
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceProduit() {
        con =DataSource.getInstance().getCnx();
    }

     public static void ajouterProduit (Produit p) throws SQLException
    {
        Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "insert into `produit` (`nom`, `categorie`, `quantite`,"
                    + "`description`,`prix`,`etatPromo`,`garantie`,`image`)"
                    + " values (?,?,?,"
                    + "?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
                      
            pre.setString(1,p.getNom()) ; 
            pre.setInt(2,p.getCategorie().getId()) ;
            pre.setInt(3,p.getQuantite()) ;
            
            pre.setString(4,p.getDescription()) ;           
            pre.setFloat(5,p.getPrix()) ;
            pre.setInt(6,0) ;
   pre.setInt(7,p.getGarantie()) ;
   pre.setString(8,p.getImage()) ;
            pre.executeUpdate() ; 
            System.out.println("DONE Produit");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }
     
     public List<Produit> sortedbyId() throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit order by id");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
    
      public List<Produit> sortedbyPrix() throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit order by prix");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
      
       public List<Produit> sortedbyPrixDesc() throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit order by prix desc");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
     
        public List<Produit> sortedbyNom() throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit order by nom");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
       
      public List<Produit> searchByNom(String nomm) throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit where nom like'"+nomm+"' or prix like '"+nomm+"'");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
        
      public List<Produit> searchByPrix(Float px1, Float px2) throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit where prix between'"+px1+"' and '"+px2+"'");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
      
       public List<Produit> Show(int idD) throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit where id='"+idD+"'");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
                            if(etatPromo != 0){

                                  List<Promotion> arpp=new ArrayList<>();

                              ResultSet rspp=stee.executeQuery("select * from promotion where idProduit='"+p.getId()+"'");
                                while (rspp.next()) { 
                                    Promotion ppp=new Promotion();
                                    
                                    int idp=rspp.getInt(1);
                                        ppp.setId(idp);
                                    int pourcentage =rspp.getInt("pourcentage");
                                        ppp.setPourcentage(pourcentage);
                                    Float prixpromo=rspp.getFloat("prix");
                                        ppp.setPrix(prixpromo);
                                    Date date=rspp.getDate("dateFin");
                                        ppp.setDateFin(date);
                                   
                                    p.setPromotion(ppp);
                                                    }
                                rspp.close();
                                            }
              
             System.out.println("jbed produit");  
               
     arr.add(p);
     }
    return arr;
    }
     
       public Produit ProduitReturn(int idD) throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit where id='"+idD+"'");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idCat=rs.getInt("categorie");
               String nom=rs.getString("nom");
               int quantite =rs.getInt("quantite");
               String description=rs.getString("description");
               float prix=rs.getFloat("Prix");
               int etatPromo =rs.getInt("etatPromo");
               int garantie =rs.getInt("garantie");
               String image= rs.getString("image");
               
               Categorie c=new Categorie();
               c.setId(idCat);
              Produit p=new Produit(id, c,nom, quantite,prix, description,garantie, image) ;
              p.setEtatPromo(etatPromo);
    System.out.println("jbed produit");  
               
     return p;
     }
    return null;
    }
       
      public int deleteProduit(int id) throws SQLException  {
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
           
       public int updateProduit(Produit p) throws SQLException {
         
           String requestUpdate="UPDATE `produit` SET `nom`=?, `description`=?, "
                   + "`image`=? ,`quantite`=?,`prix`=?, `garantie`=?  WHERE `id`=?";       
        PreparedStatement statement =con.prepareStatement(requestUpdate);
        statement.setString(1,p.getNom());
        statement.setString(2, p.getDescription());
        statement.setString(3, p.getImage());
        statement.setInt(4,p.getQuantite());
        statement.setFloat(5,p.getPrix());
        statement.setInt(6,p.getGarantie());
        statement.setInt(7,p.getId());
        
         return statement.executeUpdate();
    }
    //cette fonction aide a cree le prix de la promotion
    public Float PrixForPromo(int idD) throws SQLException {
      
    ste=con.createStatement();
    Statement stee;
    stee=con.createStatement();
     float prix=1;
    ResultSet rs=ste.executeQuery("select * from produit where id='"+idD+"'");
     while (rs.next()) {                              
              prix=rs.getFloat("Prix");                   
     }
   return prix; 
    }
}
