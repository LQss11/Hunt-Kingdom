/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Service.ServiceEspece;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 *
 * @author Cool IT Help
 */
public class FrontEController implements Initializable {
    
    
      
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Pagination pagination;
     String getImageUrl;
    
    File filesJpg[];
    @FXML
    private Button button;
    @FXML
    private Label Laffiche;
    private Button Retour;
    private JFXButton btn_logout;
    private JFXButton bnthome;
    private JFXButton ReclamationFront;
    private JFXButton FeedbackFront;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
         ServiceEspece se = new ServiceEspece();
        try {
            Laffiche.setText(se.readAll().toString());
        } catch (SQLException ex) {
            Logger.getLogger(FrontSController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = (Stage) anchor.getScene().getWindow();        
         openDirectoryChooser(stage); // calling method to open dicrectory chooser       
         
         
         //this code will create a page and load inside pagination control
         pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {  // every time when you click pagination button this method will be called
                return createPage(pageIndex);
            }
        });
       
    }
    
    private void openDirectoryChooser(Stage parent) {
        ServiceEspece se = new ServiceEspece();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.getInitialDirectory();
           
             getImageUrl = "src\\imageEspece";
              File file = new File(getImageUrl);
        if (file != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };
 
            filesJpg = file.listFiles(filterJpg);
            
        }
    }
    
    public VBox createPage(int index) {
 
        ImageView imageView = new ImageView();
 
        File file = filesJpg[index];
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(400);
            imageView.setFitHeight(360);
           // imageView.setPreserveRatio(true);
            
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
            
        }
         
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);        
        
        return pageBox;
    }  
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        
        
    }    

    private void RetourBack(ActionEvent event) {
         Retour.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FrontS.fxml"));

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

    private void openprofile(ActionEvent event) {
        
        bnthome.getScene().getWindow();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/profil.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    private void openReclalmation(ActionEvent event) {
        
        ReclamationFront.getScene().getWindow().hide();

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


    private void OpenEvenements(ActionEvent event) {
         btn_logout.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Participation.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void OpenProduit(ActionEvent event) {
         ReclamationFront.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Reclamer");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ProduitsFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private void OpenEspeces(ActionEvent event) {
        
        ReclamationFront.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FrontS.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }

    private void openFeedback(ActionEvent event) {
        
        FeedbackFront.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Feedback");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FeedbackFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
 
}
