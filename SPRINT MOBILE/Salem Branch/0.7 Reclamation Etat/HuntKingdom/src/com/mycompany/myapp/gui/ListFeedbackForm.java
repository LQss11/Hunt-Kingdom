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
import com.mycompany.myapp.entities.Feedback;
import com.mycompany.myapp.services.ServiceFeedback;

/**
 *
 * @author LQss
 */
public class ListFeedbackForm extends Form {

    Form current;

    public ListFeedbackForm(Form previous) {
        current = this;

        setTitle("List Feedback");
        for (Feedback f : ServiceFeedback.getInstance().getAllFeedbacks()) {

            Container cnt1 = new Container(BoxLayout.y());

            Label lbId = new Label(" iD= " + f.getId());

            SpanLabel lbDesc = new SpanLabel(" Desc= " + f.getDescription());
            //lbDesc.setAutoSizeMode(true);
            Label lbDate = new Label(" Date= " + f.getDate());
            Label lbRate = new Label(" Stars= " + f.getRate());
            SpanLabel lbSeparator = new SpanLabel(" \n ");

            cnt1.addAll(lbId, lbDesc, lbDate, lbRate, lbSeparator);

            Container cnt2 = new Container(BoxLayout.x());
            Button btnRemoveFeedback = new Button("Remove Feedback");
            Button btnFindFeedback = new Button("One Feedback");

            cnt2.addAll(btnFindFeedback, btnRemoveFeedback);

            btnFindFeedback.addActionListener(e -> new FindFeedbackForm(current, f).show());

            btnRemoveFeedback.addActionListener((e) -> {
                try {
                    Feedback fb = new Feedback(f.getId());
                    if (ServiceFeedback.getInstance().RemoveFeedback(f)) {
                        Dialog.show("Success", "Feedback avec ID= " + f.getId() + " a ete supprimee avec succees", new Command("OK"));
                        new ListFeedbackForm(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            });

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
