/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author LQss
 */
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.io.IOException;
import java.io.InputStream;

public class AddReclamationForm extends Form {

    Form current;

    public AddReclamationForm(Form previous) {
        current = this;
        Resources theme = UIManager.initFirstTheme("/theme");

        setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());

        //TextField tfType = new TextField("", "Type Reclamation:");
        String typeRec = "";

        ComboBox<String> tfType = new ComboBox<String>("WebService","Bug","Other") ;
        
        if (previous.toString().substring(0, previous.toString().indexOf("[")).equals("HomeForm") ) {
            tfType.addItem("Home");

            tfType.setSelectedItem("Home");
            tfType.setEnabled(false);
			
			
        }
		
		
        System.out.println("prev == " + previous.toString().substring(0, previous.toString().indexOf("[")));

        TextField tfSujet = new TextField("", "Sujet Reclamation:");
        TextField tfDescription = new TextField("", "Description Reclamation:");
        //TextField tfEtat = new TextField("", "Etat Reclamation:");

        Button btnValider = new Button("Add Reclamation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((tfDescription.getText().length() == 0) || (tfSujet.getText().length() == 0) || (tfType.getSelectedItem().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        Reclamation r = new Reclamation(tfType.getSelectedItem(), tfSujet.getText(), tfDescription.getText());
                        if (ServiceReclamation.getInstance().addReclamation(r)) {
                            Dialog.show("Success", "Complaint added successfuly", "OK", null);
                            HomeForm h = new HomeForm();
                            h.getF().show();
                        } else {
                            Dialog.show("ERROR", "Server error", "OK", null);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        });
        Button playButton = new Button("play");

        /*
        FileSystemStorage fs = FileSystemStorage.getInstance();
        String recordingsDir = fs.getAppHomePath() + "recordings/";
        fs.mkdir(recordingsDir);

        String filePath = recordingsDir + "Mario.mp3";
        System.out.println("path == " + fs);



        try {
            Util.copy(in, fs.openOutputStream(filePath));
        } catch (IOException ex) {
        }
         */
        InputStream in = Display.getInstance().getResourceAsStream(
                Form.class, "/Mario.mp3");

        playButton.addActionListener((e) -> {
            try {
                //Media m = MediaManager.createMedia(recordingsDir + "Mario.mp3", false);
                Media m = MediaManager.createMedia("/Mario.mp3", false);
                m.setVolume(10);
                m.play();

            } catch (IOException err) {
                Log.e(err);
            }
        });

        add(tfType);
        add(tfSujet);
        add(tfDescription);
        add(btnValider);
        add(playButton);

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    }

}
