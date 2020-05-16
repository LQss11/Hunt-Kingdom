/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Inscription;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aziz9
 */
public class ServiceInscription 
{
    public ArrayList<Inscription> Inscriptions;
    public static ServiceInscription instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    
    public void AInscrire(int ev, int u)
    {
        String url = Statics.BASE_URL +"/evenement/jsoninscritDelete/"+ev+"/"+u;
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    
     
    }
    
    
    
    public void Inscrire(int ev, int u)
    {
        String url = Statics.BASE_URL +"/evenement/jsoninscritadd/"+ev+"/"+u;
        req.setUrl(url);
        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    
    
    
    
    private ServiceInscription() 
    {
        req = new ConnectionRequest();
    }

    
    
    
    public static ServiceInscription getInstance() 
    {
        if (instance == null) 
        {
            instance = new ServiceInscription();
        }
        return instance;
    }   
    
    
    
    
    public ArrayList<Inscription> parseInscription(String jsonText) {
        try {
            Inscriptions = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String, Object> InscriptionsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) InscriptionsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Inscription l = new Inscription();
                
                Map<String, Object> Event = (Map<String, Object>) obj.get("idevent");
                float idevent = Float.parseFloat(Event.get("id").toString());
                
                Map<String, Object> User = (Map<String, Object>) obj.get("iduser");
                float iduser = Float.parseFloat(User.get("id").toString());
                
                float id = Float.parseFloat(obj.get("id").toString());
                /*float prix = Float.parseFloat(obj.get("prix").toString());
                float nbrplace = Float.parseFloat(obj.get("nbrplace").toString());
                float duree = Float.parseFloat(obj.get("duree").toString());*/

                l.setId((int) id);
                l.setId_event((int)idevent);
                l.setId_user((int)iduser);
                Inscriptions.add(l);
                
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        
        return Inscriptions;
    }
    
    
    
    
    
    public ArrayList<Inscription> getAllInscriptions() {
        String url = Statics.BASE_URL + "/evenement/jsoninscritall";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Inscriptions = parseInscription(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Inscriptions;
    }
}
