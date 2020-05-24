/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.commentaire;
import com.mycompany.entities.publication;
import com.mycompany.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.gui.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RAFIK
 */
public class ServiceCommentaire {

    public ArrayList<commentaire> commentaire;

    public static ServiceCommentaire instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCommentaire() {
        req = new ConnectionRequest();
    }

    public static ServiceCommentaire getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }

    public boolean addCommentaire(String contenue, String idPub) {

        String url = Statics.BASE_URL + "/forum/commentaire/new/" + contenue + "/" + idPub + "/" + SignInForm.US.getId();
        System.out.println("url == " + url);
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

    public ArrayList<commentaire> parseCommentaire(String jsonText) {
        try {
            commentaire = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> CommentairesListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list
                    = (List<Map<String, Object>>) CommentairesListJson.get("root");

            for (Map<String, Object> obj : list) {

                commentaire t = new commentaire();

                Map<String, Object> User = (Map<String, Object>) obj.get("idUser");

                float idU = Float.parseFloat(User.get("id").toString());

                Map<String, Object> publication = (Map<String, Object>) obj.get("idPublication");

                float idpub = Float.parseFloat(publication.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());

                t.setId((int) id);

                t.setContenu(obj.get("contenu").toString());

                t.setPub_Contenu(publication.get("contenu").toString());
                t.setUsername(User.get("username").toString());

                t.setDateComnt(obj.get("dateComnt").toString().substring(0, 10));

                t.setId_user((int) idU);
                t.setId_publication((int) idpub);

                commentaire.add(t);
            }

        } catch (IOException ex) {

        }

        return commentaire;
    }

    public ArrayList<commentaire> FindCommentaire(String PubId) {
        String url = Statics.BASE_URL + "/forum/commentaire/Coment/" + PubId;

        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaire = parseCommentaire(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaire;
    }

}
