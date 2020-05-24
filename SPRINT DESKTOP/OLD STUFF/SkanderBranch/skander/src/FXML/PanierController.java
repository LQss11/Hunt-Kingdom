/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import Entities.Produit;
import Service.ServiceUtilisateursAdresses;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class PanierController implements Initializable {

    @FXML
    private JFXListView<String> lp;
    @FXML
    private VBox det;
    @FXML
    private Label tot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hunt.Hunt.listeprod.forEach((t) -> {
            String str = t.getNom() + " " + t.getPrix() + " $";
            lp.getItems().add(str);
        });

        lp.setOnMouseClicked((event) -> {
            aff();
        });
        Double t = hunt.Hunt.listeprod.stream().mapToDouble((p) -> {
            return p.getPrix();
        }).sum();
        tot.setText(String.valueOf(t) + " $");
    }

    private void aff() {
        
        det.getChildren().clear();
        Produit p = hunt.Hunt.listeprod.get(lp.getSelectionModel().getSelectedIndex());
        String str="file:"+System.getProperty("user.dir")+"/src/FXML/images/"+p.getImage();
        ImageView imageView = ImageViewBuilder.create()
                .image(new Image(str))
                .build();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        JFXButton btn = new JFXButton("Supprimer");

        btn.setOnAction((event) -> {
            hunt.Hunt.listeprod.remove(p);
            lp.getItems().remove(p.getNom() + " " + p.getPrix() + " $");
            det.getChildren().clear();
            if (hunt.Hunt.listeprod.size() == 0) {
                Parent root;
                //  try {
                // root = (ScrollPane) FXMLLoader.load(getClass().getResource("/Views/store.fxml"));

                //pijava.pijava.getStage().getScene().setRoot(root);
                // } catch (IOException ex) {
                //Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                // }
            }
        });
        btn.setStyle("-fx-background-color:#000000; -fx-text-fill: white;");
        btn.setPrefWidth(200);
        det.getChildren().add(imageView);
        det.getChildren().add(btn);
    }

    @FXML
    private void Passercmd(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        stage.setTitle("adresse livraison");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/adresselivraison.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
