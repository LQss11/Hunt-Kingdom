/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author LQss
 */
public class ListReclamationForm extends Form {

    Form current;
    ArrayList<Reclamation> rec = ServiceReclamation.getInstance().getAllReclamations();

    public ListReclamationForm(Form previous) {
        current = this;

        setTitle("List Reclamation");
        TextField tfRech = new TextField("", "Rrchercher par type");
        Button recherche = new Button("Rechercher");
        add(tfRech);
        add(recherche);

        Display(rec);

        recherche.addActionListener((e) -> {
            ArrayList<Reclamation> newrec = new ArrayList<>();
            for (Reclamation r : rec) {

                if (r.getType().toLowerCase().indexOf(tfRech.getText().toLowerCase()) != -1) {
                    newrec.add(r);

                }

            }

            revalidate();
            removeAll();
            add(tfRech);
            add(recherche);
            Display(newrec);
        });

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }

    public void Display(ArrayList<Reclamation> r1) {

        for (Reclamation r : r1) {

            Container cnt1 = new Container(BoxLayout.y());

            Label lbId = new Label(" Username = " + r.getUsername());
            Label lbType = new Label(" Type= " + r.getType());
            //Label lbIdo = new Label(" Id Objet= " + r.getIdo());
            Label lbSujet = new Label(" Sujet= " + r.getSujet());
            SpanLabel lbDesc = new SpanLabel(" Description= " + r.getDescription());
            Label lbDate = new Label(" Date= " + r.getDate());
            Label lbEtat = new Label(" Etat= " + r.getEtat());

            if (lbDesc.getText().indexOf("*") != -1) {
                lbDesc.setText(lbDesc.getText().substring(0, lbDesc.getText().indexOf("*")));
            }

            SpanLabel lbSeparator = new SpanLabel(" \n ");

            cnt1.add(lbId);
            cnt1.add(lbType);
            //cnt1.add(lbIdo);
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
                        new ListReclamationForm(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }
            });

            Container EtatButtons = new Container(BoxLayout.x());
            Button btnAccept = new Button("Accepter");
            Button btnRefus = new Button("Refuser");

            btnAccept.addActionListener((e) -> {

                try {

                    Reclamation rec = new Reclamation(r.getId(), r.getDescription(), "Accepted");

                    if (ServiceReclamation.getInstance().updateReclamation(rec)) {
                        Dialog.show("Success", "Reclamation with ID= " + rec.getId() + " Accepted.", "OK", null);

                        new ListReclamationForm(current).show();

                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            });
            btnRefus.addActionListener((e) -> {

                try {

                    Reclamation rec = new Reclamation(r.getId(), r.getDescription(), "Refus");

                    if (ServiceReclamation.getInstance().updateReclamation(rec)) {
                        Dialog.show("Success", "Reclamation with ID= " + rec.getId() + " Accepted.", "OK", null);

                        new ListReclamationForm(current).show();

                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            });
            EtatButtons.add(btnAccept);
            EtatButtons.add(btnRefus);

            cnt2.add(btnRemoveReclamation);
            cnt2.add(btnFindReclamation);

            add(cnt2);
            add(EtatButtons);
            add(cnt1);

        }
    }
}
