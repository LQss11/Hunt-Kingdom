/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Feedback;
import Service.ServiceFeedback;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class FeedbackController implements Initializable {

    @FXML
    private TextField textfieldStarsFeedback;
    @FXML
    private JFXTextArea textfieldDescriptionFeedback;
    @FXML
    private JFXButton buttonAjouterFeedback;
    @FXML
    private JFXButton buttonRetourAjouterFeedback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterFeedback(ActionEvent event) {

        Date date = new Date(System.currentTimeMillis());
        Feedback f = new Feedback(textfieldDescriptionFeedback.getText(), Integer.valueOf(textfieldStarsFeedback.getText()), date, 3);
        ServiceFeedback cv = new ServiceFeedback();

        try {
            cv.ajouterFeedback(f);
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void home(ActionEvent event) {
        buttonRetourAjouterFeedback.getScene().getWindow().hide();

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
