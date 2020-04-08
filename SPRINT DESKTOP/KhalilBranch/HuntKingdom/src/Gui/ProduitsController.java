/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import doryan.windowsnotificationapi.fr.Notification;
import com.jfoenix.controls.JFXButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Connection;
import Utils.DataBase;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import Service.ServicePromotion;
import Service.ServiceWhishlist;
import Service.SendMail;
import static com.qoppa.pdf.b.oc.wb;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import entities.Categorie;
import entities.Whishlist;
import entities.User;
import entities.Promotion;
import entities.Produit;

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
    private ResultSet rs1=null;
    private PreparedStatement pst,pst1;
    
       PieChart piechart1;
    ObservableList<PieChart.Data> piechartdata;
    private final ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
private PieChart pieChart;
    ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();
    
    
    
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
    private ComboBox<String> comboBoxProduit;
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
    
    
    
    
    
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
     private XSSFRow header;
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
    private Pane PaneDashboard;
    @FXML
    private PieChart PieChartCategorie;
    @FXML
    private Label labelDashboard;
    @FXML
    private Label labelProduit;
    @FXML
    private TextField SearchProduitField;
    
        




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
        else if (event.getSource() == labelDashboard)
           {
               PaneDashboard.toFront();
           }
   else if (event.getSource() == labelProduit)
           {
               PaneHeadProduit.toFront();
           }
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
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
    public void refresh() throws SQLException
    {
          initStatProduit();
          PieChartCategorie.setData(piechartdata);
         initComboBoxProduit();
        initComboBoxCategorie();
         loadDataCategorie();
       afficherCategorie();      
        loadDataPromotion();
       afficherPromotion();
        try {
            loadDataProduit();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherProduit();
    
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

    @FXML
    private void showDashboard(MouseEvent event) {
        PaneDashboard.toFront();
    }

    @FXML
    private void showProduits(MouseEvent event) {
        PaneDashboard.toBack();
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
  private void RecupererCategorie() {
        TableViewCategorie.setOnMouseClicked(e -> {
 Categorie test = TableViewCategorie.getSelectionModel().getSelectedItem();               
            textFieldNomCategorie.setText(test.getNom());
            textFieldDescCategorie.setText(String.valueOf(test.getDescription()));
        }
        );
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
          ServicePromotion spromo= new ServicePromotion();
          listp=pr.sortedbyId();
        
        listp.forEach((k)->{
            
            float prix;
        if(k.getEtatPromo()!=0)
        {float x=0;
                try {
                    x = spromo.PrixPromo(k.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            prix=x;
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
    
      
    private void RecupererProduit() {
        tableViewProduit.setOnMouseClicked(e -> {
 Produit test = tableViewProduit.getSelectionModel().getSelectedItem();        
        
      ServiceProduit sp=new ServiceProduit(); 
      int Quantite=0;
      String image="";
            try {
             Produit    prod= sp.searchByNomProduit(test.getNom());
             Quantite=prod.getQuantite();
             image=prod.getImage();
            } catch (SQLException ex) {
                System.out.println("RecupererProduit() get produit  par nom");            }
            TextFieldNomProduit.setText(test.getNom());
           TextFieldGarantieProduit.setText(String.valueOf(test.getGarantie()));
            TextFieldDescProduit.setText(String.valueOf(test.getDescription()));
           TextFieldPrixProduit.setText(String.valueOf(test.getPrix()));
           TextFieldQuantiteProduit.setText(String.valueOf(Quantite));
         
        }
        );
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
            
           public void searchProduit(){
SearchProduitField.setOnKeyReleased(e->{
    if(SearchProduitField.getText().equals("")){
        try {
            loadDataProduit();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
Image image1=null;
             image1=imageViewProduit.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
        ServiceProduit sp = new ServiceProduit();
        
       // c.setNom(nom);
        Produit p= new Produit(c, nom, quantite, prix, description, garantie, photo);
            System.out.println(p);
         sp.ajouterProduit(p);

     //Notification.sendNotification("HuntKingdom"," \n  Product Has been added ." ,TrayIcon.MessageType.WARNING);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit ajoute");
                alert.showAndWait();
                clearFormProduit();
                  refresh();
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

    @FXML
    private void modifierProduit(ActionEvent event) throws SQLException  {
        
        
        
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
              
                         Produit p= new Produit(id,cat, nom, quantite, prix, description, garantie, photo);                            
                             i=sp.updateProduit(p);
       
              
          
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
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }
    }

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
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    catch (IOException ex) {
        Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void ajouterPromotion(ActionEvent event) throws SQLException {
        
         int pourcentage = Integer.valueOf(TextFieldPourcentagePromotion.getText());
            Date dated=Date.valueOf(datePickerDateDebPromo.getValue());
            Date dateF=Date.valueOf(datePickerDateFinPromo.getValue());  
            String prod= comboBoxProduit.getSelectionModel().getSelectedItem();
        ServicePromotion sp = new ServicePromotion();
        ServiceProduit sprod=new ServiceProduit();
    
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

    private void afficherPromotion(){
            idPromotion.setCellValueFactory(new PropertyValueFactory <>("id"));
             pourcentagePromotion.setCellValueFactory(new PropertyValueFactory <>("pourcentage"));
             dateDebutPromotion.setCellValueFactory(new PropertyValueFactory <>("dateDebut"));
             dateFinPromotion.setCellValueFactory(new PropertyValueFactory <>("DateFin"));
             prixPromotion.setCellValueFactory(new PropertyValueFactory <>("prix"));
             etatPromotion.setCellValueFactory(new PropertyValueFactory <>("active"));
                  
    }
    
private void loadDataPromotion() throws SQLException {
   dataPromotion.clear();
         try {
           pst =con.prepareStatement("Select * from promotion");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataPromotion.add(new  Promotion(rs.getInt("id"),rs.getInt("pourcentage"),
                     rs.getDate("dateFin"),rs.getDate("dateDebut"), rs.getInt("active")));           
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
       }
        tableViewPromotion.setItems(dataPromotion);
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
     
     private void RecupererPromotion() {
        tableViewPromotion.setOnMouseClicked(e -> {
Promotion test = tableViewPromotion.getSelectionModel().getSelectedItem();     
TextFieldPourcentagePromotion.setText(String.valueOf(test.getPourcentage()));
    datePickerDateDebPromo.getEditor().setText(String.valueOf(test.getDateDebut()));
     datePickerDateFinPromo.getEditor().setText(String.valueOf(test.getDateFin()));
        
        }
        );
    }
     
     
     
     
     
     
     
     
     
     
     
}
