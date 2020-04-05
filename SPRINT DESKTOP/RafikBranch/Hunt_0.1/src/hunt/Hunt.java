/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunt;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import Util.DataSource;
import Util.PasswordUtils;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author RAFIK
 */
public class Hunt {
    
    public static void main(String[] args) throws ParseException, SQLException, InvalidKeySpecException {
        

        
        
        
        
        
        DataSource ds = DataSource.getInstance();
        Connection cnx = DataSource.getInstance().getCnx();
        
       Date date = new Date(System.currentTimeMillis());
       
       
        String str = "2015-03-31";
        Date d = Date.valueOf(str);
        
         String myPassword = "myPassword123";
         String salt = PasswordUtils.getSalt(30);
        
        String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);
        Utilisateurs U =new Utilisateurs("new", "hhh@hotmail.com", mySecurePassword, d, "ddd", "12112");
        
                UtilisateursService sp= new UtilisateursService();
                 /* ----------------------------this is To ADD a user---------------------------- */
                sp.add(U);
                
                
                 /* ----------------------------this is To Update a user---------------------------- */
                 /*
                 ne9sa
                 
                 U.setID(6);
                U.setFIRST_NAME("safe");
                U.setEMAIL("safe@ggg.com");
                U.setPASSWORD("SAfsoufa");
                U.setLAST_NAME("hasni");
                U.setPHONE("278888");
                U.setDate(date);
                
                sp.update(U);
                
                */
                
        
        /* ----------------------------this is To delete a user---------------------------- */
        
       
              //sp.remove(1);
                
                
              
               /* ----------------------------this is To List all users---------------------------- */
                //List<Utilisateurs> arr = new ArrayList<>();
                //arr=sp.getAll();
                //System.out.println(arr);
    }
    
}
