/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.myapp.entities.Feedback;
import com.mycompany.myapp.services.ServiceFeedback;

/**
 *
 * @author bhk
 */
public class SignUpForm extends Form {

    public SignUpForm(Form previous) {

        setTitle("Add a new Feedback");
        setLayout(BoxLayout.y());

        TextField tfDescription = new TextField("", "Description Feedback:");
        TextField tfRate = new TextField("", "Rate: ");

        Button btnValider = new Button("Add Feedback");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0) || (tfRate.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Feedback f = new Feedback(tfDescription.getText(), (int) Float.parseFloat(tfRate.getText()));
                        if (ServiceFeedback.getInstance().addFeedback(f)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfDescription, tfRate, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}