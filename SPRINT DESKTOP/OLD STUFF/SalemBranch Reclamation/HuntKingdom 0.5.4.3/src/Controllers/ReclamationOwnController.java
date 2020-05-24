/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamation;
import Service.ServiceReclamation;
import Util.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LQss
 */
public class ReclamationOwnController implements Initializable {

    private ObservableList<Reclamation> dataReclamation;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

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
    private Button buttonSupprimerReclamation;
    @FXML
    private Button buttonRefreshReclamation;
    @FXML
    private Button buttonRetourMesReclamation;

    public void refresh() {
        loadDataReclamation();
        afficherReclamation();

    }

    public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = TableViewReclamation.getSelectionModel().getSelectedItem();
        return test;
    }

    private void afficherReclamation() {
        //idReclamation.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeReclamation.setCellValueFactory(new PropertyValueFactory<>("type"));
        //idoReclamation.setCellValueFactory(new PropertyValueFactory<>("ido"));
        sujetReclamation.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        descriptionReclamation.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateReclamation.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatReclamation.setCellValueFactory(new PropertyValueFactory<>("etat"));
        //idUReclamation.setCellValueFactory(new PropertyValueFactory<>("idU"));
    }

    private void loadDataReclamation() {

        dataReclamation.clear();
        try {
            Preferences UserId = Preferences.userRoot();
            int userId = UserId.getInt("UserId", 1);
            pst = con.prepareStatement("Select * from reclamation Where idU='" + userId + "'");

            rs = pst.executeQuery();
            while (rs.next()) {
                dataReclamation.add(new Reclamation(rs.getInt("id"),rs.getString("type"), rs.getString("sujet"), rs.getString("description"), rs.getDate("date"), rs.getString("etat")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        TableViewReclamation.setItems(dataReclamation);

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

        //Integer.valueOf(textfieldSujetReclamation.getText());
    }

    @FXML
    private void SupprimerReclamtion(ActionEvent event) throws SQLException {
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
    private void RefreshReclamation(ActionEvent event) {

        refresh();
    }

    @FXML
    private void home(ActionEvent event) {
        buttonRetourMesReclamation.getScene().getWindow().hide();

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
