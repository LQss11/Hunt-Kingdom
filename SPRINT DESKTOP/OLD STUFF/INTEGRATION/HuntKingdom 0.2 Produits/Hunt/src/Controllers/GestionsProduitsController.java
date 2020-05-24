/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Validation.TextFieldvalidation;
import doryan.windowsnotificationapi.fr.Notification;
import com.jfoenix.controls.JFXButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Connection;
import Util.DataBase;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import Service.ServicePromotion;
import Service.ServiceWhishlist;
import Service.SendMail;
import static com.qoppa.pdf.b.oc.wb;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Entities.Categorie;
import Entities.Whishlist;
import Entities.Utilisateurs;
import Entities.Promotion;
import Entities.Produit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;

import org.apache.commons.lang3.RandomStringUtils;

import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class GestionsProduitsController implements Initializable {

     private ObservableList<Produit> dataProduit;
private ObservableList<Categorie> dataCategorie;
private ObservableList<Promotion> dataPromotion;
   private Connection con;
    private ResultSet rs=null;
    private ResultSet rs1=null;
    private PreparedStatement pst,pst1;
    
       PieChart piechart1;
    ObservableList<PieChart.Data> piechartdata;
    private final ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
private PieChart pieChart;
    ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();
    
    
      private XSSFWorkbook wb;
    private XSSFSheet sheet;
     private XSSFRow header;
    
    
    
    
    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton list_user;
    @FXML
    private JFXButton list_event;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton GestionProduit;
    @FXML
    private JFXButton GestionCategorie;
    @FXML
    private JFXButton GestionPromotion;
    @FXML
    private JFXButton list_commande;
    @FXML
    private TextField TextFieldPourcentagePromotion;
    @FXML
    private ComboBox<String> comboBoxProduit;
    @FXML
    private DatePicker datePickerDateDebPromo;
    @FXML
    private DatePicker datePickerDateFinPromo;
    @FXML
    private JFXTextField searchPromotionField;
    @FXML
    private TableView<Promotion> tableViewPromotion;
    @FXML
    private TableColumn<Promotion, Integer> idPromotion;
    @FXML
    private TableColumn<Promotion, Integer> pourcentagePromotion;
    @FXML
    private TableColumn<Promotion, Date> dateDebutPromotion;
    @FXML
    private TableColumn<Promotion, Date> dateFinPromotion;
    @FXML
    private TableColumn<Promotion, Float> prixPromotion;
    @FXML
    private TableColumn<Promotion, Integer> etatPromotion;
    @FXML
    private Button buttonAjourPromotion;
    @FXML
    private Button buttonModifierPromotion;
    @FXML
    private Button buttonActiverPromotion;
    @FXML
    private Button buttonSupprimerPromotion;
    @FXML
    private Pane paneCategorie;
    @FXML
    private Pane paneProduit;
    @FXML
    private Pane panePromotion;
    @FXML
    private TextField textFieldNomCategorie;
    @FXML
    private TextField textFieldDescCategorie;
    @FXML
    private TextField searchCategorieField;
    @FXML
    private Button buttonImportCategorie;
    @FXML
    private Button buttonAjoutCategorie;
    @FXML
    private Button buttonModifierCategorie;
    @FXML
    private Button buttonSupprimerCategorie;
    @FXML
    private ImageView ImageViewCategorie;
    @FXML
    private TableView<Categorie> TableViewCategorie;
    @FXML
    private TableColumn<Categorie, Integer> idCategorie;
    @FXML
    private TableColumn<Categorie, String> nomCategorie;
    @FXML
    private TableColumn<Categorie, String> descriptionCategorie;
    @FXML
    private ImageView imageViewProduit;
    @FXML
    private Button buttonModifierProduit;
    @FXML
    private Button buttonAjourProduit;
    @FXML
    private Button buttonImprimerProduit;
    @FXML
    private Button buttonImporterProduit;
    @FXML
    private Button buttonSupprimerProduit;
    @FXML
    private ComboBox<Categorie> ComboBoxCategorie;
    @FXML
    private TextField TextFieldNomProduit;
    @FXML
    private TextField TextFieldGarantieProduit;
    @FXML
    private TextField TextFieldQuantiteProduit;
    @FXML
    private TextField TextFieldDescProduit;
    @FXML
    private TextField TextFieldPrixProduit;
    @FXML
    private TextField SearchProduitField;
    @FXML
    private TableView<Produit> tableViewProduit;
    @FXML
    private TableColumn<Produit, String> nomProduit;
    @FXML
    private TableColumn<Produit, String> categorieProduit;
    @FXML
    private TableColumn<Produit, Integer> garantieProduit;
    @FXML
    private TableColumn<Produit, Float> prixProduit;
    @FXML
    private TableColumn<Produit, String> descriptionProduit;
    @FXML
    private Pane PaneDashboard;
    @FXML
    private PieChart PieChartCategorie;
    @FXML
    private Button ButtonStat;
    @FXML
    private Label ErrorPourcentagePromotion;
    @FXML
    private Label ErrorProduitPromotion;
    @FXML
    private Label ErrorDateDebPromotion;
    @FXML
    private Label ErrorDateFinPromotion;
    @FXML
    private Label ErrorNomCategorie;
    @FXML
    private Label ErrorDescriptionCategorie;
    @FXML
    private Label ErrorImageCategorie;
    @FXML
    private Label ErrorNomProduit;
    @FXML
    private Label ErrorCategorieProduit;
    @FXML
    private Label ErrorGarantieProduit;
    @FXML
    private Label ErrorImageProduit;
    @FXML
    private Label ErrorQuantiteProduit;
    @FXML
    private Label ErrorDescriptionProduit;
    @FXML
    private Label ErrorPrixProduit;
    @FXML
    private JFXButton reclamationBack;
    @FXML
    private JFXButton list_espece;
    @FXML
    private JFXButton FeedbackBack;

    private AnchorPane listUsers;
    /**
     * Initializes the controller class.
     */
    public void refresh() throws SQLException
    {
         initComboBoxProduit();
          initStatProduit();
          PieChartCategorie.setData(piechartdata);        
        initComboBoxCategorie();
         loadDataCategorie();
       afficherCategorie();      
        loadDataPromotion();
       afficherPromotion();
        try {
            loadDataProduit();
        } catch (SQLException ex) {
            System.out.println("Controllers.GestionsProduitsController.refresh()loaddataProduit");        }
        afficherProduit();
    
    }
    @FXML
    private void openProduitPane(ActionEvent event) {
        paneProduit.toFront();
    }
 @FXML
    private void opnStat(ActionEvent event) {
        PaneDashboard.toFront();
    }
    @FXML
    private void openCategoriePane(ActionEvent event) {
        paneCategorie.toFront();
    }

    @FXML
    private void openPromotionPane(ActionEvent event) {
        panePromotion.toFront();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           con = DataBase.getInstance().getConnection();
     RecupererProduit();
     RecupererCategorie();
     RecupererPromotion();
        dataCategorie= FXCollections.observableArrayList();
        dataProduit= FXCollections.observableArrayList();
        dataPromotion= FXCollections.observableArrayList();  
      searchProduit();
      searchPromotion();
      searchCategorie();
        try {
            refresh();
        } catch (SQLException ex) {
            System.out.println("Controllers.GestionsProduitsController.initialize()refresh");        }
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
           
    
    
/////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
/////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////Partie Categorie//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////////////////////////////////
    private void RecupererCategorie() {
        TableViewCategorie.setOnMouseClicked(e -> {
 Categorie test = TableViewCategorie.getSelectionModel().getSelectedItem();               
            textFieldNomCategorie.setText(test.getNom());
            textFieldDescCategorie.setText(String.valueOf(test.getDescription()));
            
        }
        );
    }
    private void afficherCategorie(){
             idCategorie.setCellValueFactory(new PropertyValueFactory <>("id"));
             nomCategorie.setCellValueFactory(new PropertyValueFactory <>("nom"));
             descriptionCategorie.setCellValueFactory(new PropertyValueFactory <>("description"));
    }
private void loadDataCategorie() {
   dataCategorie.clear();
         try {
           pst =con.prepareStatement("Select * from categorie");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataCategorie.add(new  Categorie(rs.getInt("id"),rs.getString("nom"), rs.getString("description")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
        TableViewCategorie.setItems(dataCategorie);
    }
     
    private void clearFormCategorie()
    {
    textFieldDescCategorie.clear();
    textFieldNomCategorie.clear();
    ImageViewCategorie.setImage(null);
    
    }
     public Categorie gettempCategorie(TableColumn.CellEditEvent edittedCell) {
        Categorie test = TableViewCategorie.getSelectionModel().getSelectedItem();        
        return test;
    }
 
      public void searchCategorie(){
searchCategorieField.setOnKeyReleased(e->{
    if(searchCategorieField.getText().equals("")){
        loadDataCategorie();
    }
    else{
        dataCategorie.clear();
          String sql = "Select * from categorie where nom LIKE '%"+searchCategorieField.getText()+"%'"
                + "UNION Select * from categorie where description LIKE '%"+searchCategorieField.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {   dataCategorie.add(new  Categorie(rs.getInt("id"),rs.getString("nom"), rs.getString("description")));
 
        }
        TableViewCategorie.setItems(dataCategorie);
    } catch (SQLException ex) {
        System.err.println("requete search");    }
    
    }
});
}
    @FXML
    private void AjouterImageCategorie(ActionEvent event) {
                
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             ImageViewCategorie.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    } 

    @FXML
    private void AjouterCategorie(ActionEvent event)  throws SQLException, AWTException, MalformedURLException {

        boolean isNameEmpty=Validation.TextFieldvalidation.TextFieldNom(textFieldNomCategorie, ErrorNomCategorie, "Name required");
        boolean isDescEmpty=Validation.TextFieldvalidation.TextFieldNom(textFieldDescCategorie, ErrorDescriptionCategorie, "Description required");
       
        
            String nom = textFieldNomCategorie.getText();
            String description = textFieldDescCategorie.getText();
             
            Image image1=null;
            image1=ImageViewCategorie.getImage();
            String photo = null;
            photo = saveToFileImageNormal(image1);
        
            if(isNameEmpty && isDescEmpty )
            {ServiceCategorie sc = new ServiceCategorie();
        Categorie c = new Categorie(nom,description,photo);
            
        
         sc.ajouterCategorie(c);

      
         Notification.sendNotification("HuntKingdom"," \n  Categorie Has been added ." ,TrayIcon.MessageType.WARNING);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Categorie added");
                alert.showAndWait();
                clearFormCategorie();
                  refresh();}

    }

    @FXML
    private void ModifierCategorie(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
       
        boolean isNameEmpty=Validation.TextFieldvalidation.TextFieldNom(textFieldNomCategorie, ErrorNomCategorie, "Name required");
        boolean isDescEmpty=Validation.TextFieldvalidation.TextFieldNom(textFieldDescCategorie, ErrorDescriptionCategorie, "Description required");
       
    

        int i=0;            
    TableColumn.CellEditEvent edittedcell = null;
           Categorie x=gettempCategorie(edittedcell);
           int c=x.getId();
            //String idp = tf_idprod.getText();
           String nom = textFieldNomCategorie.getText();
          String description = textFieldDescCategorie.getText();
          
           Image image1=null;
             image1= ImageViewCategorie.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
    if(isNameEmpty && isDescEmpty )
            {
              ServiceCategorie sc = new ServiceCategorie();
             
              if(photo==null)
              {     ErrorDescriptionCategorie.setText("Image Required") ;      }
              
              else{         Categorie categ = new Categorie(c,nom,description,photo);
                            System.out.println(categ);
                            i=sc.updatecategory(categ);}
                    
                Notification.sendNotification("HuntKingdom"," \n  Categorie Has been updated." ,TrayIcon.MessageType.WARNING);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Categorie updated");
                alert.showAndWait();
                clearFormCategorie();
         refresh();
           
        }
          
        
    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        TableColumn.CellEditEvent edittedcell = null;
            Categorie x=gettempCategorie(edittedcell);         
            int i=x.getId();
            ServiceCategorie cat=new ServiceCategorie();
           
           
            
            int s=cat.deletecategory(i);
              if(s==1)
        {
                Notification.sendNotification("HuntKingdom"," \n  Categorie Has been removed." ,TrayIcon.MessageType.WARNING);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Categorie removed");
                alert.showAndWait();
          refresh();
        }
        
    }

     
    /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
/////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////Partie Produit//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////////////////////////////////
    
      @FXML
    private void modifierProduit(ActionEvent event) throws SQLException  {
          boolean isNameEmpty=Validation.TextFieldvalidation.TextFieldNom(TextFieldNomProduit, ErrorNomProduit, "Name required");
        boolean isDescEmpty=Validation.TextFieldvalidation.TextFieldNom(TextFieldDescProduit, ErrorDescriptionProduit, "Description required");
        boolean isGarantieEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldGarantieProduit, ErrorGarantieProduit, "Garantie required");
        boolean isPrixEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldPrixProduit, ErrorPrixProduit, "Prix required");
         boolean isQuantiteEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldQuantiteProduit, ErrorQuantiteProduit, "Quantite required");
        int i;            
    TableColumn.CellEditEvent edittedcell = null;
           Produit x=gettempProduit(edittedcell);
           int id=x.getId();
            String nom =  TextFieldNomProduit.getText();
    int garantie=Integer.valueOf(TextFieldGarantieProduit.getText());
    String description = TextFieldDescProduit.getText();
    float prix=Float.valueOf(TextFieldPrixProduit.getText());
    int quantite=Integer.valueOf(TextFieldQuantiteProduit.getText());
    Categorie cat=new Categorie();
    cat= ComboBoxCategorie.getSelectionModel().getSelectedItem();
     if(isNameEmpty && isDescEmpty && isGarantieEmpty && isPrixEmpty && isQuantiteEmpty)
            {
           Image image1=null;
             image1= imageViewProduit.getImage();
              String photo = null;
               if(image1==null)
               {
               ServiceProduit sp = new ServiceProduit();
              
                         Produit p= new Produit(id,cat, nom, quantite, prix, description, garantie);                            
                             i=sp.updateProduitWithoutPhoto(p);
               
               }else{
             photo = saveToFileImageNormal(image1);
  
              ServiceProduit sp = new ServiceProduit();
              
                         Produit p= new Produit(id,cat, nom, quantite, prix, description, garantie, photo);                            
                             i=sp.updateProduit(p);
       
               }
          
              if(i==1)
        {          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("produit updated");
                alert.showAndWait();
                clearFormProduit();
        try {
            refresh();
        } catch (SQLException ex) {
            System.out.println("Controllers.GestionsProduitsController.modifierProduit()/refresh");        }
           
        }}
    }

    @FXML
    private void ajouterProduit(ActionEvent event)  throws SQLException, AWTException, MalformedURLException {
        boolean isNameEmpty=Validation.TextFieldvalidation.TextFieldNom(TextFieldNomProduit, ErrorNomProduit, "Name required");
        boolean isDescEmpty=Validation.TextFieldvalidation.TextFieldNom(TextFieldDescProduit, ErrorDescriptionProduit, "Description required");
        boolean isGarantieEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldGarantieProduit, ErrorGarantieProduit, "Garantie required");
        boolean isPrixEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldPrixProduit, ErrorPrixProduit, "Prix required");
         boolean isQuantiteEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldQuantiteProduit, ErrorQuantiteProduit, "Quantite required");
          
             
         String nom =  TextFieldNomProduit.getText();
    int garantie=Integer.valueOf(TextFieldGarantieProduit.getText());
    String description = TextFieldDescProduit.getText();
    float prix=Float.valueOf(TextFieldPrixProduit.getText());
    int quantite=Integer.valueOf(TextFieldQuantiteProduit.getText());
    Categorie c=new Categorie();
                   System.out.println("ici");
   c= ComboBoxCategorie.getSelectionModel().getSelectedItem();
    
