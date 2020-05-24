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
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Evenement;
import com.mycompany.myapp.MyApplication;
import com.mycompany.services.ServiceInscription;
import com.mycompany.utils.Statics;
import java.io.IOException;

/**
 *
 * @author LQss
 */
public class ListSearchedEvenetForm extends Form {

    Form current;

    ListSearchedEvenetForm(Evenement f) {
        current = this;

        setTitle("Evenement " + f.getNom());
        setLayout(BoxLayout.y());

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

        Container sp = new Container(BoxLayout.y());
        Label lbId = new Label("ID = " + f.getId());
        Label lbNom = new Label("Nom = " + f.getNom());
        Label lbType = new Label("Type = " + f.getType());
        Label lbnbr = new Label("nbr de places = " + f.getNbrplace());
        Label lbDate = new Label("Date = " + f.getDate());
        Label lbPlace = new Label("Place = " + f.getPlace());
        SpanLabel lbDesc = new SpanLabel("ID = " + f.getDescription());
        Label lbDuree = new Label("Duree= " + f.getDuree());
        sp.add(lbId).add(lbNom).add(lbType).add(lbnbr).add(lbDate).add(lbPlace).add(lbDesc).add(lbDuree);
        if (new ListEvenementForm(null).Exist(f.getId(), SignInForm.US.getId()) == true) {
            Button btnAinscrit = new Button("Annuler L'inscription");
            btnAinscrit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceInscription.getInstance().AInscrire(f.getId());
                    Dialog.show("Success", "Inscription Annuler", "OK", null);

                    new ListSearchedEvenetForm(f).show();

                }
            });
            add(myPic);
            add(sp);
            add(btnAinscrit);
        } else {
            Button btninscrit = new Button("S'Inscrire");
            add(myPic);
            add(sp);

            add(btninscrit);

            btninscrit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceInscription.getInstance().Inscrire(f.getId());
                    Dialog.show("Success", "merci pour l'inscription", "OK", null);

                    new ListSearchedEvenetForm(f).show();

                }
            });
        }

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }
}
