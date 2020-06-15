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
import com.mycompany.myapp.entities.Feedback;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceFeedback {

    public ArrayList<Feedback> Feedbacks;

    public static ServiceFeedback instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private ServiceFeedback() {
        req = new ConnectionRequest();
    }

    public static ServiceFeedback getInstance() {
        if (instance == null) {
            instance = new ServiceFeedback();
        }
        return instance;
    }

    public boolean addFeedback(Feedback f) {

        Preferences UserId = null;
        String UserSessionId = UserId.get("UserId", null);

        String url = Statics.BASE_URL + "/reclamation/feedback/add/" + f.getDescription() + "/" + f.getRate() + "/" + UserSessionId;
        //UserId.delete("UserId");

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

    public boolean updateFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/mod/" + f.getId() + "/" + f.getDescription();
        req.setUrl(url);
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

    public boolean RemoveFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/rem/" + f.getId();
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

    public ArrayList<Feedback> parseFeedbacks(String jsonText) {
        try {
            Feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Feedback f = new Feedback();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
                float idU = Float.parseFloat(User.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());
                float rate = Float.parseFloat(obj.get("rate").toString());

                f.setId((int) id);
                f.setDescription(obj.get("description").toString());
                f.setRate((int) rate);

                f.setDate(obj.get("date").toString().substring(0, 10) + " " + obj.get("date").toString().substring(11, 19));

                f.setUser((int) idU);
                Feedbacks.add(f);
            }

        } catch (IOException ex) {

        }
        return Feedbacks;
    }

    public ArrayList<Feedback> parseOneFeedback(String jsonText) {
        try {
            Feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Feedback f = new Feedback();

            Map<String, Object> User = (Map<String, Object>) FeedbacksListJson.get("idU");
            float idU = Float.parseFloat(User.get("id").toString());

            float id = Float.parseFloat(FeedbacksListJson.get("id").toString());
            float rate = Float.parseFloat(FeedbacksListJson.get("rate").toString());

            f.setId((int) id);
            f.setDescription(FeedbacksListJson.get("description").toString());
            f.setRate((int) rate);

            f.setDate(FeedbacksListJson.get("date").toString().substring(0, 10) + " " + FeedbacksListJson.get("date").toString().substring(11, 19));

            f.setUser((int) idU);
            Feedbacks.add(f);
        } catch (IOException ex) {

        }
        return Feedbacks;
    }

    public ArrayList<Feedback> getAllFeedbacks() {
        String url = Statics.BASE_URL + "/reclamation/feedback/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Feedbacks = parseFeedbacks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }

    public ArrayList<Feedback> FindFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/find/" + f.getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Feedbacks = parseOneFeedback(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }
}
