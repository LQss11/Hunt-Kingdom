/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunt;

import Entities.Produit;
import Entities.Utilisateurs;
import de.ailis.pherialize.MixedArray;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author RAFIK
 */
public class Hunt extends Application {
  
    
  

    public static ArrayList<Produit> listeprod;

    @Override
    public void start(Stage stage) throws Exception {

      
         listeprod = new ArrayList<Produit>();
        
        

        //Preferences prefs;
        //prefs.removeNode();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));

        Scene scene = new Scene(root, 800, 575);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
