/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.publication;
import com.mycompany.services.ServicePublication;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class AddpublicationForm extends Form {

    public AddpublicationForm(Form previous) {

        setTitle("Ajouter des publications");
        setLayout(BoxLayout.y());
        

        TextField tfContenu = new TextField("", "Contenu:", 4, TextArea.NUMERIC);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        //Label findImage = new Label();

        System.out.println(tfContenu);
        Button btnValider = new Button("Ajouter");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfContenu.getText().length() == 0)) {

                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        System.out.println(tfContenu);
                        publication f = new publication(tfContenu.getText(), formatter.format(date), "hfjdhfdjfhdfjd", 3);
                        if (ServicePublication.getInstance().addPublication(f)) {
                            Dialog.show("Success", "Connection accepted",  "OK", null);
                            
                            
                            
                            LocalNotification n = new LocalNotification();
                                  n.setId("demo-notification");
                                  n.setAlertBody("It's time to take a break and look at me");
                                  n.setAlertTitle("Break Time!");
                                  n.setAlertSound("/notification_sound_beep-01a.mp3");

                                  Display.getInstance().scheduleLocalNotification(
                                  n,
                                 System.currentTimeMillis() + 10 * 1000, // fire date/time
                                LocalNotification.REPEAT_MINUTE  
                                      );
                            
                            
                            
                        } else {
                           Dialog.show("ERROR", "Server error",  "OK", null);
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number",  "OK", null);
                    }

                }

            }
        });

        add(tfContenu).add( btnValider);
        getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
}
