/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
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
import Entities.Categorie;
import Entities.Whishlist;
import Entities.Utilisateurs;
import Entities.Promotion;
import Entities.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Font;
/**
 * FXML Controller class
 *
 * @author khalil
 */
public class ProduitsFrontController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private TilePane tilePane;

    
         private ObservableList<Produit> dataProduit;
private ObservableList<Categorie> dataCategorie;
private ObservableList<Promotion> dataPromotion;

   private Connection con;
    private ResultSet rs=null;
    private ResultSet rs1=null;
    private PreparedStatement pst,pst1;
    
     int count = 0;
    int count1 = 0;
    private int nRows = 3;  //no of row for tile pane
    private int nCols = 4;  // no of column for tile pane
    
    private static final double ELEMENT_SIZE = 200;
    private static final double GAP = ELEMENT_SIZE / 10;
    File filesJpg[];
    @FXML
    private HBox HboxBarre;
    @FXML
    private ComboBox<Categorie> ComboBoxFilterCategorie;
    @FXML
    private Button butonSearchProduit;
    @FXML
    private JFXTextField SearchProduitFront;
    @FXML
    private TilePane tilePane2;
    @FXML
    private ScrollPane PaneProduits;
    @FXML
    private ScrollPane PaneWishlist;
    @FXML
    private JFXButton buttonRetourProduits;
    @FXML
    private JFXButton WishlistBut;
    @FXML
    private JFXButton buttonEvenements;
    @FXML
    private JFXButton buttonEspece;
    @FXML
    private JFXButton buttonProduitsFront;
    @FXML
    private JFXButton buttonReclamations;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con = DataBase.getInstance().getConnection();
      initComboBoxCategorie();
      searchProduit();
        ServiceProduit sp=new ServiceProduit();
          List<Produit> arr=new ArrayList<>();
        try {
            arr=sp.sortedbyId();
        } catch (SQLException ex) {
            System.out.println("Controllers.ProduitsFrontController.initialize() produit sortedById");        }
       if(arr.isEmpty()) {
           System.out.println("pas de produit dans la base");
       }else{
          int x= arr.size();
          nRows=x/4;
          nRows++;
          
          tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
tilePane2.setPrefColumns(nCols);
        tilePane2.setPrefRows(nRows);
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP); 
        tilePane2.setHgap(GAP);
        tilePane2.setVgap(GAP);
        
       createElements(0); 
        
    } 
    createWishElements();
    }   

   

    
    
    
    public VBox createPage(Produit p) {
 String str="file:C://Users/Khalil/Documents/NetBeansProjects/Hunt/src/FXML/images/"+p.getImage();
        //System.out.println(str);
        
       //ImageView iv = new ImageView(getClass().getResource(str).toExternalForm());
        ImageView v = new ImageView();
                 
                v.setImage(new Image(str));
                v.setFitWidth(ELEMENT_SIZE);
                v.setFitHeight(ELEMENT_SIZE);
                
  Label label=new Label();
  label.setText("                 "+p.getNom());
  label.setFont(new Font("Arial", 20)); 
  
  Label labelQuantite=new Label();
  labelQuantite.setTextFill(Color.web("#008000"));
  if(p.getQuantite()>10)
  { labelQuantite.setText("  In Stock");}
  else {
   if(p.getQuantite()==00)
  { labelQuantite.setText("  Out Of Stock");
  labelQuantite.setTextFill(Color.web("#FF0000"));}
   else{labelQuantite.setText(" only "+p.getQuantite()+"Piece");
   labelQuantite.setTextFill(Color.web("#ADFF2F"));
   	}
  }
  labelQuantite.setFont(new Font("Arial", 20));
  
  Label labelPrix=new Label();
  labelPrix.setText("   prix : "+p.getPrix()+"Dinars");
  labelPrix.setFont(new Font("Arial", 18));
      
          
            VBox pageBox = new VBox();
           
            if(p.getEtatPromo()!=0)
            {
                
                Label labelPrix2=new Label();
            labelPrix.setText("Anncien prix : "+p.getPrix()+"Dinars");
  labelPrix.setFont(new Font("Arial", 18));
  labelPrix2.setText("Nouveau prix : "+p.getPromotion().getPrix()+"Dinars");
  labelPrix2.setFont(new Font("Arial", 18));
  Label labelPourcentage=new Label();
  labelPourcentage.setText("   "+p.getPromotion().getPourcentage()+"% de Promotion");
  labelPourcentage.setStyle("-fx-border-color:black;");
  
  v.setFitHeight(170);
            pageBox.getChildren().add(labelPourcentage); 
            pageBox.getChildren().add(v); 
            pageBox.getChildren().add(label); 
            pageBox.getChildren().add(labelQuantite);
            pageBox.getChildren().add(labelPrix);
            pageBox.getChildren().add(labelPrix2);
            
            
            }else{
            pageBox.getChildren().add(v); 
            pageBox.getChildren().add(label); 
            pageBox.getChildren().add(labelQuantite);
             pageBox.getChildren().add(labelPrix);
            }
            
            pageBox.setSpacing(10);
           ImageView ivv=new ImageView();
            String strr="file:C://Users/Khalil/Documents/NetBeansProjects/Hunt/src/FXML/images/hr.png";
           ivv.setImage(new Image(strr));
                ivv.setFitWidth(10);
                ivv.setFitHeight(10);
Button btnWish = new Button("Accept", ivv);
            btnWish.setOnAction((ActionEvent event) -> {
               
                ServiceWhishlist sw= new ServiceWhishlist();
                Utilisateurs u=new Utilisateurs();
                u.setID(1);
                Whishlist w = new Whishlist(p, u);
     try {
         sw.ajouterWishlist(w);
     } catch (SQLException ex) {
         Logger.getLogger(ProduitsFrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
            });
             pageBox.getChildren().add(btnWish);
        return pageBox;
    } 
    
     public VBox createPage2(Produit p) {
 String str="file:C://Users/Khalil/Documents/NetBeansProjects/Hunt/src/FXML/images/"+p.getImage();
        //System.out.println(str);
        
       //ImageView iv = new ImageView(getClass().getResource(str).toExternalForm());
        ImageView v = new ImageView();
                 
                v.setImage(new Image(str));
                v.setFitWidth(ELEMENT_SIZE);
                v.setFitHeight(ELEMENT_SIZE);
                
  Label label=new Label();
  label.setText("                 "+p.getNom());
  label.setFont(new Font("Arial", 20)); 
  
  Label labelQuantite=new Label();
  labelQuantite.setTextFill(Color.web("#008000"));
  if(p.getQuantite()>10)
  { labelQuantite.setText("  In Stock");}
  else {
   if(p.getQuantite()==00)
  { labelQuantite.setText("  Out Of Stock");
  labelQuantite.setTextFill(Color.web("#FF0000"));}
   else{labelQuantite.setText(" only "+p.getQuantite()+"Piece");
   labelQuantite.setTextFill(Color.web("#ADFF2F"));
   	}
  }
  labelQuantite.setFont(new Font("Arial", 20));
  
  Label labelPrix=new Label();
  labelPrix.setText("   prix : "+p.getPrix()+"Dinars");
  labelPrix.setFont(new Font("Arial", 18));
      
          
            VBox pageBox = new VBox();
           
            if(p.getEtatPromo()!=0)
            {
                System.out.println(p.getPromotion());
                Label labelPrix2=new Label();
            labelPrix.setText("Anncien prix : "+p.getPrix()+"Dinars");
  labelPrix.setFont(new Font("Arial", 18));
  labelPrix2.setText("Nouveau prix : "+p.getPromotion().getPrix()+"Dinars");
  labelPrix2.setFont(new Font("Arial", 18));
  Label labelPourcentage=new Label();
  labelPourcentage.setText("   "+p.getPromotion().getPourcentage()+"% de Promotion");
  labelPourcentage.setStyle("-fx-border-color:black;");
  
  v.setFitHeight(170);
            pageBox.getChildren().add(labelPourcentage); 
            pageBox.getChildren().add(v); 
            pageBox.getChildren().add(label); 
            pageBox.getChildren().add(labelQuantite);
            pageBox.getChildren().add(labelPrix);
            pageBox.getChildren().add(labelPrix2);
            
            
            }else{
            pageBox.getChildren().add(v); 
            pageBox.getChildren().add(label); 
            pageBox.getChildren().add(labelQuantite);
             pageBox.getChildren().add(labelPrix);
            }
            
            pageBox.setSpacing(10);
           ImageView ivv=new ImageView();
            String strr="file:C://Users/Khalil/Documents/NetBeansProjects/Hunt/src/FXML/images/hr.png";
           ivv.setImage(new Image(strr));
                ivv.setFitWidth(10);
                ivv.setFitHeight(10);
Button btnWish = new Button("Accept", ivv);
            btnWish.setOnAction((ActionEvent event) -> {
               
                ServiceWhishlist sw= new ServiceWhishlist();
                Utilisateurs u=new Utilisateurs();
                u.setID(1);
                Whishlist w = new Whishlist(p, u);
     try {
         sw.deleteFromWhishlist(1, p.getId());
         createWishElements();
     } catch (SQLException ex) {
         Logger.getLogger(ProduitsFrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
            });
             pageBox.getChildren().add(btnWish);
        return pageBox;
    } 
    
      private void createElements(int methode) {
            tilePane.getChildren().clear();
             ServiceProduit sp=new ServiceProduit();
             ServiceCategorie sc=new ServiceCategorie();
          List<Produit> arr=new ArrayList<>();
                                    if(methode==0){
                                      try {
                                      arr=sp.sortedbyId();
                                           } catch (SQLException ex) {
                                      System.out.println("Controllers.ProduitsFrontController.initialize() produit sortedById");        }
                                                  }
                                    
                                    else{
                                      try {
                                      arr=sc.ShowProducts(methode);
                                           } catch (SQLException ex) {
                                      System.out.println("Controllers.ProduitsFrontController.initialize() produit sortedById");        }
                        }
          int x= arr.size();
            for (int i = 0; i <x; i++) {
             Produit p=  arr.get(count);
                    tilePane.getChildren().add(createPage(p));
                    count++;
                            
                
            }
            count=0;
        }
      
      
    private void createElementsSearch(String str) {
          tilePane.getChildren().clear();
          ServiceProduit sp=new ServiceProduit();
          List<Produit> arr=new ArrayList<>();                                 
                                      try {
                                      arr=sp.searchByNom(str);
                                           } catch (SQLException ex) {
                                      System.out.println("Controllers.ProduitsFrontController.initialize() produit search by nom");        }                                                                                 
          int x= arr.size();
            for (int i = 0; i <x; i++) {
             Produit p=  arr.get(count);
                    tilePane.getChildren().add(createPage(p));
                    count++;                                          
            }
            count=0;
        }
      
      private void initComboBoxCategorie() {
    ObservableList datacat=FXCollections.observableArrayList();
   ComboBoxFilterCategorie.getSelectionModel().clearSelection();
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
ComboBoxFilterCategorie.setItems(datacat);

}
      
        public void searchProduit(){
SearchProduitFront.setOnKeyReleased(e->{
    if(SearchProduitFront.getText().equals("")){
        createElements(0);
    }
    else{
        createElementsSearch(SearchProduitFront.getText());
    }
});
}

    @FXML
    private void searchProd(ActionEvent event) {
        Categorie cat=new Categorie();
    cat= ComboBoxFilterCategorie.getSelectionModel().getSelectedItem();
    createElements(cat.getId());
    }

    @FXML
    private void openWishlist(ActionEvent event) {
         createWishElements();
       PaneWishlist.toFront();
        
    }
   public void createWishElements(){
     tilePane2.getChildren().clear();
     count1=0;
             ServiceWhishlist sw=new ServiceWhishlist();
             ServiceCategorie sc=new ServiceCategorie();
          List<Produit> arr=new ArrayList<>();
                                   
                                      try {
                                      arr=sw.Display(22); 
                                           } catch (SQLException ex) {
                                      System.out.println("Controllers.ProduitsFrontController.initialize() produit diplay");        }
                                                
          int x= arr.size();
            for (int i = 0; i <x; i++) {
             Produit p=  arr.get(count1);
                    tilePane2.getChildren().add(createPage2(p));
                    count1++;
                            
                
            }
            
    
    }  

    @FXML
    private void openProduits(ActionEvent event) {
        PaneProduits.toFront();
    }
    
    
    @FXML
    private void openprofile(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
      @FXML
    private void openhome(ActionEvent event) {
         PaneProduits.toFront();
    } 

    @FXML
    private void openEvenements(ActionEvent event) {
    }

    @FXML
    private void openEspece(ActionEvent event) {
    }

    @FXML
    private void openReclamations(ActionEvent event) {
    }
    
}
