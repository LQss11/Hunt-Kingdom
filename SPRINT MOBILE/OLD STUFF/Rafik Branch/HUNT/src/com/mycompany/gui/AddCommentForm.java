/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.services.ServiceCommentaire;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author RAFIK
 */
public class AddCommentForm extends Form{
      Form current;
    public AddCommentForm(Form previous, int id) {
            Preferences UserId = null;
        String PublicationID = UserId.get("PubId", null);

         current = this;

        setTitle("Ajouter Commentaire");
        setLayout(BoxLayout.y());

            TextField tfContenu = new TextField("", "write a comment...", 19, TextArea.NUMERIC);
            Container cnt1 = new Container(BoxLayout.y());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
        
            
            Button btnValider = new Button("Ajouter");
            

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfContenu.getText().length() == 0)) {

                   Dialog.show("Alert", "Please fill all the fields",  "OK", null);
                 
                } else {
                    try {
                    
                        if (ServiceCommentaire.getInstance().addCommentaire(tfContenu.getText(),PublicationID)) {
                            Dialog.show("", "bien commenté",  "OK", null);
                        } else {
                            Dialog.show("ERROR", "Server error",  "OK", null);
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number",  "OK", null);
                    }
                }

            }
        });

        add(tfContenu).add(btnValider);
        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   
}
