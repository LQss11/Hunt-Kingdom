/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author RAFIK
 */
public class ForumIndex extends Form {

    Form current;

    public ForumIndex() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnListPublication = new Button("List publicatons");
        Button btnAddPublication = new Button("Ajouter Publication");

        btnAddPublication.addActionListener(e -> new AddpublicationForm(current).show());
        btnListPublication.addActionListener(e -> new ListPublicationForm(current).show());

        addAll(btnListPublication, btnAddPublication);

    }

}

