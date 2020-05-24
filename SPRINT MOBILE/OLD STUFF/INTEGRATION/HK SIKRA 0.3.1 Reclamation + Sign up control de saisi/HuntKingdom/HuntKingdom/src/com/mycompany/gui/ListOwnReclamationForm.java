/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author LQss
 */
public class ListOwnReclamationForm extends Form {

    Form current;

    public ListOwnReclamationForm(Form previous) {
        current = this;
        Preferences UserId = null;
        String UserSessionId = UserId.get("UserId", null);
        setTitle("List Reclamation");

        for (Reclamation r : ServiceReclamation.getInstance().getAllReclamations()) {

            if (String.valueOf(r.getIdu()).equals(UserSessionId)) {

                Container cnt1 = new Container(BoxLayout.y());

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

                cnt1.add(lbType);
                cnt1.add(lbIdo);
                cnt1.add(lbSujet);
                cnt1.add(lbDesc);
                cnt1.add(lbDate);
                cnt1.add(lbEtat);
                cnt1.add(lbSeparator);

                Container cnt2 = new Container(BoxLayout.x());
                Button btnRemoveReclamation = new Button("Remove Reclamation");
                Button btnFindReclamation = new Button("One Reclamation");

                btnFindReclamation.addActionListener(e -> new FindReclamationForm(current, r).show());

                btnRemoveReclamation.addActionListener((e) -> {
                    try {
                        Reclamation rec = new Reclamation(r.getId());
                        if (ServiceReclamation.getInstance().RemoveReclamation(rec)) {
                            Dialog.show("Success", "Reclamation avec ID= " + rec.getId() + " a ete supprimee avec succees", "OK", null);
                            new ListOwnReclamationForm(current).show();
                        } else {
                            Dialog.show("ERROR", "Server error", "OK", null);
                        }
                    } catch (NumberFormatException err) {
                        Dialog.show("ERROR", "Status must be a number", "OK", null);
                    }
                });

                cnt2.add(btnRemoveReclamation);
                cnt2.add(btnFindReclamation);

                add(cnt2);
                add(cnt1);

            }
        }
        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }
}
