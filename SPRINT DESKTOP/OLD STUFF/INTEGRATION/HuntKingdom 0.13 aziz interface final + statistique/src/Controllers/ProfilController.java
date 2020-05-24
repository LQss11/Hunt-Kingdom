/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.EditProfilController.id;
import Entities.Utilisateurs;
import Service.UtilisateursService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class ProfilController implements Initializable {

    //String getImageUrl,mail;
    @FXML
    private ImageView ProfilImgId;
    @FXML
    private Button EditBtn;
    @FXML
    private Label NomUser;
    @FXML
    private Label PrenomUser;
    @FXML
    private Label EmailUser;
    @FXML
    private Label DateUser;
    @FXML
    private Label TelephoneUser;
    private JFXButton btn_logout;

    String getImageUrl = "src/Image/image.png";
    @FXML
    private Label nomprenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences user = Preferences.userRoot();
        int userId = user.getInt("UserId", id);
        System.out.println(user.getInt("UserId", id));
        NomUser.setText(String.valueOf(userId));

        UtilisateursService s = new UtilisateursService();

        String str = "file:" + System.getProperty("user.dir") + "/src/image/" + s.findByID(userId).getIMAGE();
        System.out.println(str);
        ProfilImgId.setImage(new Image(str));
        ProfilImgId.setFitWidth(150);
        ProfilImgId.setFitHeight(150);

        NomUser.setText(s.findByID(userId).getFIRST_NAME());
        PrenomUser.setText(s.findByID(userId).getLAST_NAME());
        EmailUser.setText(s.findByID(userId).getEMAIL());
        DateUser.setText(s.findByID(userId).getDate().toString());
        TelephoneUser.setText(s.findByID(userId).getPHONE());
        nomprenom.setText(s.findByID(userId).getFIRST_NAME() + " " + s.findByID(userId).getLAST_NAME());

    }

    @FXML
    private void EditProfil(ActionEvent event) {

        EditBtn.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/EditProfil.fxml"));
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

    private void getUserInfo(Utilisateurs im) {

        Preferences UserId = Preferences.userRoot();
        int userId = UserId.getInt("UserId", id);
        NomUser.setText(String.valueOf(userId));

    }

    private void Obtenir(ActionEvent event) {

        Preferences user = Preferences.userRoot();
        int userId = user.getInt("UserId", id);
        System.out.println(user.getInt("UserId", id));
        NomUser.setText(String.valueOf(userId));

        UtilisateursService s = new UtilisateursService();
        Utilisateurs p = new Utilisateurs();

        String str = "file:"+System.getProperty("user.dir")+"/src/Image/" + s.findByID(userId).getIMAGE();
        System.out.println(str);
        ProfilImgId.setImage(new Image(str));
        ProfilImgId.setFitWidth(150);
        ProfilImgId.setFitHeight(150);

        NomUser.setText(s.findByID(userId).getFIRST_NAME());
        PrenomUser.setText(s.findByID(userId).getLAST_NAME());
        EmailUser.setText(s.findByID(userId).getEMAIL());
        DateUser.setText(s.findByID(userId).getDate().toString());
        TelephoneUser.setText(s.findByID(userId).getPHONE());

    }
}
