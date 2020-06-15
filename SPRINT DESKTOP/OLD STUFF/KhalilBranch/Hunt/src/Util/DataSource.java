/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAFIK
 */
public class DataSource { private Connection cnx ;
    
    private static DataSource instance;
    
    private String url= "jdbc:mysql://localhost:3306/hunt";
    
    private String username="root";
    
    private String password="";

    private DataSource() {
        
        try {
            cnx = (Connection) DriverManager.getConnection(url, username, password);
                    
                   System.out.println("connexion etabie");
                    } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public Connection getCnx() {
        return cnx;
    }

    public static DataSource getInstance() {
        
        if (instance == null){
        instance = new DataSource();
        
        }
        return instance;
    }
    
    
}
