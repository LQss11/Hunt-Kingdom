/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Inscription;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceInscription;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author LQss
 */
public class ListSearchedEvenetForm extends Form
{

    ListSearchedEvenetForm(Evenement f) 
    {
                    setTitle("Evenement "+f.getNom());  
                    setLayout(BoxLayout.y());

                    String path = "file://C:/wamp64/www/images/" + f.getImage();
                    Image setimg = FileEncodedImage.create(path,400, 400);
                    ImageViewer iv = new ImageViewer(setimg);
                    String Fbs = " iD:" + f.getId()+"\n"+" Nom= "+ f.getNom()+"\n"+" Type= "+ f.getType()+"\n"+" nbrPlace= "
                         + f.getNbrplace()+"\n"+" Date= "+ f.getDate()+"\n"+" place= "+ f.getPlace()+"\n"+" description= "+ f.getDescription()+"\n"+" duree= "+ f.getDuree()+"\n";
                    String name = "Vous etes inscrit a l'evenement "+f.getNom();
                    SpanLabel sp = new SpanLabel(Fbs);
                    if (new ListEvenementForm(null).Exist(f.getId(),1) == true)
            {
                Button btnAinscrit = new Button("Annuler L'inscription");
                btnAinscrit.addActionListener(new ActionListener() 
                    {
                          @Override
                         public void actionPerformed(ActionEvent evt) 
                         {
                            Dialog.show("Inscription Annuler",name, new Command("OK"));
                            ServiceInscription.getInstance().AInscrire(f.getId(),1);
                            new HomeForm().show();
                         }
                    });
                addAll(iv,sp,btnAinscrit);   
            }
            else
            {
                    Button btninscrit = new Button("S'Inscrire");
                    addAll(iv,sp,btninscrit);
                    btninscrit.addActionListener(new ActionListener() 
                    {
                          @Override
                         public void actionPerformed(ActionEvent evt) 
                         {
                            Dialog.show("Success",name, new Command("OK"));
                            ServiceInscription.getInstance().Inscrire(f.getId(),1);
                            new ListSearchedEvenetForm(f).show();
                            
                         }
                    });
            }
        
    }
}

