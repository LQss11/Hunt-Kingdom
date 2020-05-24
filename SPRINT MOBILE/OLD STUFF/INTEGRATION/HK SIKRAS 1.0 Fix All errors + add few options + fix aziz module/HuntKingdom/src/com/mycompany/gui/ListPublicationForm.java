package com.mycompany.gui;

import com.mycompany.entities.publication;
import com.mycompany.services.ServicePublication;
import com.mycompany.utils.Statics;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

public class ListPublicationForm extends Form {

    Form current;
    public ArrayList<publication> publications = new ArrayList<>();
    public ArrayList<publication> publications2 = new ArrayList<>();

    public ListPublicationForm(Form previous) {
        current = this;
        EncodedImage enc = null;

        try {
            enc = EncodedImage.create("/anonimo.jpg");
        } catch (IOException ex) {
        }

        setTitle("Liste publications");

        //current = new Form("search", BoxLayout.y());
        current.setScrollableY(true);

        Container fsearch = new Container(BoxLayout.x());
        TextField tfsearch = new TextField();
        Button btsearch = new Button("Search");
        fsearch.add(tfsearch).add(btsearch);
        current.add(fsearch);
        ServicePublication sp = new ServicePublication();
        publications = sp.getAllpublications();

        Display(publications);
        btsearch.addActionListener(e -> {

            publications2 = sp.getSearchListPublication(tfsearch.getText());
            current.revalidate();
            current.removeAll();
            current.add(fsearch);
            Display(publications2);

        });
    }

    public void Display(ArrayList<publication> publication) {
        EncodedImage enc = null;

        try {
            enc = EncodedImage.create("/anonimo.jpg");
        } catch (IOException ex) {
        }

        ServicePublication sp = new ServicePublication();
        for (publication P : publication) {

            Container cnt1 = new Container(BoxLayout.y());

            ///Form ///////////
            SpanLabel Contenu = new SpanLabel(" Contenu= " + P.getContenu());
            Label lbDate = new Label(" Date= " + P.getDatePublication());
            String url = Statics.SYMFONY_URL + "/" + P.getType();

            ///// adding Image //////////
            URLImage img2 = URLImage.createToStorage(enc, url, url);
            ScaleImageLabel myPic = new ScaleImageLabel();
            myPic.setIcon(img2);
            Dimension d = new Dimension(200, 200);
            myPic.setPreferredSize(d);
            //// adding to container ///////

            SpanLabel lbSeparator = new SpanLabel(" \n ");
            cnt1.add(myPic).add(Contenu).add(lbDate).add(lbSeparator);

            Container cnt2 = new Container(BoxLayout.x());
            Button FindPublication = new Button("Voir Plus");
            Button btnRemovePublication = new Button("Supprimer publication");

            btnRemovePublication.addActionListener((e) -> {
                try {
                    publication pub = new publication(P.getId());
                    if (ServicePublication.getInstance().RemovePublication(pub)) {
                        Dialog.show("Success", "publication  a ete supprimee avec succees", "OK", null);
                        new ListPublicationForm(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }
            });

            FindPublication.addActionListener(e -> new FindPublicationForm(current, P).show());

            cnt2.add(FindPublication).add(btnRemovePublication);

            add(cnt2).add(cnt1);
        }
        getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }
}
