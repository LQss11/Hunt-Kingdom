/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Service.ServiceSaison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author ilyess
 */
public class FrontSController implements Initializable {

    @FXML
    private Button BSaison;
    @FXML
    private Button AlbumPhoto;
    @FXML
    private Label Laffiche;
    @FXML
    private Button Retour;
    @FXML
    private Button BVideo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void handleButtonSaisonAction(ActionEvent event) {
        ServiceSaison se = new ServiceSaison();
        try {
            Laffiche.setText(se.readAll().toString());
        } catch (SQLException ex) {
            Logger.getLogger(FrontSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAlbumAction(ActionEvent event) throws IOException {
         Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FrontE.fxml"));
             Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        
    }

    @FXML
    private void RetourBack(ActionEvent event) {
         Retour.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/User_Home.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void handleButtonVideoAction(ActionEvent event) {
         Retour.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Video.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
