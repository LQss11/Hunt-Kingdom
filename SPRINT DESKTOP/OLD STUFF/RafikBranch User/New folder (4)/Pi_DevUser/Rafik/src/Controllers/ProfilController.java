/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class ProfilController implements Initializable {

    
     //String getImageUrl,mail;
    @FXML
    private ImageView ProfilImgId;
    @FXML
    private Button EditBtn;
    @FXML
    private Label NomUser;
    @FXML
    private Label PrenomUser;
    @FXML
    private Label EmailUser;
    @FXML
    private Label DateUser;
    @FXML
    private Label TelephoneUser;
    @FXML
    private JFXButton btn_logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @FXML
    private void EditProfil(ActionEvent event) {
        
        EditBtn.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/EditProfil.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
         btn_logout.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
    }
    }
    
    
     private void getUserInfo(String mail) {
         
           
        ObservableList<Utilisateurs> users = FXCollections.observableArrayList();
   
         UtilisateursService u1 = new UtilisateursService();
         u1.getAll().forEach((user) ->{ 
                         NomUser.setText(user.getFIRST_NAME());

                          PrenomUser.setText(user.getLAST_NAME());
                         EmailUser.setText(user.getEMAIL());
                          TelephoneUser.setText(user.getEMAIL());
                          //DateUser(user.getDate());
                         users.add(user);
                         }); 
           
           // UtilisateursService u1= new UtilisateursService();
            //Utilisateurs user = u1.findByMail(mail);
            
     
            //getImageUrl = user.getPROFILE_PHOTO();
            //File file = new File(getImageUrl);
            //Image ima = new Image(file.toURI().toString());
            //profileimg.setImage(ima);  
            
                
    }       
    }
    

    
    