Image image1=null;
             image1=imageViewProduit.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
        ServiceProduit sp = new ServiceProduit();
       if(isNameEmpty && isDescEmpty && isGarantieEmpty && isPrixEmpty && isQuantiteEmpty)
            {
        Produit p= new Produit(c, nom, quantite, prix, description, garantie, photo);
            System.out.println(p);
         sp.ajouterProduit(p);

     Notification.sendNotification("HuntKingdom"," \n  Product Has been added ." ,TrayIcon.MessageType.WARNING);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit ajoute");
                alert.showAndWait();
                clearFormProduit();
                  refresh();
    }} 

    @FXML
    private void imprimerListeProduit(ActionEvent event) {
        try {
         String query ="select * from produit";
        
            pst=con.prepareStatement(query);
        
        rs= pst.executeQuery();
        
        
         wb= new XSSFWorkbook();
        sheet= wb.createSheet("Product Details");
        header =sheet.createRow(0);
        header.createCell(0).setCellValue("id Produit");
        header.createCell(1).setCellValue("Nom Produit");
        header.createCell(2).setCellValue("Prix");
        header.createCell(3).setCellValue("garantie ");
        
        int index=1;
          
        while(rs.next()){
          XSSFRow row=sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString("id"));
            row.createCell(1).setCellValue(rs.getString("nom"));
            row.createCell(2).setCellValue(rs.getString("prix"));
            row.createCell(3).setCellValue(rs.getString("garantie"));
            index++;
        }
        
       FileOutputStream fileOut= new FileOutputStream("Produit.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Product Details Exported in Excel Sheet.");
        alert.showAndWait();
        
        pst.close();
        rs.close();
        
    }
    catch (SQLException ex) {
           System.out.println("Controllers.GestionsProduitsController.imprimerListeProduit()");
        }
    catch (IOException ex) {
        System.out.println("Controllers.GestionsProduitsController.imprimerListeProduit()");
    }
    }

    @FXML
    private void AjouterImageProduit(ActionEvent event) {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             imageViewProduit.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerProduit(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
         TableColumn.CellEditEvent edittedcell = null;
            Produit x=gettempProduit(edittedcell);         
            int i=x.getId();
            ServiceProduit sp=new ServiceProduit();
           
           
            
            int s=sp.deleteProduit(i);
              if(s==1)
        {
            Notification.sendNotification("HuntKingdom"," \n  Product Has been added ." ,TrayIcon.MessageType.WARNING);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit deleted");
                alert.showAndWait();
          refresh();
        }
    }
   
      
           public void searchProduit(){
SearchProduitField.setOnKeyReleased(e->{
    if(SearchProduitField.getText().equals("")){
        try {
            loadDataProduit();
        } catch (SQLException ex) {
            System.out.println("Controllers.GestionsProduitsController.searchProduit()load data PRoduit");        }
    }
    else{
        dataProduit.clear();
          String sql = "Select * from produit where nom LIKE '%"+SearchProduitField.getText()+"%'"
                + "UNION Select * from produit where prix LIKE '%"+SearchProduitField.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {   int idc=rs.getInt("categorie");
            String nom=rs.getString("nom");
            float prix=rs.getFloat("prix");
            int garantie=rs.getInt("garantie");
            String description=rs.getString("description");
            ServiceCategorie sc= new ServiceCategorie();
            String nomcat="";
              try {
                  nomcat= sc.getNomFromId(idc);
              } catch (SQLException ex) {
                  System.out.println("requete database  nom categorie");
              } 
             dataProduit.add(new Produit(nom,prix,description,garantie,nomcat));
 
        }
        tableViewProduit.setItems(dataProduit);
    } catch (SQLException ex) {
        System.err.println("requete search");    }
    
    }
});
}
   
                 private void initComboBoxCategorie() {
    ObservableList datacat=FXCollections.observableArrayList();
   ComboBoxCategorie.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from categorie");

    rs=pst.executeQuery();
     while (rs.next()) {   
         Categorie c=new Categorie();
         c.setNom(rs.getString("nom"));
         c.setId(rs.getInt("id"));
             datacat.add(c);
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
ComboBoxCategorie.setItems(datacat);

}
            public Produit gettempProduit(TableColumn.CellEditEvent edittedCell) {
        Produit test = tableViewProduit.getSelectionModel().getSelectedItem();        
        return test;
    }
            
          private void RecupererProduit() {
        tableViewProduit.setOnMouseClicked(e -> {
 Produit test = tableViewProduit.getSelectionModel().getSelectedItem();        
        
      ServiceProduit sp=new ServiceProduit(); 
      int Quantite=0;
      //String image="";
            try {
             Produit prod= sp.searchByNomProduit(test.getNom());
             Quantite=prod.getQuantite();
            // image=prod.getImage();
            } catch (SQLException ex) {
                System.out.println("RecupererProduit() get produit  par nom");            }
            TextFieldNomProduit.setText(test.getNom());
           TextFieldGarantieProduit.setText(String.valueOf(test.getGarantie()));
            TextFieldDescProduit.setText(String.valueOf(test.getDescription()));
           TextFieldPrixProduit.setText(String.valueOf(test.getPrix()));
           TextFieldQuantiteProduit.setText(String.valueOf(Quantite));
         ComboBoxCategorie.getSelectionModel().select(test.getCategorie());
        }
        );
    }     
            
       private void loadDataProduit() throws SQLException {
   dataProduit.clear();
         try {
          List<Produit> listp=new ArrayList<>();
          ServiceProduit pr=new ServiceProduit();
          ServicePromotion spromo= new ServicePromotion();
          listp=pr.sortedbyId();
        
        listp.forEach((k)->{
            
            float prix;
        if(k.getEtatPromo()!=0)
        {float x=0;
                try {
                    x = spromo.PrixPromo(k.getId());
                } catch (SQLException ex) {
                    System.out.println("Controllers.GestionsProduitsController.loadDataProduit()/prix promo");                }
            prix=x;
        }else{prix=k.getPrix();}
        ServiceCategorie sc= new ServiceCategorie();
        String nomcat="";
              try {
                  nomcat= sc.getNomFromId(k.getCategorie().getId());
                  k.getCategorie().setNom(nomcat);
              } catch (SQLException ex) {
                  System.out.println("Controllers.GestionsProduitsController.loadDataProduit()get nom categorie from id");              }
        Produit p=new Produit(k.getNom(),prix, k.getDescription(), k.getGarantie(),nomcat);
        dataProduit.add(k);}); 
        }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
        tableViewProduit.setItems(dataProduit);
    }     
         
          private void afficherProduit(){
             nomProduit.setCellValueFactory(new PropertyValueFactory <>("nom"));
             categorieProduit.setCellValueFactory(new PropertyValueFactory <>("categorie"));
             garantieProduit.setCellValueFactory(new PropertyValueFactory <>("garantie"));
             prixProduit.setCellValueFactory(new PropertyValueFactory <>("prix"));
             descriptionProduit.setCellValueFactory(new PropertyValueFactory <>("description"));
                  
    }
            
      
      
      
    private void initStatProduit()
    {
      piechartdata=FXCollections.observableArrayList();
    try {
        
        pst=con.prepareStatement("select * from categorie");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=con.prepareStatement("SELECT COUNT(*) as countab FROM produit WHERE categorie='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
            //Servicecategory cat=new Servicecategory();
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("countab"));
            piechartdata.add(new PieChart.Data(rs.getString(2),i));
            np.add(i);
            cat.add(rs.getString("nom"));
        }
        }
    } catch (SQLException ex) {
        System.out.println("stat produit");
    }
    
    }
    
    
    
    
    
      private void clearFormProduit()
    {
    TextFieldNomProduit.clear();
    TextFieldGarantieProduit.clear();
    TextFieldDescProduit.clear();
    TextFieldPrixProduit.clear();
    TextFieldQuantiteProduit.clear();
    ComboBoxCategorie.getSelectionModel().select(0);
    imageViewProduit.setImage(null);
    }
    
    
    
     /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
