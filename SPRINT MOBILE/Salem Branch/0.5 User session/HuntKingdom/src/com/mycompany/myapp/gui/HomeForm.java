/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    Preferences UserId = null;

    public HomeForm() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnListFeedback = new Button("List Feedback");
        Button btnAddFeedback = new Button("Add Feedback");
        Button btnListReclamation = new Button("List Reclamation");
        Button btnAddReclamation = new Button("Add Reclamation");
        System.out.println("Logged = " + UserId.get("UserId", null));

        btnListFeedback.addActionListener(e -> new ListFeedbackForm(current).show());
        btnAddFeedback.addActionListener(e -> new AddFeedbackForm(current).show());
        btnListReclamation.addActionListener(e -> new ListReclamationForm(current).show());
        btnAddReclamation.addActionListener(e -> new AddReclamationForm(current).show());
        getToolbar().addMaterialCommandToLeftBar("Sign Out", FontImage.MATERIAL_ARROW_DROP_UP, e -> {
            System.out.println("First = " + UserId.get("UserId", null));
            UserId.delete("UserId");
            System.out.println("After = " + UserId.get("UserId", null));

            new LoginForm().show();
        });

        addAll(btnListFeedback, btnAddFeedback, btnListReclamation, btnAddReclamation);

    }

}
