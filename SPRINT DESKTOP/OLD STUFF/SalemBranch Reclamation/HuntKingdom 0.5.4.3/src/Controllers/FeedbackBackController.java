/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Feedback;
import Util.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class FeedbackBackController implements Initializable {

    private ObservableList<Feedback> dataFeedback;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;

    @FXML
    private Button buttonRefreshFeedback;
    @FXML
    private TableView<Feedback> TableViewFeedback;
    @FXML
    private TableColumn<Feedback, ?> idFeedback;
    @FXML
    private TableColumn<Feedback, ?> starsFeedback;
    @FXML
    private TableColumn<Feedback, ?> descriptionFeedback;
    @FXML
    private Button buttonRetourListDesFeednacls;

    public void refresh() {
        loadDataFeedback();
        afficherFeedback();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con = DataBase.getInstance().getConnection();
        dataFeedback = FXCollections.observableArrayList();
        refresh();
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
            System.out.println(ex);
        }
        TableViewFeedback.setItems(dataFeedback);

    }

    private void afficherFeedback() {
        idFeedback.setCellValueFactory(new PropertyValueFactory<>("id"));
        starsFeedback.setCellValueFactory(new PropertyValueFactory<>("rate"));
        descriptionFeedback.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    private void RefreshFeedback(ActionEvent event) {
        refresh();
    }

    @FXML
    private void home(ActionEvent event) {
     buttonRetourListDesFeednacls.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/User_Home.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }}

}
