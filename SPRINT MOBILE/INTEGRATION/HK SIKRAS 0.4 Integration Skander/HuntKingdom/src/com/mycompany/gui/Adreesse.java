/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.gif.GifImage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.getResourceAsStream;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.UtilisateursAdresses;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceUtilisateursAdresses;
import com.mycompany.services.SrProduct;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.listeprod;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.entities.Commandes;
import com.mycompany.services.ServiceCommandes;
import com.mycompany.gui.Paypal;

/**
 *
 * @author takwa
 */
public class Adreesse {
    Form current;

    Form f;
    Resources theme;
    Dialog loadingAnimation;
    public Adreesse() {
        f = new Form("Adresse Livraison", BoxLayout.y());
        ServiceUtilisateursAdresses sr = new ServiceUtilisateursAdresses();
        List<UtilisateursAdresses> ls = sr.getAdresses(SignInForm.US);
        ButtonGroup grpr = new ButtonGroup();
        for (UtilisateursAdresses ad : ls) {
            RadioButton rb = new RadioButton();

            Container c = new Container(BoxLayout.x());
            SpanLabel sp = new SpanLabel(ad.getNom()+ "\n" + ad.getPrenom()+ "\n" + ad.getTelephone()+ "\n" + ad.getAdresse()+ "\n" + ad.getCp()+ "\n" + ad.getPays()+ "\n" + ad.getVille()+ "\n" + ad.getComplement());
            c.add(rb);
            c.add(sp);
            
            
            
            
        Button btnsup = new Button("remove");
            btnsup.setUIID("label");
            btnsup.addActionListener((evt) -> {
                sr.RAdresses(ad.getId());
                Adreesse fad = new Adreesse();
                fad.getF().showBack();
            });
            c.add(btnsup);
            f.add(c);
            grpr.add(rb);
        }

        Button btn = new Button("Continuer");
        btn.addActionListener((evt) -> {
            if (grpr.getSelectedIndex() == -1) {
                Dialog.show("error", "Selctionez une adresse ", "ok", null);
            } else {
                ScaleImageLabel loadingIcon;
        try {
            loadingIcon = new ScaleImageLabel(GifImage.decode(getResourceAsStream("/loader.gif"), 1177720));
             loadingAnimation = new Dialog();
            loadingAnimation.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            Style dlgStyle = loadingAnimation.getDialogStyle();
            dlgStyle.setBorder(Border.createEmpty());
            dlgStyle.setBgTransparency(0);
            loadingAnimation.add(BorderLayout.CENTER, loadingIcon);
                            passecmd(ls.get(grpr.getSelectedIndex()));

        } catch (IOException ex) {
        }
            }

        });
        f.add(btn);

        f.getToolbar().addCommandToOverflowMenu("Ajouter adresse", null, (evt) -> {
            AddAdresse pn = new AddAdresse();
            pn.getF().show();
        });
        
         f.getToolbar().addCommandToLeftBar("Panier", null, (evt) -> {
            Panier s = new Panier();
            s.getF().showBack();
        });

    }

    
    
    
    
