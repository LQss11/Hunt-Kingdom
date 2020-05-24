/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceReclamation;
import Utils.DataBase;
import entities.Reclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Button buttonAjouterReclamation;
    @FXML
    private TextField textfieldTypeReclamation;
    @FXML
    private TextField textfieldSujetReclamation;
    @FXML
    private TextField textfieldDescriptionReclamation;
    @FXML
    private TableView<Reclamation> TableViewReclamation;

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
    private Button buttonModifierReclamation1;
    @FXML
    private Button buttonSupprimerReclamation11;
    @FXML
    private TableColumn<Reclamation, ?> idReclamation;

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

    public void refresh() {
        loadDataReclamation();
        afficherReclamation();

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
                dataReclamation.add(new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getInt("ido"), rs.getString("sujet"), rs.getString("description"), rs.getDate("date"), rs.getString("etat"), rs.getInt("idU")));
            }
        } catch (SQLException ex) {
            System.out.println("GUI.ReclamationController.loadDataReclamation()");
        }
        TableViewReclamation.setItems(dataReclamation);

    }

    @FXML
    private void AjouterReclamtion(ActionEvent event) {

        Date date = new Date(System.currentTimeMillis());
        //Integer.valueOf(textfieldSujetReclamation.getText());
        Reclamation r = new Reclamation(textfieldTypeReclamation.getText(), 13, textfieldSujetReclamation.getText(), textfieldDescriptionReclamation.getText(), date, "Pending", 3);
        ServiceReclamation cv = new ServiceReclamation();

        try {
            cv.ajouterReclamation(r);
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        refresh();
    }

    private void clearFormReclamation() {
        textfieldTypeReclamation.clear();
        textfieldSujetReclamation.clear();
        textfieldDescriptionReclamation.clear();

    }

    @FXML
    private void ModifierReclamtion(ActionEvent event) throws SQLException {
        int i;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        int c = x.getId();
        String type = textfieldTypeReclamation.getText();
        String sujet = textfieldSujetReclamation.getText();
        String description = textfieldDescriptionReclamation.getText();

        ServiceReclamation sc = new ServiceReclamation();

            Reclamation rec = new Reclamation(c, type, sujet, description);

            i = sc.updatereclamation(rec);

        

        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("reclamation updated");
            alert.showAndWait();
            clearFormReclamation();
            refresh();

        }

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
}
