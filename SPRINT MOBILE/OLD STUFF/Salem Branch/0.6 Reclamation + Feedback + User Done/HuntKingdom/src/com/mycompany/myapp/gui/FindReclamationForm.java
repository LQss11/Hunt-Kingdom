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
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.StringTokenizer;

public class FindReclamationForm extends Form {

    Form current;
    Preferences UserId = null;

    public FindReclamationForm(Form previous, Reclamation rec) {
        current = this;
        String UR = UserId.get("UserRole", null);

        setTitle("Reclamation");
        for (Reclamation r : ServiceReclamation.getInstance().FindReclamation(rec)) {

            Container cnt1 = new Container(BoxLayout.y());
            Label lbId = new Label(" iD= " + r.getId());
            Label lbIdu = new Label(" User ID= " + r.getIdu());
            Label lbType = new Label(" Type= " + r.getType());
            Label lbIdo = new Label(" Id Objet= " + r.getIdo());
            Label lbSujet = new Label(" Sujet= " + r.getSujet());
            SpanLabel lbDesc = new SpanLabel(r.getDescription());
            Label lbDate = new Label(" Date= " + r.getDate());
            Label lbEtat = new Label(" Etat= " + r.getEtat());
            SpanLabel lbConversation2 = new SpanLabel(" Conversation =");

            TextField tfComment = new TextField("", "Ajouter Un Commentaire: ");
            tfComment.setVisible(false);

            String Message = "";
            String Role = "";
            Container cnt2 = new Container(BoxLayout.y());

            StringTokenizer fruitsTokenizer = new StringTokenizer(lbDesc.getText(), "*");
            while (fruitsTokenizer.hasMoreTokens()) {
                Message = fruitsTokenizer.nextToken();

                StringTokenizer RoleTokenizer = new StringTokenizer(Message, "-");
                while (RoleTokenizer.hasMoreTokens()) {

                    //Role = Role + " \n " + RoleTokenizer.nextToken();
                    String x = RoleTokenizer.nextToken();
                    if (x.contains("ROLE")) {
                        SpanLabel lbR = new SpanLabel(" ROLE= " + x.substring(x.indexOf("_") + 1));
                        //lbR.getStyle().setFgColor(0xff000);
                        lbR.getStyle().setFgColor(ColorUtil.GRAY);

                        cnt2.add(lbR);

                        //Role = Role + " \n Role:" + ;
                    } else {
                        SpanLabel lbC = new SpanLabel(" Comment= " + x);
                        cnt2.add(lbC);

                        //Comment = Comment + " \n Comment:" + x;
                    }
                }
            }
            lbConversation2.setText(Role);

            if (lbDesc.getText().indexOf("*") != -1) {
                lbDesc.setText(lbDesc.getText().substring(0, lbDesc.getText().indexOf("*")));
            }

            Label lbConv = new Label(" Conversation= " + r.getIdo());

            Button btnUpdateReclamation = new Button("Update Reclamation");
            btnUpdateReclamation.addActionListener(e -> new UpdateReclamationForm(current, rec).show());

            Button btnCommenterReclamation = new Button("Commenter Reclamation");

            cnt1.addAll(lbId, lbIdu, lbType, lbIdo, lbSujet, lbDesc, lbDate, lbEtat, btnUpdateReclamation, cnt2, btnCommenterReclamation, tfComment);

            btnCommenterReclamation.addActionListener((e) -> {

                if (tfComment.isVisible()) {
                    if (tfComment.getText().length() == 0) {
                        Dialog.show("Notice", "Field Must Not Be Empty", new Command("OK"));
                    } else {
                        try {
                            Reclamation f = new Reclamation();
                            String desc = "";
                            if (UR.equals("ROLE_ADMIN")) {
                                desc = rec.getDescription() + "*" + tfComment.getText() + "-" + "ROLE_ADMIN" + "-" + "*";

                            } else if (UR.equals("ROLE_CLIENT")) {
                                desc = rec.getDescription() + "*" + tfComment.getText() + "-" + "ROLE_CLIENT" + "-" + "*";

                            }
                            f = new Reclamation(rec.getId(), desc);

                            if (ServiceReclamation.getInstance().updateReclamation(f)) {
                                Dialog.show("Success", "Commentaire Ajouter avec succes", new Command("OK"));

                                if (UR.equals("ROLE_ADMIN")) {
                                    new ListReclamationForm(current).show();

                                } else if (UR.equals("ROLE_CLIENT")) {
                                    new ListOwnReclamationForm(current).show();

                                }

                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            }
                        } catch (NumberFormatException err) {
                            Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        }
                    }
                } else {
                    tfComment.setVisible(true);

                }
            });
            addAll(cnt1);
        }

        if (UR.equals(
                "ROLE_ADMIN")) {
//------------------------------------------------BACK------------------------------------------------\\
            getToolbar().addMaterialCommandToLeftBar("List Reclamation", FontImage.MATERIAL_ARROW_BACK, e -> new ListReclamationForm(previous).show());

        } else if (UR.equals(
                "ROLE_CLIENT")) {
//------------------------------------------------FRONT------------------------------------------------\\
            getToolbar().addMaterialCommandToLeftBar("List Own Reclamation", FontImage.MATERIAL_ARROW_BACK, e -> new ListOwnReclamationForm(previous).show());

        }

    }
}
