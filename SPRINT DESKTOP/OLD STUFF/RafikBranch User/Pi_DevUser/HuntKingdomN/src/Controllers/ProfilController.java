/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
            if (mail == null) {
                return;
            }
            UtilisateursService u1= new UtilisateursService();
            Utilisateurs user = u1.findByMail(mail);
            
            
             //NomUser.setText(user.getString("firstname"));
              //String text = "Bienvenue "+user.getFIRST_NAME();
              //NomUser.setText(text);
               //PrenomUser.setText(text);
                //EmailUser.setText(text);
                 //TelephoneUser.setText(text);
            
            //NomUser.setText(user.getFIRST_NAME());
            //PrenomUser.setText(user.getLAST_NAME());
           // EmailUser.setText(user.getEMAIL());
            //TelephoneUser.setText(user.getEMAIL());
            //DateUser.setText(user.getEMAIL());
            //String ImageUrl = user.getIMAGE();
            //File file = new File(ImageUrl);
            //Image ima = new Image(file.toURI().toString());
            //PHOTOID.setImage(ima);
           
            
                
    }       
    }
    

    
    

