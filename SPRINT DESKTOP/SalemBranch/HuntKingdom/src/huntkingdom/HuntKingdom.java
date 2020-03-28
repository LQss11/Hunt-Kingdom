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
import Service.ServiceReclamation;
import entities.Reclamation;
import entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        //current date but no time 
        Date date = new Date(System.currentTimeMillis());
        // set a date
        /*
        String str = "2015-03-31";
        Date d = Date.valueOf(str);
         */
        
        /* ----------------------------this is a test for showing only one reclamation object---------------------------- */
        
        /*
        
        ServiceReclamation cv = new ServiceReclamation();
        List<Reclamation> arr = new ArrayList<>();
        try {
            // put the id of the object u want to get
            arr = cv.show(50);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(arr);
        
        */
        
        /* ----------------------------this is a test for deleting a reclamation object---------------------------- */
        
        /*

        ServiceReclamation sr = new ServiceReclamation();
        try {
            int x = sr.deletereclamation(48);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
        */
        
        /* ----------------------------this is a test for updating a reclamation object---------------------------- */
        
        /*
 
        Reclamation r = new Reclamation("Bug", 13, "merci", "JAVA", date, "TEST", 3);
        ServiceReclamation sr = new ServiceReclamation();
        try {
            //change the value of id to update it
            r.setId(50);
            r.setType("omg");
            // the ido value is already set to 0 in the service
            //r.setIdo(50);
            r.setSujet("omg");
            r.setDescription("JA");
            r.setDate(date);
            r.setEtat("test");
            r.setUser(2);
            sr.updatereclamation(r);

            System.out.println("updated avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
        */
        
        /* ----------------------------this is a test for listing all reclamation object---------------------------- */
        
        /*
 
        ServiceReclamation cv = new ServiceReclamation();
        List<Reclamation> arr = new ArrayList<>();
        try {
            arr = cv.sortedbyId();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(arr);

        */
        
        /* ----------------------------this is a test for creating a new reclamation object---------------------------- */
        
        /*
 
        String str="2015-03-31";  
        Date date=Date.valueOf(str);
        
        User u = new User(0);

        Reclamation r = new Reclamation("Bug", 13, "JAVA", "JAVA", date, "TEST", 3);
        ServiceReclamation cv = new ServiceReclamation();

        try {
            cv.ajouterReclamation(r);
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) 
        {
            System.out.println(ex);        
        } 
 
        */
        
        /* ---------------------------------------------------------------------------------------------------------------- */

    }
}
