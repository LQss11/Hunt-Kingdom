
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private VBox pnl_scroll;

    @FXML
    private JFXButton panier;

    @FXML
    private void handleButtonAction(MouseEvent event) {
        refreshNodes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        refreshNodes();
    }

    private void refreshNodes() {
        /* pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<10; i++)
        {
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
               pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  */
    }

    @FXML
    private void panieract(ActionEvent event) throws IOException {
            System.out.println("skaaaaaaaaa");
        Stage stage = new Stage();
        stage.setTitle("Panier");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Panier.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
