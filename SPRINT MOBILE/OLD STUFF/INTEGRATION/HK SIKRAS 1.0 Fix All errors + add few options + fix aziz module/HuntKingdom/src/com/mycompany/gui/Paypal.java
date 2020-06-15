/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.Map;

public class Paypal {

    Form f;

    public Paypal(Resources theme, String link) {
        f = new Form("Paypal", new BorderLayout());

        BrowserComponent browser = new BrowserComponent();

        browser.setURL(link);
        f.add(BorderLayout.CENTER, browser);
        f.getToolbar().addCommandToLeftBar("Store", null, (evt) -> {
            FrontDisplayForm form;
            try {
                form = new FrontDisplayForm();
                form.setScrollable(true);
                form.getCurrent().show();
            } catch (InterruptedException ex) {
            }
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
