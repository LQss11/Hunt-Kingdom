/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Feedback;
import com.mycompany.services.ServiceFeedback;

/**
 *
 * @author LQss
 */
public class ListFeedbackForm extends Form {

    public Form current;

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

            cnt1.add(lbId);
            cnt1.add(lbDesc);
            cnt1.add(lbDate);
            cnt1.add(lbRate);
            cnt1.add(lbSeparator);

            Container cnt2 = new Container(BoxLayout.x());
            Button btnRemoveFeedback = new Button("Remove Feedback");
            Button btnFindFeedback = new Button("One Feedback");

            cnt2.add(btnRemoveFeedback);
            cnt2.add(btnFindFeedback);

            btnFindFeedback.addActionListener(e -> new FindFeedbackForm(current, f).show());

            btnRemoveFeedback.addActionListener((e) -> {
                try {
                    Feedback fb = new Feedback(f.getId());
                    if (ServiceFeedback.getInstance().RemoveFeedback(f)) {
                        Dialog.show("Success", "Feedback avec ID= " + f.getId() + " a ete supprimee avec succees", "OK", null);
                        new ListFeedbackForm(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }
            });

            add(cnt2);
            add(cnt1);

        }

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }
}
