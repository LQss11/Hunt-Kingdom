/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;




import com.mycompany.services.ServiceCommandes;
import com.mycompany.entities.Commandes;

/**
 *
 * @author bhk
 */
public class HomeFormSKA extends Form {

    Form current;

    public HomeFormSKA() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
      //  Button btnListFeedback = new Button("List Feedback");
     //   Button btnAddFeedback = new Button("Add Feedback");


        Button btnListCommandes = new Button("List Commandes");
    //    Button btnAddReclamation = new Button("Add Reclamation");
        
        
   

        
      /*  Commandes c1 = new Commandes(3,true, "sdfgdsfgdsf", 555, "looooooooool");
        ServiceCommandes service = new ServiceCommandes() ;
        service.addCommandes(c1);
        */
       
        
        
        
        
       // btnListFeedback.addActionListener(e -> new ListFeedbackForm(current).show());
      //  btnAddFeedback.addActionListener(e -> new AddFeedbackForm(current).show());
        
        btnListCommandes.addActionListener(e -> new ListCommandesForm(current).show());
        //btnAddReclamation.addActionListener(e -> new AddReclamationForm(current).show());

        addAll(btnListCommandes);

    }

}
