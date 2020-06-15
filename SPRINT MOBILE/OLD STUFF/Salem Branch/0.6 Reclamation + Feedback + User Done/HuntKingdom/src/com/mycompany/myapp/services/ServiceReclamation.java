/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.io.rest.RequestBuilder;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> Reclamations;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(Reclamation r) {
        Preferences UserId = null;
        String UserSessionId = UserId.get("UserId", null);
        String url = Statics.BASE_URL + "/reclamation/reclamation/add/" + r.getType() + "/" + r.getSujet() + "/" + r.getDescription() + "/" + UserSessionId;
        req.setUrl(url);
        req.setPost(true);

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

    public boolean updateReclamation(Reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/reclamation/mod/" + r.getId() + "/" + r.getDescription();
        req.setUrl(url);
        req.setPost(true);

        req.setHttpMethod("PUT");
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

    public boolean RemoveReclamation(Reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/reclamation/rem/" + r.getId();
        req.setUrl(url);
        req.setPost(true);

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

    public ArrayList<Reclamation> parseReclamations(String jsonText) {
        try {
            Reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("Array == " +jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationsListJson.get("root");

            for (Map<String, Object> obj : list) {

                Reclamation r = new Reclamation();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
                float idU = Float.parseFloat(User.get("id").toString());
                r.setIdu((int) idU);

                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                float ido = Float.parseFloat(obj.get("ido").toString());
                r.setIdo((int) ido);
                r.setType(obj.get("type").toString());
                r.setSujet(obj.get("sujet").toString());
                r.setDescription(obj.get("description").toString());
                r.setDate(obj.get("date").toString().substring(0, 10) + " " + obj.get("date").toString().substring(11, 19));
                r.setEtat(obj.get("etat").toString());
                Reclamations.add(r);
            }

        } catch (IOException ex) {

        }
        return Reclamations;
    }

    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.BASE_URL + "/reclamation/reclamation/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }

    public ArrayList<Reclamation> parseOneReclamation(String jsonText) {
        try {
            Reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Reclamation r = new Reclamation();

            Map<String, Object> User = (Map<String, Object>) obj.get("idU");
            float idU = Float.parseFloat(User.get("id").toString());
            r.setIdu((int) idU);

            float id = Float.parseFloat(obj.get("id").toString());
            r.setId((int) id);
            float ido = Float.parseFloat(obj.get("ido").toString());
            r.setIdo((int) ido);
            r.setType(obj.get("type").toString());
            r.setSujet(obj.get("sujet").toString());
            r.setDescription(obj.get("description").toString());
            r.setDate(obj.get("date").toString().substring(0, 10) + " " + obj.get("date").toString().substring(11, 19));
            r.setEtat(obj.get("etat").toString());
            Reclamations.add(r);
        } catch (IOException ex) {

        }
        return Reclamations;
    }

    public ArrayList<Reclamation> FindReclamation(Reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/reclamation/find/" + r.getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseOneReclamation(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }

}
