/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.services.ServiceEspece;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.Espece;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author LQss
 */
public class ListEspeceForm extends Form {

    Form current, DetailsEspeceForm;
    SpanLabel lb;

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form curren) {
        this.current = current;
    }

    public ListEspeceForm(Form previous) {
        setTitle("List Espece");
        //String Fbs = "Feedbacks= \n";
        for (Espece f : ServiceEspece.getInstance().getAllEspeces()) {
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

            String path = Statics.Images_Path + "/" + f.getImage();
            Image setimg = FileEncodedImage.create(path, 120, 120);
            ImageViewer iv = new ImageViewer(setimg);

            fprod2.add(iv);
            Label lnom = new Label(f.getNomEspece());
            lnom.getAllStyles().setFgColor(0xff000);
            fprod.add(lnom);

            Label l1 = new Label("type :" + f.getType());
            l1.getAllStyles().setFgColor(0xff000);
            fprod.add(l1);

            Button details = new Button("details");
            details.addActionListener(ev -> {

                DetailsEspeceForm = new Form(f.getNomEspece(), BoxLayout.y());

                ImageViewer ivv = new ImageViewer(setimg);

                DetailsEspeceForm.add(ivv);
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
                
                DetailsEspeceForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

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
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
