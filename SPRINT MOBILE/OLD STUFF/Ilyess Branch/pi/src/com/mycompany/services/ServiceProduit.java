/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Promotion;
import com.mycompany.entities.Whishlist;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.io.JSONParser;
import com.mycompany.entities.Utilisateurs;
/**
 *
 * @author khalil
 */
public class ServiceProduit {
    
    
     public ArrayList<Produit> Produits= new ArrayList<>();
     public ArrayList<Whishlist> Wishlists= new ArrayList<>();
   public ArrayList<Promotion> Promotions= new ArrayList<>();
     
     
    public ServiceProduit() {
        req = new ConnectionRequest();
    }
     
     public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
     
     
     public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     
     
       public ArrayList<Produit> getListProduit() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/HuntKingdom/web/app_dev.php/produit/produit/displayMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit sl = new ServiceProduit();
                try {
                    Produits = sl.ProduitParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {System.out.println("produit parsson 39");
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Produits;
    
    }
     
       public ArrayList<Produit> ProduitParseJson(String json) throws ParseException {
        ArrayList<Produit> listProduit = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {

                Produit p = new Produit();
               // float id = Float.parseFloat(obj.get("idExperience").toString());
              
                float id = Float.parseFloat(obj.get("id").toString());
                float garantie= Float.parseFloat(obj.get("garantie").toString());
                float etatPromo = Float.parseFloat(obj.get("etatPromo").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                 p.setId((int) id);
                p.setNom(obj.get("nom").toString());
                p.setDescription(obj.get("description").toString());
                p.setPrix(prix);
                p.setEtatPromo((int) etatPromo);
                p.setQuantite((int)quantite);
                  p.setGarantie((int)garantie);
                 p.setImage(obj.get("image").toString());
                 
                listProduit.add(p);

            }

        } catch (IOException ex) { System.out.println("com.mycompany.services.ServiceProduit.ProduitParseJson()");
        }
     return listProduit;
        
}
       
        public ArrayList<Produit> getSearchListProduit(String q) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/HuntKingdom/web/app_dev.php/mobile/findMobile?search="+q);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit sl = new ServiceProduit();
                try {
                    Produits = sl.ProduitParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Produits;
    
    }
       
        
        public ArrayList<Promotion> getListPromotion() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/HuntKingdom/web/app_dev.php/produit/promotion/displayMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit sl = new ServiceProduit();
                try {
                    Promotions = sl.PromotionParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {System.out.println("promotion parsson 39"); }
                        }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Promotions;
    
    }
     
       public ArrayList<Promotion> PromotionParseJson(String json) throws ParseException {
        ArrayList<Promotion> listP = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {

                Promotion promo = new Promotion();
              
                float id = Float.parseFloat(obj.get("id").toString());
                float prix= Float.parseFloat(obj.get("prix").toString());
                float pourcentage = Float.parseFloat(obj.get("pourcentage").toString());
                float active = Float.parseFloat(obj.get("active").toString());
                
                    Map<String, Object> produit= (Map<String, Object>) obj.get("idProduit");
                    float idProduit = Float.parseFloat(produit.get("id").toString());
                
                        promo.setId((int)id);
                        promo.setActive((int)active);
                        promo.setPrix(prix);
                        promo.setPourcentage((int)pourcentage);
                                Produit p=new Produit();
                                p.setId((int)idProduit);
                        promo.setProduit(p);
                
                
                listP.add(promo);

            }

        } catch (IOException ex) { System.out.println("com.mycompany.services.ServiceProduit.PromotionParseJson()");
        }
     return listP;
        
}
       
        
        
        
         public ArrayList<Whishlist> getListWishlist() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/HuntKingdom/web/app_dev.php/produit/whish/displayMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit sl = new ServiceProduit();
                try {
                    Wishlists = sl.WishlistParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {System.out.println("wish parsson 39"); }
                        }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Wishlists;
    
    }
     
       public ArrayList<Whishlist> WishlistParseJson(String json) throws ParseException {
        ArrayList<Whishlist> listW = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");
            for (Map<String, Object> obj : list) {

    Whishlist w = new Whishlist();            
                Map<String, Object> User = (Map<String, Object>) obj.get("idClient");
                float idc = Float.parseFloat(User.get("id").toString());
                Utilisateurs u=new Utilisateurs();
                u.setID((int)idc);              
                w.setUser(u);
                
                Map<String, Object> produit= (Map<String, Object>) obj.get("idProduit");
                float idpr = Float.parseFloat(produit.get("id").toString());
                Produit p=new Produit();
                p.setId((int)idpr);
                w.setProduit(p);
                
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                w.setId((int) id);
               // System.out.println(w);
                listW.add(w);

            }

        } catch (IOException ex) { System.out.println("com.mycompany.services.ServiceProduit.ProduitParseJson()");
        }
     return listW;
        
}
       
       
       
       public void ajoutWishlist(Whishlist w) {
        ConnectionRequest con = new ConnectionRequest(); 
        String URL="http://localhost/HuntKingdom/web/app_dev.php/produit/whish/newMobile?idp="+w.getProduit().getId()+"&idc="+2;
        System.out.println("/////////////ADDING/////////////////////////");
        con.setUrl(URL);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        //    System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }  
       
        public void deleteWishlist(int idw) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/HuntKingdom/web/app_dev.php/produit/whish/deleteMobile?" + "idw=" + idw;// création de l'URL
         System.out.println("/////////////removing/////////////////////////");
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
       
}