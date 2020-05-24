/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
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
            Container cnt = new Container(BoxLayout.y());
            Label lnom = new Label(f.getNom());
            cnt.add(lnom);
            Label lperiode = new Label(f.getPeriode());
            cnt.add(lperiode);
            Label lmode = new Label(f.getMode());
            lmode.getAllStyles().setFgColor(0xff000);
            cnt.add(lmode);
            SpanLabel sp = new SpanLabel("\n");
            cnt.add(sp);
            add(cnt);
        }
        //sp.addAll(ServiceFeedback.getInstance().getAllFeedbacks().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
