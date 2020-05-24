/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.commentaire;
import com.mycompany.services.ServiceCommentaire;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author RAFIK
 */
public class ShowcomntsForm extends Form {

    Form current;
    public ArrayList<commentaire> comnts = new ArrayList<>();

    public ShowcomntsForm(Form previous) {

        current = this;

        setTitle("List Commentaire");
        ServiceCommentaire sc = new ServiceCommentaire();
        Preferences UserId = null;
        String PublicationID = UserId.get("PubId", null);
        comnts = sc.getInstance().FindCommentaire(PublicationID);

        Button AjouterCommentaire = new Button("Commenter");
        AjouterCommentaire.addActionListener(e -> new AddCommentForm(current).show());
        add(AjouterCommentaire);

        for (commentaire P : comnts) {

            Container cnt1 = new Container(BoxLayout.y());

            //Label lbId = new Label(" iD= " + P.getId());
            SpanLabel lbDesc = new SpanLabel(" Contenu= " + P.getContenu());
            SpanLabel pubcont = new SpanLabel("Contenue de publication= " + P.getPub_Contenu());
            Label user = new Label("User= " + P.getUsername());
            Label lbRate = new Label("Date commentaire= " + P.getDateComnt());

            Label lbDate = new Label(" Date= " + P.getDateComnt());

            SpanLabel lbSeparator = new SpanLabel("\n");

            cnt1.add(user).add(pubcont).add(lbDesc).add(lbDate).add(lbRate).add(lbSeparator);

            add(cnt1);

        }

        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });            //getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar(current.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> new ListPublicationForm(current).show());
    }

}
