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
public class publication {
    
    private int id,id_User;
    private String type,contenu;
    private String datePublication;

    @Override
    public String toString() {
        return "publication{" + "id=" + id + ", id_User=" + id_User + ", type=" + type + ", contenu=" + contenu + ", datePublication=" + datePublication + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public publication(int id, int id_User, String type, String contenu, String datePublication) {
        this.id = id;
        this.id_User = id_User;
        this.type = type;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public publication() {
    }

    public publication(  String contenu,  String datePublication,String type ,int id_User) {
        
        this.id_User = id_User;
        this.type = type;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public publication(int id) {
        this.id = id;
    }

    public void getId(int id) {
        this.id = id;
    }
    
    

    }

    
    
      
    
    

