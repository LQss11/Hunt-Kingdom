/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.EditProfilController.id;
import Entities.Utilisateurs;
import Service.UtilisateursService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
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
    @FXML
    private Button ObtenirBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Preferences user = Preferences.userRoot();
                
        int userId = user.getInt("UserId", id);
        System.out.println(user.getInt("UserId", id));
        NomUser.setText(String.valueOf(userId));
        
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
    
    
     private void getUserInfo() {
         
          /* 
        ObservableList<Utilisateurs> users = FXCollections.observableArrayList();
   
         UtilisateursService u1 = new UtilisateursService();
         u1.getAll().forEach((user) ->{ 
                         NomUser.setText(user.getFIRST_NAME());

                          PrenomUser.setText(user.getLAST_NAME());
                         EmailUser.setText(user.getEMAIL());
                          TelephoneUser.setText(user.getEMAIL());
                          //DateUser(user.getDate());
                         users.add(user);
                         }); */
          
        Preferences UserId = Preferences.userRoot();
        int userId = UserId.getInt("UserId", id);
        NomUser.setText(String.valueOf(userId));
        
            //getImageUrl = user.getPROFILE_PHOTO();
            //File file = new File(getImageUrl);
            //Image ima = new Image(file.toURI().toString());
            //profileimg.setImage(ima);  
            
                
    }       

    @FXML
    private void Obtenir(ActionEvent event) {
                Preferences user = Preferences.userRoot();
                
        int userId = user.getInt("UserId", id);
        System.out.println(user.getInt("UserId", id));
        NomUser.setText(String.valueOf(userId));
        
    }
    }
    

    
    

