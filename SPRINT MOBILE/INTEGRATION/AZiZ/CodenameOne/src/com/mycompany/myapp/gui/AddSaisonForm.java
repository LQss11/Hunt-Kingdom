/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.mycompany.myapp.entities.Saison;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceSaison;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author bhk
 */
public class AddSaisonForm extends Form {

    public AddSaisonForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
         */
        setTitle("Add a new Saison");
        setLayout(BoxLayout.y());

        TextField tfNom = new TextField("", "NOM SAISON:");
        TextField tfPeriode = new TextField("", "PERIODE: ");
        TextField tfMode = new TextField("", "MODE:");

        Button btnValider = new Button("Add Saison");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfPeriode.getText().length() == 0) || (tfMode.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Saison t = new Saison(tfNom.getText(), tfPeriode.getText(),tfMode.getText());
                        if (ServiceSaison.getInstance().addSaison(t)) {
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

        addAll(tfNom, tfPeriode, tfMode , btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
