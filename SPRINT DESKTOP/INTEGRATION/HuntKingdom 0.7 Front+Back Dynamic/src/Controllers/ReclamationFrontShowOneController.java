/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import Entities.ReclamationConversation;
import Service.ServiceReclamation;
import Util.DataBase;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

    public String Description = "";
    public String Sujet = "";
    public String Type = "";
    public String DescFirst = "";

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
    @FXML
    private TextArea textCommenter;
    @FXML
    private Button buttonCommenter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataBase.getInstance().getConnection();

        dataReclamation = FXCollections.observableArrayList();
        ref();
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

    public void ref()
    {
                Preferences Reclamation = Preferences.userRoot();
        String[] Messages = Reclamation.get("Description", Description).split("\\*");

        String Desc = Reclamation.get("Description", Description);
        textDescription.setText(Reclamation.get("DescFirst", DescFirst));
        textSujet.setText(Reclamation.get("Sujet", Sujet));
        textType.setText(Reclamation.get("Type", Type));

        CommentaireReclamation.setCellValueFactory(new PropertyValueFactory<>("description"));
        RoleReclamation.setCellValueFactory(new PropertyValueFactory<>("role"));

        ObservableList<ReclamationConversation> conv = FXCollections.observableArrayList();

        for (String msg : Messages) {

            if (msg.split("\\-").length == 2) {
                if (msg.split("\\-")[1].contains("ROLE_CLIENT")) {
                    conv.add(new ReclamationConversation(msg.split("\\-")[0], "CLIENT"));
                    //CommentaireReclamation.setStyle("-fx-background-color: yellow ;");
                } else {
                    conv.add(new ReclamationConversation(msg.split("\\-")[0], "ADMIN"));
                }
            }

        }

        TableViewCommentaires.setItems(conv);
    }

    @FXML
    private void AjouterCommentaire(ActionEvent event) throws SQLException {
        int i;
        ObservableList<ReclamationConversation> conv = FXCollections.observableArrayList();

        Preferences Reclamation = Preferences.userRoot();
        int c = Reclamation.getInt("descID", 0);
        ServiceReclamation sc = new ServiceReclamation();
        String newDesc = Reclamation.get("Description", Description) + "*" + textCommenter.getText() + "-" + "ROLE_CLIENT" + "-" + "*";
        Reclamation rec = new Reclamation(newDesc, c);
        Reclamation.put("Description", newDesc);
        i = sc.updateDescription(rec);
        TableViewCommentaires.getItems().add(new ReclamationConversation(textCommenter.getText(), "CLIENT"));

        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Comment updated");
            alert.showAndWait();

        }
    }

}
