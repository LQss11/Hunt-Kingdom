/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;

    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnListFeedback = new Button("List Feedback");
        Button btnAddFeedback = new Button("Add Feedback");
        Button btnFindFeedback = new Button("One Feedback");
        Button btnUpdateFeedback = new Button("Update Feedback");
        Button btnRemoveFeedback = new Button("Delete Feedback");
        
        btnListFeedback.addActionListener(e -> new ListFeedbackForm(current).show());
        btnAddFeedback.addActionListener(e -> new AddFeedbackForm(current).show());
        btnFindFeedback.addActionListener(e -> new FindFeedbackForm(current).show());
        btnUpdateFeedback.addActionListener(e -> new UpdateFeedbackForm(current).show());
        btnRemoveFeedback.addActionListener(e -> new RemoveFeedbackForm(current).show());

        addAll(btnListFeedback, btnAddFeedback, btnFindFeedback, btnUpdateFeedback, btnRemoveFeedback);

    }

}
