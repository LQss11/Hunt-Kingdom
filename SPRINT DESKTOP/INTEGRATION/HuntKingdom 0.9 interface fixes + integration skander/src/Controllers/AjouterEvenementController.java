/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.GestionsProduitsController.saveToFileImageNormal;
import Entities.Evenement;
import Service.ServiceEvenement;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.in;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.Z;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeArray.filter;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author aziz9
 */
public class AjouterEvenementController implements Initializable 
{

    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    ObservableList<String> TypeList =FXCollections.observableArrayList("Hunting","Training","Enjoing");
    
    
    @FXML
    private TextField Ename;
    @FXML
    private ComboBox Etype;
    @FXML
    private TextField Eprix;
    @FXML
    private TextField Enbrplace;
      private AnchorPane listUsers;
    @FXML
    private DatePicker Edate;

    @FXML
    private TextField Eplace;
    @FXML
    private TextField Edescription;
    @FXML
    private TextField Eduree;
    @FXML
    private TextField Eimage;
    @FXML
    private TableView<Evenement> ETable;
    @FXML
    private TableColumn<Evenement, Integer> id;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, Double> prix;
    @FXML
    private TableColumn<Evenement, Integer> nbrplace;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> place;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Integer> duree;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TextField chercher;

    
    /*FilteredList filter = new FilteredList(data, e->true);*/
    @FXML
    private Button Save;
    @FXML
    private Button SaveChanges;
    @FXML
    private TextField Eid;
    @FXML
    private Button imp;
    @FXML
    private ImageView impi;
    
    String getImageUrl="SourcePackages/Image/back.jpg";
    private JFXButton reclamationBack;
    private JFXButton FeedbackBack;
    private JFXButton btn_logout;

     
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        SaveChanges.setVisible(false);
        Etype.setValue("Type");
        Etype.setItems(TypeList);
        ServiceEvenement SE= new ServiceEvenement();
        List<Evenement> EL=new ArrayList<>();

