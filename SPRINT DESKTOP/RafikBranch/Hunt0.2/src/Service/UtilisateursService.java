/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Utilisateurs;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thepoet
 */
public class UtilisateursService {
    
    Connection connection;

    public UtilisateursService() {
        connection = DataSource.getInstance().getCnx();
    }
    
    
    
    public void add(Utilisateurs u) {
      String req = "INSERT INTO `user`(`username`, `email`, `password`, `date`, `lastname`,`image`, `phone`) VALUES(?,?,?,?,?,?,?)"; 
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getFIRST_NAME());
            preparedStatement.setString(2, u.getEMAIL());
            preparedStatement.setString(3, u.getPASSWORD());
            preparedStatement.setDate(  4, (u.getDate()));
            preparedStatement.setString(5, u.getLAST_NAME());
            preparedStatement.setString(6, u.getIMAGE());
            preparedStatement.setString(7, u.getPHONE());
          
            preparedStatement.executeUpdate();
            
             System.out.println("User added successfully");
        } catch (SQLException ex) {
             System.out.println(ex);
        }
    }

  
    public void update(Utilisateurs u) {
        String req = "UPDATE user set FIRST_NAME=?,EMAIL=?,PASSWORD=?,DATE=?,LAST_NAME=?,PHONE=? WHERE ID = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getFIRST_NAME());
            preparedStatement.setString(2, u.getEMAIL());
            preparedStatement.setString(3, u.getPASSWORD());
            preparedStatement.setDate  (4, (u.getDate()));
            preparedStatement.setString(5, u.getLAST_NAME());
            preparedStatement.setString(6, u.getPHONE());
            
            
            preparedStatement.executeUpdate();
            System.out.println("User updated successfully");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

 
    public int remove(int id)throws SQLException {
        
        String req = "DELETE FROM user WHERE ID ="+id;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.executeUpdate();
            System.out.println("User Deleted successfully");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
    
    public List<Utilisateurs> getAll() {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateurs u = new Utilisateurs(resultSet.getString("USERNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getDate("date"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("PHOTO"),
                        resultSet.getString("PHONE"));
               
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }
    
    
    
  

    public Utilisateurs findByMail(String email) {
       Utilisateurs u = null;
        String req = "SELECT * FROM user WHERE EMAIL =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 u = new Utilisateurs(resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
  
   
}
