/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghassen
 */
public class DataAccessObject {
   private DataBase database=new DataBase();
   private ResultSet rs;
   private PreparedStatement pstmt;
   private Connection con;
   public DataAccessObject()
   {
       
   }
   public void saveData(String query){
           con=database.getConnection();
       try {
           pstmt=con.prepareStatement(query);
           pstmt.executeUpdate();
       } catch (SQLException ex) {
ex.printStackTrace();       }
      
           }
}
