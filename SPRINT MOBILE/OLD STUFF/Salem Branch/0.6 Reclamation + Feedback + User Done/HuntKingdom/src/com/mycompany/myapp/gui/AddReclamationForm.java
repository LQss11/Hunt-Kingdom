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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;

public class AddReclamationForm extends Form {

    public AddReclamationForm(Form previous) {

        Resources theme = UIManager.initFirstTheme("/theme");

        setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());

        TextField tfType = new TextField("", "Type Reclamation:");
        TextField tfSujet = new TextField("", "Sujet Reclamation:");
        TextField tfDescription = new TextField("", "Description Reclamation:");
        //TextField tfEtat = new TextField("", "Etat Reclamation:");

        Button btnValider = new Button("Add Reclamation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0) || (tfSujet.getText().length() == 0) || (tfType.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reclamation r = new Reclamation(tfType.getText(), tfSujet.getText(), tfDescription.getText());
                        if (ServiceReclamation.getInstance().addReclamation(r)) {
                            Dialog.show("Success", "Complaint added successfuly", new Command("OK"));
                            new HomeForm().show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        });

        FileSystemStorage fs = FileSystemStorage.getInstance();
        String recordingsDir = fs.getAppHomePath() + "recordings/";
        fs.mkdir(recordingsDir);
        Button playButton = new Button("play");

        String filePath = recordingsDir + "test.mp3";
        try {
            Util.copy(theme.getData("test.mp3"), fs.openOutputStream(filePath));
        } catch (IOException ex) {
        }

        playButton.addActionListener((e) -> {
            try {
                Media m = MediaManager.createMedia(recordingsDir + "test.mp3", false);
                m.setVolume(10);
                m.play();

            } catch (IOException err) {
                Log.e(err);
            }
        });

        addAll(tfType, tfSujet, tfDescription,  btnValider, playButton);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
