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
import com.codename1.io.rest.RequestBuilder;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.entities.Commandes;
import com.mycompany.utils.Statics;
import com.mycompany.entities.Produit;
import com.mycompany.utils.DB;

/**
 *
 * @author skand
 */
public class ServiceCommandes {

    public ArrayList<Commandes> Commandes;

    public static ServiceCommandes instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    public ServiceCommandes() {
        req = new ConnectionRequest();
    }

    public static ServiceCommandes getInstance() {
        if (instance == null) {
            instance = new ServiceCommandes();
        }
        return instance;
    }

    public boolean addCommandes(Commandes ad, String List) {
        String url = Statics.BASE_URL + "/Ecommerce/panier/addcmd/" + 1 + "/" + ad.getReference() + "/" + List + "/" + ad.getUtilisateur_id();

        req.setUrl(url);
        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean RemoveCommandes(Commandes r) {

        String url = Statics.BASE_URL + "/Ecommerce/panier/remcmd/" + r.getId();
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Commandes> getAllCommandes() {
        String url = Statics.BASE_URL + "/Ecommerce/panier/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commandes = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commandes;
    }

    public ArrayList<Commandes> parseCommandes(String jsonText) {

        System.out.println("jj" + jsonText);

        try {
            Commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CommandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CommandesListJson.get("root");

            for (Map<String, Object> obj : list) {

                Commandes r = new Commandes();

                Map<String, Object> User = (Map<String, Object>) obj.get("utilisateur");

                float utilisateur_id = Float.parseFloat(obj.get("id").toString());

                r.setUtilisateur_id((int) utilisateur_id);

                float id = Float.parseFloat(obj.get("id").toString());

                r.setId((int) id);

                // map commande d5alenelha ili fi westha plusieurs maps (tva/produit/....)
                Map<String, Object> cmd = (Map<String, Object>) obj.get("commande");
                System.out.println("Prix ttc == " + cmd.get("prixTTC"));
                /* System.out.println("first = " + cmd.get("produit").toString());*/
                //d5alna el map produit
                Map<String, Object> pr = (Map<String, Object>) cmd.get("produit");

                for (Map.Entry<String, Object> entry : pr.entrySet()) {
                    Map<String, Object> prod = (Map<String, Object>) entry.getValue();

                    System.out.println("entttt = " + prod.get("reference").toString());
                    float prodid = Float.parseFloat(prod.get("id").toString());

                    System.out.println("id = " + (int) prodid);

                }
                r.setValider((boolean) Boolean.valueOf(obj.get("valider").toString()));
                r.setReference(((int) Float.parseFloat(obj.get("reference").toString())));
                r.setDate(obj.get("date").toString().substring(0, 10) + " " + obj.get("date").toString().substring(11, 19));
                r.setCommande(obj.get("commande").toString());
                Commandes.add(r);
            }

        } catch (IOException ex) {

        }
        return Commandes;
    }

    public ArrayList<Commandes> parseOneCommandes(String jsonText) {
        try {
            Commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Commandes r = new Commandes();

            Map<String, Object> User = (Map<String, Object>) obj.get("utilisateur_id");
            float utilisateur_id = Float.parseFloat(User.get("id").toString());
            r.setUtilisateur_id((int) utilisateur_id);

            float id = Float.parseFloat(obj.get("id").toString());
            r.setId((int) id);
            /*  float ido = Float.parseFloat(obj.get("ido").toString());
            r.setIdo((int) ido);*/
            r.setValider((boolean) Boolean.valueOf(obj.get("valider").toString()));
            r.setReference(((int) Float.parseFloat(obj.get("reference").toString())));
            r.setDate(obj.get("date").toString().substring(0, 10) + " " + obj.get("date").toString().substring(11, 19));
            r.setCommande(obj.get("commande").toString());
            Commandes.add(r);
        } catch (IOException ex) {

        }
        return Commandes;
    }
    /*
    public ArrayList<Commandes> FindCommandes(Commandes r) {
        String url = Statics.BASE_URL + "/reclamation/reclamation/find/" + r.getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commandes = parseOneCommandes(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commandes;
    }*/

}
