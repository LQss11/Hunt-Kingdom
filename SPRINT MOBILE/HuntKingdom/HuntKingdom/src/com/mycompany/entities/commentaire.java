/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author RAFIK
 */
public class commentaire {
    private int id,id_publication,id_user;
    private String contenu,dateComnt;
    private String pub_Contenu,username;

    public String getPub_Contenu() {
        return pub_Contenu;
    }

    public void setPub_Contenu(String pub_Contenu) {
        this.pub_Contenu = pub_Contenu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public commentaire(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateComnt() {
        return dateComnt;
    }

    public void setDateComnt(String dateComnt) {
        this.dateComnt = dateComnt;
    }

    public commentaire(int id, int id_publication, int id_user, String contenu, String dateComnt) {
        this.id = id;
        this.id_publication = id_publication;
        this.id_user = id_user;
        this.contenu = contenu;
        this.dateComnt = dateComnt;
    }

    public commentaire( String contenu, String dateComnt,int id_user ,int id_publication) {
        this.id_publication = id_publication;
        this.id_user = id_user;
        this.contenu = contenu;
        this.dateComnt = dateComnt;
    }

    public commentaire() {
    }
    
    
    
  

 

    public void getId(int id) {
this.id = id;
    }
    
       
}
