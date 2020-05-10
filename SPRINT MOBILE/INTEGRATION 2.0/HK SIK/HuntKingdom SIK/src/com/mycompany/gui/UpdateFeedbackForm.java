/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author LQss
 */
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Feedback;
import com.mycompany.services.ServiceFeedback;

public class UpdateFeedbackForm extends Form {

    Form current;

    public UpdateFeedbackForm(Form previous, Feedback fb) {
        current = this;

        setTitle("Update Feedback");
        setLayout(BoxLayout.y());
        TextField tfDescription = new TextField("", "Description Feedback:");

        Button btnValider = new Button("Update");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        Feedback f = new Feedback();

                        if (fb.getDescription().indexOf("*") != -1) {
                            String desc = tfDescription.getText() + fb.getDescription().substring(fb.getDescription().indexOf("*") - 1);
                            f = new Feedback(fb.getId(), desc);

                        } else {
                            f = new Feedback(fb.getId(), tfDescription.getText());
                        }

                        if (ServiceFeedback.getInstance().updateFeedback(f)) {
                            Dialog.show("Success", "Feedback with ID= " + fb.getId() + " sccessfully updated.", "OK", null);
                            new ListFeedbackForm(current).show();

                        } else {
                            Dialog.show("ERROR", "Server error", "OK", null);

                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "OK", null);
                    }

                }

            }
        });

        add(tfDescription);
        add( btnValider);

        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
