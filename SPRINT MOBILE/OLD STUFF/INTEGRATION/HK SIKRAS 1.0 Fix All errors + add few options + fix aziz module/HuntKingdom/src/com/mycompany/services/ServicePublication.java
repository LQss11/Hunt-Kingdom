/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.publication;
import com.mycompany.utils.Statics;
import com.codename1.l10n.ParseException;
import com.mycompany.gui.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RAFIK
 */
public class ServicePublication {

    public ArrayList<publication> publication;

    public static ServicePublication instance = null;
    public boolean resultOK;
    private MultipartRequest req;

    public ServicePublication() {
        req = new MultipartRequest();
    }

    public static ServicePublication getInstance() {
        if (instance == null) {
            instance = new ServicePublication();
        }
        return instance;
    }

    public boolean addPublication(publication t, byte[] file) {

        String url = Statics.BASE_URL + "/forum/publication/create/"+t.getContenu()+"/"+t.getType()+"/"+SignInForm.US.getId();
                
                 
         req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");

        req.addData("pubImg", file, "image/png");

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

    public ArrayList<publication> parsePublication(String jsonText) {
        try {
            publication = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> PublicationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list
                    = (List<Map<String, Object>>) PublicationsListJson.get("root");
            

            for (Map<String, Object> obj : list) {

                publication t = new publication();
                Map<String, Object> User = (Map<String, Object>) obj.get("idUser");
               
                float idU = Float.parseFloat(User.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());
                
                t.getId((int) id);
                        
                t.setType(obj.get("type").toString());
                t.setContenu(obj.get("contenu").toString());
                t.setDatePublication(obj.get("datePublication").toString().substring(0, 10));

                t.setId_User((int) idU);

                publication.add(t);
            }

        } catch (IOException ex) {

        }

        return publication;
    }

    public ArrayList<publication> getAllpublications() {
        String url = Statics.BASE_URL + "/forum/publication/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publication = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publication;
    }
    
      public ArrayList<publication> parseOnePublication(String jsonText) {
        try {
            publication = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PublicationsListJson = 
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            publication f = new publication();

            float id = Float.parseFloat(PublicationsListJson.get("id").toString());
           

            f.setId((int) id);
            f.setContenu(PublicationsListJson.get("contenu").toString());
            f.setType(PublicationsListJson.get("type").toString());
            f.setDatePublication(PublicationsListJson.get("datePublication").toString().substring(0, 10));

            publication.add(f);
        } catch (IOException ex) {

        }
        return publication;
    }
      
    

    public ArrayList<publication> FindPublication(publication p) {
        String url = Statics.BASE_URL + "/forum/publication/find/" + p.getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publication = parseOnePublication(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publication;
    }
    
      public ArrayList<publication> getSearchListPublication(String q) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/forum/publication/findMobile?search="+q);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePublication sl = new ServicePublication();
                publication = sl.parsePublication(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return publication;
    
    }
       
       
    
    
    public boolean RemovePublication(publication p) {
        String url = Statics.BASE_URL + "/forum/publication/sup/" + p.getId();
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

}
