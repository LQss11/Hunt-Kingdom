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
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Evenement;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Inscription;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceInscription;
import com.mycompany.utils.Statics;
import java.io.IOException;
import static java.lang.Float.NaN;
import java.util.ArrayList;

/**
 *
 * @author LQss
 */
public class EvenementTri extends Form {

    Form current;

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    public Inscription Exist(int ev, int u) {
        for (Inscription ins : ServiceInscription.getInstance().getAllInscriptions()) {
            if ((ins.getId_event() == ev) && (ins.getId_user() == u)) {
                return ins;
            }
        }
        return null;
    }

    public Evenement Existev(int ev) {
        for (Evenement ins : ServiceEvenement.getInstance().getAllEvenements()) {
            if (ins.getId() == ev) {
                return ins;
            }
        }
        return null;
    }

    public EvenementTri(Form previous) {
        //current = this;

        setTitle("List Evenement");
        Button btnSinscrit = new Button("Chercher Par ID");
        TextField IDEvent = new TextField("", "ID");
        btnSinscrit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (Existev(Integer.parseInt(IDEvent.getText())) != null) {
                        new ListSearchedEvenetForm(Existev(Integer.parseInt(IDEvent.getText()))).show();
                    } else {
                        Dialog.show("Success", "Desn  t exist", "OK", null);
                    }
                } catch (NumberFormatException numberFormatException) {
                    Dialog.show("Error", "Server Error", "OK", null);
                }
            }
        });
        add(IDEvent);
        add(btnSinscrit);

        setLayout(BoxLayout.y());
        for (Evenement f : ServiceEvenement.getInstance().getAllEvenementstri()) {

            EncodedImage enc = null;
            try {
                enc = EncodedImage.create("/anonimo.jpg");
            } catch (IOException ex) {
            }

            String url = Statics.SYMFONY_URL + f.getImage();
            Image img2 = URLImage.createToStorage(enc, url, url);
            ScaleImageLabel myPic = new ScaleImageLabel();
            myPic.setIcon(img2);
            Dimension d = new Dimension(200, 200);
            myPic.setPreferredSize(d);

            Container sp = new Container(BoxLayout.y());
            Label lbId = new Label("ID = " + f.getId());
            Label lbNom = new Label("Nom = " + f.getNom());
            Label lbType = new Label("Type = " + f.getType());
            Label lbnbr = new Label("nbr de places = " + f.getNbrplace());
            Label lbDate = new Label("Date = " + f.getDate());
            Label lbPlace = new Label("Place = " + f.getPlace());
            SpanLabel lbDesc = new SpanLabel("ID = " + f.getDescription());
            Label lbDuree = new Label("Duree= " + f.getDuree());
            sp.add(lbId).add(lbNom).add(lbType).add(lbnbr).add(lbDate).add(lbPlace).add(lbDesc).add(lbDuree);
            //add(sp);
            if (Exist(f.getId(), SignInForm.US.getId()) != null) {
                Button btnAinscrit = new Button("Annuler L'inscription");
                btnAinscrit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Success", "Inscription Annuler", "OK", null);
                        ServiceInscription.getInstance().AInscrire(f.getId());
                        new EvenementTri(current).show();

                    }
                });

                //////////
                Button btnrate = new Button("Rate this Evenet");
                Slider starRank = new Slider();
                starRank.setEditable(true);
                starRank.setMinValue(0);
                starRank.setMaxValue(10);
                Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                        derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
                Style s = new Style(0xffff33, 0, fnt, (byte) 0);
                Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                s.setOpacity(100);
                s.setFgColor(0);
                Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
                initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
                starRank.setRenderPercentageOnTop(true);////////////////////////////////////////////////show pourcentage
                starRank.setProgress(Exist(f.getId(), SignInForm.US.getId()).getRate());
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////bouton pour valider le rate
                btnrate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ServiceInscription.getInstance().rate(starRank.getProgress(), Exist(f.getId(), SignInForm.US.getId()).getId());
                        Dialog.show("Success", "Rate ajoutee", "OK", null);

                    }
                });

                /////////
                add(myPic);
                add(sp);
                add(FlowLayout.encloseCenter(starRank));
                add(btnrate);
                add(btnAinscrit);

            } else {
                Button btninscrit = new Button("S'Inscrire");
                add(myPic);
                add(sp);
                add(btninscrit);

                btninscrit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Success", "Inscrit avec succes", "OK", null);
                        ServiceInscription.getInstance().Inscrire(f.getId(), 0);
                        new EvenementTri(current).show();

                    }
                });
            }
        }

        getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_TRENDING_DOWN, e -> new ListEvenementForm(current).show());

    }
}
