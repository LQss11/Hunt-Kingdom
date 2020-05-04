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

/**
 *
 * @author bhk
 */
public class AddReclamationForm extends Form {

    public AddReclamationForm(Form previous) {

        setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());

        TextField tfType = new TextField("", "Type Reclamation:");
        TextField tfSujet = new TextField("", "Sujet Reclamation:");
        TextField tfDescription = new TextField("", "Description Reclamation:");
        TextField tfEtat = new TextField("", "Etat Reclamation:");

        Button btnValider = new Button("Add Reclamation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0) || (tfSujet.getText().length() == 0) || (tfType.getText().length() == 0) || (tfEtat.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        //Reclamation r = new Reclamation(type, sujet, description, etat);

                        Reclamation r = new Reclamation(tfType.getText(), tfSujet.getText(), tfDescription.getText(), tfEtat.getText());
                        if (ServiceReclamation.getInstance().addReclamation(r)) {
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

        addAll(tfType, tfSujet, tfDescription, tfEtat, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
