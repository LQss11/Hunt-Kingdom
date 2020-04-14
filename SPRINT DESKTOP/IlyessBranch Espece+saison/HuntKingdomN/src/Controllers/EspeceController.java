/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Service.ServiceEspece;
import Service.ServiceSaison;
import Entities.Espece;
import Entities.Saison;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Util.DataSource;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.time.zone.ZoneRulesProvider.refresh;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
/**
 * FXML Controller class
 *
 * @author ilyess
 */
public class EspeceController implements Initializable {
     private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;

    @FXML
    private Pane PaneHeadEspece;
    @FXML
    private Pane paneProduits;
    @FXML
    private ComboBox<Saison> idS;
    @FXML
    private TextField nomEspece;
    @FXML
    private TextField type;
    @FXML
    private TextField zone;
    @FXML
    private TextField descriptionEspece;
    @FXML
    private TextField poids;
    @FXML
    private Button buttonAjourEspece;
    @FXML
    private Button buttonSupprimerEspece;
    @FXML
    private Button buttonModifierEspece;
    @FXML
    private ImageView imageViewEspece;
    @FXML
    private Button buttonEspece;
    @FXML
    private Button buttonSaison;
    
    String getImageUrl="src/Image/image.png";
    @FXML
    private TableView<Espece> tableViewEspece;
    @FXML
    private TableColumn<Espece, ?> TnomEsepce;
    @FXML
    private TableColumn<Espece, ?> Ttype;
    @FXML
    private TableColumn<Espece, ?> Tzone;
    @FXML
    private TableColumn<Espece, ?> Tville;
    @FXML
    private TableColumn<Espece, ?> TdescriptionEspece;
    @FXML
    private TableColumn<Espece, ?> Tpoids;
    @FXML
    private TextField ville;
private ObservableList<Espece> dataEspece;
    @FXML
    private TextField SearchEspeceField;
    @FXML
    private Button buttonCHercherEspece;
    @FXML
    private Button Retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con = DataSource.getInstance().getCnx();
        dataEspece= FXCollections.observableArrayList();
        initComboBoxSaison();
        afficherEspece();
        
        loadDataEspece();
        recupereEspece();
         try {
             chercherEspece();
             
            refresh();
        
             // TODO
         } catch (SQLException ex) {
             Logger.getLogger(EspeceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        
    }
    
    @FXML
    private void AjouterImageEspece(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        getImageUrl = selectedfile.getAbsolutePath();
        File file = new File(getImageUrl);
        Image ima = new Image(file.toURI().toString());
        imageViewEspece.setImage(ima);

    }


    @FXML
    private void ajouterEspece(ActionEvent event) throws SQLException {
        ServiceEspece se = new ServiceEspece();
       String photo = null;
       Image image1 =null;
       image1= imageViewEspece.getImage();
        Espece e=new Espece();
        e.setNomEspece(nomEspece.getText());
        e.setDescriptionEspece(descriptionEspece.getText());
        e.setImage(getImageUrl);
        e.setPoids(poids.getText());
        e.setType(type.getText());
        e.setVille(ville.getText());
        e.setZone(zone.getText());
        e.setIdS(2);
            se.ajouterEspece(e);
             afficherEspece();
        loadDataEspece();
        clearFormEspece();
        photo = saveToFileImageNormal(image1);
        
    }

    @FXML
    private void supprimerEspece(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
            Espece x=gettempEspece(edittedcell);         
            int i=x.getIdEspece();
        
        ServiceEspece se = new ServiceEspece();         
        se.deleteEspece(i);
            System.out.println("Espece deleted");
             afficherEspece();
        loadDataEspece();
        clearFormEspece();
    }

    @FXML
    private void modifierEspece(ActionEvent event) throws SQLException {
       TableColumn.CellEditEvent edittedcell = null;
            Espece x=gettempEspece(edittedcell);         
            
        Espece e=new Espece();
        ServiceEspece se = new ServiceEspece();
       //  int i=se.idEspeceFromNom(nomEspece.getText());
          int i=x.getIdEspece();
             e.setIdEspece(i);
            e.setNomEspece(nomEspece.getText());
            e.setDescriptionEspece(descriptionEspece.getText());
            e.setImage(getImageUrl);
            e.setPoids(poids.getText());
            e.setType(type.getText());
            e.setVille(ville.getText());
            e.setZone(zone.getText());
            e.setIdS(2);
             System.err.println("wsel lehne ");
            se.modifierEspece(e);
             System.out.println("Espece modified");
             clearFormEspece();
             afficherEspece();
        loadDataEspece(); 
         clearFormEspece();
       
    }
public void recupereEspece()
{tableViewEspece.setOnMouseClicked(e->{

Espece test=tableViewEspece.getSelectionModel().getSelectedItem();

            
          nomEspece.setText(String.valueOf(test.getNomEspece()));
          descriptionEspece.setText(String.valueOf(test.getNomEspece()));
          poids.setText(String.valueOf(test.getNomEspece()));
          type.setText(String.valueOf(test.getNomEspece()));
          zone.setText(String.valueOf(test.getNomEspece()));
          ville.setText(String.valueOf(test.getNomEspece()));

});





}
     private void initComboBoxSaison() {
    ObservableList datacat=FXCollections.observableArrayList();
   idS.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from saison");

           rs=pst.executeQuery();
     while (rs.next()) {   
            Saison c=new Saison();
            c.setNom(rs.getString("nom"));
             datacat.add(c);
     }       }
       catch (SQLException ex) {
            Logger.getLogger(ServiceSaison.class.getName()).log(Level.SEVERE, null, ex);
       }
            idS.setItems(datacat);

    }
      private void clearFormEspece()
      {
          nomEspece.clear();
          descriptionEspece.clear();
          poids.clear();
          type.clear();
          zone.clear();
          ville.clear();
      }
      
      
      