/////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////Partie Promotion//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          ///////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private void ajouterPromotion(ActionEvent event)throws SQLException {
        
        boolean isPourcentageEmpty=Validation.TextFieldvalidation.TextFieldNumber(TextFieldPourcentagePromotion, ErrorPourcentagePromotion, "Pourcentage required");
        boolean isDateDebEmpty=Validation.TextFieldvalidation.isTextFieldNoEmpty(datePickerDateDebPromo, ErrorDateDebPromotion, "date required");
        boolean isDateFinEmpty=Validation.TextFieldvalidation.isTextFieldNoEmpty(datePickerDateFinPromo, ErrorDateFinPromotion, "date required");
        
        
         int pourcentage = Integer.valueOf(TextFieldPourcentagePromotion.getText());
            Date dated=Date.valueOf(datePickerDateDebPromo.getValue());
            Date dateF=Date.valueOf(datePickerDateFinPromo.getValue());  
            String prod= comboBoxProduit.getSelectionModel().getSelectedItem();
        ServicePromotion sp = new ServicePromotion();
        ServiceProduit sprod=new ServiceProduit();
    if(isPourcentageEmpty && isDateDebEmpty && isDateFinEmpty )
            {
            Produit pp=sprod.searchByNomProduit(prod);
            
      
        
        Promotion promo = new Promotion(pourcentage, pp, dateF, dated,  pourcentage);
           
           
        sp.ajouterPromotion(promo);

        System.out.println(prod);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Promotion added");
                alert.showAndWait();
                clearFormPromotion();
                  refresh();
            }
    }

    @FXML
    private void modifierPromotion(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
           Promotion x=gettempPromotion(edittedcell);
           int id=x.getId();
         int pourcentage = Integer.valueOf(TextFieldPourcentagePromotion.getText());
          Date dated=Date.valueOf(datePickerDateDebPromo.getValue());
          Date dateF=Date.valueOf(datePickerDateFinPromo.getValue());  
Produit prod=new Produit();
   //prod= comboBoxProduit.getSelectionModel().getSelectedItem();
        ServicePromotion sp = new ServicePromotion();
        Promotion promo = new Promotion(id,pourcentage, prod, dateF, dated);
            System.out.println(promo);
         sp.updatePromotion(promo);

      
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Promotion added");
                alert.showAndWait();
                clearFormPromotion();
                  refresh();
        
        
    }

    @FXML
    private void activerPromotion(ActionEvent event) throws SQLException {
         TableColumn.CellEditEvent edittedcell = null;
           Promotion x=gettempPromotion(edittedcell);
           int id=x.getId();
           ServicePromotion spromo=new ServicePromotion();
          Promotion p=spromo.ReturnPRomotionById(id);
           if( x.getActive()==0)
           {spromo.ActiverPromotion(id);
           SendMail.sendMail("mohamedkhalil.chakroun@esprit.tn", "Promotionn!!","nouvelle promotion pour l'objet"+p.getProduit().getNom()+"de pourcentage"+p.getPourcentage()+"");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("The Email Has Been Sent ");
        alert.showAndWait();
           }
           else{spromo.DesActiverPromotion(id);}
        
        
        refresh();
        
    }

    @FXML
    private void supprimerPromotion(ActionEvent event) throws SQLException {
        
          TableColumn.CellEditEvent edittedcell = null;
            Promotion promo=gettempPromotion(edittedcell);         
            int i=promo.getId();
           ServiceProduit sprod= new ServiceProduit();  
            
            ServicePromotion sp=new ServicePromotion();
                 int idp=sp.ProduitPromo(i);
             sprod.updateEtatPromoProduit(idp,0);
            int s=sp.deletePromotion(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Promotion deleted");
                alert.showAndWait();
          refresh();
        }
        
    }
    
    
    
    
     public Promotion gettempPromotion(TableColumn.CellEditEvent edittedCell) {
        Promotion test = tableViewPromotion.getSelectionModel().getSelectedItem();  
        return test;
    } 
    
     private void clearFormPromotion()
    {
    TextFieldPourcentagePromotion.clear();
    comboBoxProduit.getSelectionModel().select(0);
    datePickerDateDebPromo.getEditor().clear();
     datePickerDateFinPromo.getEditor().clear();
    }

   
    
    private void RecupererPromotion() {
        tableViewPromotion.setOnMouseClicked(e -> {
Promotion test = tableViewPromotion.getSelectionModel().getSelectedItem();     
TextFieldPourcentagePromotion.setText(String.valueOf(test.getPourcentage()));
    datePickerDateDebPromo.getEditor().setText(String.valueOf(test.getDateDebut()));
     datePickerDateFinPromo.getEditor().setText(String.valueOf(test.getDateFin()));
     ServiceProduit sp= new ServiceProduit();
            try {
                Produit p=sp.ProduitReturn(test.getProduit().getId());
                 comboBoxProduit.getSelectionModel().select(test.getProduit().getNom());
            } catch (SQLException ex) {
                System.out.println("Gui.ProduitsController.RecupererPromotion().produitreturn");
            }
        //comboBoxProduit.getSelectionModel().selectFirst();
        }
        );
    }
    
    
      private void initComboBoxProduit() {
    ObservableList datacat=FXCollections.observableArrayList();
   comboBoxProduit.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from produit");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datacat.add(
                     rs.getString("nom"));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
       }
