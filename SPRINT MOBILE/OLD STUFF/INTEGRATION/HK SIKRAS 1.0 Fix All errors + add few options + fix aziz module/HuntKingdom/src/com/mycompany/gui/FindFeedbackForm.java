/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
public class FindFeedbackForm extends Form {

    Form current;

    public FindFeedbackForm(Form previous, Feedback fb) {
        current = this;

        setTitle("Feedback");
        for (Feedback f : ServiceFeedback.getInstance().FindFeedback(fb)) {

            Container cnt1 = new Container(BoxLayout.y());
            Label lbUsername = new Label(" Username = " + f.getUsername());
            SpanLabel lbDesc = new SpanLabel(" Desc= " + f.getDescription());
            Label lbDate = new Label(" Date= " + f.getDate());
            Label lbRate = new Label(" Stars= " + f.getRate());
            Button btnUpdateFeedback = new Button("Update Feedback");
            btnUpdateFeedback.addActionListener(e -> new UpdateFeedbackForm(current, fb).show());

            cnt1.add(lbUsername);
            cnt1.add(lbDesc);
            cnt1.add(lbDate);
            cnt1.add(lbRate);
            //cnt1.add(btnUpdateFeedback);

            add(cnt1);
        }

        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
