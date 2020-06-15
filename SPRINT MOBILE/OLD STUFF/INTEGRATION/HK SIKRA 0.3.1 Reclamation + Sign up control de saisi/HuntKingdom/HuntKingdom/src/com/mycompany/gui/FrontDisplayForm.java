/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.share.FacebookShare;
import com.codename1.media.Media;
import com.codename1.share.ShareService;
import com.codename1.messaging.Message;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceProduit;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Preferences;
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
import com.codename1.ui.TextField;
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
import com.mycompany.gui.HomeForm;
import com.mycompany.services.ServiceProduit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static javafx.scene.input.KeyCode.R;
import javafx.scene.web.WebView;

/**
 *
 * @author khalil
 */
public class FrontDisplayForm extends Form {

    Form current, details;
    SpanLabel lb;
    public ArrayList<Produit> Produits = new ArrayList<>();

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form current) {
        this.current = current;
    }

    public FrontDisplayForm() throws InterruptedException {

        current = new Form("Produits", BoxLayout.y());
        current.setScrollableY(true);

        Container fsearch = new Container(BoxLayout.x());
        TextField tfsearch = new TextField();
        Button btsearch = new Button("Search");
        fsearch.add(tfsearch).add(btsearch);
        current.add(fsearch);
        ServiceProduit sp = new ServiceProduit();

        Produits = sp.getListProduit();
        Display(Produits);
        btsearch.addActionListener(e -> {

            Produits = sp.getListProduit();
            if (tfsearch.getText() != "") {
                Produits = sp.getSearchListProduit(tfsearch.getText());
                current.revalidate();
                current.removeAll();
                current.add(fsearch);
                Display(Produits);
            } else {
                Produits = sp.getListProduit();
                current.revalidate();
                current.removeAll();
                current.add(fsearch);
                Display(Produits);
            }
        });

    }





    public void Display(ArrayList<Produit> Produits) {
        Preferences UserId = null;
        int iduse = UserId.get("id", 2);

        for (Produit p : Produits) {

            ServiceProduit sp = new ServiceProduit();
            Container fprod = new Container(BoxLayout.y());
            Container fprod2 = new Container(BoxLayout.x());
            Container fButtons = new Container(BoxLayout.x());

            String path = Statics.Images_Path + "/" + p.getImage();
            Image setimg = FileEncodedImage.create(path, 120, 120);
            ImageViewer iv = new ImageViewer(setimg);

            fprod2.add(iv);
            Label lnom = new Label(p.getNom());
            lnom.getAllStyles().setFgColor(0x00000);
            fprod.add(lnom);
lnom.setUIID("Label");
            Label l1 = new Label("prix :" + p.getPrix());
            l1.getAllStyles().setFgColor(0xff000);
            fprod.add(l1);
 l1.setUIID("Label");
            //produit details 
            //////////
            fprod2.addPointerPressedListener(e -> {
                details = new Form(p.getNom(), BoxLayout.y());
                setimg.scaled(300, 300);
                ImageViewer ivv = new ImageViewer(setimg);
                details.add(ivv);
                Label ldesc = new Label(p.getDescription());
                Label lquantite = new Label("In Stock");
                if (p.getQuantite() == 0) {
                    lquantite.setText("out of Stock");
                }
                ldesc.getAllStyles().setFgColor(0x00000);
                Label lprix = new Label("prix:" + p.getPrix());
                lprix.getAllStyles().setFgColor(0xff000);

                details.add(ldesc);
                details.add(lprix);
                details.add(lquantite);

                Button rec = new Button("Reclamer");
                details.add(rec);
                rec.addActionListener(ev -> {
                    new AddReclamationForm(current,p.getId()).show();

                });

                Image imwish = FileEncodedImage.create(Statics.ROJECT_UPLOADS_URL + "/whiteHeart.png", 35, 35);
                Button bdetails = new Button("In Wishlist");
                bdetails.setText("Add to wishlist");
                Whishlist newWish = new Whishlist();
                Produit np = new Produit();
                np.setId(p.getId());
                newWish.setProduit(np);
                Utilisateurs nu = new Utilisateurs();
                nu.setID(2);
                newWish.setUser(nu);
                bdetails.addActionListener(ev -> {
                    sp.ajoutWishlist(newWish);



                                                                LocalNotification n = new LocalNotification();
                                                                n.setId("notif");
                                                                n.setAlertBody("Produit ajoutee a votre wishlist");
                                                                n.setAlertTitle("Wishlist!");
                                                                n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
                                                                Display.getInstance().scheduleLocalNotification(
                                                                        n,
                                                                        System.currentTimeMillis() + 100,
                                                                        LocalNotification.REPEAT_NONE // Whether to repeat and what frequency
                                                                );
                                                                localNotificationReceived(n.getId());

                  });

                for (Whishlist w : sp.getListWishlist()) {
                    if (w.getProduit().getId() == p.getId() && w.getUser().getID() == iduse) {
                        bdetails.setText("In Wishlist");

                        /*    bdetails.removeActionListener(ev->{                                                  
                                                        sp.ajoutWishlist(newWish);
                                                    });
                         */
                        bdetails.addActionListener(ev -> {
                            WishlistForm form;
                            try {
                                form = new WishlistForm();
                                form.setScrollable(true);
                                form.getCurrent().show();
                            } catch (InterruptedException ex) {
                                System.out.println("direction vers page wishlist"
                                        + "");
                            }

                        });
                        break;
                    } else {

                    }
                }

                ShareButton sb = new ShareButton();
                sb.setText("Share Product");
                //details.add(sb);

                Image screenshot = Image.createImage(fprod2.getWidth(), fprod2.getHeight());
                details.revalidate();
                details.setVisible(true);
                details.paintComponent(screenshot.getGraphics(), true);

                String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                } catch (IOException err) {

                }
                sb.setImageToShare(imageFile, Statics.ROJECT_UPLOADS_URL + "/whiteHeart.png");

                details.add(bdetails);

                details.show();
                details.getToolbar().addCommandToLeftBar("back", null, ev -> {
                    current.show();
                });

            });

            fprod.add(fButtons);

            fprod2.add(fprod);

            current.add(fprod2);
            Label lempty = new Label("");
            lempty.setHeight(10);
            current.add(lempty);

        }
        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

        current.show();

    }

    private void localNotificationReceived(String id) {
        System.out.println("Received local notification " + id);
    }
}
