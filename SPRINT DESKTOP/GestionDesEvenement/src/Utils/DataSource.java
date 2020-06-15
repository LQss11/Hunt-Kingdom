/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aziz9
 */
public class DataSource {
    private Connection cnx ;
    
    private static DataSource instance;
    
    private final String url = "jdbc:mysql://localhost:3306/symfony";
    private final String username = "root";
    private final String password = "";  
    

    private DataSource() {
        
        try{
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("connexion etablie");
        } 
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }
    }

    
   public Connection getCnx() {
        return cnx;
    }

    public static DataSource getInstance() {
        if(instance == null){
            instance = new DataSource();
        }
        return instance;
    }
}
