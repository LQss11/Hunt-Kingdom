/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceFeedback;
import Utils.DataBase;
import entities.Feedback;
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
public class FeedbackController implements Initializable {

    private ObservableList<Feedback> dataFeedback;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

    @FXML
    private TextField textfieldStarsFeedback;
    @FXML
    private TextField textfieldDescriptionFeedback;
    @FXML
    private Button buttonAjouterFeedback;
    @FXML
    private TableColumn<Feedback, ?> idFeedback;
    @FXML
    private TableColumn<Feedback, ?> starsFeedback;
    @FXML
    private TableColumn<Feedback, ?> descriptionFeedback;
    @FXML
    private TableView<Feedback> TableViewFeedback;

    public void refresh() {
        loadDataFeedback();
        afficherFeedback();

    }

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {

        //Connection cnx = DataSource.getInstance().getCnx();
        con = DataBase.getInstance().getConnection();

        dataFeedback = FXCollections.observableArrayList();

        refresh();
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
    private void afficherFeedback() {
        idFeedback.setCellValueFactory(new PropertyValueFactory<>("id"));
        starsFeedback.setCellValueFactory(new PropertyValueFactory<>("rate"));
        descriptionFeedback.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    private void loadDataFeedback() {

        dataFeedback.clear();

        try {
            pst = con.prepareStatement("Select * from feedback");

            rs = pst.executeQuery();
            while (rs.next()) {
                // il faut que les nom sont les memes dans la base des donnees
                dataFeedback.add(new Feedback(rs.getInt("id"), rs.getString("description"), rs.getInt("rate"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            System.out.println("GUI.FeedbackController.loadDataFeedback()");        }
        TableViewFeedback.setItems(dataFeedback);

    }

}