comboBoxProduit.setItems(datacat);

}
    
    private void loadDataPromotion() throws SQLException {
   dataPromotion.clear();
         try {
           pst =con.prepareStatement("Select * from promotion");

    rs=pst.executeQuery();
     while (rs.next()) {     
         
          ServiceProduit sp= new ServiceProduit();
           Produit p = new Produit();
            try {
                p=sp.ProduitReturn(rs.getInt("idProduit"));
                 comboBoxProduit.getSelectionModel().select(p.getNom());
            } catch (SQLException ex) {
                System.out.println("Gui.ProduitsController.RecupererPromotion().produitreturn");
            }
         
         
             dataPromotion.add(new  Promotion(rs.getInt("id"),rs.getInt("pourcentage"),
                     rs.getDate("dateFin"),rs.getDate("dateDebut"), rs.getInt("active"),p));           
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
        tableViewPromotion.setItems(dataPromotion);
    }  
    
   private void afficherPromotion(){
            idPromotion.setCellValueFactory(new PropertyValueFactory <>("id"));
             pourcentagePromotion.setCellValueFactory(new PropertyValueFactory <>("pourcentage"));
             dateDebutPromotion.setCellValueFactory(new PropertyValueFactory <>("dateDebut"));
             dateFinPromotion.setCellValueFactory(new PropertyValueFactory <>("DateFin"));
             prixPromotion.setCellValueFactory(new PropertyValueFactory <>("prix"));
             etatPromotion.setCellValueFactory(new PropertyValueFactory <>("active"));
                  
    } 
    
   
     public void searchPromotion(){
searchPromotionField.setOnKeyReleased(e->{
    if(searchPromotionField.getText().equals("")){
        try {
            loadDataPromotion();
        } catch (SQLException ex) {
            System.out.println("searchProduit/loadDataPromotion/");
        }
    }
    else{
        dataPromotion.clear();
          String sql = "Select * from promotion where pourcentage LIKE '%"+searchPromotionField.getText()+"%'"
                + "UNION Select * from promotion where prix LIKE '%"+searchPromotionField.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        { 
            ServiceProduit sp= new ServiceProduit();
           Produit p = new Produit();
            try {
                p=sp.ProduitReturn(rs.getInt("idProduit"));
                 comboBoxProduit.getSelectionModel().select(p.getNom());
            } catch (SQLException ex) {
                System.out.println("Gui.ProduitsController.RecupererPromotion().produitreturn/searchPromotion");
            }
            dataPromotion.add(new  Promotion(rs.getInt("id"),rs.getInt("pourcentage"),
                     rs.getDate("dateFin"),rs.getDate("dateDebut"), rs.getInt("active"),p));    
 
        }
        tableViewPromotion.setItems(dataPromotion);
    } catch (SQLException ex) {
        System.err.println("requete search");    }
    
    }
});
}

    @FXML
    private void openlistCommande(ActionEvent event) {
        
    }

    @FXML
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

    @FXML
    private void openespece(ActionEvent event) {
    }

    @FXML
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

    @FXML
    private void openListStudent(ActionEvent event) {
        
        createPages();
    }

    @FXML
    private void openlistevents(ActionEvent event) {
    }


    @FXML
    private void logout(ActionEvent event) {
    }
     
     
     
     
     
    
}
