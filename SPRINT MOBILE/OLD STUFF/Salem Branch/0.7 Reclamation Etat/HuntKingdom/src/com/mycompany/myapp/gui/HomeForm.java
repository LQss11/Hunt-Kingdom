/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

public class HomeForm extends Form {

    Form current;
    Preferences UserId = null;
    private Image img2;
    EncodedImage enc;

    public HomeForm() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));

        try {
            enc = EncodedImage.create("/anonimo.jpg");
        } catch (IOException ex) {
        }

        Preferences UserId = null;
        String url = Statics.ROJECT_UPLOADS_URL  + UserId.get("UserImg", null);

        img2 = URLImage.createToStorage(enc, url, url);
        ScaleImageLabel myPic = new ScaleImageLabel();
        myPic.setIcon(img2);
        Dimension d = new Dimension(100, 100);
        myPic.setPreferredSize(d);

        SpanLabel sp = new SpanLabel("\n");

        String Role = UserId.get("UserRole", null);
        if (Role.equals("ROLE_ADMIN")) {
//------------------------------------------------BACK------------------------------------------------\\

            Button btnListFeedback = new Button("List Feedbacks");
            Button btnListReclamation = new Button("List Reclamations");

            btnListFeedback.addActionListener(e -> new ListFeedbackForm(current).show());
            btnListReclamation.addActionListener(e -> new ListReclamationForm(current).show());

            addAll(myPic, sp, btnListFeedback, btnListReclamation);

        } else if (Role.equals("ROLE_CLIENT")) {
//------------------------------------------------FRONT------------------------------------------------\\
            Button btnAddFeedback = new Button("Add Feedback");
            Button btnListOwnReclamation = new Button("List Own Reclamations");
            Button btnAddReclamation = new Button("Add Reclamation");

            btnAddFeedback.addActionListener(e -> new AddFeedbackForm(current).show());
            btnListOwnReclamation.addActionListener(e -> new ListOwnReclamationForm(current).show());
            btnAddReclamation.addActionListener(e -> new AddReclamationForm(current).show());

            addAll(myPic, sp, btnAddFeedback, btnListOwnReclamation, btnAddReclamation);

        }

        getToolbar().addMaterialCommandToLeftBar("Sign Out", FontImage.MATERIAL_ARROW_DROP_UP, e -> {
            UserId.delete("UserId");

            new LoginForm().show();
        });

    }

}
