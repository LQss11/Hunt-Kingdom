/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.UtilisateursAdresses;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Utilisateurs;
import com.mycompany.utils.DB;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.entities.Commandes;
import com.mycompany.entities.User;

/**
 *
 * @author skand
 */
public class SrProduct {

    public void addToPanier(Produit p) {
        Database db = DB.getInstance();
        try {
            db.execute("INSERT INTO panier(id, titre, prix , image) VALUES(" + p.getId() + ",'" + p.getNom() + "', " + p.getPrix() + ",'" + p.getImage() + "')");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("produitt ajouter");
    }

    public void removeFromPanier(Produit p) {
        Database db = DB.getInstance();
        try {
            db.execute("DELETE FROM panier where id = " + p.getId());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int exist(Produit p) {
        int i = 0;
        Database db = DB.getInstance();
        try {
            Cursor result = db.executeQuery("select count(*) from panier where id = " + p.getId());
            i = result.getRow().getInteger(0);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("exist " + i);
        return i;
    }

    public ArrayList<Produit> panier() {
        Database db = DB.getInstance();
        ArrayList<Produit> list = new ArrayList<Produit>();
        try {

            Cursor result = db.executeQuery("select * from panier ");
            while (result.next()) {
                Row r = result.getRow();
                Produit p = new Produit();
                p.setId(r.getInteger(0));
                p.setNom(r.getString(1));
                p.setPrix(r.getFloat(2));
                p.setImage(r.getString(3));
                list.add(p);
            }
        } catch (IOException ex) {

        }

        return list;
    }

    /*   public void upcmd(Product product,String cmd) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Eco/web/app_dev.php/Api/Store/Pro/cmd/"+product.getId()+"/"+cmd);
        
        NetworkManager.getInstance().addToQueueAndWait(con);

    }*/
    String rep;

  
    public String addcmd(Commandes ad, User user) {

        System.out.println(ad);

        rep = "";
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Ecommerce/panier/addcmd/" + 1 + "/" + ad.getReference() + "/" + ad.getCommande() + "/" + user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                rep = new String(con.getResponseData());

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return rep;
    }

  
          public String creatPay(Commandes c) {
         rep = "";
        
              
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Ecommerce/panier/Store/check/"+45);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                 rep = new String(con.getResponseData());

                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return rep;
    }
}
