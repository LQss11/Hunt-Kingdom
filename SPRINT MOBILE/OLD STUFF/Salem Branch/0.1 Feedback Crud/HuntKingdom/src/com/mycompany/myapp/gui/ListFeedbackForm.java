/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.myapp.entities.Feedback;
import com.mycompany.myapp.services.ServiceFeedback;

/**
 *
 * @author LQss
 */
public class ListFeedbackForm extends Form {

    public ListFeedbackForm(Form previous) {
        setTitle("List Feedback");
        //String Fbs = "Feedbacks= \n";
        for (Feedback f : ServiceFeedback.getInstance().getAllFeedbacks()) {
            String Fbs = " iD= " + f.getId() + " Date= " + f.getDate() + " Desc= " + f.getDescription() + " Stars= " + f.getRate() + "\n";
            Label sp = new Label(Fbs);
            add(sp);
        }

        //sp.addAll(ServiceFeedback.getInstance().getAllFeedbacks().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
