/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import Entities.ReclamationConversation;
import Util.DataBase;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class ReclamationFrontShowOneController implements Initializable {

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
    @FXML
    private TableView<ReclamationConversation> TableViewCommentaires;
    @FXML
    private TableColumn<ReclamationConversation, String> CommentaireReclamation;
    @FXML
    private TableColumn<ReclamationConversation, String> RoleReclamation;

    List<String> list = Arrays.asList("foo", "bar");
    List<String> list2 = Arrays.asList("foo", "bar");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommentaireReclamation.setCellValueFactory(new PropertyValueFactory<>("description"));

        RoleReclamation.setCellValueFactory(new PropertyValueFactory<>("role"));
        TableViewCommentaires.setItems(getConv());
    }
    ObservableList<ReclamationConversation> getConv()
    {
        ObservableList<ReclamationConversation> conv =FXCollections.observableArrayList();
        conv.add(new ReclamationConversation("aaaa", "aaa"));
        return conv;
    }

    @FXML
    private void retour(ActionEvent event) {
        buttonRetour.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Reclamation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationOwn.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {

        Preferences Reclamation = Preferences.userRoot();
        textDescription.setText(Reclamation.get("Description", Description));
        textSujet.setText(Reclamation.get("Sujet", Sujet));

        textType.setText(Reclamation.get("Type", Type));
    }

}
