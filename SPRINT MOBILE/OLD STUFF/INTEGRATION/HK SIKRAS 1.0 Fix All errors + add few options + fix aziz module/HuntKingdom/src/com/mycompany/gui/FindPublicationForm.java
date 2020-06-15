/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.commentaire;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.publication;
import com.mycompany.services.ServicePublication;
import com.mycompany.utils.Statics;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LQss
 */
public class FindPublicationForm extends Form {

    Form current;
    EncodedImage enc = null;
    Preferences PubId;
    public FindPublicationForm(Form previous, publication p) {
        current = this;
         EncodedImage enc = null;

        try {
            enc = EncodedImage.create("/anonimo.jpg");
        } catch (IOException ex) {
        }
        
        
        //System.out.println("pub id ==" + p.getId());
        setTitle("Publication");
        for (publication pub : ServicePublication.getInstance().FindPublication(p)) {

            Container cnt1 = new Container(BoxLayout.y());
            //Label lbId = new Label(" iD= " + pub.getId());
             
            SpanLabel lbDesc = new SpanLabel(" ContenuP= " + pub.getContenu());
            Label lbDate = new Label(" Date= " + pub.getDatePublication());
            
            
              String url = Statics.SYMFONY_URL + "/" + pub.getType();


            URLImage img2 = URLImage.createToStorage(enc, url, url);
            ScaleImageLabel myPic = new ScaleImageLabel();
            myPic.setIcon(img2);
            Dimension d = new Dimension(400, 400);
            myPic.setPreferredSize(d);
          
            Button btnUpdateFeedback = new Button("Voir commentaires");
            
            
            btnUpdateFeedback.addActionListener(e -> new ShowcomntsForm(current).show());
            PubId.set("PubId",pub.getId());
            cnt1.add(myPic).add(lbDesc).add( lbDate).add(btnUpdateFeedback);
             current.add(cnt1);
             current.show();
        }
            
       getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    }         

    }



  

