/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import Service.ServiceReclamation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class ReclamationController implements Initializable {

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

    @FXML
    private JFXTextField textfieldTypeReclamation;
    @FXML
    private JFXTextField textfieldSujetReclamation;
    @FXML
    private JFXTextArea textfieldDescriptionReclamation;
    @FXML
    private JFXButton buttonAjouterReclamation;
    @FXML
    private JFXButton buttonConsulterMesReclamations;
    @FXML
    private JFXButton buttonRetourAjouterReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
        Date date = new Date(System.currentTimeMillis());
        //Integer.valueOf(textfieldSujetReclamation.getText());
                    Preferences UserId = Preferences.userRoot();
            int userId = UserId.getInt("UserId", 1);
        Reclamation r = new Reclamation(textfieldTypeReclamation.getText(), 13, textfieldSujetReclamation.getText(), textfieldDescriptionReclamation.getText(), date, "Pending", userId);
        ServiceReclamation cv = new ServiceReclamation();

        try {
            cv.ajouterReclamation(r);
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void ConsulterReclamation(ActionEvent event) {
        buttonConsulterMesReclamations.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationOwn.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void home(ActionEvent event) {
        buttonRetourAjouterReclamation.getScene().getWindow().hide();

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
    


}
