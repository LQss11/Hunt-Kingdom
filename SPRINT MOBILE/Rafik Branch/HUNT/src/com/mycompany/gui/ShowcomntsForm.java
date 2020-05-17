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
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author RAFIK
 */
public class ShowcomntsForm extends Form{
      Form current;
           public ArrayList<commentaire> comnts = new ArrayList<>();

    public ShowcomntsForm(Form previous) {
        
         current = this;
         Preferences UserId = null;
         String PublicationID = UserId.get("PubId", null);

        setTitle("List Commentaire");
        ServiceCommentaire sc= new ServiceCommentaire();
        comnts=sc.getInstance().FindCommentaire(PublicationID);
        if(comnts.isEmpty()){
        
                    Button AjouterCommentaire = new Button("Commenter");
                    AjouterCommentaire.addActionListener(e -> new AddCommentForm(current,5).show());
                    add(AjouterCommentaire);
        }
        else{
        for (commentaire P : comnts) {

            Container cnt1 = new Container(BoxLayout.y());

            SpanLabel lbDesc = new SpanLabel(" Contenu= " + P.getContenu()); 
            Label lbDate = new Label(" Date= " + P.getDateComnt());
            Label lbRate = new Label("Publication= " + P.getId_publication());
            Label user = new Label("User= " + P.getId_user());
            
           

            cnt1.add( lbDesc).add(lbDate).add(  lbRate).add(user);

            Container cnt2 = new Container(BoxLayout.x());
            Button AjouterCommentaire = new Button("Commenter");

            cnt2.add(AjouterCommentaire);
            

            AjouterCommentaire.addActionListener(e -> new AddCommentForm(current, P.getId_publication()).show());

           


            add( cnt1).add(cnt2);
        }}
        
current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });            //getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        

    }
        
    }
    

