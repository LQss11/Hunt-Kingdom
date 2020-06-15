/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.Utilisateurs;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import Service.UtilisateursService;
import Util.BCrypt;
import Util.Session;

/**
 *
 * @author thepoet
 */
public class SignInController implements Initializable {

    @FXML
    private JFXButton btngocreateaccount;
    @FXML
    private ImageView signinimgprog;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private AnchorPane signinPane;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private Label labelerror;
    private SignInController globalCntrl;
    public static int id ;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidationlogin();
        signinimgprog.setVisible(false);
    }    

    @FXML
    private void opencreateaccountaction(MouseEvent event) {
        
        btngocreateaccount.getScene().getWindow().hide();
        try {
            signinimgprog.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            System.out.println(ex);

        }
    }
        
        UtilisateursService user = new UtilisateursService();
    @FXML
    private void loginaccountaction(MouseEvent event) {
        
        Utilisateurs u1 = user.findByMail(txtemail.getText());
        
                 Preferences UserId =Preferences.userRoot();
                 UserId.remove("UserId");
                 UserId.putInt("UserId", u1.getID());
                 UserId.getInt("UserId", u1.getID());
                 
                 System.out.println("user id est" +u1.getID());
                 id=u1.getID();
                 
                 
        if (txtemail.getText().isEmpty() || txtpassword.getText().isEmpty()) {
            labelerror.setTextFill(Color.TOMATO);
            labelerror.setText("Veuillez remplir les champs obligatoires");}
        else {
        labelerror.setText("");    
        signinimgprog.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
                if (txtemail.getText().equals("admin@esprit.tn") && txtpassword.getText().equals("admin123")) {
                    LoginAdmin();
                }

            else if (u1 == null) {
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Compte n'existe pas");
                signinimgprog.setVisible(false);}
            
            else if (BCrypt.checkpw(txtpassword.getText(),u1.getPASSWORD())) {
                    if (u1.getENABLED()== 0) {
                       signinimgprog.setVisible(false);
                       labelerror.setText("Votre compte n'est pas encore activée");}
                    else
                    { 
                         
                         LoginUser();}
            }
            
            else {
                signinimgprog.setVisible(false);
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Veuillez vérifier vos paramétres");
                 }

        });   
        pauseTransition.play();
      }
    }
    private void LoginUser() {
        
       
        btnsignin.getScene().getWindow().hide();
        try {
        
            signinPane.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/User_Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }


    private void LoginAdmin() {
        btnsignin.getScene().getWindow().hide();
        try {
            signinPane.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin_Dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
           
           
            
        } catch (IOException ex) {
            System.out.println(ex);

        }
    }
    
    private void handleValidationlogin() {
        RequiredFieldValidator fieldValidator1 = new RequiredFieldValidator();
        fieldValidator1.setMessage("Champ Obligatoire");
        fieldValidator1.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtemail.getValidators().add(fieldValidator1);
        txtemail.focusedProperty().addListener((ObservableValue<? extends Boolean> o,
                Boolean oldVal,
                Boolean newVal1) 
                -> {if (!newVal1) {
                txtemail.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Champ Obligatoire");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtpassword.getValidators().add(fieldValidator2);
        txtpassword.focusedProperty().addListener((ObservableValue<? extends Boolean> o,
                Boolean  oldVal,
                Boolean  newVal4) 
                -> {if (!newVal4) {
                txtpassword.validate();

            }
        });

    }
    

}