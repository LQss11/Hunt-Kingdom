/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class ReclamationFrontShowOneController implements Initializable {

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;
    @FXML
    private JFXTextArea textDescription;
    @FXML
    private Button buttonRetour;

    public String Description;
    public String Sujet;
    public String Type;

    @FXML
    private Button buttonRefresh;
    @FXML
    private Label textSujet;
    @FXML
    private Label textType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       /* Preferences Reclamation = Preferences.userRoot();
        textDescription.setText(Reclamation.get("Description", Description));
        textSujet.setText(Reclamation.get("Sujet", Sujet));

        textType.setText(Reclamation.get("Type", Type));
*/
    }

    @FXML
    private void retour(ActionEvent event) {
        buttonRetour.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Reclamation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
    }

}
