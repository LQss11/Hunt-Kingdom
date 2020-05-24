/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;


public class Paypal {

    Form f;

    public Paypal(Resources theme, String link) {
        f = new Form("Paypal", new BorderLayout());
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("http://127.0.0.1:8001/Ecommerce/panier/Store/checkout/45/");
        f.add(BorderLayout.CENTER, browser);
         f.getToolbar().addCommandToLeftBar("Store", null, (evt) -> {
         //   Store s = new Store(theme);
         //   s.getF().showBack();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
