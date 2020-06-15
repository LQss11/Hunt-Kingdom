/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.codename1.capture.Capture;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ToastBar;
import com.mycompany.services.ServiceEspece;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
//import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.Espece;
import com.mycompany.entities.Saison;
import com.mycompany.services.ServiceSaison;

/**
 *
 * @author bhk
 */
public class AddEspeceForm extends Form {

    public AddEspeceForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
         */
        setTitle("Add a new Espece");
        setLayout(BoxLayout.y());

        TextField nom = new TextField("", "nomEspece");
        TextField description = new TextField("", "description");
        TextField image = new TextField("", "image");
        TextField poids = new TextField("", "poids");
        TextField type = new TextField("", "type");
        TextField zone = new TextField("", "zone");
        TextField ville = new TextField("", "ville");

        Validator valNumber = new Validator();
        RegexConstraint numConstraint = new RegexConstraint("\\d+","");
        valNumber.addConstraint(poids, numConstraint);

        ComboBox<String> cbsaison = new ComboBox<String>();
        for (Saison f : ServiceSaison.getInstance().getAllSaisons()) {
            cbsaison.addItem(f.getNom());
        }

        Button btnValider = new Button("Add Espece");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length() == 0) || (description.getText().length() == 0) || (poids.getText().length() == 0) || (type.getText().length() == 0) || (zone.getText().length() == 0) || (ville.getText().length() == 0) || (image.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                    System.out.println("erreur");
                }else if (!valNumber.isValid()) {
                    ToastBar.showErrorMessage("Wrong number form");
                } else {
                    try {
                        //public Espece(String nomEspece, String descriptionEspece, String poids, String type, String zone, String ville, int idS) {

                        Espece t = new Espece(nom.getText(), description.getText(), poids.getText(), type.getText(), zone.getText(), ville.getText(), cbsaison.getSelectedItem());
                        String picture = Capture.capturePhoto(1024, -1);
                        FileEncodedImage setimg = FileEncodedImage.create(picture, 120, 120);
                        if (ServiceEspece.getInstance().addEspece(t, setimg.getImageData())) {
                            Dialog.show("Success", "Connection accepted", "OK", null);

                        } else {
                            Dialog.show("ERROR", "Server error", "OK", null);
                            System.out.println("erreur");
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "OK", null);
                        System.out.println("erreur");
                    }

                }

            }
        });

        // addAll(nom,description,image,poids,type,zone,ville, btnValider);
        add(nom);
        add(description);
        //add(image);
        add(poids);
        add(type);
        add(zone);
        add(ville);
        add(cbsaison);
        add(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("nouvelle espece ajouté");
        n.setAlertTitle("nouvelle");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
        );
    }

}
