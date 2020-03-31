/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import Service.ServicePromotion;
import Service.ServiceWhishlist;
import entities.Categorie;
import entities.Whishlist;
import entities.User;
import entities.Promotion;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import java.sql.Connection;
import Utils.DataBase;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import javafx.scene.input.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.apache.commons.lang3.RandomStringUtils;


/**
 * FXML Controller class
 *
 * @author khalil
 */
public class ProduitsController implements Initializable {

    private ObservableList<Produit> dataProduit;
private ObservableList<Categorie> dataCategorie;
private ObservableList<Promotion> dataPromotion;
   private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
    
    
    
    
     @FXML
    private Pane PaneHeadProduit;

    @FXML
    private Button buttonProduits;

    @FXML
    private Button buttonPromotion;

    @FXML
    private Button buttonCategorie;

    @FXML
    private Pane panePromotion;

    @FXML
    private Pane paneProduits;

    @FXML
    private Pane paneCategorie;
    @FXML
    private TextField textFieldNomCategorie;
    @FXML
    private TextField textFieldDescCategorie;
    @FXML
    private Button buttonSupprimerCategorie;
    @FXML
    private Button buttonImportCategorie;
    @FXML
    private TableView<Categorie> TableViewCategorie;
    @FXML
    private Button buttonModifierCategorie;
    @FXML
    private Button buttonAjoutCategorie;
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
    private TableView<Produit> tableViewProduit;
    @FXML
    private Button buttonAjourProduit;
    @FXML
    private Button buttonSupprimerProduit;
    @FXML
    private Button buttonModifierProduit;
    @FXML
    private Button buttonImporterProduit;
    @FXML
    private TextField TextFieldPourcentagePromotion;
    private TextField TextFieldPrixPromotion;
    @FXML
    private DatePicker datePickerDateDebPromo;
    @FXML
    private DatePicker datePickerDateFinPromo;
    @FXML
    private ComboBox<Produit> comboBoxProduit;
    @FXML
    private TableView<Promotion> tableViewPromotion;
    @FXML
    private Button buttonAjourPromotion;
    @FXML
    private Button buttonSupprimerPromotion;
    @FXML
    private Button buttonModifierPromotion;
    @FXML
    private Button buttonActiverPromotion;
    @FXML
    private TableColumn<Categorie, ?> idCategorie;
    @FXML
    private TableColumn<Categorie, ?> nomCategorie;
    @FXML
    private TableColumn<Categorie, ?> descriptionCategorie;
    @FXML
    private TableColumn<Produit, ?> nomProduit;
    @FXML
    private TableColumn<Produit, ?> categorieProduit;
    @FXML
    private TableColumn<Produit, ?> garantieProduit;
    @FXML
    private TableColumn<Produit, ?> prixProduit;
    @FXML
    private TableColumn<Produit, ?> descriptionProduit;
    @FXML
    private TableColumn<Promotion, ?> idPromotion;
    @FXML
    private TableColumn<Promotion, ?> pourcentagePromotion;
    @FXML
    private TableColumn<Promotion, ?> dateDebutPromotion;
    @FXML
    private TableColumn<Promotion, ?> dateFinPromotion;
    @FXML
    private TableColumn<Promotion, ?> prixPromotion;
    @FXML
    private TableColumn<Promotion, ?> etatPromotion;
    @FXML
    private ImageView ImageViewCategorie;
/////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////the rest//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
    @FXML
    private Button buttonImprimerProduit;
    @FXML
    private ImageView imageViewProduit;
        
