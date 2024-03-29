/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.Espece;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceEspece;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author LQss
 */
public class ListEspeceForm extends Form {

    Form current, DetailsEspeceForm;
    SpanLabel lb;
    ArrayList<Espece> rec = ServiceEspece.getInstance().getAllEspeces();

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form curren) {
        this.current = current;
    }

    public ListEspeceForm(Form previous) {
        setTitle("List Espece");

        
        
             TextField tfRech = new TextField("", "Rrchercher par type");
        Button recherche = new Button("Rechercher");
        add(tfRech);
        add(recherche);
        Display(rec);

        recherche.addActionListener((e) -> {
            ArrayList<Espece> newrec = new ArrayList<>();
            for (Espece r : rec) {

                if (r.getNomEspece().toLowerCase().indexOf(tfRech.getText().toLowerCase()) != -1) {
                    newrec.add(r);

                }

            }

            revalidate();
            removeAll();
            add(tfRech);
            add(recherche);
            Display(newrec);
        });   
        
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void Display(ArrayList<Espece> r1) {

        for (Espece f : r1) {
            /*
            String Fbs = " nom= " + f.getNomEspece() + " Description= " + f.getDescriptionEspece()+ " poids= " + f.getPoids()+ " type= " + f.getType()+ "\n";
            String Fbc = " zone= " + f.getZone()+ " ville= " + f.getVille()+ "\n";
            Label sp = new Label(Fbs);
            Label se = new Label(Fbc);
            
            add(sp);
             */
            Container fprod = new Container(BoxLayout.y());
            Container fprod2 = new Container(BoxLayout.x());
            Container fButtons = new Container(BoxLayout.x());

          EncodedImage enc = null;
            try {
                enc = EncodedImage.create("/anonimo.jpg");
            } catch (IOException ex) {
            }

            String url = Statics.SYMFONY_URL + f.getImage();
            Image img2 = URLImage.createToStorage(enc, url, url);
            ScaleImageLabel myPic = new ScaleImageLabel();
            myPic.setIcon(img2);
            Dimension d = new Dimension(200, 200);
            myPic.setPreferredSize(d);

            fprod2.add(myPic);
            Label lnom = new Label(f.getNomEspece());
            lnom.getAllStyles().setFgColor(0xff000);
            fprod.add(lnom);

            Label l1 = new Label("type :" + f.getType());
            l1.getAllStyles().setFgColor(0xff000);
            fprod.add(l1);

            Button details = new Button("details");
            details.addActionListener(ev -> {

                DetailsEspeceForm = new Form(f.getNomEspece(), BoxLayout.y());

                ImageViewer ivv = new ImageViewer(img2);

                DetailsEspeceForm.add(img2.scaled(200, 200));
                Label ldesc = new Label(f.getDescriptionEspece());
                ldesc.getAllStyles().setFgColor(0x00002);
                DetailsEspeceForm.add(ldesc);

                Label lpoids = new Label(f.getPoids());
                ldesc.getAllStyles().setFgColor(0x00002);
                DetailsEspeceForm.add(lpoids);
                Label ltype = new Label(f.getType());
                ldesc.getAllStyles().setFgColor(0x00002);
                DetailsEspeceForm.add(ltype);
                Label lzone = new Label(f.getZone());
                ldesc.getAllStyles().setFgColor(0x00002);
                DetailsEspeceForm.add(lzone);
                Label lville = new Label(f.getVille());
                ldesc.getAllStyles().setFgColor(0x00002);
                DetailsEspeceForm.add(lville);

                Button ss = new Button("Faire un screenshot");
                ss.addActionListener(e1 -> {

                    Image screenshot = Image.createImage(DetailsEspeceForm.getWidth(), DetailsEspeceForm.getHeight());
                    DetailsEspeceForm.revalidate();
                    DetailsEspeceForm.setVisible(true);
                    DetailsEspeceForm.paintComponent(screenshot.getGraphics(), true);

                    String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "test.png";
                    try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                        ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                    } catch (IOException err) {
                        Log.e(err);
                    }
                });
                DetailsEspeceForm.add(ss);

                DetailsEspeceForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().getF().show());

                DetailsEspeceForm.show();
                //DetailsEspeceForm.getToolbar().addCommandToLeftBar("back",null, ev->{
                //current.show();
                //});
                DetailsEspeceForm.show();
            });
            fprod.add(details);

            //details Espece//
            fprod2.addPointerPressedListener(e -> {

            });

            fprod.add(fButtons);

            fprod2.add(fprod);

            add(fprod2);
            Label lempty = new Label("");
            lempty.setHeight(10);
            add(lempty);
            //sp.addAll(ServiceFeedback.getInstance().getAllFeedbacks().toString());

        }
    }

}
