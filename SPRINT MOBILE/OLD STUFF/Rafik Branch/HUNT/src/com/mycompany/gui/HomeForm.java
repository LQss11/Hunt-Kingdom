/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.services.ServiceProduit;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;

/**
 *
 * @author khalil
 */
public class HomeForm extends Form {

    Form current;
    Preferences UserId = null;
    private Image img2;
    EncodedImage enc;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    Form f;

    public HomeForm() {
        current = this;

        f = new Form("home", BoxLayout.y());
        setLayout(BoxLayout.y());

        f.setScrollable(true);
        try {
            enc = EncodedImage.create("/anonimo.jpg");
        } catch (IOException ex) {
        }

        Preferences UserId = null;
      String url = Statics.ROJECT_UPLOADS_URL + "/" + SignInForm.US.getImage();
        System.out.println("iage == " + url);

        img2 = URLImage.createToStorage(enc, url, url);
        ScaleImageLabel myPic = new ScaleImageLabel();
        myPic.setIcon(img2);
        Dimension d = new Dimension(200, 200);
        myPic.setPreferredSize(d);

        Image setimg = FileEncodedImage.create(Statics.TEST + SignInForm.US.getImage(), 120, 120);
        ImageViewer iv = new ImageViewer(setimg);

        if (SignInForm.US.getRole().indexOf("ROLE_ADMIN") != -1) {
            f.getToolbar().addCommandToSideMenu("Forum", null, e -> new ForumIndex());

            f.getToolbar().addCommandToSideMenu("Feedback List", null, e -> new ListFeedbackForm(current).show());
            f.getToolbar().addCommandToSideMenu("Reclamation List", null, e -> new ListReclamationForm(current).show());

            f.getToolbar().addCommandToSideMenu("add espece", null, (ev) -> {
                HomeForm h = new HomeForm();
                AddEspeceForm c;
                c = new AddEspeceForm(f);
                c.setScrollable(true);
                c.show();
            });
            f.getToolbar().addCommandToSideMenu("add saison", null, (ev) -> {
                HomeForm h = new HomeForm();
                AddSaisonForm S;
                S = new AddSaisonForm(f);
                S.setScrollable(true);
                S.show();
            });
            f.getToolbar().addCommandToSideMenu("search espece", null, (ev) -> {
                HomeForm h = new HomeForm();
                SearchEspeceForm e;
                e = new SearchEspeceForm(f);
                e.setScrollable(true);
                e.show();
            });

        } else {
            
      f.getToolbar().addCommandToSideMenu("Forum", null, e -> new ForumIndex().show());


            
            f.getToolbar().addCommandToSideMenu("Add Feedback", null, e -> new AddFeedbackForm(current).show());

            f.getToolbar().addCommandToSideMenu("Reclamation Own List", null, e -> new ListOwnReclamationForm(current).show());
            f.getToolbar().addCommandToSideMenu("Add Reclamation", null, e -> new AddReclamationForm(current).show());

            f.getToolbar().addCommandToSideMenu("Produits", null, (ev) -> {
                HomeForm h = new HomeForm();
                FrontDisplayForm form;
                try {
                    form = new FrontDisplayForm();
                    form.setScrollable(true);
                    form.getCurrent().show();
                } catch (InterruptedException ex) {
                }
            });

            f.getToolbar().addCommandToSideMenu("My Wishlist", null, (ev) -> {
                HomeForm h = new HomeForm();
                WishlistForm form;
                try {
                    form = new WishlistForm();
                    form.setScrollable(true);
                    form.getCurrent().show();
                } catch (InterruptedException ex) {
                }
            });

            f.getToolbar().addCommandToSideMenu("List saison", null, (ev) -> {
                HomeForm h = new HomeForm();
                ListSaisonForm a;
                a = new ListSaisonForm(f);
                a.setScrollable(true);
                a.show();
            });

            f.getToolbar().addCommandToSideMenu("list espece", null, (ev) -> {
                HomeForm h = new HomeForm();
                ListEspeceForm b;
                b = new ListEspeceForm(f);
                b.setScrollable(true);
                b.show();
            });

        }

        f.getToolbar().addCommandToSideMenu("SIGN OUT", null, e -> new SignInForm().show());

        Label lbUsername = new Label("Username=" + SignInForm.US.getUsername());
        Label lbFirstnameName = new Label("Firstname=" + SignInForm.US.getFirstname());
        Label lbLastName = new Label("Lastname=" + SignInForm.US.getLastname());
        Label lbFirstLogin = new Label("First Login=" + SignInForm.US.getDate());
        Label lbLastLogin = new Label("Last_login=" + SignInForm.US.getLast_login());
        Label lbPhone = new Label("Phone=" + SignInForm.US.getPhone());
        Label lbEmail = new Label("Email=" + SignInForm.US.getEmail());
        Label lbRole = new Label("Role=" + SignInForm.US.getRole().substring(SignInForm.US.getRole().indexOf("_") + 1));

        f.add(iv);
        f.add(lbUsername);
        f.add(lbFirstnameName);
        f.add(lbLastName);
        f.add(lbFirstLogin);
        f.add(lbLastLogin);
        f.add(lbPhone);
        f.add(lbEmail);
        f.add(lbRole);

        f.show();

    }

}
