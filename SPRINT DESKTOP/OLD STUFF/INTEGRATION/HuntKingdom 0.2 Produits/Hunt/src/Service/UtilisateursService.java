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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String req = "INSERT INTO `user`(`username`, `email`, `password`, `date`, `lastname`,`image`, `phone`,`enabled` ,`roles`) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getFIRST_NAME());
            preparedStatement.setString(2, u.getEMAIL());
            preparedStatement.setString(3, u.getPASSWORD());
            preparedStatement.setDate(4, (u.getDate()));
            preparedStatement.setString(5, u.getLAST_NAME());
            preparedStatement.setString(6, u.getIMAGE());
            preparedStatement.setString(7, u.getPHONE());
            preparedStatement.setInt(8, 0);
            preparedStatement.setString(9, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");

            preparedStatement.executeUpdate();

            System.out.println("User added successfully");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void update(Utilisateurs u) {
        String req = "UPDATE user set phone=?,username=?,lastname=?,email=?,password=?,date=?,image=?,enabled=? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getPHONE());
            preparedStatement.setString(2, u.getFIRST_NAME());
            preparedStatement.setString(3, u.getLAST_NAME());
            preparedStatement.setString(4, u.getEMAIL());
            preparedStatement.setString(5, u.getPASSWORD());
            preparedStatement.setDate(6, u.getDate());
            preparedStatement.setString(7, u.getIMAGE());
            //preparedStatement.setString(8, u.getROLE());
            preparedStatement.setInt(8, u.getENABLED());
            preparedStatement.setInt(9, u.getID());

            preparedStatement.executeUpdate();
            System.out.println("User updated successfully");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public int ActivateUser(Utilisateurs r) throws SQLException {

        String requestUpdate = "UPDATE `user` SET `ENABLED`=? WHERE `EMAIL`=?";
        PreparedStatement pre = connection.prepareStatement(requestUpdate);
        pre.setInt(1, r.getENABLED());
       
        pre.setString(2, r.getEMAIL());
        System.out.println(r);
        return pre.executeUpdate();
    }
    
    
    
    
    
    
    
    

    public int updateUser(Utilisateurs r) throws SQLException {

        String requestUpdate = "UPDATE `user` SET `phone`=?, `username`=?, `lastname`=?, `email`=?, `password`=?, `date`=?, `image`=?  WHERE `id`=?";
        PreparedStatement pre = connection.prepareStatement(requestUpdate);
        pre.setString(1, r.getPHONE());
        pre.setString(2, r.getFIRST_NAME());
        pre.setString(3, r.getLAST_NAME());
        pre.setString(4, r.getEMAIL());
        pre.setString(5, r.getPASSWORD());
        pre.setDate(6, r.getDate());
        pre.setString(7, r.getIMAGE());
        pre.setInt(8, r.getID());
        System.out.println(r);
        return pre.executeUpdate();
    }

    public void remove(Utilisateurs u) {
        String req = "DELETE FROM user WHERE EMAIL =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getEMAIL());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public List<Utilisateurs> getAll() {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateurs u = new Utilisateurs(
                        resultSet.getInt("ID"),
                        resultSet.getInt("ENABLED"),
                        resultSet.getString("PHONE"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("IMAGE"),
                        resultSet.getDate("date"));

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
                u = new Utilisateurs(
                        resultSet.getInt("ID"),
                        resultSet.getInt("ENABLED"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }

    public Utilisateurs findByROLE(int status) {
        Utilisateurs u = null;
        String req = "SELECT * FROM user WHERE ENABLED =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u = new Utilisateurs(
                        resultSet.getString("USERNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getDate("date"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("PHONE"),
                        resultSet.getString("IMAGE"),
                        resultSet.getInt("ENABLED"));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }

    public List<Utilisateurs> getEmail(String email) {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user where EMAIL = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateurs u = new Utilisateurs(
                        resultSet.getString("USERNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getDate("date"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("PHONE"),
                        resultSet.getString("IMAGE"),
                        resultSet.getInt("ENABLED"));
                u.setID( resultSet.getInt("ID"));
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }

    public List<Utilisateurs> getRole(int ENABLED) {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user where ENABLED =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, ENABLED);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateurs u = new Utilisateurs(
                           resultSet.getString("USERNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getDate("date"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("PHONE"),
                        resultSet.getString("IMAGE"),
                        resultSet.getInt("ENABLED"));
                u.setID( resultSet.getInt("ID"));
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }

}
