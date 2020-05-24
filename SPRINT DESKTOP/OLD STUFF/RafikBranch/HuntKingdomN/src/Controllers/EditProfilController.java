/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import Util.BCrypt;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class EditProfilController implements Initializable {

    @FXML
    private AnchorPane signupPane;
    @FXML
    private JFXTextField txtfirstname;
    @FXML
    private JFXPasswordField txtpasswod;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private Label lblstatus;
    @FXML
    private Label lblemail;
    @FXML
    private JFXButton imgechoser;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField txtPhone;
    String getImageUrl,mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setUserInfo(String mail) {
    
            if (mail == null) {
                return;
            }
            UtilisateursService u1 = new UtilisateursService();
            Utilisateurs user = u1.findByMail(mail); 
            txtlastname.setText(user.getFIRST_NAME());
            txtfirstname.setText(user.getLAST_NAME());
            lblemail.setText(mail);
            getImageUrl = user.getIMAGE();
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            //imgechoser.setImage(ima);     
    }    

    @FXML
    private void UpdateBtn(ActionEvent event) {
        
         UtilisateursService u1 = new UtilisateursService();
        Utilisateurs user = new Utilisateurs();
        if (txtfirstname != null){
                user.setFIRST_NAME(txtfirstname.getText());
                                 }
        if (txtlastname != null){
                user.setLAST_NAME(txtlastname.getText());
                                }
        if (txtpasswod != null){
                String hashed = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
                user.setPASSWORD(hashed);
                }
        
        if (txtPhone != null){
                user.setPHONE(txtPhone.getText());}
        
        user.setIMAGE(getImageUrl);   
        u1.update(user);
        setUserInfo(mail);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText("Votre compte a été modifier avec succès.");
                alert.showAndWait();
    }

    @FXML
    private void changephoto(MouseEvent event) {
        
        
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        getImageUrl = selectedfile.getAbsolutePath();
        File file = new File(getImageUrl);
        Image ima = new Image(file.toURI().toString());
        //imgechoser.setImage(ima);
    }
    }