/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.back;
import com.mycompany.entities.Commandes;
import com.mycompany.services.ServiceCommandes;
import static com.mycompany.services.ServiceCommandes.back;

/**
 *
 * @author skand
 */
public class ListCommandesForm extends Form {

    Form current;

    public ListCommandesForm(Form previous) {
        current = this;

        setTitle("List Commandes");
        System.out.println(ServiceCommandes.getInstance().getAllCommandes());

        for (Commandes r : ServiceCommandes.getInstance().getAllCommandes()) {
            for (back b : back) {
                Container cnt1 = new Container(BoxLayout.y());

                
                Label lbType = new Label(" Reference = " + r.getReference());
                Label lbSujet = new Label(" Sujet= " + r.isValider());
                Label lbDate = new Label(" Date= " + r.getDate());
                Label nom = new Label(" Product= " + b.getNomProd());
                Label qte = new Label(" qte= " + b.getQuantity());
                Label TOT = new Label(" PRIX Totale = " + b.getPrixTOT());
                

                //lbDesc.setAutoSizeMode(true);
                SpanLabel lbSeparator = new SpanLabel("---------------  \n ");

                cnt1.add(lbType).add(lbSujet).add(lbDate).add(nom).add(qte).add(TOT).add(lbSeparator);

                Container cnt2 = new Container(BoxLayout.x());
                Button RemoveCommandes = new Button("Remove Commandes");
                Button btnFindReclamation = new Button("One Commandes");

                // btnFindReclamation.addActionListener(e -> new FindReclamationForm(current, r).show());
                RemoveCommandes.addActionListener((e) -> {
                    try {
                        Commandes rec = new Commandes(r.getId());
                        if (ServiceCommandes.getInstance().RemoveCommandes(rec)) {
                            Dialog.show("Success", "Reclamation avec ID= " + rec.getId()+ " a ete supprimee avec succees", new Command("OK"));
                            new ListCommandesForm(current).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException err) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                });

                //cnt2.add(btnFindReclamation);

                add(cnt2).add(cnt1);
            }
        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }

}}
