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
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Feedback;
import com.mycompany.services.ServiceFeedback;

public class AddFeedbackForm extends Form {

    Form current;

    public AddFeedbackForm(Form previous) {
        current = this;

        setTitle("Add a new Feedback");
        setLayout(BoxLayout.y());

        TextField tfDescription = new TextField("", "Description Feedback:");
        TextField tfRate = new TextField("", "Rate: ");
        Slider starRank = new Slider();
        
        starRank.setEditable(true);

        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        starRank.setProgress(3);
        
        Button btnValider = new Button("Add Feedback");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        Feedback f = new Feedback(tfDescription.getText(), starRank.getProgress());
                        if (ServiceFeedback.getInstance().addFeedback(f)) {
                            Dialog.show("Success", "Feedback Added", "OK", null);
                            HomeForm h = new HomeForm();
                            h.getF().show();
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
        add(starRank);
        add(btnValider);

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    }

}
