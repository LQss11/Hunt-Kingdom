/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateurs;
import Service.UtilisateursService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class Admin_DashboardController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private VBox pnl_scroll;

    private AnchorPane listUsers;
    private JFXButton reclamationBack;
    private JFXButton FeedbackBack;
    @FXML
    private JFXButton buttonSaison;
    @FXML
    private JFXButton buttonUser;
    @FXML
    private JFXButton buttonProduit;
    @FXML
    private JFXButton buttonEvent;
    @FXML
    private JFXButton buttonCmd;
    @FXML
    private JFXButton buttonReclamation;
    @FXML
    private JFXButton buttonFeedback;
    @FXML
    private JFXButton buttonEspece;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        refreshNodes();

    }

    private void refreshNodes() {
        buttonUser.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/List_Users.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

        buttonProduit.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/GestionsProduits.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

        buttonEvent.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/AjouterEvenement.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
        
        
        
        
        
        
             
            buttonCmd.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/CommandeBack.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
            
            
            
            
/*
        buttonReclamation.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/ReclamationBack.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });

        buttonFeedback.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/FeedbackBack.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
*/
        buttonEspece.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/Espece.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
        buttonSaison.setOnAction(event -> {
            pnl_scroll.getChildren().clear();
            try {
                pnl_scroll.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/FXML/Saison.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } //prints out Click Me
        });
    }

    void transferMessage(String email) {
        UtilisateursService user = new UtilisateursService();
        Utilisateurs u1 = user.findByMail(email);
        String text = "Bienvenue " + u1.getFIRST_NAME();
        name.setText(text);

    }

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

    @FXML
    private void openhome(ActionEvent event) {
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
    private void openListStudent(ActionEvent event) {
        createPages();
    }

    @FXML
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

    @FXML
    private void openFeedbackBack(ActionEvent event) {

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

    @FXML
    private void openReclamationBack(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Reclamation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
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

    @FXML
    private void openlistCommande(ActionEvent event) {
    }

    @FXML
    private void openespece(ActionEvent event) {
        reclamationBack.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Gerer Les Produits");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Espece.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void handleButtonSaisonAction(ActionEvent event) throws IOException {
        buttonSaison.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Saison.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
