/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Util.SendMail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import Entities.Utilisateurs;
import java.io.File;
import java.text.ParseException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import Service.UtilisateursService;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class List_UsersController implements Initializable {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private TableView<Utilisateurs> tableUsers;
    @FXML
    private TableColumn<?, ?> IDUser;
    @FXML
    private TableColumn<?, ?> lastname;
    @FXML
    private TableColumn<?, ?> firstname;
    @FXML
    private TableColumn<?, ?> Mail;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private AnchorPane fabPane;
    private Label fabEdit;
    @FXML
    private Button BanUser;
    private JFXRadioButton RDUserName;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXRadioButton RDMail;
    @FXML
    private JFXTextField inputSearch;
    @FXML
    private JFXButton Search;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblnom;
    @FXML
    private ImageView profilephoto;
    
    private JFXRadioButton RDFirstName;
    
    private TableColumn<?, ?> ENABLED;
    @FXML
    private Label lblstatus;
    @FXML
    private JFXRadioButton RDENABLED;
    @FXML
    private Button EnableUser;
    @FXML
    private Button DisableUser;
    
    ObservableList<Utilisateurs> userslist1 = FXCollections.observableArrayList();
    UtilisateursService u11=new UtilisateursService();
    @FXML
    private ImageView PHOTOID;
    @FXML
    private TableColumn<?, ?> ROLE;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRipples();
        buildUsersTable();
        DisableUser.setVisible(false);
        EnableUser.setVisible(false);
        BanUser.setVisible(false);

        tableUsers.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Utilisateurs> observable,
                        Utilisateurs oldValue,Utilisateurs newValue) -> {
                    if (newValue == null) {
                                
                        return;
                    }
                    getUserInfo(newValue.getEMAIL()); 
                   

                }); 
        
      
    }    

    
    
    
    private void buildUsersTable() {
        
        ObservableList<Utilisateurs> users = FXCollections.observableArrayList();
   
         UtilisateursService u1 = new UtilisateursService();
         u1.getAll().forEach((user) ->{ 
                 users.add(user);
                         });
         
         IDUser.setCellValueFactory(   new PropertyValueFactory<>("ID"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
         lastname.setCellValueFactory( new PropertyValueFactory<>("LAST_NAME"));
         Mail.setCellValueFactory(     new PropertyValueFactory<>("EMAIL"));
         ROLE.setCellValueFactory(     new PropertyValueFactory<>("ENABLED"));
         tableUsers.getItems().clear();
         tableUsers.getItems().addAll(users);
          
    }

    @FXML
    private void BanUser(ActionEvent event) throws SQLException {
        UtilisateursService u1 = new UtilisateursService();
        u1.remove(u1.findByMail(lblEmail.getText()));
        setRipples();
        buildUsersTable();
    }

    @FXML
    private void search() throws ParseException{
        if (RDENABLED.isSelected() && !"".equals(inputSearch.getText())){
          ObservableList<Utilisateurs> users = FXCollections.observableArrayList();
   
            UtilisateursService u1 = new UtilisateursService();
           u1.getRole(Integer.parseInt(inputSearch.getText())).forEach(
                   (user) ->{users.add(user);
                         });          
         IDUser.setCellValueFactory(new PropertyValueFactory<>("ID"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("LAST_NAME"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
         ENABLED.setCellValueFactory(new PropertyValueFactory<>("ENABLED"));
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(users);          
       }
        else if(RDMail.isSelected() && !"".equals(inputSearch.getText())){
            ObservableList<Utilisateurs> users = FXCollections.observableArrayList();
   
         UtilisateursService u1 = new UtilisateursService();
         u1.getEmail(inputSearch.getText()).forEach((user) ->{ 
                 users.add(user);
                         });              
         IDUser.setCellValueFactory(new PropertyValueFactory<>("ID"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("LAST_NAME"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
         ENABLED.setCellValueFactory(new PropertyValueFactory<>("ENABLED"));
         
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(users);          
            
        }
        else if ((RDENABLED.isSelected() || RDMail.isSelected()) && inputSearch.getText().equals("")){buildUsersTable(); }
    }
    
    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#F05537"));
        fabPane.getChildren().add(fXRippler);

    }
    
    private void getUserInfo(String mail) {
    
            if (mail == null) {
                return;
            }
            UtilisateursService u1= new UtilisateursService();
            Utilisateurs user = u1.findByMail(mail); 
            
            lblprenom.setText(user.getFIRST_NAME());
            lblnom.setText(user.getLAST_NAME());
            lblEmail.setText(user.getEMAIL());
            String ImageUrl = user.getIMAGE();
            //File file = new File(ImageUrl);
            //Image ima = new Image(file.toURI().toString());
            //PHOTOID.setImage(ima);
            if (user.getENABLED()== 0){
                DisableUser.setVisible(false);
                EnableUser.setVisible(true);
                BanUser.setVisible(true);
                lblstatus.setText("Etat : Désactivé");}
            else{
                EnableUser.setVisible(false);
                BanUser.setVisible(true);
                DisableUser.setVisible(true);
                lblstatus.setText("Etat : Activé");}
            
            if (user.getID()== 1){
                BanUser.setVisible(false);
                EnableUser.setVisible(false);
                DisableUser.setVisible(false);}
            
                
    }       

    @FXML
    private void EnableUser(ActionEvent event) {
        UtilisateursService u1= new UtilisateursService();
        Utilisateurs user = u1.findByMail(lblEmail.getText());
        user.setENABLED(1);
        u1.update(user);
        setRipples();
        buildUsersTable();
        SendMail.send(lblEmail.getText(), "Activation compte Huntkingdom", "Bonjour Mr/Mme " + lblprenom.getText() + ",\nNous voulons vous informer que votre compte Huntkingdom a été activée avec succès.\nVous pouvez maintenant vous connecter.\nCordialement,\nL'équipe HuntKingdom", "huntkingdom12345@gmail.com", "huntkingdom123");
        
    }

    @FXML
    private void DisableUser(ActionEvent event) {
        UtilisateursService u1= new UtilisateursService();
        Utilisateurs user = u1.findByMail(lblEmail.getText());
        user.setENABLED(0);
        u1.update(user);
        setRipples();
        buildUsersTable();
        SendMail.send(lblEmail.getText(), "Désactivation compte HuntKingdom", "Bonjour Mr/Mme " + lblprenom.getText() + ",\nNous voulons vous informer que votre compte Huntkingdom a été désactivée à cause de votre violation de notre réglement interne.\nVous ne pouvez plus vous connecter pour le moment.\nCordialement,\nL'équipe Huntkingdom", "huntkingdom12345@gmail.com", "huntkingdom123");
        
    }
}
