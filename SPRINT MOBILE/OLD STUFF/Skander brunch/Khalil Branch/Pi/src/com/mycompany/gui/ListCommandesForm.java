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

import com.mycompany.entities.Commandes;
import com.mycompany.services.ServiceCommandes;

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

            Container cnt1 = new Container(BoxLayout.y());

            Label lbId = new Label(" iD = " + r.getId());
            Label lbIdu = new Label(" User ID= " + r.getUtilisateur_id());
            Label lbType = new Label(" Type= " + r.getReference());
            Label lbSujet = new Label(" Sujet= " + r.isValider());
            Label lbDate = new Label(" Date= " + r.getDate());
            Label lbEtat = new Label(" Etat= " + r.getCommande());

/*
            Map<String, Object> User = (Map<String, Object>) obj.get("idU");
            float idU = Float.parseFloat(User.get("id").toString());
            r.setIdu((int) idU);*/
            
            
            
            
            
            
            
            
            
            
            
            
            

            //lbDesc.setAutoSizeMode(true);
            SpanLabel lbSeparator = new SpanLabel(" \n ");

            cnt1.addAll(lbId, lbIdu, lbType, lbSujet, lbDate, lbEtat, lbSeparator);

            Container cnt2 = new Container(BoxLayout.x());
            Button btnRemoveReclamation = new Button("Remove Commandes");
            Button btnFindReclamation = new Button("One Commandes");

            // btnFindReclamation.addActionListener(e -> new FindReclamationForm(current, r).show());
            btnRemoveReclamation.addActionListener((e) -> {
                try {
                    Commandes rec = new Commandes(r.getId());
                    if (ServiceCommandes.getInstance().RemoveCommandes(rec)) {
                        Dialog.show("Success", "Reclamation avec ID= " + rec.getId() + " a ete supprimee avec succees", new Command("OK"));
                        new ListCommandesForm(current).show();
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
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_DROP_UP, e -> new HomeFormSKA().show());
            //getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack());

        }

    }

}
