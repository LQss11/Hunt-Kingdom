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
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.StringTokenizer;

/**
 *
 * @author LQss
 */
public class FindReclamationForm extends Form {

    Form current;

    public FindReclamationForm(Form previous, Reclamation rec) {
        current = this;

        setTitle("Reclamation");
        for (Reclamation r : ServiceReclamation.getInstance().FindReclamation(rec)) {

            Container cnt1 = new Container(BoxLayout.y());
            Label lbId = new Label(" iD= " + r.getId());
            Label lbIdu = new Label(" User ID= " + r.getIdu());
            Label lbType = new Label(" Type= " + r.getType());
            Label lbIdo = new Label(" Id Objet= " + r.getIdo());
            Label lbSujet = new Label(" Sujet= " + r.getSujet());
            SpanLabel lbDesc = new SpanLabel(" Description= " + r.getDescription());
            Label lbDate = new Label(" Date= " + r.getDate());
            Label lbEtat = new Label(" Etat= " + r.getEtat());
            SpanLabel lbConversation2 = new SpanLabel(" Conversation =");

            String Message = "";
            String Role = "";
            Container Msg = new Container(BoxLayout.x());
            Container Conv = new Container(BoxLayout.y());

            //String fruits = "apple:pear:grape";
            //String delimiter = "*";
            StringTokenizer fruitsTokenizer = new StringTokenizer(lbDesc.getText(), "*");
            while (fruitsTokenizer.hasMoreTokens()) {
                Message = fruitsTokenizer.nextToken();

                StringTokenizer RoleTokenizer = new StringTokenizer(Message, "-");
                while (RoleTokenizer.hasMoreTokens()) {
                    Role = Role + " \n " + RoleTokenizer.nextToken() ;

                }
            }
            lbConversation2.setText(Role);

            if (lbDesc.getText().indexOf("*") != -1) {
                lbDesc.setText(lbDesc.getText().substring(0, lbDesc.getText().indexOf("*")));
            }

            Label lbConv = new Label(" Conversation= " + r.getIdo());

            Button btnUpdateReclamation = new Button("Update Reclamation");
            btnUpdateReclamation.addActionListener(e -> new UpdateReclamationForm(current, rec).show());

            cnt1.addAll(lbId, lbIdu, lbType, lbIdo, lbSujet, lbDesc, lbDate, lbEtat, btnUpdateReclamation, lbConversation2);

            addAll(cnt1);
        }

        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
