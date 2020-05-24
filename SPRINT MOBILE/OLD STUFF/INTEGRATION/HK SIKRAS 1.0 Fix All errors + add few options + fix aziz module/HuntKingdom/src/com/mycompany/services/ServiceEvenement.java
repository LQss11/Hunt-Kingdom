package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceEvenement {

    public ArrayList<Evenement> Evenements;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public ArrayList<Evenement> parseEvenements(String jsonText) {
        try {
            Evenements = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> EvenementsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) EvenementsListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Evenement l = new Evenement();

                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float nbrplace = Float.parseFloat(obj.get("nbrplace").toString());
                float duree = Float.parseFloat(obj.get("duree").toString());

                l.setId((int) id);
                l.setNom(obj.get("nom").toString());
                l.setType(obj.get("type").toString());
                l.setPrix((float) (double) prix);
                l.setNbrplace((int) nbrplace);
                l.setDate(obj.get("date").toString().substring(0, 10));
                l.setPlace(obj.get("place").toString());
                l.setDescription(obj.get("description").toString());
                l.setDuree((int) duree);
                l.setImage(obj.get("image").toString());
                Evenements.add(l);
            }

        } catch (IOException ex) {

        }

        return Evenements;
    }

    public ArrayList<Evenement> getAllEvenementstri() {
        String url = Statics.BASE_URL + "/evenement/jsoneventalltri";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenements;
    }

    public ArrayList<Evenement> getAllEvenements() {
        String url = Statics.BASE_URL + "/evenement/jsoneventall";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenements;
    }
}
