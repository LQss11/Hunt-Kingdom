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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class User_HomeController implements Initializable {

    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton btn_logout;
    private ImageView profilephoto;
    @FXML
    private VBox pnl_scroll;
    public String emailuser;
    @FXML
    private JFXButton EvntBtn;
    String getImageUrl, mail;
    @FXML
    private JFXButton ReclamationFront;
    @FXML
    private JFXButton FeedbackFront;
    @FXML
    private JFXButton PanBtn;
    @FXML
    private JFXButton ProdBtn;
    @FXML
    private JFXButton EspBtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton buttonSaison;
    @FXML
    private Label UserName;
    @FXML
    private Label JoinedDate;
    @FXML
    private ImageView imageProfil;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Preferences user = Preferences.userRoot();
        int userId = user.getInt("UserId", id);
        System.out.println(user.getInt("UserId", id));

        UtilisateursService s = new UtilisateursService();

        String str = "file:" + System.getProperty("user.dir") + "/src/image/" + s.findByID(userId).getIMAGE();
        imageProfil.setImage(new Image(str));
        UserName.setText(s.findByID(userId).getFIRST_NAME());
        JoinedDate.setText("Since " + s.findByID(userId).getDate().toString().substring(0, 4));

        refreshNodes();
    }

    UtilisateursService user = new UtilisateursService();

    void transferMessage(String email) {
        emailuser = email;
        Utilisateurs u1 = user.findByMail(email);
        String text = "Bienvenue " + u1.getFIRST_NAME();
        name.setText(text);
        String ImageUrl = u1.getIMAGE();
        File file = new File(ImageUrl);
        Image ima = new Image(file.toURI().toString());
        profilephoto.setImage(ima);
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

    @FXML
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

    /*
     public void createPages(String emailuser) {
        try {
            bnthome = FXMLLoader.load(getClass().getResource("/FXML/User_Home.fxml"));
            Stage profileStage = new Stage();
            profileStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Profil.fxml"));
            profile = loader.load();
            EditProfilController pController = loader.getController();
            pController.setUserInfo(emailuser);
            //set up default node on page load
            setNode(bnthome);
              //forum = FXMLLoader.load(getClass().getResource("/fxml/Publication.fxml"));
              //ajouterReclamation = FXMLLoader.load(getClass().getResource("/fxml/AjouterReclamation.fxml"));
              //events = FXMLLoader.load(getClass().getResource("/fxml/ShowEventClient.fxml"));


        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
     */
    @FXML
    private void openReclalmation(ActionEvent event) {

        //ReclamationFront.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("Reclamer");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Reclamation.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void panieract(ActionEvent event) {

        //ReclamationFront.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("Reclamer");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Panier.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void openFeedback(ActionEvent event) {

        //FeedbackFront.getScene().getWindow().hide();
        /*  try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Feedback");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FeedbackFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }*/
    }

    @FXML
    private void OpenPanier(ActionEvent event) {
    }

    private void refreshNodes() {

        pnl_scroll.getChildren().clear();
        /*
        Node[] nodes = new Node[15];

        for (int i = 0; i < 10; i++) {
            try {
                nodes[i] = (Node) FXMLLoader.load(getClass().getResource("p.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }*/

        btnProfile.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/profil.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

        ProdBtn.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/ProduitsFront.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

        EvntBtn.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/Participation.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

        EspBtn.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/FrontE.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
        buttonSaison.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/FrontS.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
        FeedbackFront.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/FeedbackFront.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

    }

    @FXML
    private void openprofile(ActionEvent event) {
    }

    @FXML
    private void openhome(ActionEvent event) {
    }

    @FXML
    private void OpenProduit(ActionEvent event) {
    }

    @FXML
    private void OpenEvenements(ActionEvent event) {
    }

    @FXML
    private void OpenEspeces(ActionEvent event) {
    }

    @FXML
    private void OpenSaison(ActionEvent event) {
    }

}
