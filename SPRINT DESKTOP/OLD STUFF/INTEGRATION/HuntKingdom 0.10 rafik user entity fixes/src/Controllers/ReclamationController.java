/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import Service.ServiceReclamation;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class ReclamationController implements Initializable {

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
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
    private Button buttonRetourAjouterReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) throws DocumentException, BadElementException, IOException {
        Date date = new Date(System.currentTimeMillis());
        //Integer.valueOf(textfieldSujetReclamation.getText());
        Preferences UserId = Preferences.userRoot();
        int userId = UserId.getInt("UserId", 1);
        Reclamation r = new Reclamation(textfieldTypeReclamation.getText(), 13, textfieldSujetReclamation.getText(), textfieldDescriptionReclamation.getText(), date, "Pending", userId);
        ServiceReclamation cv = new ServiceReclamation();

        try {
            cv.ajouterReclamation(r);
            System.out.println("ajouté avec succes");

            buttonAjouterReclamation.getScene().getWindow().hide();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merci");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation ajouter avec succes");
            alert.showAndWait();

            pdf();
            
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

    public void pdf() throws DocumentException, BadElementException, IOException {
        FileOutputStream file = null;
        Date date = new Date(System.currentTimeMillis());

//FileOutputStream image;
        try {

            file = new FileOutputStream(new File("D:\\Reclamation " + textfieldSujetReclamation.getText().substring(0, 2) + "... " + date + ".pdf"));

        } catch (FileNotFoundException e1) {

            e1.printStackTrace();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, file);

        document.open();
//Image img = Image.getInstance("MT.png");
//img.setAbsolutePosition(455f, 755f);
//Scale to new height and new width of image
//img.scaleAbsolute(90, 90);

//document.add(img);
        Paragraph p = new Paragraph();
        try {
            p.add("\n");
            p.add(" Reclamation Form");
            p.add("\n =============================================================");

            p.add("\n Reclamation Type:      " + textfieldTypeReclamation.getText());
            p.add("\n Reclamation Sujet:      " + textfieldSujetReclamation.getText());
            p.add("\n Reclamation Description:      " + textfieldDescriptionReclamation.getText());

            p.add("\n =============================================================");
            p.add("\n  ");

            p.add("\n                          Reclamation Ajoutee Le:      " + date);

            document.add(p);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
