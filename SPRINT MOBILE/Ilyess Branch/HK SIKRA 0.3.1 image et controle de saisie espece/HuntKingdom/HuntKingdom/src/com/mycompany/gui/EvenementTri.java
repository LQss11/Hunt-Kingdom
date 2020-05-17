/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Inscription;
import com.mycompany.myapp.MyApplication;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceInscription;
import static java.lang.Float.NaN;

/**
 *
 * @author LQss
 */
public class EvenementTri extends Form {

    Form curren;

    public boolean Exist(int ev, int u) {
        for (Inscription ins : ServiceInscription.getInstance().getAllInscriptions()) {
            if ((ins.getId_event() == ev) && (ins.getId_user() == u)) {
                System.out.println("there");
                return true;
            }
        }
        return false;
    }

    public Evenement Existev(int ev) {
        for (Evenement ins : ServiceEvenement.getInstance().getAllEvenements()) {
            if (ins.getId() == ev) {
                return ins;
            }
        }
        return null;
    }

    public EvenementTri() {
        //current = this;

        setTitle("List Evenement");
        Button btnSinscrit = new Button("Chercher Par ID");
        TextField IDEvent = new TextField("", "ID");
        btnSinscrit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (Existev(Integer.parseInt(IDEvent.getText())) != null) {
                        new ListSearchedEvenetForm(Existev(Integer.parseInt(IDEvent.getText()))).show();
                    } else {
                        Dialog.show("Success", "Desn  t exist", "OK", null);
                    }
                } catch (NumberFormatException numberFormatException) {
                    Dialog.show("Error", "Must be a number", "OK", null);
                }
            }
        });
        add(IDEvent);
        add(btnSinscrit);

        setLayout(BoxLayout.y());
        for (Evenement f : ServiceEvenement.getInstance().getAllEvenementstri()) {

            String path = "file://C:/wamp64/www/images/" + f.getImage();
            Image setimg = FileEncodedImage.create(path, 400, 400);
            ImageViewer iv = new ImageViewer(setimg);

            String Fbs = " iD:" + f.getId() + "\n" + " Nom= " + f.getNom() + "\n" + " Type= " + f.getType() + "\n" + " nbrPlace= "
                    + f.getNbrplace() + "\n" + " Date= " + f.getDate() + "\n" + " place= " + f.getPlace() + "\n" + " description= " + f.getDescription() + "\n" + " duree= " + f.getDuree() + "\n";
            String name = "Vous etes inscrit a l'evenement " + f.getNom();
            SpanLabel sp = new SpanLabel(Fbs);
            //add(sp);
            if (Exist(f.getId(), 1) == true) {
                Button btnAinscrit = new Button("Annuler L'inscription");
                btnAinscrit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Success", "Must be a number", "OK", null);
                        ServiceInscription.getInstance().AInscrire(f.getId(), 1);
                        new HomeForm().show();
                    }
                });
                add(iv);
                add(sp);
                add(btnAinscrit);

            } else {
                Button btninscrit = new Button("S'Inscrire");
                add(iv);
                add(sp);
                add(btninscrit);

                btninscrit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Success", name, "OK", null);
                        ServiceInscription.getInstance().Inscrire(f.getId(), 1);
                        new HomeForm().show();

                    }
                });
            }
        }

        getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    }
}