    @FXML
    void handleButtonAction(ActionEvent event) {
   if (event.getSource() == buttonProduits)
           {
               paneProduits.toFront();
           } 
                 else if (event.getSource() == buttonPromotion)
           {
               panePromotion.toFront();
           } 
                 else if (event.getSource() == buttonCategorie)
           {
               paneCategorie.toFront();
           }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataBase.getInstance().getConnection();
        dataCategorie= FXCollections.observableArrayList();
        dataProduit= FXCollections.observableArrayList();
        dataPromotion= FXCollections.observableArrayList();  
        
       refresh();
       
    }    
    public void refresh()
    {
         initComboBoxProduit();
        initComboBoxCategorie();
         loadDataCategorie();
       afficherCategorie();      
       
        try {
            loadDataProduit();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherProduit();
    
    }
           public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\Users\\Khalil\\Documents\\NetBeansProjects\\HuntKingdom\\src\\images");
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
          /////////////////////////////Partie Categorie//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
        
    
    
    
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
  
    @FXML
    private void AjouterCategorie(ActionEvent event) throws SQLException {

        // int id = Integer.valueOf(Ab_IdAb.getText());
        //Date dateDebut = Date.valueOf(datePickerDateDebPromo.getValue());
         String nom = textFieldNomCategorie.getText();
          String description = textFieldDescCategorie.getText();
             
Image image1=null;
             image1=ImageViewCategorie.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
        ServiceCategorie sc = new ServiceCategorie();
        Categorie c = new Categorie(nom,description,photo);
            System.out.println(c);
         sc.ajouterCategorie(c);

      
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Categorie added");
                alert.showAndWait();
                clearFormCategorie();
                  refresh();

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

       public Categorie gettempCategorie(TableColumn.CellEditEvent edittedCell) {
        Categorie test = TableViewCategorie.getSelectionModel().getSelectedItem();        
        return test;
    }

      
    @FXML
    private void SupprimerCategorie(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
            Categorie x=gettempCategorie(edittedcell);         
            int i=x.getId();
            ServiceCategorie cat=new ServiceCategorie();
           
           
            
            int s=cat.deletecategory(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Categorie deleted");
                alert.showAndWait();
          refresh();
        }
        
    }

    @FXML
    private void ModifierCategorie(ActionEvent event) throws SQLException {
        
        //boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
    // boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idprod, error_idprod, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nameprod, error_nameprod, "Name is require");
         //boolean isPriceEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_prodprice, error_priceprod, "price is require");
       /**
        * tl
        */  
    

        int i;            
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
   
              ServiceCategorie sc = new ServiceCategorie();
              
              if(photo==null)
              {             Categorie categ = new Categorie(c,nom,description);
                            System.out.println(categ);
                             i=sc.updatecategory(categ);}
              
              else{         Categorie categ = new Categorie(c,nom,description,photo);
                            System.out.println(categ);
                            i=sc.updatecategory(categ);}
              
          
              if(i==1)
        {          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("categorie updated");
                alert.showAndWait();
                clearFormCategorie();
         refresh();
           
        }
          
        
    }
    
    
    private void clearFormCategorie()
    {
    textFieldDescCategorie.clear();
    textFieldNomCategorie.clear();
    ImageViewCategorie.setImage(null);
    
    }
          
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////Partie Produit//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
        
    
    
    
    private void afficherProduit(){
             nomProduit.setCellValueFactory(new PropertyValueFactory <>("nom"));
             categorieProduit.setCellValueFactory(new PropertyValueFactory <>("categorie"));
             garantieProduit.setCellValueFactory(new PropertyValueFactory <>("garantie"));
             prixProduit.setCellValueFactory(new PropertyValueFactory <>("prix"));
             descriptionProduit.setCellValueFactory(new PropertyValueFactory <>("description"));
                  
    }
    
private void loadDataProduit() throws SQLException {
   dataProduit.clear();
         try {
          List<Produit> listp=new ArrayList<>();
          ServiceProduit pr=new ServiceProduit();
          listp=pr.sortedbyId();
        
        listp.forEach((k)->{
            
            float prix;
        if(k.getEtatPromo()!=0)
        {prix=k.getPromotion().getPrix();
        }else{prix=k.getPrix();}
        ServiceCategorie sc= new ServiceCategorie();
        String nomcat="";
              try {
                  nomcat= sc.getNomFromId(k.getCategorie().getId());
                  k.getCategorie().setNom(nomcat);
              } catch (SQLException ex) {
                  Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
              }
        Produit p=new Produit(k.getNom(),prix, k.getDescription(), k.getGarantie(),nomcat);
        dataProduit.add(k);}); 
        }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
        tableViewProduit.setItems(dataProduit);
    }
    
    
    
            public Produit gettempProduit(TableColumn.CellEditEvent edittedCell) {
        Produit test = tableViewProduit.getSelectionModel().getSelectedItem();        
        return test;
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
            
         /*   public void searchProduct(){
prod_search.setOnKeyReleased(e->{
    if(prod_search.getText().equals("")){
        loadDataprod();
    }
    else{
        datap.clear();
          String sql = "Select * from product where productName LIKE '%"+prod_search.getText()+"%'"
                + "UNION Select * from product where productPrice LIKE '%"+prod_search.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
            int idProduct=rs.getInt("idProduct");
         String productName=rs.getString("productName");
         Float productPrice=rs.getFloat("productPrice");
         int idcat=rs.getInt("categoryId");
                        //datap.add(new Produits(idProduct,productName,productPrice,idcat));
                        Servicecategory cat =new Servicecategory();
             datap.add(new Produits(idProduct,productName,productPrice,cat.getNamecatbyId(idcat)));
 
        }
        tab_prod.setItems(datap);
    } catch (SQLException ex) {
        Logger.getLogger(produitsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
}
            
            
            
            
            
            */
            
            
            
            
               @FXML
    private void ajouterProduit(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
          
             
         String nom =  TextFieldNomProduit.getText();
    int garantie=Integer.valueOf(TextFieldGarantieProduit.getText());
    String description = TextFieldDescProduit.getText();
    float prix=Float.valueOf(TextFieldPrixProduit.getText());
    int quantite=Integer.valueOf(TextFieldQuantiteProduit.getText());
    Categorie c=new Categorie();
                   System.out.println("ici");
   c= ComboBoxCategorie.getSelectionModel().getSelectedItem();
    System.out.println("ici");
Image image1=null;
             image1=imageViewProduit.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
        ServiceProduit sp = new ServiceProduit();
        
       // c.setNom(nom);
        Produit p= new Produit(c, nom, quantite, prix, description, garantie, photo);
            System.out.println(p);
         sp.ajouterProduit(p);

     // Notification.sendNotification("HuntKingdom"," \n  Product Has been added ." ,TrayIcon.MessageType.WARNING);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit ajoute");
                alert.showAndWait();
                clearFormProduit();
                  refresh();
    }

    @FXML
    private void supprimerProduit(ActionEvent event) throws SQLException {
        
         TableColumn.CellEditEvent edittedcell = null;
            Produit x=gettempProduit(edittedcell);         
            int i=x.getId();
            ServiceProduit sp=new ServiceProduit();
           
           
            
            int s=sp.deleteProduit(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit deleted");
                alert.showAndWait();
          refresh();
        }
    }

    @FXML
    private void modifierProduit(ActionEvent event) throws SQLException {
        
        
        
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
    
           Image image1=null;
             image1= imageViewProduit.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
   
              ServiceProduit sp = new ServiceProduit();
              
              if(photo==null)
              {            Produit p= new Produit(id,cat, nom, quantite, prix, description, garantie, photo);                            
                             i=sp.updateProduit(p);}
              
              else{         Produit p= new Produit(id,cat, nom, quantite, prix, description, garantie, photo);                         
                             i=sp.updateProduit(p);}
              
          
              if(i==1)
        {          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("produit updated");
                alert.showAndWait();
                clearFormCategorie();
         refresh();
           
        }
    }

    @FXML
    private void imprimerListeProduit(ActionEvent event) {
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
          /////////////////////////////Partie Promotion//////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
          /////////////////////////////////////////////////////////////////////////////////// 
        
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

    @FXML
    private void ajouterPromotion(ActionEvent event) {
    }

    @FXML
    private void supprimerPromotion(ActionEvent event) {
    }

    @FXML
    private void modifierPromotion(ActionEvent event) {
    }

    @FXML
    private void activerPromotion(ActionEvent event) {
    }

    private void initComboBoxProduit() {
    ObservableList datacat=FXCollections.observableArrayList();
   comboBoxProduit.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from produit");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datacat.add(rs.getString("nom"));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
       }
comboBoxProduit.setItems(datacat);

}
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
