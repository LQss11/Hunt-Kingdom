/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Service.ServiceSaison;
import Util.DataSource;
import Entities.Saison;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ilyess
 */
public class SaisonController implements Initializable {
 private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;    

private AnchorPane listUsers;
    private Button buttonEspece;
    @FXML
    private Pane paneProduits;
    @FXML
    private TextField nomSaison;
    @FXML
    private TextField periode;
    @FXML
    private TextField mode;
    @FXML
    private TableView<Saison> tableViewSaison;
    @FXML
    private TableColumn<Saison, ?> TnomSaison;
    @FXML
    private TableColumn<Saison, ?> Tperiode;
    @FXML
    private TableColumn<Saison, ?> Tmode;
    @FXML
    private Button buttonAjouterSaison;
    @FXML
    private Button buttonSupprimerSaison;
    @FXML
    private Button buttonModifierSaison;
    private ObservableList<Saison> dataSaison;
    private Button Retour;
    private JFXButton reclamationBack;
    private JFXButton FeedbackBack;
    private JFXButton btn_logout;
    @FXML
    private AnchorPane pane;
    @FXML
    private VBox pnl_scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataSource.getInstance().getCnx();
        dataSaison= FXCollections.observableArrayList();
        
        afficherSaison();
        loadDataSaison();
        recupereSaison();
    
        // TODO
    }    

    private void handleButtonAction(ActionEvent event) throws IOException {
         buttonEspece.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Espece.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }    
    }

    @FXML
    private void ajouterSaison(ActionEvent event) throws SQLException {
        ServiceSaison ss = new ServiceSaison();
        Saison s = new Saison();
        s.setNom(nomSaison.getText());
        s.setPeriode(periode.getText());
        s.setMode(mode.getText());
        ss.ajouterSaison(s);
        clearFormSaison();
        afficherSaison();
        recupereSaison();
        loadDataSaison();
    }

    @FXML
    private void supprimerSaison(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
            Saison x=gettempSaison(edittedcell);         
            int i=x.getIdSaison();
        
        ServiceSaison se = new ServiceSaison();         
        se.deleteSaison(i);
            System.out.println("Espece deleted");
             afficherSaison();
        loadDataSaison();
    }

    @FXML
    private void modifierSaison(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
            Saison x=gettempSaison(edittedcell);         
            
        Saison e=new Saison();
        ServiceSaison se = new ServiceSaison();
       //  int i=se.idEspeceFromNom(nomEspece.getText());
          int i=x.getIdSaison();
             e.setIdSaison(i);
            e.setNom(nomSaison.getText());
            e.setPeriode(periode.getText());
            
            e.setMode(mode.getText());
            
             System.err.println("wsel lehne ");
            se.modifierSaison(e);
             System.out.println("Espece modified");
             clearFormSaison();
             afficherSaison();
        loadDataSaison(); 
    }
     private void clearFormSaison()
      {
          nomSaison.clear();
          periode.clear();
          mode.clear();
         
      }
     
     
     public Saison gettempSaison(TableColumn.CellEditEvent edittedCell) {
        Saison test = tableViewSaison.getSelectionModel().getSelectedItem();        
        return test;
    } 
     
     private void loadDataSaison() {
            dataSaison.clear();
         try {
           pst =con.prepareStatement("Select * from saison");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataSaison.add(new  Saison(rs.getInt("idSaison"),rs.getString("nom"), rs.getString("periode"), rs.getString("mode")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceSaison.class.getName()).log(Level.SEVERE, null, ex);
       }
        tableViewSaison.setItems(dataSaison);
    }
     
     
      private void afficherSaison(){
              TnomSaison.setCellValueFactory(new PropertyValueFactory <>("nom"));
             Tperiode.setCellValueFactory(new PropertyValueFactory <>("periode"));             
             Tmode.setCellValueFactory(new PropertyValueFactory <>("mode"));
              
    }
      
      
            public void recupereSaison()
      {tableViewSaison.setOnMouseClicked(e->{

      Saison test=tableViewSaison.getSelectionModel().getSelectedItem();


           nomSaison.setText(String.valueOf(test.getNom()));
           periode.setText(String.valueOf(test.getPeriode()));
                mode.setText(String.valueOf(test.getMode()));
               

      });


      }    

    private void handleButtonEspeceAction(ActionEvent event) throws IOException {
          }

    private void RetourDashboard(ActionEvent event){
     Retour.getScene().getWindow().hide();
    
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

     private void createPages() {
        try {
            
            
            Stage stage = new Stage();
            listUsers = FXMLLoader.load(getClass().getResource("/FXML/List_Users.fxml"));
            Scene scene = new Scene(listUsers);
            stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
     private void setNode(Node node) {
        pnl_scroll.getChildren().clear();
        pnl_scroll.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    private void openhome(ActionEvent event) {
          FeedbackBack.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Admin_Dashboard.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void openListStudent(ActionEvent event) {
         createPages();
    }

    private void openListProduits(ActionEvent event) {
        reclamationBack.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Gerer Les Produits");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/GestionsProduits.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }

    private void openlistevents(ActionEvent event) {
          btn_logout.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/AjouterEvenement.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    private void openReclamationBack(ActionEvent event) {
         reclamationBack.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Feedback");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void openFeedbackBack(ActionEvent event) {
        FeedbackBack.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Feedback");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FeedbackBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    private void logout(ActionEvent event) {
        btn_logout.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    }
    


