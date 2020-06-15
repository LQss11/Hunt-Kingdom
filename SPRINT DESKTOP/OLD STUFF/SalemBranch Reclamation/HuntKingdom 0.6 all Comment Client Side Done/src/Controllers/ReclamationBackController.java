/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import Service.ServiceReclamation;
import Util.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class ReclamationBackController implements Initializable {

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

    String desc="";
    
    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private TableColumn<Reclamation, ?> idReclamation;
    @FXML
    private TableColumn<Reclamation, ?> typeReclamation;
    @FXML
    private TableColumn<Reclamation, ?> idoReclamation;
    @FXML
    private TableColumn<Reclamation, ?> sujetReclamation;
    @FXML
    private TableColumn<Reclamation, ?> descriptionReclamation;
    @FXML
    private TableColumn<Reclamation, ?> dateReclamation;
    @FXML
    private TableColumn<Reclamation, ?> etatReclamation;
    @FXML
    private TableColumn<Reclamation, ?> idUReclamation;
    @FXML
    private JFXButton buttonRetour;
    @FXML
    private JFXButton buttonRefuser;
    @FXML
    private JFXButton buttonAccepter;
    @FXML
    private JFXButton buttonSupprimer;
    @FXML
    private JFXButton buttonRefresh;

    public void refresh() {
        loadDataReclamation();
        afficherReclamation();

    }

    public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = TableViewReclamation.getSelectionModel().getSelectedItem();
        return test;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Connection cnx = DataSource.getInstance().getCnx();
        con = DataBase.getInstance().getConnection();

        dataReclamation = FXCollections.observableArrayList();

        refresh();
    }

    private void afficherReclamation() {
        idReclamation.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeReclamation.setCellValueFactory(new PropertyValueFactory<>("type"));
        idoReclamation.setCellValueFactory(new PropertyValueFactory<>("ido"));
        sujetReclamation.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        descriptionReclamation.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateReclamation.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatReclamation.setCellValueFactory(new PropertyValueFactory<>("etat"));
        idUReclamation.setCellValueFactory(new PropertyValueFactory<>("idU"));
    }

    private void loadDataReclamation() {

        dataReclamation.clear();

        try {
            pst = con.prepareStatement("Select * from reclamation");

            rs = pst.executeQuery();
            while (rs.next()) {
                                
                if (rs.getString("description").indexOf("*") != -1) {
                     desc = rs.getString("description").substring(0, rs.getString("description").indexOf("*"));
                } else {
                     desc = rs.getString("description");
                }

                dataReclamation.add(new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getInt("ido"), rs.getString("sujet"), desc, rs.getDate("date"), rs.getString("etat"), rs.getInt("idU")));
            }
        } catch (SQLException ex) {
            System.out.println("GUI.ReclamationController.loadDataReclamation()");
        }
        TableViewReclamation.setItems(dataReclamation);

    }

    @FXML
    private void home(ActionEvent event) {
     buttonRetour.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Admin_Dashboard.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }}

    @FXML
    private void refuserReclamation(ActionEvent event) throws SQLException {
                int i;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        int c = x.getId();
        String etat = "Rejected";

        ServiceReclamation sc = new ServiceReclamation();

        Reclamation rec = new Reclamation(c, etat);

        i = sc.updateEtat(rec);

        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("reclamation updated");
            alert.showAndWait();
            refresh();

        }
    }

    @FXML
    private void AccepterReclamation(ActionEvent event) throws SQLException {
                        int i;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        int c = x.getId();
        String etat = "Accepted";

        ServiceReclamation sc = new ServiceReclamation();

        Reclamation rec = new Reclamation(c, etat);

        i = sc.updateEtat(rec);

        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("reclamation updated");
            alert.showAndWait();
            refresh();
        }
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        int i = x.getId();
        ServiceReclamation cat = new ServiceReclamation();

        int s = cat.deletereclamation(i);
        if (s == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation deleted");
            alert.showAndWait();
            refresh();
        }

    }

    @FXML
    private void refreshReclamation(ActionEvent event) {
        refresh();
    }

}
