/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import Service.ServicePromotion;
import Service.ServiceWhishlist;
import entities.Categorie;
import entities.Whishlist;
import entities.User;
import entities.Promotion;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import java.sql.Connection;
import Utils.DataBase;
import java.awt.AWTException;
import java.net.MalformedURLException;
import javafx.scene.input.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


/**
 * FXML Controller class
 *
 * @author khalil
 */
public class ProduitsController implements Initializable {

    private ObservableList<Produit> dataProduit;
private ObservableList<Categorie> dataCategorie;
private ObservableList<Promotion> dataPromotion;
   private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
    
    
    
    
     @FXML
    private Pane PaneHeadProduit;

    @FXML
    private Button buttonProduits;

    @FXML
    private Button buttonPromotion;

    @FXML
    private Button buttonCategorie;

    @FXML
    private Pane panePromotion;

    @FXML
    private Pane paneProduits;

    @FXML
    private Pane paneCategorie;
    @FXML
    private TextField textFieldNomCategorie;
    @FXML
    private TextField textFieldDescCategorie;
    @FXML
    private Button buttonSupprimerCategorie;
    @FXML
    private Button buttonImportCategorie;
    @FXML
    private TableView<Categorie> TableViewCategorie;
    @FXML
    private Button buttonModifierCategorie;
    @FXML
    private Button buttonAjoutCategorie;
    @FXML
    private ComboBox<Categorie> ComboBoxCategorie;
    @FXML
    private TextField TextFieldNomProduit;
    @FXML
    private TextField TextFieldGarantieProduit;
    @FXML
    private TextField TextFieldQuantiteProduit;
    @FXML
    private TextField TextFieldDescProduit;
    @FXML
    private TextField TextFieldPrixProduit;
    @FXML
    private TableView<Produit> tableViewProduit;
    @FXML
    private Button buttonAjourProduit;
    @FXML
    private Button buttonSupprimerProduit;
    @FXML
    private Button buttonModifierProduit;
    @FXML
    private Button buttonImporterProduit;
    @FXML
    private TextField TextFieldPourcentagePromotion;
    @FXML
    private TextField TextFieldPrixPromotion;
    @FXML
    private DatePicker datePickerDateDebPromo;
    @FXML
    private DatePicker datePickerDateFinPromo;
    @FXML
    private ComboBox<Produit> comboBoxProduit;
    @FXML
    private TableView<Promotion> tableViewPromotion;
    @FXML
    private Button buttonAjourPromotion;
    @FXML
    private Button buttonSupprimerPromotion;
    @FXML
    private Button buttonModifierPromotion;
    @FXML
    private Button buttonActiverPromotion;
    @FXML
    private TableColumn<Categorie, ?> idCategorie;
    @FXML
    private TableColumn<Categorie, ?> nomCategorie;
    @FXML
    private TableColumn<Categorie, ?> descriptionCategorie;
    @FXML
    private TableColumn<Produit, ?> nomProduit;
    @FXML
    private TableColumn<Produit, ?> categorieProduit;
    @FXML
    private TableColumn<Produit, ?> garantieProduit;
    @FXML
    private TableColumn<Produit, ?> prixProduit;
    @FXML
    private TableColumn<Produit, ?> descriptionProduit;
    @FXML
    private TableColumn<Promotion, ?> idPromotion;
    @FXML
    private TableColumn<Promotion, ?> pourcentagePromotion;
    @FXML
    private TableColumn<Promotion, ?> dateDebutPromotion;
    @FXML
    private TableColumn<Promotion, ?> dateFinPromotion;
    @FXML
    private TableColumn<Promotion, ?> prixPromotion;
    @FXML
    private TableColumn<Promotion, ?> etatPromotion;

    @FXML
    void handleButtonAction(ActionEvent event) {
   if (event.getSource() == buttonProduits)
           {
               paneProduits.toFront();
           } 
                 else if (event.getSource() == buttonPromotion)
           {
               panePromotion.toFront();
           } 
                 else if (event.getSource() == buttonCategorie)
           {
               paneCategorie.toFront();
           }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
   /* private void afficherProduit(){

             nomProduit.setCellValueFactory(new PropertyValueFactory <>("id"));
             categorieProduit.setCellValueFactory(new PropertyValueFactory <>("nom"));
             garantieProduit.setCellValueFactory(new PropertyValueFactory <>("garantie"));
             prixProduit.setCellValueFactory(new PropertyValueFactory <>("prix"));
             descriptionProduit.setCellValueFactory(new PropertyValueFactory <>("description"));
    }
private void loadDataProduit() {
   dataProduit.clear();
         try {
           pst =con.prepareStatement("Select * from produit");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataProduit.add(new  Produit(rs.getString("nom"), rs.getString("nomlivre"), rs.getFloat("prixlivre"), rs.getInt("quantitelivre")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_livre.setItems(dataProduit);
    }
  */
          
          
           
        
    
   
}
