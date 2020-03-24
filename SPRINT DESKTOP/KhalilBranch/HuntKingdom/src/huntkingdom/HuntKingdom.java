/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntkingdom;
import Utils.DataSource;
import entities.Categorie;
import entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author khalil
 */
public class HuntKingdom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource ds = DataSource.getInstance();
        Connection cnx = DataSource.getInstance().getCnx();
        Categorie c = new Categorie("test","test","test");
        c.setId(10);
                Produit p =new Produit(c,"test",10,10,"test",10,"test");
                ServiceProduit sp= new ServiceProduit();
                try {
                    sp.ajouterProduit(p);
                }catch (SQLException ex) {
            System.out.println(ex);        }  
       /* Categorie c = new Categorie("test","test","test");
        ServiceCategorie cv = new ServiceCategorie();
       
        try {
            cv.ajouterCategorie(c);
            c.setId(10);
            c.setDescription("hne");
            c.setImage("hne");
            cv.updatecategory(c);
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);        }    
    
    List<Categorie> arr=new ArrayList<>();
     try {
    arr =cv.sortedbyId();
        } catch (SQLException ex) {
            System.out.println(ex);        } 
    System.out.println(arr);*/
    
   /*try {
   int x=cv.deletecategory(9);
     } catch (SQLException ex) {
            System.out.println(ex);        } */
   List<Produit> app=new ArrayList<>();
   try{
   app=sp.sortedbyId();
   }catch (SQLException ex) {
            System.out.println(ex);        } 
   
    System.out.println(app);
    
    }
}