        try {
             EL = SE.afficherEvenement();
             ObservableList<Evenement> data = FXCollections.observableArrayList(EL);
             id.setCellValueFactory(new PropertyValueFactory <Evenement, Integer>("id"));
             nom.setCellValueFactory(new PropertyValueFactory <Evenement, String>("nom"));
             type.setCellValueFactory(new PropertyValueFactory <Evenement, String>("type"));
             prix.setCellValueFactory(new PropertyValueFactory <Evenement, Double>("prix"));
             nbrplace.setCellValueFactory(new PropertyValueFactory <Evenement, Integer>("nbrplace"));
             date.setCellValueFactory(new PropertyValueFactory <Evenement, Date>("date"));
             place.setCellValueFactory(new PropertyValueFactory <Evenement, String>("place"));
             description.setCellValueFactory(new PropertyValueFactory <Evenement, String>("description"));
             duree.setCellValueFactory(new PropertyValueFactory <Evenement, Integer>("duree"));
             image.setCellValueFactory(new PropertyValueFactory <Evenement, String>("image"));

             ETable.setItems(data);
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public int TestEvenement(String nom, String type, double prix, int nbrplace,String place, String description, int duree, String image)
    {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////name
        if((nom == null) || nom.equals("") || nom.equals(" "))
                return 1;
        else
        {
                for (int i = 0; i < nom.length(); i++) 
                {
                    if (!(('a' <= nom.charAt(i) && nom.charAt(i) <= 'z')|| ('A' <= nom.charAt(i) && nom.charAt(i) <= 'Z') )) 
                        return 2;
                }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////place
        if((place == null) || place.equals("") || place.equals(" "))
                return 3;
        else
        {
                for (int i = 0; i < place.length(); i++) 
                {
                    if (!(('a' <= place.charAt(i) && place.charAt(i) <= 'z')|| ('A' <= place.charAt(i) && place.charAt(i) <= 'Z') )) 
                        return 4;
                }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////description
        if((description == null) || description.equals("") || description.equals(" "))
                return 5;
        else
        {
                for (int i = 0; i < description.length(); i++) 
                {
                    if (!(('a' <= description.charAt(i) && description.charAt(i) <= 'z')|| ('A' <= description.charAt(i) && description.charAt(i) <= 'Z') )) 
                        return 6;
                }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////prix/nbrplace/duree
        if((prix <= 0)||(nbrplace <= 0)||(duree <= 0)||(String.valueOf(duree)=="")||(String.valueOf(nbrplace)=="")||(String.valueOf(prix)==""))
                return 7;
        
        
        
        
        return 0;
    }
    
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\Users\\Khalil\\Documents\\NetBeansProjects\\integ\\Hunt\\src\\FXML\\images");
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
    private void AjouterEvenement(ActionEvent event) throws SQLException 
    {
        
        ServiceEvenement SE= new ServiceEvenement();
        Etype.setValue("Type");
        Etype.setItems(TypeList);
        String nom = Ename.getText();
        String type = Etype.getValue().toString();
        Double prix = Double.valueOf(Eprix.getText());
        int nbrplace = Integer.valueOf(Enbrplace.getText());
        Date date = Date.valueOf(Edate.getValue());
        String place = Eplace.getText();
        String description = Edescription.getText();
        int duree =Integer.valueOf(Eduree.getText());
        String image= impi.toString();
        
        
        
         Image image1=null;
            image1=impi.getImage();
            String photo = null;
            photo = saveToFileImageNormal(image1);
        
        
        
        
        Evenement E =new Evenement(nom,type,prix,nbrplace,date,place,description,duree, photo);
        int test=TestEvenement(nom,type,prix,nbrplace,place,description,duree,image);
        System.out.println(test);
        
        if(test==0)
        {
                SE.ajouterEvenement(E);
                ETable.refresh();
                alert.setTitle("Succes");
                alert.setHeaderText(null);
                alert.setContentText("Categorie added");
                alert.showAndWait();
                ETable.refresh();
        }      
        else if(test==1)
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Name is empty");
                alert.showAndWait();                
        }
        else if(test==2)
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Name must include only caracters");
                alert.showAndWait();
        }
        else if(test==3)
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Place is empty");
                alert.showAndWait();
        }
        else if(test==4)
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("place must include only caracters");
                alert.showAndWait();
        }
        else if(test==5)
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Description is empty");
                alert.showAndWait();
        }
        else if(test==6)
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Description must include only caracters");
                alert.showAndWait();
        }
        else if((test==7))
        {
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Prix,nbrplace,duree should be >= 0 and with Integer type");
                alert.showAndWait();
        }
    }

    private void AfficherEvenement(ActionEvent event) 
    {
        ServiceEvenement SE= new ServiceEvenement();
        List<Evenement> EL=new ArrayList<>();

        try {
             EL = SE.afficherEvenement();
             ObservableList<Evenement> data = FXCollections.observableArrayList(EL);
             id.setCellValueFactory(new PropertyValueFactory <Evenement, Integer>("id"));
             nom.setCellValueFactory(new PropertyValueFactory <Evenement, String>("nom"));
             type.setCellValueFactory(new PropertyValueFactory <Evenement, String>("type"));
             prix.setCellValueFactory(new PropertyValueFactory <Evenement, Double>("prix"));
             nbrplace.setCellValueFactory(new PropertyValueFactory <Evenement, Integer>("nbrplace"));
             date.setCellValueFactory(new PropertyValueFactory <Evenement, Date>("date"));
             place.setCellValueFactory(new PropertyValueFactory <Evenement, String>("place"));
             description.setCellValueFactory(new PropertyValueFactory <Evenement, String>("description"));
             duree.setCellValueFactory(new PropertyValueFactory <Evenement, Integer>("duree"));
             image.setCellValueFactory(new PropertyValueFactory <Evenement, String>("image"));

             ETable.setItems(data);

        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SearchEvenement(KeyEvent event) 
    {
        
      /* chercher.textProperty().addListener((observable, oldValue, newValue) -> {
            
        filter.setPredicate((predicate<? super Std>)(Std std)->{
        
            if(newValue.isEmpty() || newValue==null){
                return true;
            }
            else if (std.getId().contains(newValue))
                return true;
            
        return false;
            });
        });
        SortedList sort=new SortedList(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(sort); */
    }

    private void SupprimerEvenement(ActionEvent event)throws SQLException 
    {
        ServiceEvenement SE= new ServiceEvenement();
        ObservableList<Evenement> EvenementSelected, allEvenements;
        allEvenements= ETable.getItems();
        EvenementSelected= ETable.getSelectionModel().getSelectedItems();
        for (Evenement tab : EvenementSelected) 
        {
            int id=tab.getId();
            SE.supprimerEvenement(id);
            EvenementSelected.forEach(allEvenements::remove);
        }
    }

    @FXML
    private void ClearForm(ActionEvent event) 
    {
        Ename.setText("");
        Eprix.setText("");
        Enbrplace.setText("");
        Edate.setValue(LocalDate.MIN);
        Eplace.setText("");
        Edescription.setText("");
        Eduree.setText("");
        Eimage.setText("");        
                
    }


    private void ModifierEvenement(ActionEvent event) throws SQLException 
    {
        ObservableList<Evenement> EvenementSelected, allEvenements;
        allEvenements= ETable.getItems();
        EvenementSelected= ETable.getSelectionModel().getSelectedItems();
        for (Evenement tab : EvenementSelected) 
        {
            /////////////////////////////Recuperer///////////////////////
            int id=tab.getId();
            String nom = tab.getNom();
            String type = tab.getType();
            Double prix = tab.getPrix();
            int nbrplace = tab.getNbrplace();
            Date date = tab.getDate();
            String place = tab.getPlace();
            String description = tab.getDescription();
            int duree = tab.getDuree();
            String image = tab.getImage();
            //////////////////////////afficher dans text fields//////////
            Eid.setText(Integer.toString(id));
            Ename.setText(nom);
            Etype.setValue(type);
            Eprix.setText(Double.toString(prix));
            Enbrplace.setText(Integer.toString(nbrplace));
           // Edate.setValue(date.toLocalDate());
            Eplace.setText(place);
            Edescription.setText(description);
            Eduree.setText(Integer.toString(duree));
            Eimage.setText(image);
            Save.setVisible(false);
            SaveChanges.setVisible(true);
                       
        }
    }
    
   

    @FXML
    private void savechangeevent(ActionEvent event) throws SQLException {
            ServiceEvenement SE= new ServiceEvenement();
            
            int idm =Integer.valueOf(Eid.getText());
            String nomm = Ename.getText();
            String typem = Etype.getValue().toString();
            Double prixm = Double.valueOf(Eprix.getText());
            int nbrplacem = Integer.valueOf(Enbrplace.getText());
            Date datem = Date.valueOf(Edate.getValue());
            String placem = Eplace.getText();
            String descriptionm = Edescription.getText();
            int dureem =Integer.valueOf(Eduree.getText());
            String imagem = Eimage.getText();
            
            /////////////////////////save///////////////////////////////
            Evenement Emodif= new Evenement(idm,nomm,typem,prixm,nbrplacem,datem,placem,descriptionm,dureem,imagem);
            SE.modifierEvenement(Emodif);
            Save.setVisible(true);
            SaveChanges.setVisible(false);
    }

    @FXML
    private void impajout(ActionEvent event) {
        
        
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             impi.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        
        
        
    }

    private void openhome(ActionEvent event) {
        FeedbackBack.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Admin_Dashboard.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void openListStudent(ActionEvent event) {
        createPages();
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


    private void openReclamationBack(ActionEvent event) {
        reclamationBack.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Feedback");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void openFeedbackBack(ActionEvent event) {
         FeedbackBack.getScene().getWindow().hide();

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
    
}
    
    

