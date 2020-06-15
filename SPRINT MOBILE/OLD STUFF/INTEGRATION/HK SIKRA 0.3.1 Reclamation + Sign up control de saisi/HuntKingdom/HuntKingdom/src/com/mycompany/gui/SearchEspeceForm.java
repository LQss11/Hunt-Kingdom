/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Espece;
import com.mycompany.services.ServiceEspece;


/**
 *
 * @author LQss
 */

public class SearchEspeceForm extends Form {
    public SearchEspeceForm(Form previous) {
        setTitle("Search Espece");
        Espece e = new Espece();
        String s="";
        //String Fbs = "Feedbacks= \n";
        TextField nom = new TextField("","nomEspece");
        Button btnValider = new Button("Search");
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                for (Espece f : ServiceEspece.getInstance().getEspece(nom.getText())) {
            String Fbs = " nom= " + f.getNomEspece() + " Description= " + f.getDescriptionEspece()+ " poids= " + f.getPoids()+ " type= " + f.getType()+ "\n";
            String Fbc = " zone= " + f.getZone()+ " ville= " + f.getVille()+ "\n";
            Label sp = new Label(Fbs);
            Label se = new Label(Fbc);
            
            add(sp);
                
        }
            } 
         });
         
         add(nom);
         add(btnValider);
        //sp.addAll(ServiceFeedback.getInstance().getAllFeedbacks().toString());
       
    
}
}