/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commandes;
import com.mycompany.entities.Produit;
import com.mycompany.services.SrProduct;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author takwa
 */
public class Panier {

    Form f;
    Double tot = 0.0;
    public Panier() {
        f = new Form("Panier", BoxLayout.y());
        SrProduct sr = new SrProduct();
        ArrayList<Produit> ls = sr.panier();
        
        for (Produit p : ls) {
            Container c = new Container(BoxLayout.x());
            EncodedImage encImg
                    = EncodedImage.createFromImage(Image.createImage(200, 200, 0xffff0000), false);
            URLImage imgUrl
                    = URLImage.createToStorage(encImg, Statics.SYMFONY_URL + p.getImage(), Statics.SYMFONY_URL + p.getImage(), URLImage.RESIZE_SCALE);

            imgUrl.fetch();
            ImageViewer img = new ImageViewer(imgUrl);

            c.add(img);
            Container c1 = new Container(BoxLayout.y());
            c1.setWidth(300);

            SpanLabel sp = new SpanLabel(p.getNom());
            sp.setSize(new Dimension(50, 20));
          
            Label lb = new Label(""+p.getPrix());
            c1.add(sp);
          c1.add(lb);
            c.add(c1);
            Button btn = new Button("remove");
            btn.setUIID("label");
            btn.addActionListener((evt) -> {
                if (Dialog.show("Confirmation", "vous voulez vraimant Supprimer " + p.getNom(), "Supprimer", "Anuuler")) {

                    sr.removeFromPanier(p);
                    Dialog.show("Information", "Produit supprimer", "ok", null);
                    Panier pn = new Panier();
                    pn.getF().show();
                }
            });
            c.add(btn);
            f.add(c);
            tot += p.getPrix();
        }

        Container ctot = new Container(BoxLayout.x());
        Label lbtot = new Label("Total : ");
        Label aftot = new Label(tot.toString() + " $ ");
        ctot.add(lbtot);
        ctot.add(aftot);
        f.add(ctot);

        Button btnchk = new Button("Passer commande");
        btnchk.addActionListener((evt) -> {
            Adreesse ad = new Adreesse();
            ad.getF().show();
        });

        f.add(btnchk);

        f.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