          private void afficherEspece(){
              TnomEsepce.setCellValueFactory(new PropertyValueFactory <>("nomEspece"));
             Ttype.setCellValueFactory(new PropertyValueFactory <>("type"));             
             Tzone.setCellValueFactory(new PropertyValueFactory <>("zone"));
              Tville.setCellValueFactory(new PropertyValueFactory <>("ville"));
              TdescriptionEspece.setCellValueFactory(new PropertyValueFactory <>("descriptionEspece"));
              Tpoids.setCellValueFactory(new PropertyValueFactory <>("poids"));
    }
          
private void loadDataEspece() {
   dataEspece.clear();
         try {
           pst =con.prepareStatement("Select * from espece");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataEspece.add(new  Espece(rs.getInt("idEspece"),rs.getString("nomEspece"), rs.getString("descriptionEspece"), rs.getString("poids"), rs.getString("type"), rs.getString("zone"), rs.getString("ville")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceEspece.class.getName()).log(Level.SEVERE, null, ex);
       }
        tableViewEspece.setItems(dataEspece);
    }
  
      public Espece gettempEspece(TableColumn.CellEditEvent edittedCell) {
        Espece test = tableViewEspece.getSelectionModel().getSelectedItem();        
        return test;
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
        } }

    @FXML
    private void chercherEspece() throws SQLException {
      SearchEspeceField.setOnKeyReleased(e->{
        if(SearchEspeceField.getText().equals("")){
            loadDataEspece();
    }
    else{
        dataEspece.clear();
          String sql = "Select * from espece where nomEspece LIKE '%"+SearchEspeceField.getText()+"%'"
                  +"Union Select * from espece where type LIKE'%"+SearchEspeceField.getText()+"%'";
          try{
          pst=con.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next()){
           dataEspece.add(new  Espece(rs.getInt("idEspece"),rs.getString("nomEspece"), rs.getString("descriptionEspece"), rs.getString("poids"), rs.getString("type"), rs.getString("zone"), rs.getString("ville")));
          }
           tableViewEspece.setItems(dataEspece);
          }catch(SQLException ex){
              System.out.println("requete search ");}          
          
          
        
    
        }    
        
     });
    }
      public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("src\\imageEspece");
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
   
     private void RetourDashboard(ActionEvent event){
     Retour.getScene().getWindow().hide();
    
      try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Admin_Dashboard.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }}
      
     
     
     
    
     
     
}
