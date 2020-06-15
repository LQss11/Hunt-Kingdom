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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

public class UpdateReclamationForm extends Form {

    Form current;

    public UpdateReclamationForm(Form previous, Reclamation rec) {
        current = this;

        setTitle("Update Reclamation");
        setLayout(BoxLayout.y());

        TextField tfDescription = new TextField("", "Description Reclamation:");

        Button btnValider = new Button("Update");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reclamation f = new Reclamation(rec.getId(), tfDescription.getText());
                        if (ServiceReclamation.getInstance().updateReclamation(f)) {
                            Dialog.show("Success", "Reclamation with ID= " + rec.getId() + " sccessfully updated.", new Command("OK"));
                            new ListReclamationForm(current).show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfDescription, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
