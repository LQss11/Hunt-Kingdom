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
import com.codename1.io.rest.RequestBuilder;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.BCrypt;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceUser {

    public ArrayList<User> Users;

    public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ArrayList<User> parseOneUser(String jsonText) {
        try {
            System.out.println("jsontext == " + jsonText);

            Users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> UsersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            User u = new User();

            /*
            Map<String, Object> User = (Map<String, Object>) UsersListJson.get("idU");
            float idU = Float.parseFloat(User.get("id").toString());

            float ida = Float.parseFloat(UsersListJson.get("id").toString());
            u.setId((int) ida);

            u.setUsername(UsersListJson.get("username").toString());
             */
            float id = Float.parseFloat(UsersListJson.get("id").toString());
            u.setId((int) id);
            u.setFirstname(UsersListJson.get("firstname").toString());
            u.setLastname(UsersListJson.get("lastname").toString());
            u.setPhone(UsersListJson.get("phone").toString());
            u.setDate(UsersListJson.get("date").toString().substring(0, 10));
            u.setImage(UsersListJson.get("image").toString());
            u.setUsername(UsersListJson.get("username").toString());
            u.setEmail(UsersListJson.get("email").toString());
            u.setPassword(UsersListJson.get("password").toString());
            u.setLast_login(UsersListJson.get("lastLogin").toString().substring(0, 10));

            List<Object> role = (List<Object>) UsersListJson.get("roles");
            u.setRole(role.get(0).toString());


            Users.add(u);
        } catch (IOException ex) {

        }
        return Users;
    }

    public ArrayList<User> FindUser(User u) {
        String url = Statics.BASE_URL + "/test/user/all/" + u.getUsername() + "/fze";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseOneUser(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
}
