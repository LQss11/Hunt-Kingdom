/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import com.mycompany.services.ServiceUtilisateursAdresses;
import com.mycompany.myapp.MyApplication;


/**
 *
 * @author takwa
 */
public class AddAdresse {

    Form f;

    public AddAdresse(Resources theme) {
        f = new Form("Ajouter Adresse", BoxLayout.y());
        TextField txnom = new TextField("", "nom");
        TextField txprenom = new TextField("", "prenom");
        TextField txtel = new TextField("", "telephone");
        TextField txst = new TextField("", "pays");
        TextField txci = new TextField("", "Ville");
        TextField txzip = new TextField("", "Code Zip");
        TextField txln1 = new TextField("", "Adresse ");
        TextField txln2 = new TextField("", "complement");
   
        Button btn = new Button("Ajouter");
        btn.addActionListener((evt) -> {
            if(txst.getText().equals(""))
            {
                Dialog.show("error","Region est obligatoire","ok",null);
                return;
            }
            if(txci.getText().equals(""))
            {
                Dialog.show("error","ville est obligatoire","ok",null);
                return;
            }
            if(txzip.getText().equals(""))
            {
                Dialog.show("error","code zip est obligatoire","ok",null);
                return;
            }
            
            if(txln1.getText().equals(""))
            {
                Dialog.show("error","Adresse line 1 est obligatoire","ok",null);
                return;
            }
            
            try
            {
                Integer.parseInt(txzip.getText());
            }catch(Exception ex)
            {   
                Dialog.show("error","Code zip doit etre just","ok",null);
                return ;
            }
            ServiceUtilisateursAdresses sr = new ServiceUtilisateursAdresses();
   
            sr.addAdresses(txnom.getText(),txprenom.getText(),txtel.getText(),txln1.getText(), txzip.getText(), txst.getText(), txci.getText(),txln2.getText(),MyApplication.user);
                                  
            
           Adreesse ad = new Adreesse(theme);
            ad.getF().showBack();
        });

        f.add(txnom).add(txprenom).add(txtel).add(txln1).add(txzip).add(txst).add(txci).add(txln2).add(btn);
        
         f.getToolbar().addCommandToLeftBar("Back", null, (evt) -> {
          Adreesse s = new Adreesse(theme);
           s.getF().showBack();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
