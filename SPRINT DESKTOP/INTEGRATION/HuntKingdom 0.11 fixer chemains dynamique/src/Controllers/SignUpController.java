

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import Util.BCrypt;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class SignUpController implements Initializable {

    @FXML
    private JFXTextField FnId;
    @FXML
    private JFXTextField LnId;
    @FXML
    private JFXTextField EmId;
    @FXML
    private JFXPasswordField PswId;
    @FXML
    private JFXTextField PhId;
    @FXML
    private JFXDatePicker BiId;
    
    private ImageView signUpimgprog;
     
    @FXML
    private Label lblrole;
    
   
    String getImageUrl="SourcePackages/Image/back.jpg";
    @FXML
    private Button BtnAction;
    @FXML
    private ImageView ImageProfProg;
 
    @FXML
    private ImageView PicProfil;
    @FXML
    private AnchorPane profilPic;
    @FXML
    private ImageView Recaptcha;
    @FXML
    private JFXTextField NickId;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       handleValidation();
        ImageProfProg.setVisible(false);
        
        
    }    

    @FXML
    private void choosephoto(ActionEvent event) {
                
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             PicProfil.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File(System.getProperty("user.dir")+"/src/image");
        String name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    } 
        
    
    
    

    @FXML
    private void Add(ActionEvent event) throws SQLException {
        
         UtilisateursService user = new UtilisateursService();
         java.util.Date BiId = java.sql.Date.valueOf(this.BiId.getValue());
         
         
         Image image1=null;
             image1=PicProfil.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
         
       String Email = EmId.getText()+"@esprit.tn";
       Utilisateurs u1 = user.findByMail(Email);
       String hashed = BCrypt.hashpw(PswId.getText(), BCrypt.gensalt());
       Date date = new Date(System.currentTimeMillis());
       Utilisateurs u = new Utilisateurs(
               FnId.getText(),
               Email,
               hashed,
               (Date) BiId,
               LnId.getText(),
               PhId.getText(),
               photo,
               NickId.getText(),
               NickId.getText(),
               Email,
               date
                );
      
       if (u1 != null) {
                lblrole.setTextFill(Color.TOMATO);
                lblrole.setText("Compte Existant");
            } 
       else if (u1 == null){ 
         if (    FnId.getText().isEmpty() ||
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
    
     private void handleValidation() {
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Champ Obligatoire");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        FnId.getValidators().add(fieldValidator);
        FnId.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                FnId.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Champ Obligatoire");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        LnId.getValidators().add(fieldValidator2);
        LnId.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal2) -> {
            if (!newVal2) {
                FnId.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator3 = new RequiredFieldValidator();
        fieldValidator3.setMessage("Champ Obligatoire");
        fieldValidator3.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        EmId.getValidators().add(fieldValidator3);
        EmId.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal3) -> {
            if (!newVal3) {
                EmId.validate();

            }
        });
        
        
        RequiredFieldValidator fieldValidator4 = new RequiredFieldValidator();
        fieldValidator4.setMessage("Champ Obligatoire");
        fieldValidator4.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        PswId.getValidators().add(fieldValidator4);
        PswId.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal4) -> {
            if (!newVal4) {
                PswId.validate();

            }
        });
    }
    
     
    
    }
 