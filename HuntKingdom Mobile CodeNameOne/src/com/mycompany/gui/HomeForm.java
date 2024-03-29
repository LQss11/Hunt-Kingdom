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
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.mycompany.entities.Feedback;
import com.mycompany.services.ServiceProduit;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceFeedback;
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
    private Resources theme;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    Form f;

    public HomeForm() {
        current = this;
        Boolean Fexist=false;
        f = new Form("home", BoxLayout.y());
        setLayout(BoxLayout.y());

        f.setScrollable(true);
        try {
            enc = EncodedImage.create("/anonimo.jpg");
        } catch (IOException ex) {
        }

        String url = Statics.SYMFONY_URL + SignInForm.US.getImage();

        img2 = URLImage.createToStorage(enc, url, url);
        ScaleImageLabel myPic = new ScaleImageLabel();
        myPic.setIcon(img2);
        Dimension d = new Dimension(200, 200);
        myPic.setPreferredSize(d);

        if (SignInForm.US.getRole().indexOf("ROLE_ADMIN") != -1) {
            //f.getToolbar().addCommandToSideMenu("Forum", null, e -> new ForumIndex());

            f.getToolbar().addCommandToSideMenu("List Commandes", null, e -> new ListCommandesForm(current).show());

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

        } else {

            f.getToolbar().addCommandToLeftBar("Panier", null, (evt) -> {
                Panier s = new Panier();
                s.getF().showBack();
            });

            f.getToolbar().addCommandToSideMenu("EVENT", null, e -> new EvenementTri(current).show());

            f.getToolbar().addCommandToSideMenu("Forum", null, e -> new ForumIndex().show());
            for (Feedback f : ServiceFeedback.getInstance().getAllFeedbacks()) {
                if (f.getUser() == SignInForm.US.getId()) {
                    Fexist=true;
                }

            }
            if(!Fexist)
            f.getToolbar().addCommandToSideMenu("Add Feedback", null, e -> new AddFeedbackForm(current).show());

            f.getToolbar().addCommandToSideMenu("Reclamation Own List", null, e -> new ListOwnReclamationForm(current).show());
            f.getToolbar().addCommandToSideMenu("Add Reclamation", null, e -> new AddReclamationForm(current, 0).show());

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

        f.getToolbar().addComponentToSideMenu(new Label(SignInForm.US.getUsername(), "SideCommandNoPad"));
        f.getToolbar().addComponentToSideMenu(new Label(SignInForm.US.getEmail(), "SideCommandSmall"));

        Label lbUsername = new Label("Username=" + SignInForm.US.getUsername());
        Label lbFirstnameName = new Label("Firstname=" + SignInForm.US.getFirstname());
        Label lbLastName = new Label("Lastname=" + SignInForm.US.getLastname());
        Label lbFirstLogin = new Label("First Login=" + SignInForm.US.getDate());
        Label lbLastLogin = new Label("Last_login=" + SignInForm.US.getLast_login());
        Label lbPhone = new Label("Phone=" + SignInForm.US.getPhone());
        Label lbEmail = new Label("Email=" + SignInForm.US.getEmail());
        Label lbRole = new Label("Role=" + SignInForm.US.getRole().substring(SignInForm.US.getRole().indexOf("_") + 1));
        String file = null;
        f.add(myPic);
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
