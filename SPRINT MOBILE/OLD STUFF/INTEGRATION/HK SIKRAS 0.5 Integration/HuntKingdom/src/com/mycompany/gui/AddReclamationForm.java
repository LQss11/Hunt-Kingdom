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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.CalendarForm;
import com.codename1.uikit.pheonixui.InboxForm;
import com.codename1.uikit.pheonixui.StatsForm;
import com.codename1.uikit.pheonixui.TrendingForm;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.io.IOException;
import java.io.InputStream;

public class AddReclamationForm extends Form {

    Form current;

    public AddReclamationForm(Form previous, int objectId) {
        current = this;

        Resources theme = UIManager.initFirstTheme("/theme");

        setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());

        //TextField tfType = new TextField("", "Type Reclamation:");
        String typeRec = "";

        ComboBox<String> tfType = new ComboBox<String>();
        System.out.println("Previous is "+ previous.toString());
        if (previous.toString().substring(0, previous.toString().indexOf("[")).equals("Form")) {
            tfType.addItem("Produit");
            tfType.setSelectedItem("Produit");
            tfType.setEnabled(false);

        } else if (previous.toString().substring(0, previous.toString().indexOf("[")).equals("HomeForm")) {

            tfType.addItem("WebService");
            tfType.addItem("Bug");
            tfType.addItem("Other");
            tfType.setSelectedItem("Other");
            tfType.setEnabled(true);

        }

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
                        Reclamation r = new Reclamation(tfType.getSelectedItem(),objectId, tfSujet.getText(), tfDescription.getText());
            System.out.println("id =" + objectId);

                        if (ServiceReclamation.getInstance().addReclamation(r)) {
                            try {
                                Media m = MediaManager.createMedia("file://home/iPhone.mp3", false);
                                m.getDuration();

                                m.play();

                            } catch (IOException err) {
                                Log.e(err);
                            }
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

        System.out.println("path == " + fs);



        try {
            Util.copy(in, fs.openOutputStream(filePath));
        } catch (IOException ex) {
        }
         */
        //FileSystemStorage fs = FileSystemStorage.getInstance();
        //String recordingsDir = fs.getAppHomePath() + "recordings/";
        //fs.mkdir(recordingsDir);
        //System.out.println("com.mycompany.gui.AddReclamationForm.<init>()" + recordingsDir);
        //String filePath = recordingsDir + "iPhone.mp3";
        //InputStream in = Display.getInstance().getResourceAsStream(Form.class, "/Mario.mp3");
        //InputStream in2 = getData(                Form.class, "/Mario.mp3");
        /*
        playButton.addActionListener((e) -> {
            try {
                Media m = MediaManager.createMedia("file://home/iPhone.mp3", false);
                m.play();
                

            } catch (IOException err) {
                Log.e(err);
            }
        });
         */
        add(tfType);
        add(tfSujet);
        add(tfDescription);
        add(btnValider);
        // add(playButton);

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    }

    protected boolean isCurrentInbox() {
        return false;
    }

    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
