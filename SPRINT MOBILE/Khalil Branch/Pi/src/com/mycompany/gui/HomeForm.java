/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.services.ServiceProduit;
import com.mycompany.entities.Produit;
import java.io.IOException;

/**
 *
 * @author khalil
 */


public class HomeForm {

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    Form f;
    public HomeForm()
    {
 f = new Form("home");
f.setScrollable(true);

        f.getToolbar().addCommandToSideMenu("Produits", null, (ev)->{HomeForm h=new HomeForm();
FrontDisplayForm form;      
     try {
         form = new FrontDisplayForm();
         form.setScrollable(true);
         form.getCurrent().show();
     } catch (InterruptedException ex) {      
     }        });
 f.getToolbar().addCommandToSideMenu("My Wishlist", null, (ev)->{HomeForm h=new HomeForm();
WishlistForm form;      
     try {
         form = new WishlistForm();
         form.setScrollable(true);
         form.getCurrent().show();
     } catch (InterruptedException ex) {      
     }        });




}
    
    
    
    
    
    
}
