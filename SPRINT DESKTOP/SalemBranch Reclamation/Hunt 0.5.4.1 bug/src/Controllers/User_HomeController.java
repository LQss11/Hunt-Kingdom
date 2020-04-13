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
public class User_HomeController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private VBox pnl_scroll;
    public String emailuser;
    @FXML
    private JFXButton FeedbackFront;
    @FXML
    private JFXButton ReclamationFront;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    
    } 
    
    
    UtilisateursService user = new UtilisateursService();
     void transferMessage(String email) {
        emailuser =email;
        Utilisateurs u1 = user.findByMail(email);
        String text = "Bienvenue "+u1.getFIRST_NAME();
        name.setText(text);
        String ImageUrl = u1.getIMAGE();
        File file = new File(ImageUrl);
        Image ima = new Image(file.toURI().toString());
        profilephoto.setImage(ima);
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
    private void openhome(ActionEvent event) {
    }

    @FXML
    private void openprofile(ActionEvent event) {
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
    
    
     public void createPages(String emailuser) {
        try {
            bnthome = FXMLLoader.load(getClass().getResource("/FXML/User_Home.fxml"));
            Stage profileStage = new Stage();
            profileStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Profil.fxml"));
            profile = loader.load();
            ProfilController pController = loader.getController();
            pController.setUserInfo(emailuser);
            //set up default node on page load
            setNode(bnthome);
              //forum = FXMLLoader.load(getClass().getResource("/fxml/Publication.fxml"));
              //ajouterReclamation = FXMLLoader.load(getClass().getResource("/fxml/AjouterReclamation.fxml"));
              //events = FXMLLoader.load(getClass().getResource("/fxml/ShowEventClient.fxml"));


        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void openReclalmation(ActionEvent event) {
        
    ReclamationFront.getScene().getWindow().hide();
    
        
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Reclamer");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Reclamation.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }}

    @FXML
    private void openFeedback(ActionEvent event) {
        
    FeedbackFront.getScene().getWindow().hide();
    
        
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Feedback");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FeedbackFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }}
}
    

