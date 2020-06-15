/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.services.ServiceSaison;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Saison;


/**
 *
 * @author LQss
 */
public class ListSaisonForm extends Form {
    public ListSaisonForm(Form previous) {
        setTitle("List Saison");
        //String Fbs = "Feedbacks= \n";
        for (Saison f : ServiceSaison.getInstance().getAllSaisons()) {
            Container fs1 = new Container( BoxLayout.y());
            Container fs2 = new Container( BoxLayout.y());
            Container fs3 = new Container( BoxLayout.y());
                    Label lnom= new Label(f.getNom());
                    lnom.getAllStyles().setFgColor(0xff000);
                    fs1.add(lnom);
                    Label lperiode= new Label(f.getPeriode());
                    lperiode.getAllStyles().setFgColor(0xff000);
                    fs2.add(lperiode);
                    Label lmode= new Label(f.getMode());
                    lmode.getAllStyles().setFgColor(0xff000);
                    fs3.add(lmode);
       add(fs1);
       add(fs2);
       add(fs3);
        }
        //sp.addAll(ServiceFeedback.getInstance().getAllFeedbacks().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
