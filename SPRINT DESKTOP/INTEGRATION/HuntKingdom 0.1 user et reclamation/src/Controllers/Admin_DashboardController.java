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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class Admin_DashboardController implements Initializable {


    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton list_user;
    @FXML
    private JFXButton list_event;
    @FXML
    private JFXButton list_organi;
    @FXML
    private JFXButton list_ticket;
    @FXML
    private JFXButton list_espace;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton Produit;
    
    private AnchorPane listUsers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       //createPages();
       
      // Integer.valueOf(textfieldStarsFeedback.getText()
       
       
       //iduser.setText(String.valueOf(id));
    } 
    
    
   
    void transferMessage(String email) {
        UtilisateursService user = new UtilisateursService();
        Utilisateurs u1 = user.findByMail(email);
        String text = "Bienvenue "+u1.getFIRST_NAME();
        name.setText(text);
       
    }
    
    
    
    
    private void createPages() {
        try {
            
            
            Stage stage = new Stage();
            listUsers = FXMLLoader.load(getClass().getResource("/FXML/List_Users.fxml"));
            Scene scene = new Scene(listUsers);
            stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    

    @FXML
    private void openhome(ActionEvent event) {
    }
    
    
     private void setNode(Node node) {
        pnl_scroll.getChildren().clear();
        pnl_scroll.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void openListStudent(ActionEvent event) {
        createPages();
    }

    @FXML
    private void openlistevents(ActionEvent event) {
    }

    @FXML
    private void openlistorgani(ActionEvent event) {
    }

    @FXML
    private void openlisttickets(ActionEvent event) {
    }

    @FXML
    private void openespace(ActionEvent event) {
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
        }}
    }

    

