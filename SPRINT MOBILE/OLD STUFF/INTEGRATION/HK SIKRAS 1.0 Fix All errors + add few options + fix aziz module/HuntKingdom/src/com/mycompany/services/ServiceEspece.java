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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Espece;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceEspece {

    public ArrayList<Espece> Especes;

    public static ServiceEspece instance = null;
    public boolean resultOK;
    private MultipartRequest req;
    private ConnectionRequest req2;

    private ServiceEspece() {
        req = new MultipartRequest();
        req2 = new ConnectionRequest();

    }

    public static ServiceEspece getInstance() {
        if (instance == null) {
            instance = new ServiceEspece();
        }
        return instance;
    }

    public boolean addEspece(Espece t, byte[] file) {
        //String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus(); //création de l'URL
        String url = Statics.BASE_URL + "/espece/espece/add/" + t.getNomEspece() + "/" + t.getDescriptionEspece() + "/" + t.getPoids() + "/" + t.getType() + "/" + t.getZone() + "/" + t.getVille() + "/" + t.getSaison();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);
        req.setHttpMethod("POST");
        req.addData("espImg", file, "image/png");

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

    public ArrayList<Espece> parseEspeces(String jsonText) {
        try {
            Especes = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> EspecesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) EspecesListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obji : list) {
                //Création des tâches et récupération de leurs données
                Espece e = new Espece();
                float ide = Float.parseFloat(obji.get("idEspece").toString());
                e.setIdEspece((int) ide);
                e.setNomEspece(obji.get("nomEspece").toString());
                e.setDescriptionEspece(obji.get("descriptionEspece").toString());
                e.setImage(obji.get("image").toString());
                e.setPoids(obji.get("poids").toString());
                e.setType(obji.get("type").toString());
                e.setZone(obji.get("zone").toString());
                e.setVille(obji.get("ville").toString());

                Especes.add(e);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return Especes;
    }

    public ArrayList<Espece> getAllEspeces() {
        String url = Statics.BASE_URL + "/espece/espece/all";
        req2.setUrl(url);
        req2.setPost(false);
        req2.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Especes = parseEspeces(new String(req2.getResponseData()));
                req2.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req2);
        return Especes;
    }

    public ArrayList<Espece> getEspece(String e) {
        String url = Statics.BASE_URL + "/espece/espece/find?search" + e;
        req2.setUrl(url);
        req2.setPost(false);
        req2.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Especes = parseEspeces(new String(req2.getResponseData()));
                req2.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req2);
        return Especes;
    }

}
