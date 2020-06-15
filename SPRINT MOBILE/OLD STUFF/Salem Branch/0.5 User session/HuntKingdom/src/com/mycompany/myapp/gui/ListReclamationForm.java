/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;


/**
 *
 * @author LQss
 */
public class ListReclamationForm extends Form {

    Form current;

    public ListReclamationForm(Form previous) {
        current = this;

        setTitle("List Reclamation");

        for (Reclamation r : ServiceReclamation.getInstance().getAllReclamations()) {

            Container cnt1 = new Container(BoxLayout.y());

            Label lbId = new Label(" iD= " + r.getId());
            Label lbIdu = new Label(" User ID= " + r.getIdu());
            Label lbType = new Label(" Type= " + r.getType());
            Label lbIdo = new Label(" Id Objet= " + r.getIdo());
            Label lbSujet = new Label(" Sujet= " + r.getSujet());
            SpanLabel lbDesc = new SpanLabel(" Description= " + r.getDescription());
            Label lbDate = new Label(" Date= " + r.getDate());
            Label lbEtat = new Label(" Etat= " + r.getEtat());


            if (lbDesc.getText().indexOf("*") != -1) {
                lbDesc.setText(lbDesc.getText().substring(0, lbDesc.getText().indexOf("*")));
            }

            //lbDesc.setAutoSizeMode(true);
            SpanLabel lbSeparator = new SpanLabel(" \n ");

            cnt1.addAll(lbId, lbIdu, lbType, lbIdo, lbSujet, lbDesc, lbDate, lbEtat, lbSeparator);

            Container cnt2 = new Container(BoxLayout.x());
            Button btnRemoveReclamation = new Button("Remove Reclamation");
            Button btnFindReclamation = new Button("One Reclamation");

            btnFindReclamation.addActionListener(e -> new FindReclamationForm(current, r).show());

            btnRemoveReclamation.addActionListener((e) -> {
                try {
                    Reclamation rec = new Reclamation(r.getId());
                    if (ServiceReclamation.getInstance().RemoveReclamation(rec)) {
                        Dialog.show("Success", "Reclamation avec ID= " + rec.getId() + " a ete supprimee avec succees", new Command("OK"));
                        new ListReclamationForm(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            });

            cnt2.addAll(btnRemoveReclamation, btnFindReclamation);

            addAll(cnt2, cnt1);
        }
        if (previous.getTitle() == "Home") {
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        } else {
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_DROP_UP, e -> new HomeForm().show());
            //getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack());

        }

    }
}
