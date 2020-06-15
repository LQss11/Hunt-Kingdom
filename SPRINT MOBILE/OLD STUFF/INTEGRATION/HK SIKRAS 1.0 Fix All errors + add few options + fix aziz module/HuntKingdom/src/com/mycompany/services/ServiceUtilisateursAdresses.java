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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.entities.UtilisateursAdresses;
import com.mycompany.utils.Statics;

/**
 *
 * @author skand
 */
public class ServiceUtilisateursAdresses {

    List<UtilisateursAdresses> prods = new ArrayList<UtilisateursAdresses>();

    public List<UtilisateursAdresses> getAdresses(User user) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Ecommerce/panier/getallAdresses/" + user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUtilisateursAdresses ser = new ServiceUtilisateursAdresses();
                prods = ser.parseListad(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return prods;
    }

    public void addAdresses(String nom, String prenom, String telephone, String adresse, String cp, String pays, String ville, String complement, User user) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Ecommerce/panier/add/" + nom + "/" + prenom + "/" + telephone + "/" + adresse + "/" + cp + "/" + pays + "/" + ville + "/" + complement + "/" + user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String rep = new String(con.getResponseData());

                if (con.getResponseCode() == 200) {
                    Dialog.show("success", "Adresse ajoute avec success", "ok", null);

                } else {
                    Dialog.show("error", "Opps! try again", "ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void RAdresses(int r) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/Ecommerce/panier/rem/" + r);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String rep = new String(con.getResponseData());

                if (con.getResponseCode() == 200) {
                    Dialog.show("success", "Adresse Supprime avec success", "ok", null);

                } else {
                    Dialog.show("error", "Opps! try again", "ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public List<UtilisateursAdresses> parseListad(String json) {
        ArrayList<UtilisateursAdresses> list = new ArrayList<>();
        JSONParser j = new JSONParser();
        try {
            Map<String, Object> products = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> listp = (List<Map<String, Object>>) products.get("root");

            for (Map<String, Object> obj : listp) {
                //Création des tâches et récupération de leurs données
                UtilisateursAdresses ad = new UtilisateursAdresses();

                float id = Float.parseFloat(obj.get("id").toString());
                Map<String, Object> User = (Map<String, Object>) obj.get("utilisateur");
                float idU = Float.parseFloat(User.get("id").toString());

                //   float zip = Float.parseFloat(obj.get("zip").toString());
//                float tel = Float.parseFloat(obj.get("tel").toString());
                ad.setId((int) id);
                ad.setUtilisateur_id((int) idU);
                ad.setNom(obj.get("nom").toString());
                ad.setPrenom(obj.get("prenom").toString());
                ad.setVille(obj.get("telephone").toString());
                ad.setCp(obj.get("adresse").toString());
                ad.setTelephone(obj.get("cp").toString());
                ad.setAdresse(obj.get("pays").toString());
                ad.setPays(obj.get("ville").toString());
                ad.setComplement(obj.get("complement").toString());

                list.add(ad);

            }

        } catch (IOException ex) {
        }

        return list;
    }

}
