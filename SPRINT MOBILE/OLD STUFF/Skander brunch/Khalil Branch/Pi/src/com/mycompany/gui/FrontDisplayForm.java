/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceProduit;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
//import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Whishlist;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Promotion;
import com.mycompany.entities.Utilisateurs;
import com.mycompany.services.ServiceProduit;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.web.WebView;

/**
 *
 * @author khalil
 */
public class FrontDisplayForm extends Form{
    Form current,details;
    SpanLabel lb;

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form current) {
        this.current = current;
    }

   
    
     public FrontDisplayForm() throws InterruptedException {
         
         
          current = new Form("Produits", BoxLayout.y());
          current.setScrollableY(true);
    
          ServiceProduit sp=new ServiceProduit();
          for(Produit p :sp.getListProduit()){
              
              
                Container fprod = new Container( BoxLayout.y());
                Container fprod2= new Container( BoxLayout.x());
                Container fButtons= new Container( BoxLayout.x());
                
                    String path = "file://C:/wamp64/www/images/" + p.getImage();
                    Image setimg = FileEncodedImage.create(path,120, 120);
                    ImageViewer iv = new ImageViewer(setimg);
                
                    fprod2.add(iv);
                    Label lnom= new Label(p.getNom());
                    lnom.getAllStyles().setFgColor(0xff000);
                    fprod.add(lnom);

                    Label l1 = new Label("prix :"+p.getPrix());
                    l1.getAllStyles().setFgColor(0xff000);
                    fprod.add(l1);

                                                      //produit details 
                                                        //////////
                                                       
                                                fprod2.addPointerPressedListener(e->{
                                                details=new Form(p.getNom(),BoxLayout.y());
                                                setimg.scaled(300, 300);
                                                ImageViewer ivv = new ImageViewer(setimg);
                                                    details.add(ivv);
                                                Label ldesc= new Label(p.getDescription());
                                                ldesc.getAllStyles().setFgColor(0x00002);                                                      
                                                Label lprix= new Label("prix:"+p.getPrix());
                                                lprix.getAllStyles().setFgColor(0x00002);    
                                                    details.add(ldesc);
                                                    details.add(lprix);    
                                                    
                                                    Image imwish = FileEncodedImage.create("file://C:/wamp64/www/images/whiteHeart.png",35,35);                                                 
                                                    Button bdetails=new Button("In Wishlist");
                                                     for (Whishlist w:sp.getListWishlist())
                                                    {if (w.getProduit().getId()==p.getId() && w.getUser().getID()==2)
                                                    {bdetails.setText("In Wishlist");
                                                        break;}
                                                    else{
                                                        bdetails.setText("Add to wishlist");
                                                     Whishlist newWish= new Whishlist();
                                          Produit np=new Produit();    np.setId(p.getId());   newWish.setProduit(np);
                                          Utilisateurs nu= new Utilisateurs();nu.setID(2);    newWish.setUser(nu);
                                                    bdetails.addActionListener(ev->{
                                                   
                                                        sp.ajoutWishlist(newWish);
                                                    });
                                                    } }
                                                     
                                                    details.add(bdetails);
     
                                                 details.show();
                                                details.getToolbar().addCommandToLeftBar("back",null, ev->{
                                                current.show();
                                                    });

                                                });

            fprod.add(fButtons);

          fprod2.add(fprod);                      
                                

current.add(fprod2);
Label lempty = new Label("");
lempty.setHeight(10);
current.add(lempty) ;           
             
              
          }
               current.getToolbar().addCommandToRightBar("Home", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
          
          current.show();
          
          
          

    }

    
}
