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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ReclamationBackShowOneController implements Initializable {

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

    public String Description = "";
    public String Sujet = "";
    public String Type = "";
    public String DescFirst = "";

    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonRefresh;
    @FXML
    private Label textSujet;
    @FXML
    private Label textType;
    @FXML
    private JFXTextArea textDescription;
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
    @FXML
    private JFXButton buttonPDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataBase.getInstance().getConnection();

        dataReclamation = FXCollections.observableArrayList();
    }

    @FXML
    private void retour(ActionEvent event) {
        buttonRetour.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Reclamation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        ref();
    }

    public void ref() {

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
                if (msg.split("\\-")[1].contains("ROLE_ADMIN")) {
                    conv.add(new ReclamationConversation(msg.split("\\-")[0], "ADMIN"));
                    //CommentaireReclamation.setStyle("-fx-background-color: yellow ;");
                } else {
                    conv.add(new ReclamationConversation(msg.split("\\-")[0], "Client"));
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
        String newDesc = Reclamation.get("Description", Description) + "*" + textCommenter.getText() + "-" + "ROLE_ADMIN" + "-" + "*";
        Reclamation rec = new Reclamation(newDesc, c);
        Reclamation.put("Description", newDesc);
        i = sc.updateDescription(rec);
        TableViewCommentaires.getItems().add(new ReclamationConversation(textCommenter.getText(), "ADMIN"));

        ref();

        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Comment updated");
            alert.showAndWait();

        }
    }


    @FXML
    private void toPdf(ActionEvent event) throws DocumentException, BadElementException, IOException {
        pdf();
        System.out.println("Controllers.ReclamationBackShowOneController.toPdf()");
    }

    public void pdf() throws DocumentException, BadElementException, IOException {

        FileOutputStream file = null;
        Date date = new Date(System.currentTimeMillis());
        Preferences Reclamation = Preferences.userRoot();

        try {

            file = new FileOutputStream(new File("D:\\Reclamation " + Reclamation.getInt("UserId", 0) + "... " + date + ".pdf"));

        } catch (FileNotFoundException e1) {

            e1.printStackTrace();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, file);

        document.open();

        Paragraph p = new Paragraph();
        try {
            p.add("\n");
            p.add(" Reclamation Form");
            p.add("\n =============================================================");
            
            p.add("\n Reclamation Id:      " + Reclamation.getInt("descID", 0));
            p.add("\n Reclamation Type:      " + Reclamation.get("Type", ""));
            p.add("\n Reclamation Sujet:      " + Reclamation.get("Sujet", ""));
            p.add("\n Reclamation  User id:      " + Reclamation.getInt("UserId", 0));
            p.add("\n Reclamation Description:      " + Reclamation.get("DescFirst", ""));

            p.add("\n =============================================================");
            p.add("\n  ");

            p.add("\n                               Reclamation Enregistre Le:      " + date);

            p.add("\n \n \n");
            p.add(" Conversation");
            p.add("\n =============================================================");
            String[] Messages = Reclamation.get("Description", "").split("\\*");

            for (String msg : Messages) {

                if (msg.split("\\-").length == 2) {
                    if (msg.split("\\-")[1].contains("ROLE_ADMIN")) {
                        p.add("\nAdmin : " + msg.split("\\-")[0]);
                        //CommentaireReclamation.setStyle("-fx-background-color: yellow ;");
                    } else {
                        p.add("\nClient : " + msg.split("\\-")[0]);
                    }
                }

            }
            p.add("\n =============================================================");
            p.add("\n  ");

            document.add(p);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