    public String decrypttva(ArrayList<Produit> produits, UtilisateursAdresses utilisateur) {
        String str = "";
        String strProduit = "";
        int TotaleHT = 0;
        int TotaleTTC = 0;

        for (int i = 0; i < produits.size(); i++) {
            int k = i + 1;
            String s = "i:" + k
                    + ";a:5:{s:2:\"id\";i:" + produits.get(i).getId()
                    + ";s:9:\"reference\";s:" + produits.get(i).getNom().length()
                    + ":\"" + produits.get(i).getNom()
                    + "\";s:8:\"quantite\";s:1:\"" + produits.size()
                    + "\";s:6:\"prixHT\";d:" + Math.round(produits.get(i).getPrix())
                    + ";s:7:\"prixTTC\";d:" + Math.round(produits.get(i).getPrix())
                    + ";}";
            strProduit += s;
        }

        for (int i = 0; i < produits.size(); i++) {
            int Tot = Math.round(produits.get(i).getPrix());
            TotaleHT += Tot;
        }

        for (int i = 0; i < produits.size(); i++) {
            int Tot1 = Math.round(produits.get(i).getPrix());
            TotaleTTC += Tot1;
        }

        str += "a:7:{s:3:\"tva\";a:1:{s:2:\"1\";d:" + 0
                + ";}"
                + "s:7:\"produit\";a:" + produits.size() + ":{" + strProduit
                + "}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:" + utilisateur.getPrenom().length()
                + ":\"" + utilisateur.getPrenom()
                + "\";s:3:\"nom\";s:" + utilisateur.getNom().length()
                + ":\"" + utilisateur.getNom()
                + "\";s:9:\"telephone\";s:" + utilisateur.getTelephone().length()
                + ":\"" + utilisateur.getTelephone()
                + "\";s:7:\"adresse\";s:" + utilisateur.getAdresse().length()
                + ":\"" + utilisateur.getAdresse()
                + "\";s:2:\"cp\";s:" + utilisateur.getCp().length()
                + ":\"" + utilisateur.getCp()
                + "\";s:5:\"ville\";s:" + utilisateur.getVille().length()
                + ":\"" + utilisateur.getVille()
                + "\";s:4:\"pays\";s:" + utilisateur.getPays().length()
                + ":\"" + utilisateur.getPays()
                + "\";s:10:\"complement\";s:" + utilisateur.getComplement().length()
                + ":\"" + utilisateur.getComplement()
                + "\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:" + utilisateur.getPrenom().length()
                + ":\"" + utilisateur.getPrenom()
                + "\";s:3:\"nom\";s:" + utilisateur.getNom().length()
                + ":\"" + utilisateur.getNom()
                + "\";s:9:\"telephone\";s:" + utilisateur.getTelephone().length()
                + ":\"" + utilisateur.getTelephone()
                + "\";s:7:\"adresse\";s:" + utilisateur.getAdresse().length()
                + ":\"" + utilisateur.getAdresse()
                + "\";s:2:\"cp\";s:" + utilisateur.getCp().length()
                + ":\"" + utilisateur.getCp()
                + "\";s:5:\"ville\";s:" + utilisateur.getVille().length()
                + ":\"" + utilisateur.getVille()
                + "\";s:4:\"pays\";s:" + utilisateur.getPays().length()
                + ":\"" + utilisateur.getPays()
                + "\";s:10:\"complement\";s:" + utilisateur.getComplement().length()
                + ":\"" + utilisateur.getComplement()
                + "\";}s:6:\"prixHT\";d:" + TotaleHT
                + ";s:7:\"prixTTC\";d:" + TotaleTTC
                + ";s:5:\"token\";"
                + "s:40:\"58f995582826e0e74169d0d33010fdbf468724b2\";"
                + "}";
        return str;
    }
    
 
    
    
    
    
    
   void passecmd(UtilisateursAdresses ad) {
       
    loadingAnimation.showModeless();
            Commandes c = new Commandes();
        SrProduct sr = new SrProduct();
           listeprod = sr.panier();
      //  ArrayList<Produit> ls = sr.panier();
    /*    Double tot = 0.0;
        for (Produit p : ls) {
            tot += p.getPrix();
        }*/
        
        String List = decrypttva((ArrayList<Produit>) listeprod, ad);
        c.setCommande(List);
     
        
        
        String idcmd = sr.addcmd(c,SignInForm.US);
        
          for (Produit p : listeprod) {
            sr.removeFromPanier(p);
        }
          
        String link = sr.creatPay(c);
     
        loadingAnimation.dispose();
        Paypal pay = new Paypal(theme, link);
        pay.getF().show();
     
        
        
        
   /*     for (Produit p : ls) {
            sr.upcmd(p, idcmd);
        }

        String link = sr.creatPay(idcmd);

        for (Produit p : ls) {
            sr.removeFromPanier(p);
        }

        System.out.println(link);
        loadingAnimation.dispose();*/
  //      Paypal pay = new Paypal(theme, link);
  //      pay.getF().show();
                

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
