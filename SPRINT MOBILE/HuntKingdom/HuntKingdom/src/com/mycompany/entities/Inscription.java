/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author aziz9
 */

public class Inscription implements java.io.Serializable 
{
    private int id;
    private int id_event;
    private int id_user;

    public Inscription(int id_event, int id_user) {
        this.id_event = id_event;
        this.id_user = id_user;
    }

    public Inscription(int id, int id_event, int id_user) {
        this.id = id;
        this.id_event = id_event;
        this.id_user = id_user;
    }

    public Inscription() 
    {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", id_event=" + id_event + ", id_user=" + id_user + '}';
    }


    
    
    
    
}
