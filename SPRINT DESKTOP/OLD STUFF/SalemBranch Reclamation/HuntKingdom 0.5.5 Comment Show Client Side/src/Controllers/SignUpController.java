/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import Util.BCrypt;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField FnId;
    @FXML
    private TextField LnId;
    @FXML
    private TextField EmId;
    @FXML
    private PasswordField PswId;
    @FXML
    private TextField PhId;
    @FXML
    private DatePicker BiId;
    
    private ImageView signUpimgprog;
     
    @FXML
    private Label lblrole;
    
   
    String getImageUrl="SourcePackages/Image/back.jpg";
    @FXML
    private Button BtnAction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choosephoto(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        getImageUrl = selectedfile.getAbsolutePath();
        File file = new File(getImageUrl);
        Image ima = new Image(file.toURI().toString());
        signUpimgprog.setImage(ima);
        
        
    }
    
    /*
     @FXML
    private void AjouterImageCategorie(ActionEvent event) {
                
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             ImageViewCategorie.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
    
    
    
    

    @FXML
    private void Add(ActionEvent event) {
        
         UtilisateursService user = new UtilisateursService();
         java.util.Date BiId = java.sql.Date.valueOf(this.BiId.getValue());
         
       String Email = EmId.getText()+"@esprit.tn";
       Utilisateurs u1 = user.findByMail(Email);
       String hashed = BCrypt.hashpw(PswId.getText(), BCrypt.gensalt());
       Utilisateurs u = new Utilisateurs(FnId.getText(),
               Email,
               hashed,
               (Date) BiId,
               LnId.getText(),
               PhId.getText(),
               getImageUrl
              );
      
         //String LAST_NAME,String PHONE,String IMAGE
        //Utilisateurs u = new Utilisateurs(FnId.getText(), LnId.getText(),
       //Email,hashed,getImageUrl);
       if (u1 != null) {
                lblrole.setTextFill(Color.TOMATO);
                lblrole.setText("Compte Existant");
            } 
       else if (u1 == null){ 
         if (FnId.getText().isEmpty() ||
                 LnId.getText().isEmpty() ||
                 EmId.getText().isEmpty() ||
                 PswId.getText().isEmpty()) {
            lblrole.setTextFill(Color.TOMATO);
            lblrole.setText("Veuillez remplir les champs obligatoires");
            }
         else {
             lblrole.setText("");
                //signUpimgprog.setVisible(true);
                PauseTransition pauseTransition = new PauseTransition();
                pauseTransition.setDuration(Duration.seconds(7));
                pauseTransition.setOnFinished(ev -> {
                   
                user.add(u);
                
                BtnAction.getScene().getWindow().hide();
                try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                 System.out.println(ex);        }
                 });   
        pauseTransition.play();
        Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText("Votre compte a été crée avec succès. Vous recevrez un email indiquant l'activation de votre compte pour pouvoir vous connecter. ");
                alert.showAndWait();
              }
                        
         }  
         
        
    }
    
}
