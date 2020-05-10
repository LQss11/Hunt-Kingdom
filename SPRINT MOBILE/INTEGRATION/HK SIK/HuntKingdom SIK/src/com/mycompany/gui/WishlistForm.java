/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Whishlist;
import com.mycompany.gui.HomeForm;
import com.mycompany.services.ServiceProduit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author khalil
 */
public class WishlistForm extends Form {

    Form current, details;
    SpanLabel lb;

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form current) {
        this.current = current;
    }

    public WishlistForm() throws InterruptedException {

        current = new Form("My Wishlist", BoxLayout.y());
        current.setScrollableY(true);

        ArrayList<Whishlist> alwl = ServiceProduit.getInstance().getListWishlist();
        ArrayList<Produit> alpr = ServiceProduit.getInstance().getListProduit();

        ServiceProduit sp = new ServiceProduit();
        for (Whishlist w : alwl) {
            for (Produit p : alpr) {

                if (w.getProduit().getId() == p.getId() && w.getUser().getID() == 2) {

                    Container fprod = new Container(BoxLayout.y());
                    Container fprod2 = new Container(BoxLayout.x());
                    Container fButtons = new Container(BoxLayout.x());

                    String path = Statics.Images_Path + "/" + p.getImage();
                    Image setimg = FileEncodedImage.create(path, 120, 120);
                    ImageViewer iv = new ImageViewer(setimg);

                    fprod2.add(iv);
                    Label lnom = new Label(p.getNom());
                    lnom.getAllStyles().setFgColor(0xff000);
                    fprod.add(lnom);

                    Label l1 = new Label("prix :" + p.getPrix());
                    l1.getAllStyles().setFgColor(0xff000);
                    fprod.add(l1);

                    //produit details 
                    //////////
                    fprod2.addPointerPressedListener(e -> {
                        details = new Form(p.getNom(), BoxLayout.y());
                        setimg.scaled(300, 300);
                        ImageViewer ivv = new ImageViewer(setimg);
                        details.add(ivv);
                        Label ldesc = new Label(p.getDescription());
                        ldesc.getAllStyles().setFgColor(0x00002);
                        Label lprix = new Label("prix:" + p.getPrix());
                        lprix.getAllStyles().setFgColor(0x00002);
                        Label lquantite = new Label("In Stock");
                        if (p.getQuantite() == 0) {
                            lquantite.setText("out of Stock");
                        }
                        details.add(ldesc);
                        details.add(lprix);
                        details.add(lquantite);
                        Button bdelete = new Button("Remove from Wishlist");
                        Whishlist newWish = new Whishlist();

                        bdelete.addActionListener(ev -> {

                            ServiceProduit.getInstance().deleteWishlist(w.getId());
                            WishlistForm form;
                            try {
                                form = new WishlistForm();
                                form.setScrollable(true);
                                form.getCurrent().show();
                            } catch (InterruptedException ex) {
                                System.out.println("direction vers page wishlist");
                            }
                        });
                        ShareButton sb = new ShareButton();
                        sb.setText("Share Product");
                        details.add(sb);

                        Image screenshot = Image.createImage(fprod2.getWidth(), fprod2.getHeight());
                        details.revalidate();
                        details.setVisible(true);
                        details.paintComponent(screenshot.getGraphics(), true);

                        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
                        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                        } catch (IOException err) {

                        }
                        sb.setImageToShare(imageFile, Statics.Images_Path + "/whiteHeart.png");

                        details.add(bdelete);

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

            }
        }
        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

        current.show();

    }

}
